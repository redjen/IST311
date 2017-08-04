package app;

import hospital.Patient;
import hospital.PatientStatus;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class for the patient status view
 *
 */
public class PatientStatusViewController implements Initializable {

   // patient number text field
   @FXML
   private TextField patientNumberTextField;

   // admitted date text field
   @FXML
   private TextField admittedDateTextField;

   // first name text field
   @FXML
   private TextField firstNameTextField;

   // last name text field
   @FXML
   private TextField lastNameTextField;

   // status options combo box
   @FXML
   private ComboBox statusOptionsLabel;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      addSelectedPatientListener();
      for (PatientStatus ps : PatientStatus.values()) {
         statusOptionsLabel.getItems().add(ps.name());
      }
   }

   /**
    * Update patient data when update button clicked
    *
    * @param event
    */
   public void onUpdateClicked(ActionEvent event) {
      Patient patient = ViewManager.getManager().getSelectedPatientProperty().get();
      patient.setFirstName(firstNameTextField.getText());
      patient.setLastName(lastNameTextField.getText());
      patient.setStatus(PatientStatus.valueOf((String) statusOptionsLabel.getValue()));
      
   }

   public void displayPatient(Patient patient) {

      if (patient != null) {
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy H:m");
         LocalDateTime admitDate = LocalDateTime.from(patient.getAdmissionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

         patientNumberTextField.setText(patient.getPublicId());
         firstNameTextField.setText(patient.getFirstName());
         lastNameTextField.setText(patient.getLastName());
         admittedDateTextField.setText(admitDate.format(dtf));
         statusOptionsLabel.setValue(patient.getStatus().name());
      } else {
         patientNumberTextField.clear();
         firstNameTextField.clear();
         lastNameTextField.clear();
         admittedDateTextField.clear();
      }
       

   }

   private void addSelectedPatientListener() {
      ViewManager.getManager().getSelectedPatientProperty().addListener(new ChangeListener<Patient>() {
         @Override
         public void changed(ObservableValue<? extends Patient> observable, Patient oldValue, Patient newValue) {
            displayPatient(newValue);
         }
      });
   }
}
