import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Ball {

    int screenX;
    int screenY;
    final int ballWidth = 20;
    final int ballHeight = 20;
    final int speed = 10;
    double angle;
    final double MAX_BOUNCE_ANGLE = Math.PI / 3; //60 degrees

    public Clip hitSoundClip;
    public Clip goalScoreSoundClip;

    GamePanel gp;
    KeyHandler keyH;


    Ball(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        hitSoundClip = getClip("Sounds/ball_hit.wav");
        goalScoreSoundClip = getClip("Sounds/goal_scored.wav");


        setDefaultValues();
    }

    public void update(Paddle paddle1, Paddle paddle2) {

            screenY += (int) (Math.sin(angle) * speed);
            screenX += (int) (Math.cos(angle) * speed);

            checkWallCollision();
            checkPaddleCollision(paddle1, paddle2);

    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(screenX, screenY, ballWidth, ballHeight);
    }

    private void checkWallCollision() {
        //check for top/bottom collisions with walls
        if(screenY <= 0) {
            screenY = 0;
            angle = -angle;
        }
        if(screenY + ballHeight >= gp.screenHeight) {
            screenY = gp.screenHeight - ballHeight;
            angle = -angle;
        }
    }

    private boolean leftPaddleCollidedWith(Paddle paddle1) {
        return screenX <= paddle1.screenX + paddle1.paddleWidth && screenX >= paddle1.screenX
                && screenY + ballHeight / 2 >= paddle1.screenY
                && screenY - ballHeight / 2 <= paddle1.screenY + paddle1.paddleHeight;
    }

    private boolean rightPaddleCollidedWith(Paddle paddle2) {
        return screenX + ballWidth >= paddle2.screenX && screenX + ballWidth <= paddle2.screenX + paddle2.paddleWidth
                && screenY + ballHeight / 2 >= paddle2.screenY
                && screenY - ballHeight / 2 <= paddle2.screenY + paddle2.paddleHeight;
    }

    private void checkPaddleCollision(Paddle paddle1, Paddle paddle2) {
        if(leftPaddleCollidedWith(paddle1)) {
            playHitSound();
            //make sure the ball doesn't go inside off the paddle
            screenX = paddle1.screenX + paddle1.paddleWidth;

            //difference from the paddle center and the y where the ball hit
            double relativeIntersectY = (paddle1.screenY + paddle1.paddleHeight / 2.0) - (screenY + ballHeight / 2.0);
            //divided by the paddle height / 2 to get a number between -1 and 1 and 0 as the middle of the paddle
            double normalizedRelativeIntersectY = relativeIntersectY / (paddle1.paddleHeight / 2.0) * -1;

            angle = normalizedRelativeIntersectY * MAX_BOUNCE_ANGLE;

        }
        if(rightPaddleCollidedWith(paddle2)) {
            playHitSound();
            //make sure the ball doesn't go inside off the paddle
            screenX = paddle2.screenX - ballWidth;

            //difference from the paddle center and the y where the ball hit
            double relativeIntersectY = (paddle2.screenY + paddle2.paddleHeight / 2.0) - (screenY + ballHeight / 2.0);
            //divided by the paddle height / 2 to get a number between -1 and 1 and 0 as the middle of the paddle
            double normalizedRelativeIntersectY = relativeIntersectY / (paddle2.paddleHeight / 2.0) * -1;

            angle = Math.PI - normalizedRelativeIntersectY * MAX_BOUNCE_ANGLE;//reverse the angle for the right paddle
        }
    }

    public void setDefaultValues() {
        //position the ball in the center
        screenX = gp.screenWidth / 2 - ballWidth / 2;
        screenY = gp.screenHeight / 2 - ballHeight / 2;
    }

    public void setStartingDirection(Game.PlayerType player) {
        if(player == Game.PlayerType.PLAYER_1){
            //if player 2 scored send the ball to player 1
            angle = Math.atan2(0, -1);
        }
        if(player == Game.PlayerType.PLAYER_2){
            //if player 1 scored send the ball to player 2
            angle = Math.atan2(0, 1);
        }
    }

    private Clip getClip(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void playHitSound() {
        if(hitSoundClip != null) {
            hitSoundClip.setFramePosition(0);
            hitSoundClip.start();
        }
    }

    public void playGoalScoredSound() {
        if(goalScoreSoundClip != null) {
            goalScoreSoundClip.setFramePosition(0);
            goalScoreSoundClip.start();
        }
    }
}
