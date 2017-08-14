package app;

import dao.DaoException;
import dao.PatientChangeListener;
import hospital.Patient;
import hospital.PatientCollection;
import hospital.PatientStatus;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.Background;
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
    private ListView mailboxListView;

    private static final ObservableList listViewCollection = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailboxListView.setItems(listViewCollection);

        mailboxListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new LabelCell();
            }
        }
        );

        ViewManager.getManager().getPatientCollection().getPatientList().addListener((ListChangeListener.Change<? extends Patient> changes) -> {
            while (changes.next()) {
                if (changes.wasRemoved()) {
                    for (Patient patient : changes.getRemoved()) {
                        listViewCollection.add(String.format("(%s, %s) is currently removed", patient.getLastName(), patient.getFirstName(), patient.getStatusDisplayText(), new Date().toString()));
                        return;
                    }
                } else if (changes.wasAdded()) {
                    for (Patient patient : changes.getAddedSubList()) {
                        listViewCollection.add(String.format("(%s, %s) is currently added", patient.getLastName(), patient.getFirstName(), patient.getStatusDisplayText(), new Date().toString()));
                        return;
                    }
                } else if (changes.wasUpdated()) {
                    for (int i = changes.getFrom(); i < changes.getTo(); i++) {
                        Patient patient = ViewManager.getManager().getPatientCollection().find(changes.getList().get(i).getPatientId());
                        listViewCollection.add(String.format("(%s, %s) is currently %s", patient.getLastName(), patient.getFirstName(), patient.getStatusDisplayText(), new Date().toString()));
                        return;
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
                if (item.contains("waiting")) {
                    label.setStyle("-fx-background-color:green");
                } else if (item.contains("in treatment")) {
                    label.setStyle("-fx-background-color:Yellow");
                } else if (item.contains("in recovery")) {
                    label.setStyle("-fx-background-color:Red");
                } else {
                    label.setStyle("-fx-background-color:rgb(51, 102, 153)");
                }
                label.setText(item);
                setGraphic(label);
            }
        }
    }
}
