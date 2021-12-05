package Will_Hero;
import java.util.*;

public class Player{
    private final ArrayList<Integer> gameList;
    private int curr_coins;
    private int life_count = 1;
    private Hero hero;
    private int high_coins;
    public Player(){
        this.curr_coins = 0;
        this.high_coins = 0;
        this.hero = new Hero(0,0);
        this.gameList = new ArrayList<Integer>();
    }
    Hero getHero(){
        return this.hero;
    }
}
