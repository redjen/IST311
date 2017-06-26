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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/**
 * TabViewController is the controller for the TabView, the view top-level
 * view for the application that contains the objects that users need to
 * navigate the application.
 */
public class TabViewController implements Initializable {

   /**
    * Initializes the controller class.
    */
   @FXML
   private ScrollPane injectionContainer;
   @FXML
   private Label clockLabel;

   private Timer timer;

   public TabViewController() {
   }

   /**
    * Starts the view's clock
    */
   private void startClock() {
      String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
      clockLabel.setText(timeStamp);
      timer = new java.util.Timer(true);

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

   @Override
   public void initialize(URL url, ResourceBundle rb) {
      startClock();
   }

   /**
    * navigate to patient list when button clicked
    */
   public void patientListOnClicked(ActionEvent event) {
      internalInject("PatientListView.fxml");
   }

   /**
    * navigate to admit view when button clicked
    *
    * @param event
    */
   public void admittingOnClicked(ActionEvent event) {
      internalInject("AdmittingView.fxml");
   }

   /**
    * navigate to patient status view when button clicked
    */
   public void patientStatusOnClicked(ActionEvent event) {
      internalInject("PatientStatusView.fxml");
   }

   //  DO NOT MODIFY!! Modifications will cause the whole view system to BREAK! Contact @maximdumont for clarification
   //  Inject specific view by uri name and inject into scroll view
   private void internalInject(String uri) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(uri));
         injectionContainer.setContent(loader.load());
      } catch (IOException x) {
         x.printStackTrace();
      }
   }
}
