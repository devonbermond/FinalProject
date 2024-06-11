import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Bird{

    private int boardWidth = 640;
    private int boardHeight = 640;

    private int birdX = boardWidth/8;
    private int birdY = boardHeight/2;
    private int birdWidth = 34;
    private int birdHeight = 24;

    Image img;

    public Bird(Image img){
        this.img = img;
    }

    public int getX() {
        return birdX;
    }

    public int getY(){
        return birdY;
    }

    public int getWidth(){
        return birdWidth;
    }

    public int getHeight(){
        return birdHeight;
    }

    public void setY(int i){
        birdY+=i;
        birdY = Math.max(birdY, 0);
    }
    public void resetY(){
        birdY = boardHeight/2;
    }
}
