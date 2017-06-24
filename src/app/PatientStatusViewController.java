package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author maximdumont
 */
public class PatientStatusViewController implements Initializable {

    // search field for searching patients
    @FXML
    private TextField searchField;

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

    /* Search for patient data when go button clicked*/
    public void OnGoClicked(ActionEvent event) {
        String searchString = searchField.getText();
    }

    /* Update patient data when update button clicked*/
    public void onUpdateClicked(ActionEvent event) {
    }
}
