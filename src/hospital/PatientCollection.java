package hospital;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

/**
 * PatientCollection manages the list of patients in the system
 *
 * @author maximdumont
 * @author redjen
 */
public class PatientCollection {

   private final HashMap<String, Patient> patients = new HashMap<>();
   private final ObservableList<Patient> patientList;

   public PatientCollection() {

      // Reading the documentation for this method is recommended.
      patientList = createObservableList();

   }

   public void add(Patient p) {
      patients.put(p.getPatientId(), p);
      patientList.add(p);
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
    * Adds the patients
    *
    * @param newPatients the patients to add
    */
   public void addAll(List<Patient> newPatients) {
      for (Patient newPatient : newPatients) {
         add(newPatient);
      }
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

   /**
    * Creates an observable list to hold Patient objects.
    *
    * An ObservableList<Object> is required as the data source for JavaFX
    * tables. We can also use this list and the event it fires to save and
    * update patient data (persisting) "for free", cutting down the work needed
    * to implement that behavior.
    *
    * There are two main purposes for this list:
    *
    * 1. An implicit listener updates the patient table view automatically
    *
    * 2. Additional listeners can be (but are not required to be) attached to
    * listen for changes to the list or to the list elements. This allows us
    * to automatically save data to disk without having to update this or the
    * Patient model object and without needing to update the ViewManager
    * controller. Everything is in the dao package.
    *
    * If an object is added or removed the list will fire an event
    * that the saving data data controller can catch and use to add or archive
    * the data for the object.
    *
    * The parameter passed to the anonymous list constructor sets listeners on
    * properties for each Patient object. Whenever one of the observed
    * properties changes an event is fired. The controller responsible for
    * saving data (if any) is notified of the update and updates the object's
    * saved data. The list will not fire this event, which creates the need
    * for the extractor. This step is required because the list will not
    * otherwise fire events when its objects change.
    *
    * We should only do this with the Patient properties that can change to
    * avoid firing events and writing data unnecessarily. When we add a status
    * field we'll need to figure out how to manage that.
    */
   private ObservableList<Patient> createObservableList() {
      return FXCollections.observableArrayList(new Callback<Patient, Observable[]>() {
         @Override
         public Observable[] call(Patient param) {
            return new Observable[]{
               param.getFirstNameProperty(),
               param.getLastNameProperty(),
               param.getStatusProperty()
            };
         }
      });
   }

}
