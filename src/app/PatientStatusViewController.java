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

    @FXML private TextField searchField;
    @FXML private TextField patientNumberTextField;
    @FXML private TextField admittedDateTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private ComboBox statusOptionsLabel;
                    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void OnGoClicked(ActionEvent event){
        String searchString = searchField.getText();
    }
    
    public void onUpdateClicked(ActionEvent event){
    } 
}
