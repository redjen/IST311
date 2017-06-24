package app;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author maximdumont
 */
public class ViewManager {

    private ScrollPane injectorControl;
    private static ViewManager instance;
    private Stage stage;
    private int x, y;

    // Constructor
    // @param stafe the root vancas of application
    // @param x x dimension of application
    // @param y y dimension of application
    public ViewManager(Stage stage, int x, int y) {
        this.stage = stage;
        this.instance = this;
        this.x = x;
        this.y = y;
    }

    // Constructor 
    // @param Stage the root canvas of applicaton
    public ViewManager(Stage stage) {
        this(stage, 640, 500);
    }

    // set screen dimensions
    public void setDimensions(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // get x dimension
    public int getX() {
        return this.x;
    }

    // get y dimension
    public int getY() {
        return this.y;
    }

    // validate if string is valid fxml document
    private String internalValidate(String uri) {
        if (!uri.endsWith(".fxml")) {
            uri += ".fxml";
        }
        return uri;
    }

    // nnavigate to uri based on uri name
    public void navigate(String uri) throws IOException {
        uri = internalValidate(uri);
        Parent root = FXMLLoader.load(getClass().getResource(uri));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    // get current view manager instance
    public static ViewManager getManager() {
        return instance;
    }

}
