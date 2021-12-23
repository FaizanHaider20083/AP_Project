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

public class Game_Controller{
    private Boss boss;
    private boolean boss_generate;
    private boolean platform_contact;
    private Random rand;
    private Hero hero;
    private Parent root;
    private Player player;
    @FXML
    private AnchorPane MainAnchorPane;
    private boolean isMoving;
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
    private ArrayList <WeaponChest> weaponList  = new ArrayList<>(); ;
    ArrayList <WeaponChest> getWeaponList(){return this.weaponList;}

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
        for (Coins coin : coins){
            Node temp = coin.getNode();
            if(hero.collision(temp)){
                player.setCurr_coins((player.getCurr_coins() + 10));
                cointext.setText(Integer.toString(player.getCurr_coins()));
                temp.setOpacity(0);
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
                if (chest.getWeapon().getClass().equals(player.getHero().getHelmet().getWeaponlist().get(0).getClass())){
                    player.getHero().getHelmet().getWeaponlist().get(0).setActive_status(true);
                    player.getHero().getHelmet().getWeaponlist().get(1).setActive_status(false);

                    player.getHero().getHelmet().getWeaponlist().get(0).setQuantity(player.getHero().getHelmet().getWeaponlist().get(0).getQuantity() + 1);
                    player.getHero().getHelmet().getWeaponlist().get(0).display(MainAnchorPane);
                    player.getHero().getHelmet().getWeaponlist().get(1).display(MainAnchorPane);
                    player.getHero().getHelmet().getWeaponlist().get(0).setRange(player.getHero().getHelmet().getWeaponlist().get(0).getRange() + 2);
                }
                else if (chest.getWeapon().getClass().equals(player.getHero().getHelmet().getWeaponlist().get(1).getClass())){
                    player.getHero().getHelmet().getWeaponlist().get(1).setActive_status(true);
                    player.getHero().getHelmet().getWeaponlist().get(0).setActive_status(false);

                    player.getHero().getHelmet().getWeaponlist().get(1).setQuantity(player.getHero().getHelmet().getWeaponlist().get(1).getQuantity() + 1);
                    player.getHero().getHelmet().getWeaponlist().get(1).display(MainAnchorPane);
                    player.getHero().getHelmet().getWeaponlist().get(0).display(MainAnchorPane);
                    player.getHero().getHelmet().getWeaponlist().get(1).setRange(player.getHero().getHelmet().getWeaponlist().get(1).getRange() + 0.1);

                }
                else {
                    System.out.println("Elseeeeee " +chest.getWeapon().getClass());
                }
            }
            temp.setTranslateX(temp.getTranslateX() -80);


        }
        for (Green_Orcs orc:orc){
            Node temp = orc.getNode();
            if (hero.collision(temp)) System.out.println("got orc");
            temp.setTranslateX(temp.getTranslateX() -80);

        }
        if (boss_generate){
        Node temp = boss.getNode();
        if(hero.collision(temp)){
            boss.setHealth(boss.getHealth() -50);
            if(boss.getHealth() <= 0) temp.setOpacity(0);
            temp.setTranslateX(temp.getTranslateX()+100);
        }
        temp.setTranslateX(temp.getTranslateX() -80);}
        //setIsMoving(!getIsMoving());
    }
    void fire(){

        for (Weapon w: this.player.getHero().getHelmet().getWeaponlist()){
            if (w.getActive_status()){
                w.fire(orc,boss_generate,boss);
            }
        }
    }
    void create_coins(){
        for (int i =0;i<10;i++){
            int index = rand.nextInt(platform.size() );
            while (index != 0) index = rand.nextInt(platform.size() );
            Coins coin = new Coins(platform.get(index).getPos_x() + 40,platform.get(index).getPos_y()-40,20,20,MainAnchorPane);
            coins.add(coin);
        }
    }
    void create_orcs(){
        for (int i =0;i<10;i++){
            int index = rand.nextInt(platform.size() );
            while (platform.get(index).getWidth() < 80 && index != 0) index = rand.nextInt(platform.size());
            Green_Orcs temp  = new Green_Orcs(platform.get(index).getPos_x() + 40, platform.get(index).getPos_y() -40, 15, 0, 6, 40, 40, MainAnchorPane, 1);
            orc.add(temp);
        }
    }
    void create_chests(){
        for (int i =0;i<10;i++){
            int index = rand.nextInt(platform.size());
            while (platform.get(index).getWidth() < 100 && index != 0) index = rand.nextInt(platform.size());
            CoinChest chest1 = new CoinChest(platform.get(index).getPos_x() + 40,platform.get(index).getPos_y() -40,rand.nextInt(100),40,60,MainAnchorPane);
            coinChests.add(chest1);
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

    void platform_check(){
//        System.out.println(platform_contact);
        boolean anycontact = false;
        for (Platform plat : platform){

            Node temp = plat.getNode();
            if(hero.platfrom_collision(temp)) {
                platform_contact = true;
                anycontact = true;
            }



        }
        if (anycontact == false) platform_contact = false;
    }
    void create_weapon_chests(){
        for (int i =0;i<10;i++){
            int index = rand.nextInt(platform.size());
            Weapon w;
            while (platform.get(index).getWidth() < 100 && index != 0) index = rand.nextInt(platform.size());
            if (index %2 ==0) w = new Lance ((float)player.getHero().getGladiator().getX() + 35,(float)player.getHero().getGladiator().getY() + 20, MainAnchorPane,false);
            else w = new Sword ((float)player.getHero().getGladiator().getX() + 35,(float)player.getHero().getGladiator().getY() + 20, MainAnchorPane,false);
            WeaponChest chest1 = new WeaponChest(platform.get(index).getPos_x() + 40,platform.get(index).getPos_y() -40,w,MainAnchorPane);
            weaponList.add(chest1);
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


        Player player1 = new Player(130,130,0,8,30,40,MainAnchorPane,0);
        player = player1;
        Lance lance = new Lance((float)player.getHero().getGladiator().getX() + 35,(float)player.getHero().getGladiator().getY() + 20, MainAnchorPane,false);

        Sword sword = new Sword((float)player.getHero().getGladiator().getX() + 35,(float)player.getHero().getGladiator().getY(),MainAnchorPane,true);

        player.getHero().getHelmet().getWeaponlist().add(lance);
        player.getHero().getHelmet().getWeaponlist().add(sword);
        System.out.println("Create 50% ");
        Green_Orcs orc1  = new Green_Orcs(400, 160, 15, 0, 6, 40, 40, MainAnchorPane, 1);
        TNT TNT1 = new TNT(475,160,0,0,40,40,MainAnchorPane,1);

        score = 0;

//

        //hero = new Hero(130,110,0,8,40,40,MainAnchorPane,0);
        for (int i =0;i<13;i++){
            Platform temp1 = new Platform(893*i +100,170,50, rand.nextInt(200)+40, MainAnchorPane,0);
            Platform temp2 = new Platform(893*i +350, 170, 50,rand.nextInt(200)+50,MainAnchorPane,0);
            Platform temp3 = new Platform(893*i +600,170,50, rand.nextInt(100)+50, MainAnchorPane,2);
            Platform temp4 = new Platform(893*i +750,170,50, rand.nextInt(100)+40, MainAnchorPane,0);
            platform.add(temp1);
            platform.add(temp3);
            platform.add(temp2);
            platform.add(temp4);
        }
        hero = player1.getHero();
        create_chests();
        create_orcs();
        create_coins();
        create_weapon_chests();



        orc.add(orc1);
        tnt.add(TNT1);


      System.out.println("Create success");
    }

//            platform.get(0).motion();
//            platform.get(1).motion();
//            platform.get(2).motion();



    void scoreIncrement(){
        int current_score = getScore();
        current_score++;
        setScore(current_score );
        scoreText.setText(Integer.toString(current_score));
        if (current_score  >= 10 && get_boss_generated() == false ){
             boss = new Boss(650,20,500,0,0,150,180,MainAnchorPane,1);
            set_boss_generate(true);
        }
        if (current_score >= 125) endgame();


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
//            platform.get(0).motion();
//            platform.get(1).motion();
//            platform.get(2).motion();
//            orc.get(0).motion(platform.get(orc.get(0).getPlatform_info()));
            platform_check();
            hero.motion(platform.get(hero.getPlatform_info()),platform_contact);
            death();


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
}