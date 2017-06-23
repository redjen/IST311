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
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private CheckBox sendUpdates;
    @FXML private Label clockLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public AdmittingViewController(){
        
    }
    
    public void onPatientAdded(ActionEvent event){
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        boolean sendUpdates = this.sendUpdates.isPressed();
    }
    
}
