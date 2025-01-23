package UI;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {


    public Button(String text) {
        this.setText(text);
        this.setBackground(Color.blue);
        this.setForeground(Color.red);
        this.setFont(new Font("Verdana", Font.BOLD, 20));
        this.setBorderPainted(false);
    }
}
