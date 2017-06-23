package app;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
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
    @FXML private Label errorLabel;
    @FXML private Label clockLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startClock();
    }    
    
        private void startClock(){
        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        clockLabel.setText(timeStamp);
        Timer timer = new java.util.Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                public void run() {
                    String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                    clockLabel.setText(timeStamp);
                }
            });
    }
},0,1000);
    }
        
    public void login(ActionEvent event) throws IOException{
        boolean isLoggedIn = true;
        
        if(isLoggedIn){
            ViewManager.getManager().navigate("TabView");
        }else{
            errorLabel.setVisible(true);
        }
    }
    
}
