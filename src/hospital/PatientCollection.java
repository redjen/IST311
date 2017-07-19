package hospital;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * PatientCollection manages the list of patients in the system
 *
 * @author maximdumont
 * @author redjen
 */
public class PatientCollection {

   private final HashMap<String, Patient> patients = new HashMap<>();
   private final ObservableList<Patient> patientList = FXCollections.observableArrayList();

   public PatientCollection() {
   }

   public void add(Patient p) {
      patients.put(p.getPatientId(), p);
   }

   /**
    * Adds a patient to the patient collection and returns the patient's ID
    *
    * @param firstName the patient's first name
    * @param lastName the patient's last name
    * @return the patient's assigned ID
    */
   public String add(String firstName, String lastName) {
      Patient patient;
      String id = UUID.randomUUID().toString();

      while (patients.containsKey(id)) {
         id = UUID.randomUUID().toString();
      }

      patient = new Patient(firstName, lastName, Date.from(Instant.now()), id);
      patients.put(id, patient);
      patientList.add(patient);

      System.out.printf("[Created patient] id: %s, firstName: %s, lastName: %s%n",
              patient.getPatientId(), patient.getFirstName(), patient.getLastName());
      return id;
   }

   /**
    * Removes the patient with the specified patient ID, if found
    *
    * @param patientId the patient ID
    */
   public void remove(String patientId) {
      if (patients.containsKey(patientId)) {
         Patient patient = patients.get(patientId);
         patients.remove(patientId);
         patientList.remove(patient);
      }
      // TODO archive patient
   }

   /**
    * Returns the patient with the specified ID, if present
    *
    * @param patientId the patient ID
    * @return the patient or null if not found
    */
   public Patient find(String patientId) {
      if (patients.containsKey(patientId)) {
         return patients.get(patientId);
      }
      return null;
   }

   /**
    * Returns the observable list for the patient
    *
    * This method is intended for use with a table view
    *
    * @return the observable list
    */
   public ObservableList<Patient> getPatientList() {
      return patientList;
   }

}
