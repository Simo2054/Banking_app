package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LogInSuccess extends JPanel
{
    private UserManager userManager;
    private MainFrame mainFrame;
    private LoginPage loginPage;
    
    public LogInSuccess(MainFrame mainFrame, LoginPage loginPage) throws IOException
    {
        userManager = new UserManager();
        this.mainFrame = mainFrame;
        this.loginPage = loginPage;
        
        setBackground(new Color(110, 20, 90));
        setLayout(null);

        Rectangle();
        RememberMeBox();
        NextButton();
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

    public boolean rememberUser = false;
    // decides if user's credentails will be remembered or not

    JCheckBox rememberMe; 

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

        rememberMe = new JCheckBox("Yes, remember me");
        rememberMe.setBounds(50, 520, 300, 50);
        rememberMe.setFont(new Font("Arial", Font.BOLD, 18));
        rememberMe.setForeground(Color.white);
        rememberMe.setBackground(new Color(0,0,0,0));

        rememberMe.addActionListener(new ActionListener() // checks if user checked the checkbox :)
        {
            @Override
            public void actionPerformed(ActionEvent checked)
            {
                if(rememberMe.isSelected())
                {
                    System.out.println("checked!");
                    rememberUser = true;
                }
                else
                {
                    rememberUser = false;
                    System.out.println("no no!");
                }
            }
        });

        add(question);
        add(rememberMe);
    }

    private void NextButton()
    {
        JButton nextButton = new JButton("Next >");
        nextButton.setBounds(300, 730, 80, 50);

        nextButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(rememberUser == true)
                {
                    userManager.RememberUser(loginPage.input_mail, loginPage.user_password);
                    // by clicking the "remember me" checkbox, user's credentials will be 
                    // kept in a file for future logging in
                }
                System.out.println("next>>>");
            }
        });

        add(nextButton);
    }
}