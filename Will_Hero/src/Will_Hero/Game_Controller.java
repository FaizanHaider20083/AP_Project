package Will_Hero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;

import javax.swing.event.ChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game_Controller implements Serializable{
    private Boss boss;
    private boolean boss_generate;
    private boolean platform_contact;
    private Random rand;
    private Hero hero;
    private Parent root;
    private ArrayList <Decorations> decorationsList =new ArrayList<>();;
    private Player player;
    @FXML
    private AnchorPane MainAnchorPane;
    private boolean isMoving;
    private ArrayList <Platform> small_platforms = new ArrayList<>();
    private ArrayList <Platform> total_platforms = new ArrayList<>();
    private int score;
    private static Stage myStage;
    private static Scene gamePlayScene;
    private Timeline time;
    private ArrayList<Platform> platform = new ArrayList<>();
    private ArrayList<Green_Orcs> orc = new ArrayList<>();
    private ArrayList<TNT> tnt = new ArrayList<>();
    private ArrayList <Coins> coins = new ArrayList<>();
    private ArrayList <CoinChest> coinChests = new ArrayList<>();
    ArrayList<Coins> getCoins(){return  this.coins;}
    boolean getIsMoving(){return  this.isMoving;}
    void setIsMoving(boolean isMoving){this.isMoving = isMoving;}
    boolean get_boss_generated(){return this.boss_generate;}
    void set_boss_generate(boolean boss_generate){this.boss_generate = boss_generate;}
    void setScore(int score){
        this.score = score;
    }
    int getScore(){return this.score;}
    private Text scoreText;
    private Text cointext;
    private ArrayList <WeaponChest> weaponList  = new ArrayList<>();
    private Platform bossPlatform ;
    ArrayList <WeaponChest> getWeaponList(){return this.weaponList;}
    ArrayList<GameObjects> gameobjectlist = new ArrayList<>();
    void death(){
//        System.out.println(hero.getGladiator().getY());
        if (hero.getGladiator().getY() > 300)
        try {
            System.out.println("Tch Tch");
            if(time!=null){
                time.pause();
            }
            Parent root = FXMLLoader.load(getClass().getResource("resurrect.fxml"));
            MainAnchorPane = (AnchorPane) root;
            gamePlayScene = new Scene(root);
            scoreText = new Text(Integer.toString(getScore()));
            scoreText.setX(350);
            scoreText.setY(183);
            scoreText.setFont(Font.font("Arial Black"));
            MainAnchorPane.getChildren().add(scoreText);
            cointext = new Text(Integer.toString(player.getCurr_coins()));
            cointext.setX(350);
            cointext.setY(223);
            cointext.setFont(Font.font("Arial Black"));
            MainAnchorPane.getChildren().add(cointext);
            myStage.setScene(gamePlayScene);
            myStage.show();
        }
        catch ( Exception f){
            f.printStackTrace();
        }

    }
    void move(){
        setIsMoving(!getIsMoving());
//        for (int i =7;i< MainAnchorPane.getChildren().size() ;i++){
//            if((MainAnchorPane.getChildren().get(i) != hero.getGladiator() ) && i != 7){
//                Node temp = MainAnchorPane.getChildren().get(i);
//                if(temp.getId() != "background"){
//                    hero.collision(temp);
//                temp.setTranslateX(temp.getTranslateX() -80);
//                //System.out.println(temp.getId() + " " + i);
//
//               }
//
//
//            }
//
//
//        }

        for (Platform plat : platform){
            Node temp = plat.getNode();
            hero.collision(temp);
            temp.setTranslateX(temp.getTranslateX() -80);
        }
        for (Platform plat : small_platforms){
            Node temp = plat.getNode();
            hero.collision(temp);
            temp.setTranslateX(temp.getTranslateX() -80);
        }
        for (Coins coin : coins){
            Node temp = coin.getNode();
            if(hero.collision(temp)){
                player.setCurr_coins((player.getCurr_coins() + 10));
                cointext.setText(Integer.toString(player.getCurr_coins()));
                temp.setOpacity(0);
                System.out.println("Coin collision");
            }
            temp.setTranslateX(temp.getTranslateX() -80);
        }
        for (CoinChest chest : coinChests){
            Node temp = chest.getNode();
            if(hero.collision(temp)){
                String path = chest.getPath();
                path = path.substring(0,path.length()-4) + "_open.png";
                System.out.println(path);
                chest.getNode().setImage(new Image(path));
                player.setCurr_coins(player.getCurr_coins() + chest.getCoin_count());
            }
            temp.setTranslateX(temp.getTranslateX() -80);
        }
        for (WeaponChest chest : weaponList){
            Node temp = chest.getNode();
            if(hero.collision(temp)){
                String path = chest.getPath();
                path = path.substring(0,path.length()-4) + "_open.png";
                System.out.println(chest.getWeapon());
                chest.getNode().setImage(new Image(path));
                chest.getNode().setFitWidth(60);
                chest.getNode().setFitHeight(40);
                // lance -> 0 sword -> 1
                if (chest.getWeapon().getClass().equals(player.getHero().getHelmet().getWeaponlist().get(0).getClass())){
                    player.getHero().getHelmet().getWeaponlist().get(0).setActive_status(true);
                    player.getHero().getHelmet().getWeaponlist().get(1).setActive_status(false);
                    System.out.println(chest.getWeapon().getClass());

                    player.getHero().getHelmet().getWeaponlist().get(0).setQuantity(player.getHero().getHelmet().getWeaponlist().get(0).getQuantity() + 1);

                    player.getHero().getHelmet().getWeaponlist().get(1).getNode().setOpacity(0);
                    player.getHero().getHelmet().getWeaponlist().get(0).getNode().setOpacity(1);

                    player.getHero().getHelmet().getWeaponlist().get(0).setRange(player.getHero().getHelmet().getWeaponlist().get(0).getRange() + 2);
                }
                else if (chest.getWeapon().getClass().equals(player.getHero().getHelmet().getWeaponlist().get(1).getClass())){
                    player.getHero().getHelmet().getWeaponlist().get(1).setActive_status(true);
                    player.getHero().getHelmet().getWeaponlist().get(0).setActive_status(false);
                    System.out.println(chest.getWeapon().getClass());
                    player.getHero().getHelmet().getWeaponlist().get(1).setQuantity(player.getHero().getHelmet().getWeaponlist().get(1).getQuantity() + 1);
                    player.getHero().getHelmet().getWeaponlist().get(1).getNode().setOpacity(1);
                    player.getHero().getHelmet().getWeaponlist().get(0).getNode().setOpacity(0);
                    player.getHero().getHelmet().getWeaponlist().get(1).setRange(player.getHero().getHelmet().getWeaponlist().get(1).getRange() + 0.1);

                }
                else {
                    System.out.println("Elseeeeee " +chest.getWeapon().getClass());
                }
            }
            temp.setTranslateX(temp.getTranslateX() -80);


        }
        ArrayList <Integer> removal = new ArrayList<>();
        for (Green_Orcs orcc:orc){
            Node temp = orcc.getNode();
            //updated code here

            double distance = temp.getBoundsInParent().getMinX() - hero.getGladiator().getX();
            if (distance <= 80) {

               TranslateTransition tt = new TranslateTransition(Duration.millis(50), temp);
               tt.setFromX(temp.getTranslateX());
               tt.setToX(temp.getTranslateX() - distance);
               tt.play();

               orcc.setHealth(orcc.getHealth() - 10);
               tt.setOnFinished(e->{

                   temp.setTranslateX(temp.getTranslateX() + 60);
                  TranslateTransition tt2 = new TranslateTransition(Duration.millis(200), temp);
                   tt2.setFromX(temp.getTranslateX());
                   tt2.setToX(temp.getTranslateX() + 60);
                   tt2.play();
                   boolean contact = false;
                   for (Platform p : platform){
                       if(orcc.platfrom_collision(p.getNode())) contact = true;
                   }
                   if (!contact) {
                       orcc.setHealth(0);
                      orc_death(orcc);
                       removal.add(orc.indexOf(orcc));
                       System.out.println("Free fall");

                   }

               });


            }
            else
            temp.setTranslateX(temp.getTranslateX() -80);
            if (orcc.getHealth() <= 0){
                orc_death(orcc);
               removal.add( orc.indexOf(orcc));

            }

        }
       for (int orc_index : removal)
        orc.remove(orc_index);
        if (boss_generate){
        Node temp = boss.getNode();
        if(hero.collision(temp)){
            boss.setHealth(boss.getHealth() -50);
            if(boss.getHealth() <= 0) temp.setOpacity(0);
            temp.setTranslateX(temp.getTranslateX()+100);
            boss.getWeapon().setTranslateX(boss.getWeapon().getTranslateX() + 100);
        }
            boss.getWeapon().setTranslateX(boss.getWeapon().getTranslateX() - 80);
        temp.setTranslateX(temp.getTranslateX() -80);}
        //setIsMoving(!getIsMoving());
        for (TNT t: tnt){
            Node temp = t.getNode();
            if(hero.collision(temp)){
               t.burst();
               hero.setHealth(hero.getHealth() - 5);
               if (hero.getHealth() <= 0) playerDeath();
            }
            temp.setTranslateX(temp.getTranslateX() -80);
        }

        for (Decorations d: decorationsList){
            Node temp = d.getNode();
            temp.setTranslateX(temp.getTranslateX() -80);
        }
    }
    void fire(){
        for (Weapon w: this.player.getHero().getHelmet().getWeaponlist()){
            if (w.getActive_status()){
                w.fire(orc,boss_generate,boss,hero);
            }
        }
    }
    void orc_death(Green_Orcs orc){
        Node node = orc.getNode();
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), node);
        tt.setFromY(node.getTranslateY());
        tt.setToY(node.getTranslateY() - 60);
        tt.play();


        tt.setOnFinished(e->{

            node.setTranslateY(node.getTranslateY() - 60);
            RotateTransition rr = new RotateTransition(Duration.millis(100),node);
            rr.setByAngle(270);
            rr.play();
            rr.setOnFinished(f->{

                   orc.free_fall();

                });
            });
        }


    void create_coins(){
        for (int i =0;i<10;i++){
            int index = rand.nextInt(platform.size() );
            while (index == 0) index = rand.nextInt(platform.size() );
            Coins coin = new Coins(platform.get(index).getPos_x() + 40,platform.get(index).getPos_y()-40,20,20,MainAnchorPane);
            coins.add(coin);
            platform.get(index).setObjects(platform.get(index).getObjects() + 1);
            gameobjectlist.add(coin);
        }
    }
    void create_tnt(){
        for (int i =0;i<10;i++){
            int index = rand.nextInt(platform.size() );
            while (index == 0 || platform.get(index).getObjects()>2) index = rand.nextInt(platform.size());
            float distance = 50;
            if (platform.get(index).getWidth() < 50) distance = 20;
            if (platform.get(index).getObjects() > 0) distance = platform.get(index).getWidth()-50;
            TNT temp = new TNT(platform.get(index).getPos_x() + 20,platform.get(index).getPos_y()-30,10,5,40,30,MainAnchorPane,0);
            tnt.add(temp);
            platform.get(index).setObjects(platform.get(index).getObjects() + 1);
            gameobjectlist.add(temp);
        }
    }
    void create_orcs(){
        for (int i =0;i<15;i++){
            int index = rand.nextInt(platform.size() );
            while (platform.get(index).getWidth() < 140 || index == 0 || platform.get(index).getObjects()>2) index = rand.nextInt(platform.size());
            float distance = 40;
            if (platform.get(index).getObjects() > 0) distance = platform.get(index).getWidth()-50;
            Green_Orcs temp  = new Green_Orcs(platform.get(index).getPos_x() + distance, platform.get(index).getPos_y() -40, 15, 0, 9, 40, 40, MainAnchorPane, 1);
            orc.add(temp);
            platform.get(index).setObjects(platform.get(index).getObjects() + 1);
            gameobjectlist.add(temp);
        }
    }
    void coinCollision(){
        int index = -1;
        for (Coins c: coins){
            Node node = c.getNode();
            if (node.getBoundsInParent().intersects(hero.getGladiator().getBoundsInParent())){
                c.getNode().setOpacity(0);
                c.getNode().setY(350);
                index = coins.indexOf(c);
            }
        }
        if (index != -1){
            coins.remove(index);
        }
    }
    void tntCollision(){
        int index = -1;
        for (TNT t: tnt){
            Node node = t.getNode();
            if (node.getBoundsInParent().intersects(hero.getGladiator().getBoundsInParent()) || hero.collision(node)){
                t.burst();
                hero.setHealth(hero.getHealth() - 5);
                if (hero.getHealth() <= 0) playerDeath();
            }
        }
        if (index != -1){
            coins.remove(index);
        }
    }
    void create_chests(){
        for (int i =0;i<10;i++){
            int index = rand.nextInt(platform.size());
            while (platform.get(index).getWidth() < 100 || index == 0 || platform.get(index).getObjects()>2) index = rand.nextInt(platform.size());

            float distance = 40;
            if (platform.get(index).getObjects() > 0) distance = platform.get(index).getWidth()-50;
            CoinChest chest1 = new CoinChest(platform.get(index).getPos_x() + distance,platform.get(index).getPos_y() -35,rand.nextInt(100),40,60,MainAnchorPane);
            platform.get(index).setObjects(platform.get(index).getObjects() + 1);
            coinChests.add(chest1);
            platform.get(index).setObjects(platform.get(index).getObjects() + 1);
            gameobjectlist.add(chest1);
        }
    }
    void orcsMotion(){
        for (int i = 0;i<6;i++) {
            int seed = orc.size();
            if (seed <= 0) seed = 1;
            int random = rand.nextInt(seed);
            if (random < orc.size())
            orc.get(random).motion(platform.get(0));
        }
    }

    void bossDeath(){
        if (boss_generate){
            if (!boss.platfrom_collision(bossPlatform.getNode()))
            {
                boss.free_fall();
                System.out.println("Boss free falll");
            }

        }
    }
    void endgame() {
        try {
            System.out.println("Sayonara");
            if(time!=null){
                time.pause();
            }
            Parent root = FXMLLoader.load(getClass().getResource("Ending.fxml"));
            MainAnchorPane = (AnchorPane) root;
            gamePlayScene = new Scene(root);
            scoreText = new Text(Integer.toString(getScore()));
            scoreText.setX(350);
            scoreText.setY(183);
            scoreText.setFont(Font.font("Arial Black"));
            MainAnchorPane.getChildren().add(scoreText);
            cointext = new Text(Integer.toString(player.getCurr_coins()));
            cointext.setX(350);
            cointext.setY(223);
            cointext.setFont(Font.font("Arial Black"));
            MainAnchorPane.getChildren().add(cointext);
            myStage.setScene(gamePlayScene);
            myStage.show();
        }
        catch ( Exception f){
            f.printStackTrace();
        }
    }

    int platform_check(){
//        System.out.println(platform_contact);
        boolean anycontact = false;
        int index = -1;
        for (Platform plat : platform){
            Node temp = plat.getNode();
            if(hero.platfrom_collision(temp)) {
                platform_contact = true;
                anycontact = true;
                index = platform.indexOf(plat);
            }
        }





        if (anycontact == false) platform_contact = false;

        if (!anycontact) return index;
        return index;
    }
    int smallPlatformCheck(){
        int index = -1;
        boolean anycontact = false;
        for (Platform plat : small_platforms){
            Node temp = plat.getNode();
            if(hero.platfrom_collision(temp)) {
                platform_contact = true;
                anycontact = true;
                index = small_platforms.indexOf(plat);
            }
        }
        if (anycontact == false) platform_contact = false;

        if (!anycontact) return index;
        return index;
    }

    void create_weapon_chests(){
        for (int i =0;i<6;i++){
            int index = rand.nextInt(platform.size());
            Weapon w;
            while (platform.get(index).getWidth() < 140 || index == 0  || platform.get(index).getObjects()>2) index = rand.nextInt(platform.size());
            if (index %2 ==0) w = new Lance ((float)player.getHero().getGladiator().getX() + 35,(float)player.getHero().getGladiator().getY() + 10, MainAnchorPane,false);
            else w = new Sword ((float)player.getHero().getGladiator().getX() + 35,(float)player.getHero().getGladiator().getY() + 10, MainAnchorPane,false);
            WeaponChest chest1 = new WeaponChest(platform.get(index).getPos_x() + 40,platform.get(index).getPos_y() -28,w,MainAnchorPane);
            weaponList.add(chest1);
            platform.get(index).setObjects(platform.get(index).getObjects() + 1);
            gameobjectlist.add(chest1);
        }
    }

    void decorate(){

        for(Platform p : platform){
            Birch b  = new Birch(p.getPos_x(),p.getPos_y()-p.getHeight()-30,p.getHeight()+30,40,MainAnchorPane);
            decorationsList.add(b);
            gameobjectlist.add(b);
        }
    }
    public void create() {
        /*
        to gauge if this works or not do this
        1.comment out the creation/method of everything related to orc1, check out this in the keyevent of play method too
        2.run
        3. The platforms should work perfectly fine with plat2 moving up and down
        */
        scoreText = new Text("0");
        scoreText.setX(50);
        scoreText.setY(50);
        scoreText.setFont(Font.font("Arial Black"));
        MainAnchorPane.getChildren().add(scoreText);
        cointext = new Text("0");
        cointext.setX(650);
        cointext.setY(50);
        cointext.setFont(Font.font("Arial Black"));
        MainAnchorPane.getChildren().add(cointext);
        Button lanceButton = new Button();
        lanceButton.setText("Enable spear");
        lanceButton.setLayoutX(56);
        lanceButton.setLayoutY(320);
        lanceButton.setMaxWidth(40);
        lanceButton.setMinHeight(65);
        lanceButton.setFont(Font.font("Arial Black"));
        lanceButton.setOpacity(0);
        Button swordButton = new Button();
        swordButton.setText("Enable spear");
        swordButton.setLayoutX(16);
        swordButton.setLayoutY(320);
        swordButton.setMaxWidth(40);
        swordButton.setMinHeight(65);
        swordButton.setFont(Font.font("Arial Black"));
        swordButton.setOpacity(0);
        MainAnchorPane.getChildren().add(lanceButton);
        MainAnchorPane.getChildren().add(swordButton);
        lanceButton.setOnAction(e->{
            System.out.println("Pressed ");
            if (player.getHero().getHelmet().getWeaponlist().get(0).getQuantity() > 0){
                player.getHero().getHelmet().getWeaponlist().get(0).setActive_status(true);
                player.getHero().getHelmet().getWeaponlist().get(1).setActive_status(false);
            }
        });
        swordButton.setOnAction(e->{
            System.out.println("Sword button Pressed ");
            if (player.getHero().getHelmet().getWeaponlist().get(1).getQuantity() > 0){
                player.getHero().getHelmet().getWeaponlist().get(1).setActive_status(true);
                player.getHero().getHelmet().getWeaponlist().get(0).setActive_status(false);
            }
        });
        clouds cloud1 = new clouds(200,50,50,100,MainAnchorPane);
        clouds cloud2 = new clouds(600,50,70,90,MainAnchorPane);
        gameobjectlist.add(cloud1);gameobjectlist.add(cloud2);


        Player player1 = new Player(130,130,0,8,30,40,MainAnchorPane,0);
        player = player1;
        Lance lance = new Lance((float)player.getHero().getGladiator().getX() + 35,(float)player.getHero().getGladiator().getY() + 60, MainAnchorPane,true);
        Sword sword = new Sword((float)player.getHero().getGladiator().getX() + 35,(float)player.getHero().getGladiator().getY(),MainAnchorPane,false);

        player.getHero().getHelmet().getWeaponlist().add(lance);
        player.getHero().getHelmet().getWeaponlist().add(sword);
        System.out.println("Create 50% ");

        score = 0;
        //hero = new Hero(130,110,0,8,40,40,MainAnchorPane,0);
        for (int i =0;i<13;i++){
            if (i!=10) {
                Platform temp1 = new Platform(893 * i + 100, 220, 50, rand.nextInt(200) + 40, MainAnchorPane, 0);

                Platform temp3 = new Platform(893 * i + 500, 220, 50, rand.nextInt(200) + 50, MainAnchorPane, 2);
                Platform temp4 = new Platform(893 * i + 750, 220, 50, rand.nextInt(150) + 40, MainAnchorPane, 0);
                platform.add(temp1);
                platform.add(temp3);
                total_platforms.add(temp1);
                total_platforms.add(temp3);
                total_platforms.add(temp4);

                platform.add(temp4);
                gameobjectlist.add(temp1);

                gameobjectlist.add(temp3);
                gameobjectlist.add(temp4);
            }
        }
        bossPlatform = new Platform(9100, 290, 50, 600, MainAnchorPane, 0);
        gameobjectlist.add(bossPlatform);
        platform.add(bossPlatform);
        total_platforms.add(bossPlatform);
        hero = player1.getHero();
        gameobjectlist.add(hero);
        create_chests();
        create_orcs();
        create_coins();
        create_weapon_chests();
        generateSmallIslands();
        create_tnt();
        decorate();
        hero.getGladiator().toFront();
      System.out.println("Create success");
    }

