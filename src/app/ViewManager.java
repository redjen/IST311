package app;

import dao.DaoException;
import dao.DaoFactory;
import dao.PatientChangeListener;
import dao.PatientDao;
import hospital.Account;
import hospital.PatientCollection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The ViewManager is a top-level controller/manager for the application.
 *
 * It provides the following functionality:
 *
 * 1. Acts as an intermediary between classes by providing methods to exchange
 * information. See specific methods for more information.
 *
 * 2. Controls the size of the application window. The current height and width
 * can be obtained from getX() and getY(). The dimensions can be changed through
 * setDimensions().
 *
 * 3. Sets the current user (from LoginController) and shows the main inteface.
 *
 * 4. Logs out users by unsetting the current user and showing the login screen.
 *
 * 5. Creates and provides a reference to the PatientCollection.
 *
 * 6. Instantiates the PatientChangeListener and adds it to the
 * PatientCollection for persisting data.
 *
 *
 *
 * @author maximdumont
 * @author redjen
 */
public class ViewManager {

   private static ViewManager instance;
   private Stage stage;
   private int x, y;
   private static Account currentAccount = null;
   private final PatientCollection patientCollection;
   private PatientChangeListener patientChangeListener;

   private final static int DEFAULT_WIDTH = 640;
   private final static int DEFAULT_HEIGHT = 500;

   /**
    * Constructs a new ViewManager using the width and height specified
    *
    * @param stage the root stage
    * @param x width
    * @param y height
    *
    */
   public ViewManager(Stage stage, int x, int y) {
      this.stage = stage;
      this.instance = this;
      this.x = x;
      this.y = y;
      patientCollection = new PatientCollection();

      // get all patients from repository then set up the patient change listener
      try {
         this.patientChangeListener = new PatientChangeListener();

         PatientDao dao = DaoFactory.getPatientDao();
         patientCollection.addAll(dao.getAllActivePatients());
         patientCollection.getPatientList().addListener(patientChangeListener);
      } catch (DaoException ex) {
         Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * Constructs a new ViewManager using the default width and height
    *
    * @param stage the root stage
    *
    */
   public ViewManager(Stage stage) {
      this(stage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
   }

   /**
    * Sets the application window's dimensions
    *
    * @param x
    * @param y
    */
   public void setDimensions(int x, int y) {
      this.x = x;
      this.y = y;
      stage.setWidth(x);
      stage.setHeight(y);
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   /**
    * Returns the collection/controller for the list of patients in the system
    *
    * @return the collection/controller
    */
   public PatientCollection getPatientCollection() {
      return patientCollection;
   }

   /**
    * Validate if string is valid fxml document and return a valid one if not
    *
    * @param uri
    * @return the original URI if valid or updated one if not
    */
   private String internalValidate(String uri) {
      if (!uri.endsWith(".fxml")) {
         uri += ".fxml";
      }
      return uri;
   }

   /**
    * Navigate to uri based on uri name
    *
    * @param uri
    */
   public void navigate(String uri) {
      try {
         uri = internalValidate(uri);
         Parent root = FXMLLoader.load(getClass().getResource(uri));
         Scene scene = new Scene(root);

         stage.setScene(scene);
         stage.show();
      } catch (IOException ex) {
         Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * get current view manager instance
    *
    * @return ViewManager instance
    */
   public static ViewManager getManager() {
      return instance;
   }

   /**
    * Sets the account currently logged in
    *
    * @param currentAccount
    */
   public void loginAccount(Account currentAccount) {
      ViewManager.currentAccount = currentAccount;
      System.out.printf("Logged in account %s%n", currentAccount.getLoginName());
   }

   /**
    * Logs out the current account, if set
    */
   public void logoutAccount() {
      if (ViewManager.currentAccount != null) {
         System.out.printf("Logged out current account%n", currentAccount.getLoginName());
         ViewManager.currentAccount = null;
         navigate("Login.fxml");
      }
   }

   /**
    * Tests the currently logged in account (if any) for employee status
    *
    * @return true if the current account is an employee, false otherwise or if
    * no account is logged in
    */
   public boolean isEmployeeAccountLoggedIn() {
      return (ViewManager.currentAccount != null && ViewManager.currentAccount.isEmployee());
   }

   public static Account getCurrentAccount() {
      return currentAccount;
   }
   
}
