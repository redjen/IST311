package app;

import hospital.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class for the patient list view
 *
 */
public class PatientListViewController implements Initializable {

   // table view used to populate patient data
   @FXML
   private TableView<Patient> patientListTableView;

   // table columns
   @FXML
   private TableColumn<Patient, String> tableColumnId;
   @FXML
   private TableColumn<Patient, String> tableColumnFirstName;
   @FXML
   private TableColumn<Patient, String> tableColumnLastName;
   @FXML
   private TableColumn<Patient, String> tableColumnStatus;

   private ViewManager manager;

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      manager = ViewManager.getManager();
      populateView();
   }

   public PatientListViewController() {
   }

   /**
    * Populate the table view with patient data based on the current user's
    * access level
    */
   private void populateView() {

      // TODO add patient status once we've implemented it in the app
      tableColumnId.setCellValueFactory(cellData -> cellData.getValue().getPublicIdProperty());

      if (manager.isEmployeeAccountLoggedIn()) {
         tableColumnFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
         tableColumnLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
      } else {
         patientListTableView.getColumns().remove(tableColumnFirstName);
         patientListTableView.getColumns().remove(tableColumnLastName);
      }

      patientListTableView.setItems(manager.getPatientCollection().getPatientList());
   }
}
