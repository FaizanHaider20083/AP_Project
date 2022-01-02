package Will_Hero;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.*;
import java.util.ArrayList;

public class ReGenerate{
    private ArrayList<GameObjects> objlist;
    private GameObjects deserializedObj = null;
    private Hero dehero = null;
    private Player deplayer = null;
    private Hero hero;
    private Helmet helmet;
    private Player player;
    public Hero getHero(){
        return hero;
    }
    public Player getPlayer() {
        System.out.println(player.getCurr_coins());
        return player;
    }
    private void deserialize(String fileName, AnchorPane MainAnchorPane) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        objlist = new ArrayList<>();
        try{
            in = new ObjectInputStream(new FileInputStream(fileName));
            //deserializedObj = (Hero) in.readObject();
            dehero = (Hero) in.readObject();
            ArrayList<Weapon> wep = new ArrayList<>();
            wep.add(dehero.getHelmet().getWeaponlist().get(0));
            wep.add(dehero.getHelmet().getWeaponlist().get(1));
            wep.get(0).display(MainAnchorPane);
            wep.get(1).display(MainAnchorPane);
            wep.get(0).getNode().setOpacity(0);
            wep.get(1).getNode().setOpacity(0);
            Helmet helmet = new Helmet();
            helmet.setWeaponlist(wep);
            System.out.println(helmet);
            hero = new Hero(dehero.getPos_x(),dehero.getPos_y(),0,8,30,40, 0, helmet);
            hero.display(MainAnchorPane);
//            hero.getGladiator().setX(hero.getPos_x());
//            hero.getGladiator().setY(hero.getPos_y());
            //hero.display(MainAnchorPane);
//            deplayer = (Player) in.readObject();
            player = new Player();
            player.setHero(hero);
//            player.setLife_count(deplayer.getLife_count());
//            player.setGameno(deplayer.getGameno());
//            player.setCurr_coins(deplayer.getCurr_coins());

            while(true) {
                try{
                    GameObjects tmp = (GameObjects) in.readObject();
                    objlist.add(tmp);
                }catch (EOFException e) {
                    break;
                }catch (ClassCastException e) {
                    System.out.println("Invalid Class Cast Exception");
                }
            }
        }finally{in.close();}

    }

    public ArrayList<GameObjects> regenerateGameObjects(String fileName, AnchorPane MainAnchorPane) throws IOException, ClassNotFoundException {
        deserialize(fileName, MainAnchorPane);
        System.out.println(fileName);
        ArrayList<GameObjects> finalList = new ArrayList<>();
        for(int i=0;i<objlist.size();i++) {
            GameObjects obj = objlist.get(i);
            if(obj instanceof Coins) {
                Coins c = new Coins(obj.getPos_x(), obj.getPos_y(), 20, 20);
                c.display(MainAnchorPane);
//                c.getNode().setX(obj.getNode().getX());
//                c.getNode().setY(obj.getNode().getY());
                finalList.add(c);
            }
            else if(obj instanceof WeaponChest) {
                WeaponChest c = new WeaponChest(obj.getPos_x(),obj.getPos_y(),((WeaponChest) obj).getWeapon());
                c.display(MainAnchorPane);
//                c.getNode().setX(obj.getNode().getX());
//                c.getNode().setY(obj.getNode().getY());
                //c.display(MainAnchorPane);
                finalList.add(c);
            }
            else if(obj instanceof CoinChest) {
                CoinChest c = new CoinChest(obj.getPos_x(),obj.getPos_y(), ((CoinChest) obj).getCoin_count(), ((CoinChest) obj).getHeight(), ((CoinChest) obj).getWidth());
                c.display(MainAnchorPane);
//                c.getNode().setX(obj.getNode().getX());
//                c.getNode().setY(obj.getNode().getY());
                //c.display(MainAnchorPane);
                finalList.add(c);
            }
            else if(obj instanceof Platform) {
                Platform c = new Platform(obj.getPos_x(), obj.getPos_y(),((Platform) obj).getHeight(), ((Platform) obj).getWidth(), ((Platform) obj).getY_speed());
                c.display(MainAnchorPane);
//                c.getNode().setX(obj.getNode().getX());
//                c.getNode().setY(obj.getNode().getY());
                //c.display(MainAnchorPane);
                finalList.add(c);

            }
            else if(obj instanceof TNT) {
                TNT c = new TNT(obj.getPos_x(), obj.getPos_y(), 10, 5, ((TNT) obj).getWidth(),((TNT) obj).getHeight(), ((TNT) obj).getPlatform_info());
                c.display(MainAnchorPane);
//                c.getNode().setX(obj.getNode().getX());
//                c.getNode().setY(obj.getNode().getY());
                //c.display(MainAnchorPane);
                finalList.add(c);

            }
            else if(obj instanceof Green_Orcs) {
                Green_Orcs c = new Green_Orcs(obj.getPos_x(), obj.getPos_y(), ((Green_Orcs) obj).getHealth(),0, ((Green_Orcs) obj).getY_speed(), ((Green_Orcs) obj).getHeight(), ((Green_Orcs) obj).getWidth(), ((Green_Orcs) obj).getPlatform_info());
                c.display(MainAnchorPane);
//                c.getNode().setX(obj.getNode().getX());
//                c.getNode().setY(obj.getNode().getY());
                //c.display(MainAnchorPane);
                finalList.add(c);

            }
            else if(obj instanceof Red_Orcs) {
                Red_Orcs c = new Red_Orcs(obj.getPos_x(), obj.getPos_y(), ((Red_Orcs) obj).getHealth(), 0, ((Red_Orcs) obj).getY_speed(), ((Red_Orcs) obj).getHeight(), ((Red_Orcs) obj).getWidth(), MainAnchorPane, ((Red_Orcs) obj).getPlatform_info());
                //c.display(MainAnchorPane);
//                c.getNode().setX(obj.getNode().getX());
//                c.getNode().setY(obj.getNode().getY());
//                finalList.add(c);

            }
            else if(obj instanceof Boss) {
                Boss c = new Boss(obj.getPos_x(), obj.getPos_y(), ((Boss) obj).getHealth(), ((Boss) obj).getX_speed(), ((Boss) obj).getY_speed(), ((Boss) obj).getWidth(), ((Boss) obj).getHeight(), ((Boss) obj).getPlatform_info());
                c.display(MainAnchorPane);
//                c.getNode().setX(obj.getNode().getX());
//                c.getNode().setY(obj.getNode().getY());
                //c.display(MainAnchorPane);
                finalList.add(c);

            }
//            else if (obj instanceof Hero)
//            {
//                Hero hero = new Hero(obj.getPos_x(), obj.getPos_y(), ((Hero) obj).getX_speed(), ((Hero) obj).getY_speed(), ((Hero) obj).getWidth(), ((Hero) obj).getHeight(), MainAnchorPane, ((Hero) obj).getPlatform_info());
//                hero.getNode().setX(obj.getNode().getX());
//                hero.getNode().setY(obj.getNode().getY());
//                finalList.add(hero);
//            }
        }
        return finalList;
    }
}

class ReGeneratePlayer {
    private Player generatedPlayer;

    public Player getPlayer(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {

            in = new ObjectInputStream(new FileInputStream(fileName));
            try {
                generatedPlayer = (Player)in.readObject();
            }catch (ClassCastException e) {
                System.out.println("Invalid Cast Exception");
            }

        }finally {
            in.close();
        }
        return generatedPlayer;
    }
}