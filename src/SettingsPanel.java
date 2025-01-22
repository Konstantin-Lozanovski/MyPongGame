import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel implements ActionListener {

    MainWindow mainWindow;
    JButton backButton;

    SettingsPanel(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.setPreferredSize(new Dimension(mainWindow.screenWidth, mainWindow.screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        backButton = new Button("Back");
        backButton.setBounds(0, 0, 100, 100);
        backButton.addActionListener(this);

        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            mainWindow.startMainMenuPanel();
        }
    }
}
