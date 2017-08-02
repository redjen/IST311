package hospital;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Patient represents a patient in the hospital
 *
 * @author maximdumont
 * @author redjen
 */
public class Patient implements Serializable {

   private static final long serialVersionUID = 1L;

   private final ObjectProperty<Date> admissionDate;
   private final StringProperty patientId;
   private final StringProperty firstName;
   private final StringProperty lastName;
   private final StringProperty patientPublicId;
   private ObjectProperty<PatientStatus> status;

   public Patient(String firstName, String lastName, Date admittanceDate, String patientId, PatientStatus patientStatus) {

      this.admissionDate = new SimpleObjectProperty<>(admittanceDate);
      this.firstName = new SimpleStringProperty(firstName);
      this.lastName = new SimpleStringProperty(lastName);
      this.patientId = new SimpleStringProperty(patientId);
      this.patientPublicId = new SimpleStringProperty(patientId.substring(patientId.length() - 4));
      this.status = new SimpleObjectProperty<>(patientStatus);
   }
   
   public Patient(String firstName, String lastName, Date admittanceDate, String patientId) {
      this(firstName, lastName, admittanceDate, patientId, PatientStatus.WAITING);
   }

   public ObjectProperty<PatientStatus> getStatusProperty() {
      return status;
   }

   public void setStatus(PatientStatus patientStatus) {
      this.status = new SimpleObjectProperty<>(patientStatus);
   }

   public PatientStatus getStatus() {
      return status.get();
   }

   /**
    * Returns the human-friendly status description as defined in PatientStatus
    * 
    * @see PatientStatus
    *
    * @return the status as a string
    */
   public String getStatusDisplayText() {
      return status.get().getDisplayText();
   }

   /**
    * Returns a human-friendly substring of the patient ID for use in public
    * displays
    *
    * @return the human-friendly ID
    */
   public String getPublicId() {
      return patientPublicId.get();
   }

   public StringProperty getPublicIdProperty() {
      return patientPublicId;
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName.get();
   }

   /**
    * @param firstName the firstName to set
    */
   public void setFirstName(String firstName) {
      this.firstName.set(firstName);
   }

   public StringProperty getFirstNameProperty() {
      return firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName.get();
   }

   /**
    * @param lastName the lastName to set
    */
   public void setLastName(String lastName) {
      this.lastName.set(lastName);
   }

   public StringProperty getLastNameProperty() {
      return lastName;
   }

   /**
    * @return the admissionDate
    */
   public Date getAdmissionDate() {
      return admissionDate.get();
   }

   /**
    * @param admissionDate the admissionDate to set
    */
   public void setAdmissionDate(Date admissionDate) {
      this.admissionDate.set(admissionDate);
   }

   public ObjectProperty<Date> getAdmissionDateProperty() {
      return admissionDate;
   }

   /**
    * @return the patientId
    */
   public String getPatientId() {
      return patientId.get();
   }

   /**
    * @param patientId the patientId to set
    */
   public void setPatientId(String patientId) {
      this.patientId.set(patientId);
   }
}