//            platform.get(0).motion();
//            platform.get(1).motion();
//            platform.get(2).motion();


    void generateSmallIslands(){
        for (int i = 0;i<9;i++){
            Platform small = new Platform(893*i +350, 220, 50,rand.nextInt(100)+50,MainAnchorPane,2);
            small_platforms.add(small);
            gameobjectlist.add(small);
            total_platforms.add(small);
        }
        decorateSmallTowers();
    }
    void moveSmall(){

        for (Platform p:small_platforms){
            p.getDecoration().motion(p.getY_speed());
            p.motion();
        }
    }
    void decorateSmallTowers(){
        for (int i = 0;i<3;i++){
            tower t = new tower(small_platforms.get(i*3).getPos_x()+small_platforms.get(i*3).getWidth()-50,small_platforms.get(i*3).getPos_y()-100,100,40,MainAnchorPane);
            bush b = new bush(small_platforms.get(3*i+1).getPos_x() + small_platforms.get(3*i+1).getWidth() -30,small_platforms.get(3*i+1).getPos_y() -35,35,20,MainAnchorPane);
            tree tr = new tree(small_platforms.get(i*3 +2).getPos_x()+small_platforms.get(i*3+2).getWidth()-40,small_platforms.get(i*3 +2).getPos_y()-90,90,30,MainAnchorPane);
            small_platforms.get(3*i+1).setDecoration(b);
            small_platforms.get(3*i+2).setDecoration(tr);
            small_platforms.get(3*i).setDecoration(t);
            gameobjectlist.add(t);
            gameobjectlist.add(b);
            gameobjectlist.add(tr);
            decorationsList.add(t);
            decorationsList.add(b);
            decorationsList.add(tr);


        }
    }

    void playerDeath(){
        Node node = player.getHero().getGladiator();
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), node);
        tt.setFromY(node.getTranslateY());
        tt.setToY(node.getTranslateY() - 60);
        tt.play();


        tt.setOnFinished(e->{

            node.setTranslateY(node.getTranslateY() - 60);
            RotateTransition rr = new RotateTransition(Duration.millis(100),node);
            rr.setByAngle(270);
            rr.play();
            rr.setOnFinished(f->{

                hero.freefall();

            });
        });


}

