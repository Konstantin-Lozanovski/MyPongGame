import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, wPressed, sPressed;
    public boolean spacePressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if(keyCode == KeyEvent.VK_W) {
            wPressed = true;
        }
        if(keyCode == KeyEvent.VK_S) {
            sPressed = true;
        }
        if(keyCode == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(keyCode == KeyEvent.VK_W) {
            wPressed = false;
        }
        if(keyCode == KeyEvent.VK_S) {
            sPressed = false;
        }
        if(keyCode == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
