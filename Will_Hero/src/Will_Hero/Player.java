package Will_Hero;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable{
    private int gameno;
    private int curr_coins;
    private int life_count = 1;
    private Hero hero;
    private int high_coins;
    public int getGameno() { return gameno; }
    public void setGameno(int gameno) { this.gameno = gameno; }
    public int getLife_count() { return life_count; }
    public void setLife_count(int life_count) { this.life_count = life_count; }
    public void setHero(Hero hero) { this.hero = hero; }
    public Player(){
        this.curr_coins = 0;
        this.high_coins = 0;
        //this.hero = new Hero(x,y,x_speed,y_speed,width,height,pane,platform_info, helmet);
        this.gameno = 0;
    }
    public void setCurr_coins(int curr_coins){this.curr_coins = curr_coins;}
    public int getCurr_coins(){return  this.curr_coins;}
    public Hero getHero(){
        return this.hero;
    }
}