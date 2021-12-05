package Will_Hero;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public abstract class Orcs extends GameObjects{
    private int health;
    private int x_speed;
    private int y_speed;


    Orcs(float x, float y, int health, int x_speed, int y_speed) {
        super(x, y);
        this.health = health;
        this.x_speed = x_speed;
        this.y_speed = y_speed;

    }


}
class Green_Orcs extends Orcs{
    Green_Orcs(float x, float y, int health, int x_speed, int y_speed) {
        super(x, y, health, x_speed, y_speed);
    }
}

class Red_Orcs extends Orcs{
    Red_Orcs(float x, float y, int health, int x_speed, int y_speed) {
        super(x, y, health, x_speed, y_speed);
    }
}

class Boss extends Orcs{
    private boolean death_status;
    Boss(float x, float y, int health, int x_speed, int y_speed) {
        super(x, y, health, x_speed, y_speed);
        this.death_status = false;
    }
}


