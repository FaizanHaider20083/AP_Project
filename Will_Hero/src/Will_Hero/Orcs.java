package Will_Hero;
import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.*;
import javafx.geometry.Bounds;
import java.util.Random;

public abstract class Orcs extends GameObjects{
    private int health;
    private int x_speed;
    private int y_speed;
    abstract void display(AnchorPane pane);
    Orcs(float x, float y, int health, int x_speed, int y_speed) {
        super(x, y);
        this.health = health;
        this.x_speed = x_speed;
        this.y_speed = y_speed;
    }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getX_speed() { return x_speed; }
    public void setX_speed(int x_speed) { this.x_speed = x_speed; }
    public int getY_speed() { return y_speed; }
    public void setY_speed(int y_speed) { this.y_speed = y_speed;}
}
class Green_Orcs extends Orcs{
    private float width;
    private float height;
    private AnchorPane anchor;

    private String path = "assets/greenorc.png";
    private int platform_info;
    Green_Orcs(float x, float y, int health, int x_speed, int y_speed, float height, float width, AnchorPane pane, int platform_info) {
        super(x, y, health, x_speed, y_speed);
        this.width = width;
        this.height = height;
        this.anchor = pane;
        this.platform_info = platform_info;
        display(pane);

    }
    boolean platfrom_collision(Node obj){
        Bounds boundsInscreen = obj.localToParent(obj.getBoundsInLocal());
        return boundsInscreen.intersects(this.getNode().getBoundsInParent());
//        if(check_y(boundsInscreen) && (check_x(boundsInscreen))){

//            System.out.println("gladiator true " + this.getGladiator().getY() + " " + boundsInscreen.getMinY() + " " + boundsInscreen.getMaxY());

//            return true;
//        }
//        System.out.println("Else started");
//        System.out.println("gladiator Y " + this.getGladiator().getY());
//        System.out.println("gladiator X " + this.getGladiator().getX());
//        System.out.println(boundsInscreen.getMinY());System.out.println(boundsInscreen.getMaxY());
//        System.out.println("Else finished");
//        return false;
    }

    void free_fall(){
//        while (this.getNode().getY() < 350){
//            this.getNode().setY(this.getNode().getY() + 8);
//        }
//        works but just shows result not the transition animation

        TranslateTransition tt = new TranslateTransition(Duration.millis(2000),this.getNode());
        tt.setFromY(-20);
        System.out.println(this.getNode().getY());
        tt.setToY(350);
        tt.play();
       // this.getNode().setOpacity(0);
    }
    private void setPath(){
        Random rand = new Random();
        this.path = "assets/orc" + Integer.toString(rand.nextInt(6) + 1) +".png";
    }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public AnchorPane getAnchor() { return anchor; }
    public String getPath() { return path; }
    public int getPlatform_info() { return platform_info; }
    public void display(AnchorPane pane){
        setPath();
        System.out.println(getPath());
        Image image = new Image(getPath());
        ImageView node = getNode();
        node = new ImageView(image);
        node.setX(super.getPos_x());
        node.setY(super.getPos_y());
        node.setFitHeight(this.getHeight());
        node.setFitWidth((this.getWidth()));
        this.getAnchor().getChildren().add(node);
        setNode(node);
    }
    public void motion(Platform plat){
//        if(green_orc.getY()-this.getY_speed()>=plat.getIsland().getY()-40){
//            green_orc.setY(plat.getIsland().getY()-40);
//        }
//        else{
//            green_orc.setY(green_orc.getY()-this.getY_speed());
//        }
//        if(green_orc.getY()>=plat.getIsland().getY()-40 ||green_orc.getY()>=plat.getIsland().getY()-100){
//            float yspeed2 = this.getY_speed();
//            this.setY_speed(-yspeed2);
//        }
        if(this.getY_speed()!=0){
            getNode().setY(getNode().getY()-this.getY_speed());
            if(getNode().getY()>=plat.getPos_y() - 40 || getNode().getY()<= plat.getPos_y() -100){
                int y_speed2 = this.getY_speed();
                this.setY_speed(-y_speed2);
            }
        }
    }
}

class Red_Orcs extends Orcs{
    private float width;
    private float height;
    private AnchorPane anchor;
    private String path = "assets/redorc.png";
    private int platform_info;
    Red_Orcs(float x, float y, int health, int x_speed, int y_speed, float height, float width, AnchorPane pane, int platform_info) {
        super(x, y, health, x_speed, y_speed);
        this.width = width;
        this.height = height;
        this.anchor = pane;
        this.platform_info = platform_info;
        display(pane);

    }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public AnchorPane getAnchor() { return anchor; }
    public String getPath() { return path; }
    public int getPlatform_info() { return platform_info; }
    public void display(AnchorPane pane){
        System.out.println(getPath());
        Image image = new Image(getPath());
        ImageView node = getNode();
        node = new ImageView(image);
        node.setX(super.getPos_x());
        node.setY(super.getPos_y());
        node.setFitHeight(this.getHeight());
        node.setFitWidth((this.getWidth()));
        this.getAnchor().getChildren().add(node);
        setNode(node);
    }
    public void motion(Platform plat){
        if(this.getY_speed()!=0){
            getNode().setY(getNode().getY()-this.getY_speed());
            if(getNode().getY()>=plat.getPos_y() - 40 || getNode().getY()<= plat.getPos_y() -100){
                int y_speed2 = this.getY_speed();
                this.setY_speed(-y_speed2);
            }
        }
    }
}

class Boss extends Orcs{
    private boolean death_status;
    private int width;
    private int height;
    private AnchorPane anchor;
    private ImageView node;
    private String path = "assets/boss.png";
    private int platform_info;
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public AnchorPane getAnchor() { return anchor; }
    public String getPath() { return path; }
    public int getPlatform_info() { return platform_info; }
    public ImageView getNode(){return  this.node;}
    void setNode(ImageView node){this.node = node;}
    Boss(float x, float y, int health, int x_speed, int y_speed,int width,int height,AnchorPane pane,int platform_info) {
        super(x, y, health, x_speed, y_speed);
        this.death_status = false;
        this.width = width;
        this.height = height;
        this.anchor = pane;
        this.platform_info = platform_info;
        display(pane);
    }
    public void display(AnchorPane pane){
        Image image = new Image(getPath());
        this.node = new ImageView(image);
        node.setX(super.getPos_x());
        node.setY(super.getPos_y());
        node.setFitHeight(this.getHeight());
        node.setFitWidth((this.getWidth()));
        this.getAnchor().getChildren().add(node);
        this.setNode(node);
    }
}

