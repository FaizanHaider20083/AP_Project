package Will_Hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Will_Hero/mainmenu.fxml"));

        primaryStage.setTitle("Will Hero Online");
        Scene scene = new Scene(root, 793, 373);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println("Hello fucking world");
        launch(args);

    }
}
