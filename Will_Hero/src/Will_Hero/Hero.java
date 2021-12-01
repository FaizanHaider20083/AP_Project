package Will_Hero;
import java.util.*;
class Helmet{
    private ArrayList<Weapon> weaponlist;
    public ArrayList<Weapon> getWeaponlist() { return weaponlist; }
}
abstract class Weapon extends GameObjects{
    private int dps;
    private int level;
    private boolean active_status;
    private int range;
    Weapon(float x, float y, int dps, int level, boolean active_status, int range) {
        super(x, y);
        this.dps = dps;
        this.level = level;
        this.active_status = active_status;
        this.range = range;
    }
}
class Lance extends Weapon{
    Lance(float x, float y) {
        super(x, y,5 ,1 , true,10 );

    }
}
class Sword extends Weapon{
    Sword(float x, float y) {
        super(x, y,5 ,1 , true,10 );

    }
}
public class Hero extends GameObjects{
    private Helmet helmet;
    private boolean boss_status;
    private int health;
    Hero(float x, float y) {
        super(x, y);
        this.health = 20;
        this.helmet = new Helmet();
        this.boss_status = false;
    }
}
