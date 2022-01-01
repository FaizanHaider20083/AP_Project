package Will_Hero;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.*;
class Helmet{
    private ArrayList<Weapon> weaponlist;
    public ArrayList<Weapon> getWeaponlist() { return weaponlist; }
    Helmet(){
        this.weaponlist = new ArrayList<>();
    }
}
abstract class Weapon extends GameObjects{
    private int dps;
    private int level;
    private boolean active_status;
    private double range;
    private AnchorPane anchor;
    private String path ;
    private int height ;
    private  int width;
    private  int quantity;

    Weapon(float x, float y, int dps, int level, boolean active_status, double range) {
        super(x, y);
        this.dps = dps;
        this.level = level;
        this.active_status = active_status;
        this.range = range;
        this.quantity = 0;
        this.setNode(new ImageView());
    }
    abstract public void fire(ArrayList <Green_Orcs> orc,boolean boss_generate, Boss boss, Hero hero);
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public AnchorPane getAnchor() { return anchor; }
    public String getPath() { return path; }
    public void setPath(String path){this.path = path;}
    public void setAnchor(AnchorPane anchor){this.anchor = anchor ;}
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}
    public void setActive_status(boolean active_status){this.active_status = active_status;}
    public boolean getActive_status(){return  this.active_status;}
    double getRange(){return this.range;}
    void setRange(double range){this.range = range;}
    void setQuantity(int quantity){this.quantity = quantity;}
    int getQuantity(){return this.quantity;}

}
class Lance extends Weapon{
    Lance(float x, float y,AnchorPane pane,boolean activeStatus) {
        super(x, y,5 ,1 , true,10 );
        this.setActive_status(activeStatus);
       setAnchor(pane);
       display(pane);
        this.setPath("assets/lance.png");
        setQuantity(0);
    }
    @Override
    public void fire(ArrayList <Green_Orcs> orcs,boolean boss_generate, Boss boss, Hero hero){
        System.out.println("Lance fire");
        Image fire = new Image(this.getPath());
        ImageView projectile = new ImageView(fire);
        projectile.setX(160);

        projectile.setY(hero.getGladiator().getY());
        projectile.setFitHeight(15);
        projectile.setFitWidth(40);
        this.getAnchor().getChildren().add(projectile);
        TranslateTransition tt = new TranslateTransition(Duration.millis(400),projectile);
        tt.setFromX(0);
        tt.setToX(20*this.getRange());
        tt.play();
        int removal = -1;
        for (Green_Orcs g: orcs){
            boolean collision = g.getNode().getBoundsInParent().intersects(projectile.getBoundsInParent().getMinX(),projectile.getBoundsInParent().getMinY(),projectile.getBoundsInParent().getMinZ(),this.getRange()*20,this.getHeight(),0.0);
            if (collision){
                g.getNode().setOpacity(0);
                removal = orcs.indexOf(g);
//                System.out.println("Came here " + orcs.indexOf(g));
//                System.out.println("Orc" + g.getNode().getBoundsInParent());
//                System.out.println(projectile.getBoundsInParent());
//                System.out.println("Came here");
            }
            else{
//                System.out.println("didnt");
//                System.out.println("Orc" + g.getNode().getBoundsInParent());
//                System.out.println(projectile.getBoundsInParent());
            }


        }
        if (removal != -1)
        orcs.remove(removal);
        tt.setOnFinished(e->{projectile.setOpacity(0);});
        if (boss_generate){
            boolean collision = boss.getNode().getBoundsInParent().intersects(projectile.getBoundsInParent().getMinX(),projectile.getBoundsInParent().getMinY(),projectile.getBoundsInParent().getMinZ(),this.getRange()*20,projectile.getBoundsInParent().getHeight(),0.0);
            if (collision){
                boss.setHealth(boss.getHealth() - 100);
                if (boss.getHealth() <= 0)boss.getNode().setOpacity(0);
                System.out.println("Boss health " + boss.getHealth());
            }
            else{
                System.out.println("didnt " + this.getHeight());
                System.out.println("Orc" + boss.getNode().getBoundsInParent());
                System.out.println(projectile.getBoundsInParent());
            }
        }



    }
    public void display(AnchorPane pane){

        this.setPath("assets/lance.png");
        Image image = new Image(this.getPath());
        ImageView node = getNode();
        node = new ImageView(image);
        node.setX(super.getPos_x()-15);
        node.setY(super.getPos_y());
        node.setRotate(-30);
        System.out.println(this.getHeight() + " " + this.getWidth());
        node.setFitHeight(15);
        node.setFitWidth(40);

        this.getAnchor().getChildren().add(node);
        setNode(node);

        this.getNode().setOpacity(0);


    }
}
class Sword extends Weapon{
    Sword(float x, float y,AnchorPane pane,boolean activeStatus) {
        super(x, y,5 ,1 , true,1 );
        this.setActive_status(activeStatus);
        this.setAnchor(pane);
        this.display(pane);
        this.setPath("assets/sword2.png");
        setQuantity(1);
    }
    public void display(AnchorPane pane){

            this.setPath("assets/sword2.png");
            Image image = new Image(this.getPath());
            ImageView node = new ImageView(image);
            node.setX(super.getPos_x()-5);
            node.setY(super.getPos_y());
            node.setRotate(30);
            System.out.println(this.getHeight() + " " + this.getWidth());
            node.setFitHeight(25);
            node.setFitWidth(10);

            this.getAnchor().getChildren().add(node);
            setNode(node);

            this.getNode().setOpacity(0);


    }
    @Override
    public void fire(ArrayList <Green_Orcs> orcs,boolean boss_generate, Boss boss,Hero hero){
        System.out.println("Sword fire");
        Circle cir = new Circle();
        cir.setCenterX(this.getPos_x());
        cir.setCenterY(hero.getGladiator().getY());
        cir.setRadius(1);
        RadialGradient rg = new RadialGradient(360,0.4,0.5,0.5,0.5,true, CycleMethod.NO_CYCLE,new Stop(.5f,Color.GOLDENROD),new Stop(0,Color.color(1,1,1)));
        cir.setFill(rg);



        this.getAnchor().getChildren().add(cir);
        ScaleTransition st = new ScaleTransition(Duration.millis(400),cir);
        st.setByX(32f);
        st.setByY(32f);
        st.play();

        st.setOnFinished(e->{cir.setOpacity(0);});
        int removal = -1;
        for (Green_Orcs g: orcs){
            boolean collision = g.getNode().getBoundsInParent().intersects(cir.getBoundsInParent().getMinX(),cir.getBoundsInParent().getMinY(),cir.getBoundsInParent().getMinZ(),this.getRange()*20,cir.getBoundsInParent().getHeight(),0.0);
            if (collision){
                g.getNode().setOpacity(0);
                removal = orcs.indexOf(g);
//                System.out.println("Came here " + orcs.indexOf(g));
//                System.out.println("Orc" + g.getNode().getBoundsInParent());
//                System.out.println(cir.getBoundsInParent());
//                System.out.println("Came here");
            }
            else{
//                System.out.println("didnt " + this.getHeight());
//                System.out.println("Orc" + g.getNode().getBoundsInParent());
//                System.out.println(cir.getBoundsInParent());
            }


        }
        if (removal != -1)
            orcs.remove(removal);
        if (boss_generate){
            boolean collision = boss.getNode().getBoundsInParent().intersects(
                    cir.getBoundsInParent().getMinX(),
                    cir.getBoundsInParent().getMinY(),
                    cir.getBoundsInParent().getMinZ(),
                            this.getRange()*10,
                    cir.getBoundsInParent().getHeight(),
                    cir.getBoundsInParent().getWidth()

            );
            if (collision){
                boss.setHealth(boss.getHealth() - 250);
                if (boss.getHealth() <= 0)boss.getNode().setOpacity(0);
                System.out.println("Boss health " + boss.getHealth());
            }
            else{
                System.out.println("didnt " + this.getHeight());
                System.out.println("Orc" + boss.getNode().getBoundsInParent());
                System.out.println(cir.getBoundsInParent());
            }
        }
        else System.out.println("Boss generate : " + boss_generate);
    }
}
public class Hero extends GameObjects{
    private float x_speed;
    private float y_speed;
    private Helmet helmet;
    private boolean boss_status;
    private int health;
    private float width;
    private float height;
    private AnchorPane anchor;
    private ImageView Gladiator;
    private String path = "assets/gladiator.png";
    private int platform_info;
    void setHealth(int health){this.health = health;}
    Hero(float x, float y, float x_speed, float y_speed, float width, float height, AnchorPane pane, int platform_info) {
        super(x, y);
        this.x_speed = x_speed;
        this.y_speed= y_speed;
        this.health = 200;
        this.helmet = new Helmet();
        this.boss_status = false;
        this.height = height;
        this.width = width;
        this.anchor = pane;
        this.platform_info = platform_info;
        display(pane);
    }
    public void display(AnchorPane pane){
        Image image = new Image(getPath());
        this.Gladiator = new ImageView(image);
        Gladiator.setX(super.getPos_x());
        Gladiator.setY(super.getPos_y());
        Gladiator.setFitHeight(this.getHeight());
        Gladiator.setFitWidth((this.getWidth()));
        this.getAnchor().getChildren().add(Gladiator);
    }