void bossManage(){
    boss = new Boss(850,110,500,0,5,150,180,MainAnchorPane,1);
    set_boss_generate(true);
}

    void scoreIncrement(){
        int current_score = getScore();
        current_score++;
        setScore(current_score );
        scoreText.setText(Integer.toString(current_score));
        if (current_score  >= 107 && get_boss_generated() == false ){
            bossManage();
        }
        if (current_score >= 120) endgame();


    }
    @FXML
    void play(ActionEvent e){
        this.rand = new Random();
        platform_contact = true;
        set_boss_generate(false);
        System.out.println("Play here");
        if(time==null){
            MainAnchorPane = null;
        }
        if(time!=null){
            time.play();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = null;
        try {
            root = loader.load();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        MainAnchorPane = (AnchorPane) root;
        create();

//        Text t = new Text();
//        score = Integer.toString(this.score);
//        t.setText(score);
//        t.setFont(Font.font ("Verdana", 70));
//        t.setFill(Color.WHITE);
//        t.setX(410);
//        t.setY(130);
//        mainPane.getChildren().add(t);


        myStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        gamePlayScene = new Scene(root,793,373);
        myStage.setScene(gamePlayScene);
        myStage.show();

        KeyFrame Frame = new KeyFrame(Duration.millis(50), e1->{
           moveSmall();
           orcsMotion();
//            platform.get(0).motion();
//            platform.get(1).motion();
//            platform.get(2).motion();
//            orc.get(0).motion(platform.get(orc.get(0).getPlatform_info()));
//            if (platform_check() != -1) hero.motion(platform.get(platform_check()),platform_contact);
//            else {
//                if (smallPlatformCheck() != -1) hero.motion(small_platforms.get(smallPlatformCheck()),platform_contact);
//                else
//                hero.motion(platform.get(0), false);
//            }
            hero.jumping(total_platforms);
            coinCollision();
            tntCollision();
            death();
            playerHealthCheck();
            if (boss_generate){

                boss.motion(bossPlatform);
                bossDeath();
                boss.attack(hero);
            }


        });

        gamePlayScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case D : scoreIncrement();
                            move();

                        break;
                    case Z: fire();
                    break;

                }
            }
        });
        this.time = new Timeline(Frame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }
    @FXML
    void quit(){
        System.exit(1);
    }
    @FXML
    void pause(ActionEvent e) throws  IOException{
        try {
            System.out.println("Pause Menu");
            if(time!=null){
                time.pause();
            }
            Parent root = FXMLLoader.load(getClass().getResource("Pause Menu.fxml"));
            myStage = (Stage)((Node)e.getSource()).getScene().getWindow();
            gamePlayScene = new Scene(root);
            myStage.setScene(gamePlayScene);
            myStage.show();
        }
        catch ( Exception f){
            f.printStackTrace();
        }
    }
    @FXML
    void load(ActionEvent e) throws IOException {
        try {
            System.out.println("Loading");
            Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            myStage = (Stage)((Node)e.getSource()).getScene().getWindow();
            gamePlayScene = new Scene(root);
            myStage.setScene(gamePlayScene);
            myStage.show();
        }
        catch (Exception f){
            f.printStackTrace();
        }
    }
    @FXML
    void backtomenu(ActionEvent e) throws IOException{
        try {
            System.out.println("Back to screen");
            Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
            myStage = (Stage)((Node)e.getSource()).getScene().getWindow();
            gamePlayScene = new Scene(root);
            myStage.setScene(gamePlayScene);
            myStage.show();
        }
        catch (Exception f){
            f.printStackTrace();
        }
    }
    private void loadFile(String file,String playerFile,int gameNumber) throws IOException, ClassNotFoundException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            root = (Parent) loader.load();
            MainAnchorPane = (AnchorPane) root;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Scene gameplayscene1 = new Scene(root,793,373);
        ReGeneratePlayer regenPlayer = new ReGeneratePlayer();
        myStage.setScene(gameplayscene1);
        gamePlayScene=gameplayscene1;
        player = regenPlayer.getPlayer(playerFile);
        player.setCurr_coins(player.getCurr_coins());
        //setupScene(gamePlayScene,myStage,currentPlayer);
        loadtheGame(file);
    }
    public void loadtheGame(String filename) throws IOException, ClassNotFoundException {
        scoreText.setText(Integer.toString(player.getCurr_coins()));
        ReGenerate regenObs = new ReGenerate();
        System.out.println(filename);
        gameobjectlist = regenObs.regenerateGameObjects(filename, MainAnchorPane);
        for(GameObjects g:gameobjectlist){
            if(g instanceof Hero)hero=(Hero)g;
            //g.display(MainAnchorPane);
        }
        gameobjectlist=new ArrayList<>();
    }
    public void Serialize(String fileName) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(hero);
            for (GameObjects gameObject : gameobjectlist) {
                out.writeObject(gameObject);
            }
        }finally {
            assert out != null;
            out.close();
        }
    }

    void playerHealthCheck(){
        if (hero.getHealth() <= 0){
            playerDeath();
        }
    }

    public void SerializePlayer(String fileName) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            try {
                out.writeObject(player);
            }
            catch (NullPointerException e) {
                System.out.println("PLAYER NOT INITIALIZED");
            }
        }finally {
            assert out != null;
            out.close();
        }
    }
}