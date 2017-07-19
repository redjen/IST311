package dao;

import hospital.Patient;
import java.util.ArrayList;

/**
 * The interface PatientDao defines the behaviors required for a Patient data
 * access object
 *
 * @author redjen
 *
 */
public interface PatientDao {

   /**
    * Returns all saved, active (non-archived) patients
    * @return a list of Patients
    */
   public ArrayList<Patient> getAllActivePatients();

   /**
    * Returns all saved, archived (inactive) patients
    * @return a list of Patients
    */
   public ArrayList<Patient> getAllArchivedPatients();

   /**
    * Returns the patient corresponding to the specified ID
    * @param patientId the patient's ID
    * @return the patient or null if the patient was not found
    */
   public Patient getPatientById(String patientId);

   public Patient getPatientById(String patientId, boolean includeArchived);

   public void archivePatient(Patient patient);

   public void savePatient(Patient patient);

}
