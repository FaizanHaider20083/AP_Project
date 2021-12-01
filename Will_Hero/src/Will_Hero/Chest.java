package Will_Hero;

public abstract class Chest extends GameObjects{
    Chest(float x, float y) {
        super(x, y);
    }
}
class WeaponChest extends Chest{
    private Weapon weapon;
    WeaponChest(float x, float y, Weapon weapon) {
        super(x, y);
        this.weapon = weapon;
    }
}
class CoinChest extends Chest{
    private int coin_count;
    CoinChest(float x, float y, int coins) {
        super(x, y);
        this.coin_count = coins;
    }
}