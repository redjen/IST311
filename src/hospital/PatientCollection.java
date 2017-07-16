package hospital;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * PatientCollection manages the list of patients in the system
 *
 * @author maximdumont
 * @author redjen
 */
public class PatientCollection {

   private final HashMap<String, Patient> patients = new HashMap<>();

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

      System.out.printf("[Created patient] id: %s, firstName: %s, lastName: %s%n",
              patient.getPatientId(), patient.getFirstName(), patient.getLastName());
      return id;
   }

   public void remove(String patientId) {
      if (patients.containsKey(patientId)) {
         patients.remove(patientId);
      }
   }

   public Patient find(String patientId) {
      if (patients.containsKey(patientId)) {
         return patients.get(patientId);
      }
      return null;
   }
}
