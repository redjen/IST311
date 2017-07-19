package dao;

import hospital.Patient;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;

/**
 * The SerializedPatientDaoImpl class represents
 *
 */
public class SerializedPatientDaoImpl implements PatientDao {

   private static final String PATIENT_DATA_DIRECTORY = "data/";
   private static final String ARCHIVED_PATIENT_DATA_DIRECTORY = PATIENT_DATA_DIRECTORY + "archived/";

   @Override
   public ArrayList<Patient> getAllActivePatients() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public ArrayList<Patient> getAllArchivedPatients() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Patient getPatientById(String patientId) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Patient getPatientById(String patientId, boolean includeArchived) {
      Patient patient = null;
      
      
      
      return patient;
   }

   @Override
   public void archivePatient(Patient patient) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void savePatient(Patient patient) {
      // no op
   }

   @Override
   public void reset() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   
   /**
    * Creates the patient data repository if it does not exist
    * @throws DaoException if the data repository cannot be created
    */
   private void initializeRepository() throws DaoException {
      Path path = Paths.get(ARCHIVED_PATIENT_DATA_DIRECTORY);
      
      try {
         if (!Files.exists(path, (LinkOption) null)) {
            Files.createDirectories(path, (FileAttribute<?>) null);
         }
      } catch (IOException iOException) {
         System.err.println(iOException.getMessage());
         throw new DaoException("Could not create patient data repository", iOException);
      }
    
      
   }

}
