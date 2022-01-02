package Will_Hero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public abstract class Obstacles extends GameObjects{
    Obstacles(float x, float y) {
        super(x, y);
    }
}

class TNT extends Obstacles{

    private int damage;
    private int range;
    private float width;
    private float height;
    private transient AnchorPane anchor;
    private transient ImageView TNT;
    private String path = "assets/tnt.png";
    private int platform_info;
    TNT(float x, float y, int damage, int range, float width, float height, int platform_info) {
        super(x, y);
        this.damage = 10;
        this.range = 5;
        this.height = height;
        this.width = width;
        //this.anchor = pane;
        this.platform_info = platform_info;

        //display(pane);
    }
    public void display(AnchorPane pane){
        this.anchor = pane;
        Image image = new Image(this.getPath());
        ImageView node = new ImageView(image);
        node.setX(super.getPos_x());
        node.setY(super.getPos_y());
        node.setFitHeight(this.getHeight());
        node.setFitWidth((this.getWidth()));
        pane.getChildren().add(node);
        this.setNode(node);
//        this.getAnchor().getChildren().add(TNT);
//
//        System.out.println("display" + this.getPos_x());
    }
    void burst(){
        this.getNode().setImage(new Image("assets/tntburst.gif"));
        this.getNode().setFitHeight(this.getHeight()*3);
        this.getNode().setFitWidth((this.getWidth()*3));
        this.getNode().setY(this.getNode().getY() - 80);
        KeyFrame Frame = new KeyFrame(Duration.millis(100), e1->{
            this.getNode().setOpacity(this.getNode().getOpacity()-0.05);
        });
        Timeline time1 = new Timeline(Frame);
        time1.setCycleCount(20);
        time1.play();
    }
    public int getDamage() { return damage; }
    public int getRange() { return range; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public AnchorPane getAnchor() { return anchor; }
    public String getPath() { return path; }
    public int getPlatform_info() { return platform_info; }
    public ImageView getTNT() { return TNT; }
}