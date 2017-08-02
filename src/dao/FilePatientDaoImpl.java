package dao;

import hospital.Patient;
import hospital.PatientStatus;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FilePatientDaoImpl is a simple implementation of PatientDao that reads and
 * writes patient data stored in plain text files.
 *
 * Patient data is stored in files named with the patient ID and formatted with
 * one attribute per line:
 *
 * patient ID
 * admission date (formatted as a string for use with Instance)
 * first name
 * last name
 *
 * Active patients are stored in the directory PATIENT_DATA_DIRECTORY. Archived
 * patients are stored in the directory ARCHIVED_PATIENT_DATA_DIRECTORY, which
 * is currently a subdirectory of PATIENT_DATA_DIRECTORY.
 *
 * This class mainly serves to support our second use case (showing patients and
 * nurses their version of the patient list). We need to show patients data, but
 * since they cannot create patients we will need to use saved data. This may
 * not be the implementation that we choose to use for the actual persistence of
 * data assignment, but that's OK since we already have the PatientDAO interface
 * defined and can use this class as a reference for building the one we will
 * actually use. Switching from this implementation to another requires only
 * changing the default DAO in DaoFactory or calling DaoFactory's getPatientDao
 * method with a parameter specifying the new PatientDao implementation.
 * 
 * Please refer to PatientDao for general information on implementations should
 * work.
 *
 * @see PatientDao
 * @see DaoFactory
 * @see PatientChangeListener
 * 
 * @author redjen
 */
public class FilePatientDaoImpl implements PatientDao {

   private static final String PATIENT_DATA_DIRECTORY = "data";
   private static final String ARCHIVED_PATIENT_DATA_DIRECTORY = "archived";

   public FilePatientDaoImpl() throws DaoException {
      initializeRepository();
   }

   /**
    * Returns a list of active patients
    *
    * @return
    */
   @Override
   public ArrayList<Patient> getAllActivePatients() {
      return getAllPatientsInDirectory(Paths.get(PATIENT_DATA_DIRECTORY));
   }

   /**
    * Returns a list of archived patients
    *
    * @return
    */
   @Override
   public ArrayList<Patient> getAllArchivedPatients() {
      return getAllPatientsInDirectory(Paths.get(ARCHIVED_PATIENT_DATA_DIRECTORY));
   }

   /**
    * Returns the patient specified by ID if data has been saved to disk. Only
    * active patients are searched
    *
    * @param patientId the ID of the patient
    * @return the patient or null if the patient wasn't found
    */
   @Override
   public Patient getPatientById(String patientId) {
      return getPatientById(patientId, false);
   }

   /**
    * Returns the patient specified by ID if data has been saved to disk. If
    * includeArchive is true the archived patients directory will also be
    * searched.
    *
    * @param patientId the ID of the patient
    * @param includeArchived true to search for archived patients
    * @return the patient or null if the patient wasn't found
    */
   @Override
   public Patient getPatientById(String patientId, boolean includeArchived) {
      Patient patient = null;
      Path patientDataFile = Paths.get(PATIENT_DATA_DIRECTORY, patientId);

      try {
         patient = deserializePatient(patientDataFile);
         if (patient == null && includeArchived) {
            patientDataFile = Paths.get(ARCHIVED_PATIENT_DATA_DIRECTORY, patientId);
            patient = deserializePatient(patientDataFile);
         }
      } catch (IOException ex) {
         Logger.getLogger(FilePatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }

      return patient;
   }

   /**
    * Archives a patient, removing them from the active patient list.
    *
    * @param patient the patient to archive
    */
   @Override
   public void archivePatient(Patient patient) {
      Path patientSavedDataFile = Paths.get(PATIENT_DATA_DIRECTORY, patient.getPatientId());
      Path patientArchiveDataFile = Paths.get(ARCHIVED_PATIENT_DATA_DIRECTORY, patient.getPatientId());
      try {
         Files.deleteIfExists(patientSavedDataFile);
         serializePatient(patient, patientArchiveDataFile);
      } catch (IOException ex) {
         Logger.getLogger(FilePatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   /**
    * Saves the specified patient's data
    *
    * @param patient the patient to save
    */
   @Override
   public void savePatient(Patient patient) {
      Path patientDataFile = Paths.get(PATIENT_DATA_DIRECTORY, patient.getPatientId());
      serializePatient(patient, patientDataFile);
   }

   /**
    * Creates the patient data repository if it does not exist
    *
    * @throws DaoException if the data repository cannot be created
    */
   private void initializeRepository() throws DaoException {
      Path path = Paths.get(PATIENT_DATA_DIRECTORY, ARCHIVED_PATIENT_DATA_DIRECTORY);

      try {
         if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectories(path);
            System.out.println("missing directory");
         }
      } catch (IOException iOException) {
         System.err.println(iOException.getMessage());
         throw new DaoException("Could not create patient data repository", iOException);
      }

   }

   /**
    * Saves the specified patient's data to the specified file
    *
    * @param patient the patient to save
    * @param filePath the save file
    */
   private void serializePatient(Patient patient, Path filePath) {
      try (BufferedWriter out = Files.newBufferedWriter(filePath)) {
         out.write(patient.getPatientId());
         out.newLine();
         Instant zdt = patient.getAdmissionDate().toInstant();

         out.write(zdt.toString());
         out.newLine();
         out.write(patient.getFirstName());
         out.newLine();
         out.write(patient.getLastName());
         out.newLine();
         out.write(patient.getStatus().name());
         System.out.printf("Saved patient %s%n", patient.getPatientId());

      } catch (IOException ex) {
         Logger.getLogger(FilePatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * Retrieves a single patient from the specified directory
    *
    * @param filePath the patient's data file
    * @return the patient, or null if no saved data was found
    * @throws IOException
    */
   private Patient deserializePatient(Path filePath) throws IOException {
      Patient patient = null;

      if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
         try (BufferedReader in = Files.newBufferedReader(filePath)) {
            String patientId = in.readLine();
            Date admissionDate = Date.from(Instant.parse(in.readLine()));
            String firstName = in.readLine();
            String lastName = in.readLine();
            String status = in.readLine();
            patient = new Patient(firstName, lastName, admissionDate, patientId, PatientStatus.valueOf(status));
            
         }
      }

      return patient;
   }

   /**
    * Retrieves all patients in a directory
    *
    * @param directoryPath the directory to search for patients
    * @return a list of patients, which may be empty if no patients were found
    */
   private ArrayList<Patient> getAllPatientsInDirectory(Path directoryPath) {
      ArrayList<Patient> list = new ArrayList<>();

      try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {

         for (Path path : stream) {
            if (!Files.isDirectory(path)) {
               list.add(deserializePatient(path));
            }
         }

      } catch (IOException ex) {
         Logger.getLogger(FilePatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
      }

      return list;
   }
}
