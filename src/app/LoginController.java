package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author maximdumont
 */
public class LoginController implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label error;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void login(ActionEvent event) throws IOException{
        boolean isLoggedIn = true;
        
        if(isLoggedIn){
            ViewManager.getManager().navigate("TabView");
        }else{
            error.setTextFill(Color.RED);
            error.setText("Error Logging In");
        }
    }
    
}
