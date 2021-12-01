package Will_Hero;

public abstract class GameObjects{
    private float pos_x;
    private float pos_y;
    GameObjects(float x, float y){
        this.pos_x = x;
        this.pos_y = y;
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


