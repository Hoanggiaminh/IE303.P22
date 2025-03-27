package LAB2.src;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        
        JFrame mainWindow = new JFrame("Flappy Bird");

        int boardWidth = 360;
        int boardHeight = 640;
        mainWindow.setSize(boardWidth, boardHeight);

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        mainWindow.add(flappyBird);

        mainWindow.pack();
        flappyBird.requestFocus();
        
        mainWindow.setVisible(true);

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
