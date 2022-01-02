package Will_Hero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Game extends Application{
    public static Stage myStage;
    public static MediaPlayer mplayer;
    public void start(Stage primaryStage) throws Exception {
        myStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../Will_Hero/mainmenu.fxml"));
        primaryStage.setTitle("Will Hero Online");
        Scene scene = new Scene(root, 793, 373);
        primaryStage.setScene(scene);
        primaryStage.show();
        Media media = null;
        try {
            media = new Media(getClass().getResource("../assets/music.mp3").toURI().toString());
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        mplayer = new MediaPlayer(media);
        mplayer.setCycleCount(1000);
        mplayer.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
}