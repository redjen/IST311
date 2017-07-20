package dao;

import hospital.Patient;
import java.util.ArrayList;

/**
 * The interface PatientDao defines the behaviors required for a Patient data
 * access object.
 *
 * PatientDao implementations can be used as standalone objects, but are
 * currently used with PatientChangeListener so that they do not need to be
 * created and used by model objects or ViewManager.
 * 
 * @see PatientChangeListener
 *
 * @author redjen
 *
 */
public interface PatientDao {

   /**
    * Returns all saved, active (non-archived) patients
    *
    * @return a list of Patients
    */
   public ArrayList<Patient> getAllActivePatients();

   /**
    * Returns all saved, archived (inactive) patients
    *
    * @return a list of Patients
    */
   public ArrayList<Patient> getAllArchivedPatients();

   /**
    * Returns the patient corresponding to the specified ID
    *
    * @param patientId the patient's ID
    * @return the patient or null if the patient was not found
    */
   public Patient getPatientById(String patientId);

   /**
    * Returns the patient corresponding to specified ID, including archived
    * patients if indicated.
    *
    * @param patientId the patient's ID
    * @param includeArchived true to search archive patients, otherwise false
    * @return the patient or null if not found
    */
   public Patient getPatientById(String patientId, boolean includeArchived);

   /**
    * Stores the patient's data in the archived folder and removes it from the
    * active patients folder.
    *
    * @param patient the patient to archive
    */
   public void archivePatient(Patient patient);

   /**
    * Save the data for a single active patient. This method overwrites existing
    * patient data and can be used for both adding and updating patient info.
    *
    * @param patient the patient to save
    */
   public void savePatient(Patient patient);

}
