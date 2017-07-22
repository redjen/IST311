package app;

import hospital.Account;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author redjen
 */
public class HomeViewController implements Initializable {

   @FXML
   private Button logoutButton;

   @FXML
   private Label userNameLabel;

   private static final String LABEL_TEXT_FORMAT = "Logged in as %s (%s)";

   /**
    * Initializes the controller class.
    *
    * @param url
    * @param rb
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      setName();
   }

   @FXML
   public void logout() {
      ViewManager.getManager().logoutAccount();
   }

   private void setName() {
      Account account = ViewManager.getCurrentAccount();
      userNameLabel.setText(String.format(LABEL_TEXT_FORMAT,
              account.getFullName(), account.getLoginName()));
   }

}
