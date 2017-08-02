package app;

import java.net.URL;
import java.util.ResourceBundle;
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
      // TODO
   }

   /**
    * Update patient data when update button clicked
    *
    * @param event
    */
   public void onUpdateClicked(ActionEvent event) {
   }
}
