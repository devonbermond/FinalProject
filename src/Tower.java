import java.awt.*;

public class Tower {
    private int boardWidth = 640;
    private int boardHeight = 640;


    private int towerX = boardWidth;
    private int towerY= 0;
    private int towerWidth = 64;
    private int towerHeight = 512;


    Image img;
    private boolean passed = false;

    public Tower(Image img){
        this.img = img;
    }


    public int getX() {
        return towerX;
    }

    public int getY(){
        return towerY;
    }

    public int getWidth(){
        return towerWidth;
    }

    public int getHeight(){
        return towerHeight;
    }

    public void setX(int i){
        towerX+=i;
    }

    public boolean getPassed(){
        return passed;
    }

    public void setPassed(){
        passed = true;
    }
    public Image getImg(){
        return this.img;
    }

    public void setY(int i){
        towerY = i;
    }
}
