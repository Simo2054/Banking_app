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
    private MainFrame mainFrame;
    private UserManager userManager;
    
    public LogInSuccess(MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;
        userManager = new UserManager();
        
        setBackground(new Color(110, 20, 90));
        setLayout(null);

        Rectangle();
        RememberMeBox();
        NextButton();
    }

    private String email; 
    private String password;
    private int userID;

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
                    // by clicking the "remember me" checkbox, user's credentials will be 
                    // kept in a file for future logging in

                    email = mainFrame.getEmail();
                    password = mainFrame.getPass();
                    userManager.RememberUser(email, password);

                    // nevoie de userID pentru a scoate array-ul de cardIDs in urmatoarea pagina
                    

                    // mainFrame.cardLayout.show(mainFrame.mainPanel, "bkAccountPage");
                }

                mainFrame.multipleCardsPage.updateFields();
                System.out.println("next>>>");
            }
        });

        add(nextButton);
    }
}