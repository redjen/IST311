package app;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * TabViewController is the controller for the TabView, the view top-level
 * view for the application that contains the objects that users need to
 * navigate the application.
 */
public class TabViewController implements Initializable {

   @FXML
   private Label clockLabel;

   @FXML
   private TabPane tabPane;

   @FXML
   private Tab homeTab;

   @FXML
   private Tab patientListTab;

   @FXML
   private Tab admittingTab;

   @FXML
   private Tab mailboxTab;
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
         @Override
         public void run() {
            Platform.runLater(new Runnable() {
               @Override
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
      setTabs();
      startClock();

   }

   /**
    * Tests employee level and sets the tab pane to show associated tabs
    */
   private void setTabs() {
      tabPane.getTabs().clear();
      
      if (ViewManager.getManager().isEmployeeAccountLoggedIn()) {
         // nurse view
         tabPane.getTabs().addAll(homeTab, patientListTab, admittingTab,mailboxTab);
      } else {
         // patient view
         // TODO add the complete set of tabs here once the patient-related views have been created
         tabPane.getTabs().addAll(homeTab, patientListTab);
      }
   }

}
