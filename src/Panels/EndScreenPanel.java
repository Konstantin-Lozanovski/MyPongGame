package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.MainWindow;
import GameComponents.Game;


public class EndScreenPanel extends JPanel implements ActionListener {

    MainWindow mainWindow;
    Button mainMenuButton;
    Game.PlayerType winner;

    public EndScreenPanel(MainWindow mainWindow, Game.PlayerType winner) {
        this.mainWindow = mainWindow;
        this.winner = winner;

        this.setPreferredSize(new Dimension(mainWindow.screenWidth, mainWindow.screenHeight));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        mainMenuButton = new Button("Main.Main Menu");
        mainMenuButton.setBounds(mainWindow.screenWidth / 2 - 100, mainWindow.screenHeight / 2 - 25 + 100, 200, 50);
        mainMenuButton.addActionListener(this);

        this.add(mainMenuButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainMenuButton) {
            mainWindow.startMainMenuPanel();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String text = "DA";

        if(winner == Game.PlayerType.PLAYER_1){
            System.out.println("HELLO");
            text = "Red";
            g.setColor(Color.RED);
        }else if(winner == Game.PlayerType.PLAYER_2){
            text = "Blue";
            g.setColor(Color.BLUE);
        }

        g.setFont(new Font("Verdana", Font.BOLD, 40));

        g.drawString("Winner: ", mainWindow.screenWidth / 2 - 150, 100);


        g.drawString(text, mainWindow.screenWidth / 2 + 30, 100);

        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.setColor(Color.green);
        g.drawString("Made by K0K0",mainWindow.screenWidth - 100, mainWindow.screenHeight - 10);

    }
}
