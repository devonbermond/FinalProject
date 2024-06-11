import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class BirdFlapping extends JPanel implements ActionListener, KeyListener {
    private int boardWidth = 640;
    private int boardHeight = 640;

    private int velocityY = 0;
    private int velocityX = -4;
    private int gravity = 1;
    private int randomPipeY;
    private int space;

    //change image in subclass
    private Image birdImg;
    private Image topTowerImg;
    private Image bottomTowerImg;
    private Image backgroundImg;


    Bird bird;
    Tower towerr;
    Tower towery;
    ArrayList<Tower> towers;
    Timer loop;
    Timer towerLoop;
    private boolean gameOver = false;
    private double score = 0;

    public BirdFlapping(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);
        backgroundImg = new ImageIcon(getClass().getResource("background.png")).getImage();
        topTowerImg = new ImageIcon(getClass().getResource("./toptower.png")).getImage();
        bottomTowerImg = new ImageIcon(getClass().getResource("./bottomtower.png")).getImage();


        //make image in sublcass
        birdImg = new ImageIcon(getClass().getResource("./pigeon.png")).getImage();

        //inheritance here
        bird = new Bird(birdImg);



        towers = new ArrayList<Tower>();

        towerLoop = new Timer(1500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                placePipes();
            }
        });

        towerLoop.start();

        loop = new Timer(1000/60, this);
        loop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g){
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

        g.drawImage(bird.img, bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight(), null);

        for(int i = 0; i< towers.size(); i++){
            Tower tower = towers.get(i);
            g.drawImage(tower.img, tower.getX(), tower.getY(), tower.getWidth(), tower.getHeight(), null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if(gameOver){
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);

        }
        else{
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move(){
        velocityY +=gravity;
        bird.setY(velocityY);

        for(int i = 0; i< towers.size(); i++){
            Tower tower = towers.get(i);
            tower.setX(velocityX);

            if(!tower.getPassed() && bird.getX() > tower.getX() + tower.getWidth()){
                tower.setPassed();
                score+=0.5;
            }
            if(collides(bird, tower)){
                gameOver = true;
            }
        }

        if (bird.getY() > boardHeight){
            gameOver = true;
        }

    }

    public boolean collides(Bird a, Tower b){
        return a.getX()<b.getX() + b.getWidth() &&
                a.getX() + a.getWidth()> b.getX() &&
                a.getY()<b.getY() + b.getHeight() &&
                a.getY() + a.getHeight()> b.getY();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver){
            towerLoop.stop();
            loop.stop();
        }
    }

    public void placePipes(){
        randomPipeY = (int) (0 - 512 / 4 - Math.random() * (512 / 2));
        space = boardHeight / 4;

        towerr = new Tower(topTowerImg);
        towerr.setY(randomPipeY);
        towers.add(towerr);

        towery = new Tower(bottomTowerImg);
        towery.setY(towerr.getY() + towerr.getHeight() + space);
        towers.add(towery);


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY= -9;
            if(gameOver){
                bird.resetY();
                velocityY = 0;
                towers.clear();
                score = 0;
                gameOver = false;
                loop.start();
                towerLoop.start();

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {    }
    @Override
    public void keyReleased(KeyEvent e) {}
}