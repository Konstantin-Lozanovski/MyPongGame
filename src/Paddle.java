import java.awt.*;

public class Paddle {

    int screenX;
    int screenY;
    public final int paddleWidth = 15;
    public final int paddleHeight = 80;

    int speed = 10;

    GamePanel gp;
    KeyHandler keyH;
    boolean isLeftPaddle;

    public Paddle(GamePanel gp, KeyHandler keyH, boolean isLeftPaddle) {
        this.gp = gp;
        this.keyH = keyH;
        this.isLeftPaddle = isLeftPaddle;

        resetPaddle();

    }

    public void resetPaddle() {
        if(isLeftPaddle){
            screenX = 5;
        }
        if(!isLeftPaddle){
            screenX = gp.screenWidth - paddleWidth - 5;
        }
        screenY = gp.screenHeight / 2 - paddleHeight / 2;
    }

    public void update() {
        if(isLeftPaddle) {
            if(keyH.wPressed && canMoveUp()){
                screenY -= speed;
            }
            if(keyH.sPressed && canMoveDown()){
                screenY += speed;
            }
        }
        if(!isLeftPaddle) {
            if(keyH.upPressed && canMoveUp()){
                screenY -= speed;
            }
            if(keyH.downPressed && canMoveDown()){
                screenY += speed;
            }
        }
    }

    private boolean canMoveUp() {
        return screenY - speed > 0;
    }

    private boolean canMoveDown() {
        return screenY + paddleHeight + speed < gp.screenHeight;
    }

    public void draw(Graphics g) {
        if(isLeftPaddle){
            g.setColor(Color.red);
        }
        if(!isLeftPaddle){
            g.setColor(Color.blue);
        }
        g.fillRect(screenX, screenY, paddleWidth, paddleHeight);
    }

}
