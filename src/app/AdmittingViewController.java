package app;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Timer;
import java.util.TimerTask;

/**
 * FXML Controller class
 *
 * @author maximdumont
 */
public class AdmittingViewController implements Initializable {

    // First Name TextField used by FXML
    @FXML
    private TextField firstName;

    // Last Name TextField used by FXML
    @FXML
    private TextField lastName;

    // SendUpdates checkbox used by FXML
    @FXML
    private CheckBox sendUpdates;

    // Clock Label used by FXML
    @FXML
    private Label clockLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*
        Method is called when the patient add button is clicked
        @param ActionEvent event being sent from fxml click event
     */
    public void onPatientAdded(ActionEvent event) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        boolean sendUpdates = this.sendUpdates.isPressed();
    }

}
