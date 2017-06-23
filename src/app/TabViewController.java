package app;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author maximdumont
 */
public class TabViewController implements Initializable {
    /**
     * Initializes the controller class.
     */
    
    @FXML private ScrollPane injectionContainer;
    @FXML private Label clockLabel;
    
    public TabViewController(){
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startClock();
    }    
    
    
    public void patientListOnClicked(ActionEvent event){
       internalInject("PatientListView.fxml");
    }
    
    public void admittingOnClicked(ActionEvent event){
       internalInject("AdmittingView.fxml");
    }
    
    public void patientStatusOnClicked(ActionEvent event){
        internalInject("PatientStatusView.fxml");
    }
    
    private void internalInject(String uri){
        try{
         FXMLLoader loader = new FXMLLoader(getClass().getResource(uri));
         injectionContainer.setContent(loader.load());
        }
        catch(IOException x){
            x.printStackTrace();
        }
    }
}
