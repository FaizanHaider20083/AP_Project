package Will_Hero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Decorations extends GameObjects{
    private transient AnchorPane pane;
    private float height;
    private float width;
    private String path;
    private float y_speed;
    void setY_speed(float y_speed){this.y_speed = y_speed;}
    float getY_speed(){return this.y_speed;}
    void setPath(String path){
        this.path = path;
    }
    String getPath(){return  this.path;}
    float getHeight(){return this.height;}
    float getWidth(){return this.width;}
    void setHeight(float Height){this.height = Height;}
    void setWidth(float width){this.width = width;}

    Decorations(float x, float y,String path, float height, float width){
        super(x,y);
        //this.pane = pane;
        setHeight(height);
        setWidth(width);
        setPath(path);
        //display(pane);
    }
    public void display(AnchorPane pane){
        this.pane = pane;
        Image image = new Image(this.getPath());
        ImageView node = new ImageView(image);
        node.setX(super.getPos_x());
        node.setY(super.getPos_y());
        node.setFitHeight(this.getHeight());
        node.setFitWidth((this.getWidth()));
        pane.getChildren().add(node);
        this.setNode(node);
    }

    public void motion(float speed){
        setY_speed(speed);
        if(this.getY_speed()!=0){
            getNode().setY(getNode().getY()-this.getY_speed());
            if(getNode().getY()<=this.getPos_y() - 75 || getNode().getY()>= this.getPos_y() + 75){
                float y_speed2 = this.getY_speed();
                this.setY_speed(-y_speed2);
            }
        }
    }

}
class Birch extends Decorations{
    Birch(float x,float y, float height, float width,AnchorPane pane){

        super(x, y, "assets/birch.png",height,width);
    }
    public void display(AnchorPane pane){
        super.display(pane);
    }
}
class clouds extends Decorations{
    clouds(float x,float y, float height, float width,AnchorPane pane){
        super(x, y, "assets/clouds.png",height,width);
    }
    public void display(AnchorPane pane){
        super.display(pane);
    }
}

class tower extends Decorations{
    tower(float x,float y, float height, float width,AnchorPane pane){
        super(x, y, "assets/tower.png",height,width);
    }
    public void display(AnchorPane pane){
        super.display(pane);
    }

}

class bush extends Decorations{
    bush(float x,float y, float height, float width,AnchorPane pane){
        super(x, y, "assets/bush.png",height,width);
    }
    public void display(AnchorPane pane){
        super.display(pane);
    }
}

class tree extends Decorations{
    tree(float x,float y, float height, float width,AnchorPane pane){
        super(x, y, "assets/tree.png",height,width);
    }
    public void display(AnchorPane pane){
        super.display(pane);
    }
}

