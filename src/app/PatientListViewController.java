package app;

import hospital.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

/**
 * FXML Controller class for the patient list view
 *
 */
public class PatientListViewController implements Initializable {

   private static final String UPDATE_STATUS_TAB_NAME = "PatientStatusView.fxml";

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
      addSelectionListener();
      populateView();
   }

   public PatientListViewController() {
   }

   /**
    * Populate the table view with patient data based on the current user's
    * access level
    */
   private void populateView() {

      // Bind cells
      tableColumnId.setCellValueFactory(cellData -> cellData.getValue().getPublicIdProperty());
      tableColumnStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty().asString());

      //
      if (manager.isEmployeeAccountLoggedIn()) {
         tableColumnFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
         tableColumnLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

      } else {
         patientListTableView.getColumns().remove(tableColumnFirstName);
         patientListTableView.getColumns().remove(tableColumnLastName);
      }

      // create double-click handler to open update view if user is an employee
      if (ViewManager.getManager().isEmployeeAccountLoggedIn()) {
         patientListTableView.setRowFactory(tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
               if (event.getClickCount() == 2 && !row.isEmpty()) {
                  Patient patient = row.getItem();
                  ViewManager.getManager().setSelectedPatient(patient);
                  ViewManager.getManager().navigate(UPDATE_STATUS_TAB_NAME);
               }
            });
            return row;
         });
      }

      patientListTableView.setItems(manager.getPatientCollection().getPatientList());
   }

   private void addSelectionListener() {
      patientListTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
         Patient selectedPatient = newSelection;
         ViewManager.getManager().setSelectedPatient(selectedPatient);
      });
   }
}
