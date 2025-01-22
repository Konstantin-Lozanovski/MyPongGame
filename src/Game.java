import java.awt.*;

public class Game {

    public enum PlayerType {
        PLAYER_1,
        PLAYER_2
    }

    public boolean gameIsOver = false;

    MainWindow mainWindow;
    GamePanel gp;
    KeyHandler keyH;
    public Player player1;
    public Player player2;
    public Ball ball;
    PlayerType winner = null;

    public boolean ballInPlay = false;
    public PlayerType playerScoredOn = PlayerType.PLAYER_1;//if = 1 player 2 scored if = 2 player 1 scored

    Game(GamePanel gp, KeyHandler keyH, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.gp = gp;
        this.keyH = keyH;
        player1 = new Player(gp, keyH, true);
        player2 = new Player(gp, keyH, false);
        ball = new Ball(gp, keyH);
    }

    public void update() {

        if (keyH.spacePressed && !ballInPlay) {
            ballInPlay = true;
        }
        if(ballInPlay){
            player1.update();
            player2.update();
            ball.update(player1.paddle, player2.paddle);
            checkIfScored();
        }

    }

    public void draw(Graphics g){
        drawMap(g);
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
        drawScore(g);
    }

    private void drawMap(Graphics g){
        g.setColor(Color.red);
        g.fillRect(0, 0, gp.screenWidth / 2, 10);//top bar
        g.fillRect(0, gp.screenHeight - 10, gp.screenWidth / 2, 10);//bottom bar
        g.fillRect(gp.screenWidth / 2 - 5, 5, 5, gp.screenHeight);//center top bar

        //red half circle
        g.drawArc(gp.screenWidth / 2 - 100, gp.screenHeight / 2 - 100, 200, 200, 90, 180);


        g.setColor(Color.blue);
        g.fillRect(gp.screenWidth / 2,0, gp.screenWidth / 2, 10);//top bar
        g.fillRect(gp.screenWidth / 2, gp.screenHeight - 10, gp.screenWidth / 2, 10);//bottom bar
        g.fillRect(gp.screenWidth / 2, 5, 5, gp.screenHeight);//center top bar

        //blue half circle
        g.drawArc(gp.screenWidth / 2 - 100, gp.screenHeight / 2 - 100, 200, 200, 90, -180);
    }

    private void drawScore(Graphics g){
        g.setFont(new Font("Verdana", Font.BOLD, 50));
        g.setColor(Color.red);
        g.drawString(String.valueOf(player1.getScore()), gp.screenWidth / 4, 50); // Player 1 score on the left
        g.setColor(Color.blue);
        g.drawString(String.valueOf(player2.getScore()), gp.screenWidth * 3 / 4, 50); // Player 2 score on the right
    }

    public void updateScore(Player player) {
        player.score++;
        if(player.paddle.isLeftPaddle){
            playerScoredOn = PlayerType.PLAYER_2;
        }
        if(!player.paddle.isLeftPaddle){
            playerScoredOn = PlayerType.PLAYER_1;
        }
        if(player.score >= 5){

            if(player.paddle.isLeftPaddle){
                winner = PlayerType.PLAYER_1;
            }else{
                winner = PlayerType.PLAYER_2;
            }

            gameIsOver = true;

        }
    }

    private void checkIfScored() {
        //score detection
        if(ball.screenX > gp.screenWidth){
            ball.playGoalScoredSound();
            updateScore(player1);
            resetGame();
        }
        if(ball.screenX + ball.ballWidth < 0){
            ball.playGoalScoredSound();
            updateScore(player2);
            resetGame();
        }

    }

    private void resetGame() {
        ball.setStartingDirection(playerScoredOn);
        ball.setDefaultValues();
        player1.paddle.resetPaddle();
        player2.paddle.resetPaddle();
        ballInPlay = false;
    }


}
