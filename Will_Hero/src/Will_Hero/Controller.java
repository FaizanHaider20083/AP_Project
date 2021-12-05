package Will_Hero;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    HBox hbox;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void play(ActionEvent e){
        try {
            System.out.println("Play");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = loader.load();





            stage = (Stage)((Node)e.getSource()).getScene().getWindow();

            scene = new Scene(root);


            stage.setScene(scene);
            stage.show();

        }
        catch (Exception f){
            f.printStackTrace();
        }

    }
    void example(){
        System.out.println("Hello here son");
    }
    @FXML
    void quit(){
        System.out.println("quit");
        System.exit(1);
    }
    @FXML
    void pause(ActionEvent e) throws  IOException{
        try {
            System.out.println("Pause Menu");
            Parent root = FXMLLoader.load(getClass().getResource("Pause Menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch ( Exception f){
            f.printStackTrace();
        }
    }
    @FXML
    void load(ActionEvent e) throws IOException {
        try {
            System.out.println("Loading");
            Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception f){
            f.printStackTrace();
        }
    }
    @FXML
    void backtomenu(ActionEvent e) throws IOException{
        try {
            System.out.println("Back to screen");
            Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception f){
            f.printStackTrace();
        }
    }
}
