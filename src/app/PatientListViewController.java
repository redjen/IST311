package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class for the patient list view
 *
 */
public class PatientListViewController implements Initializable {

   // table view used to populate patient data
   @FXML
   private TableView patientListTableView;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      // TODO
   }

   public PatientListViewController() {
      populateView();
   }

   /* 
        Populate the table view with patient data
    */
   private void populateView() {
      // TODO :Populate view
   }
}
