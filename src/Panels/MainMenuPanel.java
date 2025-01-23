package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Main.MainWindow;
import UI.Button;

public class MainMenuPanel extends JPanel implements ActionListener {

    MainWindow mainWindow;
    private final JButton playButton;
    private final JButton settingsButton;

    public MainMenuPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setPreferredSize(new Dimension(mainWindow.screenWidth, mainWindow.screenHeight));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        playButton = new Button("Play");
        playButton.setBounds(mainWindow.screenWidth / 2 - 50, mainWindow.screenHeight / 2 - 25, 100, 50);
        playButton.addActionListener(this);

        settingsButton = new Button("Settings");
        settingsButton.setBounds(mainWindow.screenWidth / 2 - 100, mainWindow.screenHeight / 2 - 25 + 100, 200, 50);
        settingsButton.addActionListener(this);

        this.add(playButton);
        this.add(settingsButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            mainWindow.startGamePanel();
        }
        if(e.getSource() == settingsButton) {
            mainWindow.startSettingsPanel();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Verdana", Font.BOLD, 40));
        g.setColor(Color.red);
        g.drawString("PONG", mainWindow.screenWidth / 2 - 150, 100);

        g.setColor(Color.blue);
        g.drawString("GAME", mainWindow.screenWidth / 2, 100);

        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.setColor(Color.green);
        g.drawString("Made by K0K0",mainWindow.screenWidth - 100, mainWindow.screenHeight - 10);

    }
}