    public void motion(Platform plat,boolean platformContact){

//          Y_speed initially is 8.
                Gladiator.setY(Gladiator.getY() - this.getY_speed());
                for (Weapon w: this.getHelmet().getWeaponlist()){
                    if (w.getActive_status()){
//                        System.out.println("inside " + w.getNode().getY());
                        w.getNode().setY(w.getNode().getY() - this.getY_speed());
                    }
                }

                if ((Gladiator.getY() >= plat.getNode().getY() - 40 || Gladiator.getY() <= plat.getNode().getY() - 100) ) {
                    float y_speed2 = this.getY_speed();

                    if ((this.getY_speed() <0 && (platformContact))|| (this.getY_speed()>0 && (!platformContact))){
                    this.setY_speed(-y_speed2);

                    }

                }

//                else {
//                    this.setY_speed(-this.getY_speed());
//                    System.out.println("other");
//                }
    }








    boolean check_x(Bounds bounds){
        if (this.getGladiator().getX()>=bounds.getMinX() && this.getGladiator().getX()<= bounds.getMaxX()) return true;
        return false;
    }
    boolean check_y(Bounds bounds){
        if ((this.getGladiator().getY() + this.getHeight() >= bounds.getMinY() -8) && (this.getGladiator().getY() + this.getHeight() <= bounds.getMinY() +8)) return true;
        return false;
    }
    boolean platfrom_collision(Node obj){
        Bounds boundsInscreen = obj.localToParent(obj.getBoundsInLocal());
        return boundsInscreen.intersects(this.getGladiator().getBoundsInParent());
//        if(check_y(boundsInscreen) && (check_x(boundsInscreen))){

//            System.out.println("gladiator true " + this.getGladiator().getY() + " " + boundsInscreen.getMinY() + " " + boundsInscreen.getMaxY());

//            return true;
//        }
//        System.out.println("Else started");
//        System.out.println("gladiator Y " + this.getGladiator().getY());
//        System.out.println("gladiator X " + this.getGladiator().getX());
//        System.out.println(boundsInscreen.getMinY());System.out.println(boundsInscreen.getMaxY());
//        System.out.println("Else finished");
//        return false;
    }

