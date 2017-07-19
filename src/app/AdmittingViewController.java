package app;

import hospital.PatientCollection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class for the admitting view shown when adding a new patient.
 *
 */
public class AdmittingViewController implements Initializable {

   // First name field
   @FXML
   private TextField firstName;

   // Last name field
   @FXML
   private TextField lastName;

   // Checkbox to opt in or out to notifications
   @FXML
   private CheckBox sendUpdates;

   // Validation error message
   @FXML
   private Label errorMessage;

   // Patient added success message
   @FXML
   private Label successMessage;

   // Add a new patient button
   @FXML
   private Button addNewButton;

   // Clear the form button
   @FXML
   private Button clearButton;

   // Add another patient after successful add
   @FXML
   private Button addAnotherButton;

   // Patient ID label
   @FXML
   private Label patientIdTitleLabel;

   // Patient ID
   @FXML
   private Label patientIdLabel;

   /**
    * {@inheritDoc }
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }

   /**
    * Handler for clicking the add button
    *
    * @param event event being sent from fxml click event
    */
   public void onAddClicked(ActionEvent event) {
      addPatient();
   }

   /**
    * clears the form, clearing all fields and resetting button visibility
    *
    * @param event
    */
   public void reset(ActionEvent event) {
      firstName.clear();
      firstName.setEditable(true);
      lastName.clear();
      patientIdLabel.setText("");
      patientIdLabel.setVisible(false);
      patientIdTitleLabel.setVisible(false);
      firstName.setEditable(true);
      lastName.setEditable(true);
      addNewButton.setVisible(true);
      clearButton.setVisible(true);
      errorMessage.setVisible(false);
      successMessage.setVisible(false);
      addAnotherButton.setVisible(false);
      
      // activate addNewButton on enter key
      addAnotherButton.setDefaultButton(false);
      addNewButton.setDefaultButton(true);
   }

   /**
    * Adds a new patient to the patient list.
    */
   private void addPatient() {
      String patientId;
      PatientCollection patientCollection = ViewManager.getManager().getPatientCollection();
      String firstNameText = firstName.getText();
      String lastNameText = lastName.getText();

      // TODO create a new account if this is selected so patients can log in or design around the Patient class implementing Authenticatable
      boolean updatesSelected = sendUpdates.isSelected();

      if (!firstNameText.isEmpty() && !lastNameText.isEmpty()) {
         errorMessage.setVisible(false);

         patientId = patientCollection.add(firstNameText, lastNameText);
         // DEBUG
         patientCollection.find(patientId).setLastName("new last name");
         patientCollection.find(patientId).setFirstName("new first name");

         updateViewPatientAdded(patientCollection.find(patientId).getPublicId());
      } else {
         errorMessage.setVisible(true);
      }
   }

   /**
    * Sets the form state after successfully adding a patient by hiding editing
    * controls, showing the success message, and showing the "add another"
    * button
    *
    * @param patientId new patient's ID
    */
   private void updateViewPatientAdded(final String patientId) {
      patientIdLabel.setText(patientId);
      patientIdLabel.setVisible(true);
      patientIdTitleLabel.setVisible(true);
      firstName.setEditable(false);
      lastName.setEditable(false);
      addNewButton.setVisible(false);
      clearButton.setVisible(false);
      errorMessage.setVisible(false);
      successMessage.setVisible(true);
      addAnotherButton.setVisible(true);
      
      // activate addAnotherButton on enter key
      addNewButton.setDefaultButton(false);
      addAnotherButton.setDefaultButton(true);
   }

}
