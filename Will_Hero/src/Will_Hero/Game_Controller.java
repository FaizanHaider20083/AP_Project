package Will_Hero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Game_Controller {
    private Stage stage;
    private Scene scene;
    private HBox hbox;
    @FXML
    void pause(ActionEvent e) throws IOException {
        try {
            System.out.println("Pause Menuuuuu");
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
    void quit(){
        System.out.println("quit");
        System.exit(1);
    }
    @FXML
    void play(ActionEvent e){
        try {
            System.out.println("Playyyyyyyy");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = loader.load();



            Orcs orc = new Green_Orcs(100,50,100,10,10);
            orc.setImage("assets/greenorc.png");
            Canvas c = new Canvas(793,373);
            orc.render(c.getGraphicsContext2D());
            Player my_player = new Player();
            my_player.getHero().setImage("assets/redorc.png");
            my_player.getHero().render(c.getGraphicsContext2D());
            Controller controller = loader.getController();

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();

            scene = new Scene(root);

            hbox.getChildren().add(c);

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch (keyEvent.getCode()){
                        case W: controller.example();
                    }
                }
            });
            stage.setScene(scene);
            stage.show();

            my_player.getHero().move();

        }
        catch (Exception f){
            f.printStackTrace();
        }

    }
    void example(){
        System.out.println("Javafx is a nightmarex");
    }

    @FXML
    protected void initialize(){
        this.hbox = new HBox();
        hbox.getChildren().add(new Button("Helle "));
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
