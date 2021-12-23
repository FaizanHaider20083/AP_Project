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
    private AnchorPane anchor;
    private ImageView TNT;
    private String path = "assets/ezgif.com-gif-maker.gif";
    private int platform_info;
    TNT(float x, float y, int damage, int range, float width, float height, AnchorPane pane, int platform_info) {
        super(x, y);
        this.damage = 10;
        this.range = 5;
        this.height = height;
        this.width = width;
        this.anchor = pane;
        this.platform_info = platform_info;
        display(pane);
    }
    public void display(AnchorPane pane){
        Image image = new Image(this.getPath());
        this.TNT = new ImageView(image);
        TNT.setX(super.getPos_x());
        TNT.setY(super.getPos_y());
        TNT.setFitHeight(this.getHeight());
        TNT.setFitWidth((this.getWidth()));
        this.getAnchor().getChildren().add(TNT);
        KeyFrame Frame = new KeyFrame(Duration.millis(50), e1->{
            TNT.setOpacity(TNT.getOpacity()-0.05);
        });
        Timeline time1 = new Timeline(Frame);
        time1.setCycleCount(20);
        time1.play();
        System.out.println("display" + this.getPos_x());
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