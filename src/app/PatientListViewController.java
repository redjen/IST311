package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author maximdumont
 */
public class PatientListViewController implements Initializable {

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
