package Will_Hero;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObjects{
    private float pos_x;
    private float pos_y;
    private double height;
    private double width;
    private Image image;
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

    void setPos_y(float pos_y){
        this.pos_y = pos_y;

    }
    void set_Image(Image image){
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }
    void setImage (String image_location){
        set_Image(new Image(image_location));
    }
    void render(GraphicsContext gc){
        gc.drawImage(image,this.getPos_x(),300);
    }


}
class Platform extends GameObjects{
    Platform(float x, float y) {
        super(x, y);
    }
}
class Coins extends GameObjects{
    Coins(float x, float y) {
        super(x, y);
    }
}


