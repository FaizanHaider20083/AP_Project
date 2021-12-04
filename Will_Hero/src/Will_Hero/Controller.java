package Will_Hero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
    @FXML
    void play(ActionEvent e){
        System.out.println("Play ");
        System.exit(1);

    }
    @FXML
    void quit(ActionEvent e){
        System.out.println("quit");
        System.exit(1);
    }
    @FXML
    void load(ActionEvent e){
        System.out.println("Load");
        System.exit(1);
    }
}
