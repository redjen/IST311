package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class for the post-admitted patient view.
 *
 */
public class AdmittingPatientAddedViewController implements Initializable {

    /*
        Patient Number Label used by FXML
     */
    @FXML
    private Label patientNumberLabel;

    /*
        Admitted On DateTime Label used by FXML
     */
    @FXML
    private Label admittedOnLabel;

    /*
        First Name Label used by FXML
     */
    @FXML
    private Label firstNameLabel;

    /*
        Last Name Label used by FXML
     */
    @FXML
    private Label lastNameLabel;

    /*
        Send Updates Checkbox used by FXML
     */
    @FXML
    private CheckBox sendUpdatesCheckBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
