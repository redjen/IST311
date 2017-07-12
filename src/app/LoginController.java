package app;

import dao.AccountDao;
import dao.AccountDaoException;
import dao.AccountDaoFactory;
import hospital.Account;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * LoginController is the FXML controller for LoginView. It handles showing the
 * loginButtonHandler view and authenticating users.
 *
 */
public class LoginController implements Initializable {

   // username textfield for username 
   @FXML
   private TextField username;

   // password box for user password
   @FXML
   private PasswordField password;

   // validator when isLoggedIn flags as false
   @FXML
   private Label errorLabel;

   // used for clock on top right
   @FXML
   private Label clockLabel;

   private Timer timer;

   public LoginController() {
   }

   /**
    * Initializes the controller class.
    *
    * @param url
    * @param rb
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
      startClock();
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

   /**
    * Listens for input in the username and password fields, attempting login
    * if the enter key is pressed and hiding the error message otherwise.
    *
    * The reason for clearing the error message is that, if it's shown, the user
    * is retyping their credentials and doesn't need to see the message until
    * they try to authenticate again with bad credentials.
    * 
    * This needs to be set on the onKeyReleased action, not onKeyTyped action
    * as event.keyCode() is always undefined for the latter by design. (Why? I 
    * don't know).
    * 
    * @see KeyCode
    *
    * @param event keyReleased event
    */
   @FXML
   public void keyHandler(KeyEvent event) {
      if (event.getCode().equals(KeyCode.ENTER)) {
         loginUser();
      } else {
         errorLabel.setVisible(false);
      }
   }

   /**
    * Login event when loginButtonHandler button is clicked.
    *
    * @param event when button clicked
    */
   public void loginButtonHandler(ActionEvent event) {
      loginUser();
   }

   /**
    * Authenticates the user with the credentials supplied and loads the TabView
    * if successful.
    */
   private void loginUser() {
      try {
         AccountDao loginDao = AccountDaoFactory.getDao();
         Account account = loginDao.getAccountByLoginName(username.getText());
         boolean isLoggedIn = account != null && account.validatePassword(password.getText());
         if (isLoggedIn) {
            timer.cancel();
            ViewManager.getManager().loginAccount(account);
            ViewManager.getManager().navigate("TabView");
         } else {
            errorLabel.setVisible(true);
         }
      } catch (AccountDaoException ex) {
         Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

}
