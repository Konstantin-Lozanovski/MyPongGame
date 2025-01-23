package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import Main.MainWindow;
import GameComponents.KeyHandler;
import GameComponents.Game;

public class GamePanel extends JPanel implements Runnable{

    public final int screenWidth = 768;
    public final int screenHeight = 576;

    private BufferedImage buffer;

    int FPS = 60;

    MainWindow mainWindow;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Game game;


    public GamePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        game = new Game(this, keyH, mainWindow);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGameThread() {
        gameThread = null;
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

            if(game.gameIsOver){
                stopGameThread();
                mainWindow.startEndScreenPanel(game.winner);
                return;
            }

        }

    }

    public void update() {
        game.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(buffer == null) {
            buffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        }

        Graphics bufferGraphics = buffer.getGraphics();

        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, screenWidth, screenHeight);

        game.draw(bufferGraphics);

        g.drawImage(buffer, 0, 0, null);

        bufferGraphics.dispose();
    }

}
