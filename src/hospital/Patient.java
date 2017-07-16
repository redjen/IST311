package hospital;

import java.util.Date;

/**
 * Patient represents a patient in the hospital
 *
 * @author maximdumont
 * @author redjen
 */
public class Patient {

   public Patient(String firstName, String lastName, Date admittanceDate, String patientId) {
      this.admissionDate = admittanceDate;
      this.firstName = firstName;
      this.lastName = lastName;
      this.patientId = patientId;
   }

   private Date admissionDate;
   private String patientId;
   private String firstName;
   private String lastName;

   /**
    * Returns a human-friendly substring of the patient ID for use in public
    * displays
    *
    * @return the human-friendly ID
    */
   public String getPublicId() {
      return patientId.substring(patientId.length() - 4);
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @param firstName the firstName to set
    */
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param lastName the lastName to set
    */
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   /**
    * @return the admissionDate
    */
   public Date getAdmissionDate() {
      return admissionDate;
   }

   /**
    * @param admissionDate the admissionDate to set
    */
   public void setAdmissionDate(Date admissionDate) {
      this.admissionDate = admissionDate;
   }

   /**
    * @return the patientId
    */
   public String getPatientId() {
      return patientId;
   }

   /**
    * @param patientId the patientId to set
    */
   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }
}
