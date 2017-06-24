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

    // username textfield for username 
    @FXML
    private TextField username;

    // password box for user password
    @FXML
    private PasswordField password;

    // validator when isLoggedIn flags as false
    @FXML
    private Label errorLabel;

    // used for clock on top right
    @FXML
    private Label clockLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startClock();
    }

    /* Starts the view clock */
    private void startClock() {
        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        clockLabel.setText(timeStamp);
        Timer timer = new java.util.Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                        clockLabel.setText(timeStamp);
                    }
                });
            }
        }, 0, 60 * 1000);
    }

    /*
        Login event when login button is clicked. 
        @param ActionEvent when button clicked
     */
    public void login(ActionEvent event) throws IOException {
        boolean isLoggedIn = true;

        if (isLoggedIn) {
            ViewManager.getManager().navigate("TabView");
        } else {
            errorLabel.setVisible(true);
        }
    }

}
