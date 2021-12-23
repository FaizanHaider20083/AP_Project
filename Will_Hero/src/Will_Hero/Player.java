package Will_Hero;
import javafx.scene.layout.AnchorPane;

import java.util.*;

public class Player{
    private final ArrayList<Integer> gameList;
    private int curr_coins;
    private int life_count = 1;
    private Hero hero;
    private int high_coins;
    public Player(float x, float y, float x_speed, float y_speed, float width, float height, AnchorPane pane, int platform_info){
        this.curr_coins = 0;
        this.high_coins = 0;
        this.hero = new Hero(x,y,x_speed,y_speed,width,height,pane,platform_info);
        this.gameList = new ArrayList<Integer>();
    }
    void setCurr_coins(int curr_coins){this.curr_coins = curr_coins;}
    int getCurr_coins(){return  this.curr_coins;}
    Hero getHero(){
        return this.hero;
    }
}