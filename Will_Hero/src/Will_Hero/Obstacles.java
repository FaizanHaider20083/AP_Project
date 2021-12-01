package Will_Hero;

public abstract class Obstacles extends GameObjects{
    Obstacles(float x, float y) {
        super(x, y);
    }
}

class TNT extends Obstacles{
    private int damage;
    private int range;
    TNT(float x, float y) {
        super(x, y);
        this.damage = 10;
        this.range = 5;
    }
}
