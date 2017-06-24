package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author maximdumont
 */
public class DischargePatientViewController implements Initializable {

    @FXML
    private Label patientNumberLabel;
    @FXML
    private Label admittedDateLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private ComboBox statusOptionsComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*
        Method used to discharge patient
        @param ActionEvent event being passed from button event in FXML
    */
    public void dischagePatient(ActionEvent event) {

    }
}
