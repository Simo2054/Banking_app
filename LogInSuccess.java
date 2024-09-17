import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LogInSuccess extends JPanel
{
    private MainFrame mainFrame;
    
    public LogInSuccess(MainFrame mainFrame) throws IOException
    {
        //this.mainFrame = mainFrame;
        
        setBackground(new Color(110, 20, 90));
        setLayout(null);

        Rectangle();
    }

    private void Rectangle()
    {
        JTextArea logInSuccess = new JTextArea("Log In Successful");
        logInSuccess.setBounds(50, 300, 300, 160);
        logInSuccess.setEditable(false);
        logInSuccess.setFont(new Font("Arial", Font.CENTER_BASELINE, 55));
        logInSuccess.setBackground(Color.white);
        logInSuccess.setForeground(Color.black);
        logInSuccess.setLineWrap(true);
        logInSuccess.setWrapStyleWord(true);
        
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
        logInSuccess.setBorder(lineBorder);

        add(logInSuccess);
    }
}