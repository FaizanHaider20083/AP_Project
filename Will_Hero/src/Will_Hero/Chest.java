package Will_Hero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public abstract class Chest extends GameObjects{
    Chest(float x, float y) {
        super(x, y);
    }
}
class WeaponChest extends Chest{
    private Weapon weapon;
    private float width;
    private float height;
    private transient AnchorPane anchor;
    private String path = "assets/weaponchest.png";

    WeaponChest(float x, float y, Weapon weapon) {
        super(x, y);
        this.weapon = weapon;
//        this.anchor = pane;
//        display(anchor);
    }
    private void setPath(){
        Random rand = new Random();
        this.path = "assets/weaponchest" + ".png";
    }
    public void display(AnchorPane pane){
        setPath();
        this.anchor = pane;
        System.out.println(this.getPath());
        Image image = new Image(this.getPath());
        ImageView node = new ImageView(image);
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

    public Weapon getWeapon() {
        return weapon;
    }
}
class CoinChest extends Chest{
    private float width;
    private float height;
    private transient AnchorPane anchor;
    private String path = "assets/coinchest.png";
    private int coin_count;
    int getCoin_count(){return this.coin_count;}
    private void setPath(){
        Random rand = new Random();
        this.path = "assets/coinchest" + Integer.toString(rand.nextInt(4) + 1) +".png";
    }
    CoinChest(float x, float y, int coins, float height, float width) {
        super(x, y);
        this.coin_count = coins;
        this.height = height;
        this.width = width;
//        this.anchor = pane;
//        display(pane);
    }
    public void display(AnchorPane pane){
        setPath();
        this.anchor = pane;
        System.out.println(this.getPath());
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