    void freefall(){
//        while (this.getNode().getY() < 350){
//            this.getNode().setY(this.getNode().getY() + 8);
//        }
//        works but just shows result not the transition animation

        TranslateTransition tt = new TranslateTransition(Duration.millis(2000),this.getGladiator());
        tt.setFromY(-20);
        System.out.println(this.getGladiator().getY());
        tt.setToY(350);
        tt.play();
        tt.setOnFinished(e->{
            this.getGladiator().setY(350);
        });
        // this.getNode().setOpacity(0);
    }
    boolean collision(Node obj){
        Bounds boundsInscreen = obj.getBoundsInParent();//h-40 w-30
        //this.getPos_y() +this.getHeight()>= boundsInscreen.getMinY() && this.getPos_y() <= boundsInscreen.getMaxY()
//        if ((this.getPos_x() + this.getWidth() <= boundsInscreen.getMinX() && this.getPos_x()+ this.getWidth() +80>= boundsInscreen.getMinX() ) && ( (this.getPos_y() >= boundsInscreen.getMinY() && this.getPos_y()  <= boundsInscreen.getMaxY()) || (this.getPos_y() +this.getHeight() >= boundsInscreen.getMinY() && this.getPos_y() +this.getHeight() <= boundsInscreen.getMaxY()))){
//            System.out.println("Collision");
//
//            return  true;
//
//        }
//        else{

//            System.out.println("Else");
//            System.out.println(this.getPos_x());
//
//            System.out.println(boundsInscreen.getMaxX() + " x " + boundsInscreen.getCenterX() + " " + boundsInscreen.getMinX());
//            System.out.println("Else");
//            System.out.println(this.getPos_y());
//            System.out.println(boundsInscreen.getMaxY() + " y " + boundsInscreen.getCenterY() + " " + boundsInscreen.getMinY());
//            System.out.println("else finished");
//            return false;
//        }
        if( boundsInscreen.intersects(this.getGladiator().getBoundsInParent())) return true;
        else {
            if (this.getGladiator().getX() <= boundsInscreen.getMinX() && this.getGladiator().getX() +80 >= boundsInscreen.getMinX())
                if (this.getGladiator().getY() <= boundsInscreen.getMinY() && this.getGladiator().getY() +this.getHeight() >= boundsInscreen.getMinY())
                    return true;
                else if (boundsInscreen.getMaxY() - boundsInscreen.getMinY() > this.getHeight()) return  true;

            return false;
        }
    }
    public Helmet getHelmet() { return helmet; }
    public boolean isBoss_status() { return boss_status; }
    public int getHealth() { return health; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public AnchorPane getAnchor() { return anchor; }
    public ImageView getGladiator() { return Gladiator; }
    public String getPath() { return path; }
    public int getPlatform_info() { return platform_info; }
    public float getX_speed() { return x_speed; }
    public float getY_speed() { return y_speed; }
    public void setY_speed(float y_speed) { this.y_speed = y_speed; }

    public void jumping(ArrayList<Platform> platformList){
        Weapon w = this.getHelmet().getWeaponlist().get(0);
        for (int i =0;i<this.getHelmet().getWeaponlist().size();i++){
            if(this.getHelmet().getWeaponlist().get(i).getActive_status())
                w = this.getHelmet().getWeaponlist().get(i);
        }
        int contact = -1;
        for (Platform p:platformList) {
            Bounds platformBounds = p.getNode().getBoundsInParent();
            if (this.getGladiator().getX() + this.getWidth() >= platformBounds.getMinX() && this.getGladiator().getX() <= platformBounds.getMaxX()) {
                Gladiator.setY(Gladiator.getY() - this.getY_speed());
                w.getNode().setY(Gladiator.getY() - this.getY_speed());
                contact = 0;
                System.out.println("true");
                if (((this.getGladiator().getY() >= p.getNode().getY() - 40 && this.getGladiator().getY() <= p.getNode().getY() - 10 && this.getY_speed() <0)  || (this.getGladiator().getY() <= p.getNode().getY() - 100 && this.getGladiator().getY() <= p.getNode().getY() - 130 && this.getY_speed() >0))) {
                    float y_speed2 = this.getY_speed();
                    System.out.println("true ins");
                    System.out.println(this.getGladiator().getY() + " " + p.getNode().getY() + " " );

                    this.setY_speed(-y_speed2);

                }
                else System.out.println(this.getGladiator().getY() + " " + p.getNode().getY() + " else" );

            }
        }

        if (contact == -1)

        {    System.out.println("false");
            if (this.getY_speed() < 0) //increase y and make it fall down
            Gladiator.setY(Gladiator.getY() - this.getY_speed()/2);
            else Gladiator.setY(Gladiator.getY() + this.getY_speed()/2);
        }
    }


}