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
        RememberMeBox();
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

    private void RememberMeBox()
    {
        JTextArea question = new JTextArea();
        question.setText("Would you like us to remember you?");
        question.setBounds(50, 480, 300, 50);
        question.setEditable(false);
        question.setFont(new Font("Arial", Font.BOLD, 18));
        question.setForeground(Color.WHITE);
        question.setBackground(new Color(0,0,0,0));
        question.setLineWrap(true);
        question.setWrapStyleWord(true);

        add(question);
    }
}