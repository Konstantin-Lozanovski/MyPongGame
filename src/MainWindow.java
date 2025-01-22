import javax.swing.*;

public class MainWindow extends JFrame implements Runnable {

    public final int screenWidth = 768;
    public final int screenHeight = 576;

    GamePanel gamePanel;
    MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
    SettingsPanel settingsPanel = new SettingsPanel(this);
    EndScreenPanel endScreenPanel;

    MainWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("PONG");

        this.add(mainMenuPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void startEndScreenPanel(Game.PlayerType winner) {
        endScreenPanel = new EndScreenPanel(this, winner);
        this.getContentPane().removeAll();
        this.add(endScreenPanel);
        this.pack();
        this.revalidate();
        this.repaint();

        endScreenPanel.requestFocusInWindow();
    }

    public void startGamePanel() {
        gamePanel = new GamePanel(this);
        this.getContentPane().removeAll();
        this.add(gamePanel);
        this.pack();
        this.revalidate();
        this.repaint();

        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();
    }

    public void startMainMenuPanel() {
        this.getContentPane().removeAll();
        this.add(mainMenuPanel);
        this.pack();
        this.revalidate();
        this.repaint();

        mainMenuPanel.requestFocusInWindow();
    }

    public void startSettingsPanel() {
        this.getContentPane().removeAll();
        this.add(settingsPanel);
        this.pack();
        this.revalidate();
        this.repaint();

        settingsPanel.requestFocusInWindow();
    }

    @Override
    public void run() {

    }
}
