import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpeningPage extends JPanel
{
    private MainFrame mainFrame; // a field to hold the reference (connection) to the MainFrame class
    // this is allowing the OpeningPage to store the MainFrame instance and use it in the class

    public OpeningPage(MainFrame mainFrame) // constructor
    // an object/parameter (mainFrame) of type MainFrame is being passed to OpeningPage class
    {
        this.mainFrame = mainFrame; // storing the reference to MainFrame class 
        // this.mainframe referes to the field mentioned first and mainFrame is the parameter
        // elements from the MainFrame class will be used in the methods of OpeningPage class
        setLayout(null);
        setBackground(new Color(50, 52, 88));

        LoginButton();
        SignUpButton();
    }

    private void LoginButton()
    {
        RoundedButton LogInButton = new RoundedButton("Log in", 30);
        LogInButton.setBounds(50, 600, 300, 70);
        LogInButton.setFont(new Font("Arial", Font.BOLD, 18));
        LogInButton.setBackground(Color.white);
        LogInButton.setForeground(Color.black);
        LogInButton.setBorder(new RoundedBorder(30, Color.BLACK, 4));

        LogInButton.addActionListener(new ActionListener()
        // attaches an action listener to the LogInButton 
        {
            @Override
            public void actionPerformed(ActionEvent login)
            {
                // access the MainFrame fields for switching between cards
                mainFrame.cardLayout.show(mainFrame.mainPanel, "loginPage");
            }
        });
        /*
         * An action listener is an object that listens for actions (like button clicks) 
         *  and executes a specified block of code when the event occurs.
         * 
         *  ActionListener is an interface so it doesn’t have an implementation by default
         *  We are providing the implementation by overriding the actionPerformed() method 
         *  inside the curly braces 
         * 
         *  The method receives an object (login) of type ActionEvent which will contain information
         *  about the event 
         *  (login is not used here but we usually use the obect when we need to know which button is
         *  pressed if there are more buttons)
         */

        add(LogInButton);
    }

    private void SignUpButton()
    {
        RoundedButton SignUpButton = new RoundedButton("Sign Up", 30);
        SignUpButton.setBounds(50, 700, 300, 70);
        SignUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        SignUpButton.setBackground(Color.WHITE);
        SignUpButton.setForeground(Color.black);
        SignUpButton.setBorder(new RoundedBorder(30, Color.BLACK, 4));

        SignUpButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                mainFrame.cardLayout.show(mainFrame.mainPanel, "signUpPage");
                // testing purposes:
            }
        });

        add(SignUpButton);
    }
}