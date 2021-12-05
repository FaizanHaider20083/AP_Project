package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Untitled.fxml"));
        primaryStage.setTitle("Will Hero Online");
        primaryStage.setScene(new Scene(root, 793, 373));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println("Hello world Hello");
        launch(args);

    }
}
