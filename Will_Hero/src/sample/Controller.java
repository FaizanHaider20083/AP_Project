//package sample;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class Controller {
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//    @FXML
//    void play(ActionEvent e){
//        try {
//            System.out.println("Load");
//            Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
//            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//        catch (Exception f){
//            f.printStackTrace();
//        }
//
//    }
//    @FXML
//    void quit(ActionEvent e){
//        System.out.println("quit");
//        System.exit(1);
//    }
//    @FXML
//    void load(ActionEvent e) throws IOException {
//        try {
//            System.out.println("Load");
//            Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
//            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//        catch (Exception f){
//            f.printStackTrace();
//        }
//    }
//    @FXML
//    void backtomenu(ActionEvent e) throws IOException{
//        try {
//            System.out.println("Load");
//            Parent root = FXMLLoader.load(getClass().getResource("Untitled.fxml"));
//            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//        catch (Exception f){
//            f.printStackTrace();
//        }
//    }
//}
