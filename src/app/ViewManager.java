package app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * The ViewManager is a top-level controller/manager for the application's
 * views and stage.
 */
public class ViewManager {

   private ScrollPane injectorControl;
   private static ViewManager instance;
   private Stage stage;
   private int x, y;

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
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
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

}
