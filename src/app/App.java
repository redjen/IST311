package app;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The App class launches the application
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ViewManager manager = new ViewManager(stage);
        manager.navigate("Login");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
