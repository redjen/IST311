package app;

import hospital.Patient;
import hospital.PatientCollection;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import notifcation.NotificationQueueingService;

/**
 * FXML Controller class
 *
 * @author Maxim Dumont
 */
public class MailboxViewController implements Initializable {

    @FXML
    private TextField mailboxDetailPaneTextField;

    @FXML
    private ListView mailboxListView;

    private static ObservableList listViewCollection = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailboxDetailPaneTextField.setText("No Record Selected. Please Selected A Record to view Details");
        mailboxListView.setItems(listViewCollection);

        mailboxListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new LabelCell();

            }
        }
        );
        mailboxListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue.contains("added") || newValue.contains("removed")) {
                    mailboxDetailPaneTextField.setText("No Additional Data For Selection");
                } else {
                    PatientCollection patientCollection = ViewManager.getManager().getPatientCollection();
                    ObservableList<Patient> patientList = patientCollection.getPatientList();
                    for (Patient patient : patientList) {
                        if (patient.getPublicId().equals(newValue)) {
                            String childView = String.format("(%s, %s) is currently %s", patient.getLastName(), patient.getFirstName(), patient.getStatusDisplayText(), new Date().toString());
                            mailboxDetailPaneTextField.setText(childView);
                            return;
                        }
                    }
                }
            }

        });

        ViewManager.getManager().getPatientCollection().getPatientList().addListener((ListChangeListener.Change<? extends Patient> changes) -> {
            while (changes.next()) {
                if (changes.wasRemoved()) {
                    for (Patient patient : changes.getRemoved()) {
                        listViewCollection.add(String.format("%s removed", patient.getPublicId()));
                    }
                }
                else if (changes.wasAdded()) {
                    for (Patient patient : changes.getAddedSubList()) {
                        listViewCollection.add(String.format("%s added", patient.getPublicId()));
                    }
                }
                else if (changes.wasUpdated()) {
                    for (int i = changes.getFrom(); i < changes.getTo(); i++) {
                        Patient collectionPatient = ViewManager.getManager().getPatientCollection().find(changes.getList().get(i).getPatientId());
                        listViewCollection.add(collectionPatient.getPublicId());
                    }
                }
            }
        });

    }

    static class LabelCell extends ListCell<String> {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Label label = new Label();

            if (item != null) {
                label.setText(item);
                setGraphic(label);
            }
        }
    }
}
