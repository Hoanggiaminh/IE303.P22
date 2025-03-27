package LAB2.src;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;
    
    public class Bird extends JPanel {
        int x = 10, y = 320;
        int width = 34, height = 24;
        Image image;

        Bird(Image image){
            this.image = image;
            resetSettings();
        }

        public void resetSettings(){
            x = boardWidth / 2 - width / 2;
            y = boardHeight / 2 - height / 2;
        }

    }

    public class Pipe extends JPanel {
        int x = boardWidth, y = 0;
        int width = 64, height = 512;
        Image image;
        boolean isTheBirdPass = false;

        Pipe(Image image){
            this.image = image;
        }
    }

    Bird bird;
    ArrayList<Pipe> pipes = new ArrayList<Pipe>();

    int pipeVelocity = -4;
    int birdVelocity = 0;
    int gravity = 1;
    boolean isGameOver = false;
    double score = 0;
    
    Timer gameLoop;
    Timer placePipesLoop;

    public FlappyBird(){
        super();

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("images/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("images/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("images/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("images/bottompipe.png")).getImage();

        bird = new Bird(birdImage);

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        placePipesLoop = new Timer(1200, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesLoop.start();
    }

    public void resetSettings(){
        bird.resetSettings();
        pipes = new ArrayList<Pipe>();

        pipeVelocity = -4;
        birdVelocity = 0;
        gravity = 1;
        isGameOver = false;
        score = 0;

        gameLoop.start();
        placePipesLoop.start();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics){
        graphics.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        graphics.drawImage(bird.image, bird.x, bird.y, bird.width, bird.height, null);

        for(Pipe pipe : pipes){
            graphics.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        if(isGameOver){
            graphics.drawString("Game Over! Your score: " + String.valueOf((int)(score)), 50, 50);
        }
        else{
            graphics.drawString("Score: " + String.valueOf((int)(score)), 50, 50);
        }
    }

    public void moveBird(){
        birdVelocity += gravity;
        bird.y += birdVelocity;

        bird.y = Math.min(boardHeight - bird.height, bird.y);
        bird.y = Math.max(0, bird.y);

        if(bird.y + bird.height >= boardHeight){
            isGameOver = true;
        }

        if(bird.y == 0){
            isGameOver = true;
        }
    }

    public void placePipes(){
        Pipe topPipe = new Pipe(topPipeImage);
        int randomYPipe = (int)(topPipe.y - (topPipe.height/4) - Math.random() * (topPipe.height/2));
        topPipe.y = randomYPipe;
        pipes.add(topPipe);

        int spaceBetweenPipes = boardHeight / 4;

        Pipe bottomPipe = new Pipe(bottomPipeImage);
        bottomPipe.y = topPipe.y + topPipe.height + spaceBetweenPipes;
        pipes.add(bottomPipe);
    }

    public boolean hasColision(Bird bird, Pipe pipe){
        return  bird.x < pipe.x + pipe.width && 
                bird.x + bird.width > pipe.x && 
                bird.y < pipe.y + pipe.height && 
                bird.y + bird.height > pipe.y;
    }

    public void movePipes() {
        Iterator<Pipe> iterator = pipes.iterator();
        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();
            pipe.x += pipeVelocity;
    
            if (pipe.x + pipe.width < 0) {
                iterator.remove(); // Xóa phần tử an toàn
            } else {
                if (pipe.x + pipe.width < bird.x && !pipe.isTheBirdPass) {
                    pipe.isTheBirdPass = true;
                    score += 0.5;
                }
                if (hasColision(bird, pipe)) {
                    isGameOver = true;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        moveBird();
        movePipes();
        repaint();

        if(isGameOver){
            placePipesLoop.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_SPACE) || (e.getKeyCode() == KeyEvent.VK_ENTER)){
            birdVelocity = -10;
            if(isGameOver){
                resetSettings();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
