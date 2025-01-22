import java.awt.*;

public class Player {
    public int score;
    public String name;
    public Paddle paddle;

    public Player(GamePanel gp, KeyHandler keyH, boolean isLeftPlayer){
        paddle = new Paddle(gp, keyH, isLeftPlayer);
        score = 0;
    }

    public void update() {
        paddle.update();
    }

    public void draw(Graphics g) {
        paddle.draw(g);
    }

    public void setDefaultValues() {
        score = 0;
    }

    public int getScore() {
        return score;
    }
}
