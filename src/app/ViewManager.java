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
    private int x,y;
    
    public ViewManager(Stage stage,int x,int y){
        this.stage = stage;
        this.instance = this;
        this.x = x;
        this.y = y;
    }
    
    public ViewManager(Stage stage){
        this(stage,640,500);
    }
    
    public void setDimensions(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    private String internalValidate(String uri)
    {
          if(!uri.endsWith(".fxml")){
            uri+=".fxml";
        }
        return uri;
    }
    
    public void navigate(String uri) throws IOException{
        uri = internalValidate(uri);
        Parent root = FXMLLoader.load(getClass().getResource(uri));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static ViewManager getManager(){
        return instance;
    }
    
}