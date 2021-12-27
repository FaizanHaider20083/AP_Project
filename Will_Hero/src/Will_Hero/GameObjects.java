package Will_Hero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.Random;

public abstract class GameObjects implements Serializable{
    private float pos_x;
    private float pos_y;
    private ImageView node;
    ImageView getNode(){return  this.node;}
    void setNode(ImageView node){this.node = node;}
    GameObjects(float x, float y){
        this.pos_x = x;
        this.pos_y = y;
    }
    float getPos_x(){
        return  this.pos_x;
    }
    float getPos_y(){
        return  this.pos_y;
    }
    public void setPos_x(float pos_x) { this.pos_x = pos_x; }
    public void setPos_y(float pos_y) { this.pos_y = pos_y; }
    abstract void display(AnchorPane pane);
}
class Platform extends GameObjects{
    private Decorations decoration;
    private float y_speed;
    private float width;
    private float height;
    private int objects;
    void setObjects(int objects){this.objects = objects;}
    int getObjects(){return this.objects;}
    private AnchorPane anchor;

    void setDecoration(Decorations decoration){this.decoration = decoration;}
    Decorations getDecoration(){return this.decoration;}
    private String path = "assets/T_Islands_01.png";
    private void setPath(){
        Random rand = new Random();
        this.path = "assets/island" + Integer.toString(rand.nextInt(5) + 1) +".png";
    }
    Platform(float x, float y, float height, float width, AnchorPane pane, float y_speed) {
        super(x,y);
        this.height = height;
        this.width = width;
        this.anchor = pane;
        this.y_speed = y_speed;
        this.objects = 0;
        System.out.println("Created");
        display(pane);
    }
    public void display(AnchorPane pane){
        setPath();
        System.out.println(this.getPath());
        Image image = new Image(this.getPath());
        ImageView node = getNode();
        node = new ImageView(image);

        node.setX(super.getPos_x());
        node.setY(super.getPos_y());
        node.setFitHeight(this.getHeight());
        node.setFitWidth((this.getWidth()));
        this.getAnchor().getChildren().add(node);
        System.out.println("display" + this.getPos_x());
        setNode(node);
    }

    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public AnchorPane getAnchor() { return anchor; }
    public float getY_speed() {return y_speed; }
    public void setY_speed(float y_speed) { this.y_speed = y_speed; }
    public String getPath() { return path; }
    public void motion(){
        if(this.getY_speed()!=0){
            getNode().setY(getNode().getY()-this.getY_speed());
            if(getNode().getY()<=this.getPos_y() - 75 || getNode().getY()>= this.getPos_y() + 75){
                float y_speed2 = this.getY_speed();
                this.setY_speed(-y_speed2);
            }
        }
    }
}
class Coins extends GameObjects{
    private float width;
    private float height;
    private AnchorPane anchor;

    private String path = "assets/coin.png";
    Coins(float x, float y, float height, float width, AnchorPane pane) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.anchor = pane;
        display(pane);
    }
    public void display(AnchorPane pane){
        Image image = new Image(this.getPath());
        ImageView node = getNode();
        node = new ImageView(image);
        node.setX(super.getPos_x());
        node.setY(super.getPos_y());
        node.setFitHeight(this.getHeight());
        node.setFitWidth((this.getWidth()));
        this.getAnchor().getChildren().add(node);
        setNode(node);
    }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public AnchorPane getAnchor() { return anchor; }
    public String getPath() { return path; }
}
