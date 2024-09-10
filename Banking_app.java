import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MainFrame
{
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // create instances of Login and Signup pages
    LoginPage loginPage = new LoginPage(this);
    SignUpPage signUpPage = new SignUpPage(this);
    /*
     * we create instances to keep the code for each page 
     * in a separate class and to use them for the card layout
    */

    MainFrame() // constructor
    {
        startFrame();
        initMainPanel();

        setupOpeningPage();

        mainPanel.add(openingPage, "openingPage");
        mainPanel.add(loginPage, "loginPage");
        mainPanel.add(signUpPage, "signUpPage");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void startFrame()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // when we close application, it will stop running in terminal too
        frame.setSize(400, 800);
        frame.setLocationRelativeTo(null);
        // places the frame on the center of user display
    }

    private void initMainPanel()
    {
        cardLayout = new CardLayout();
        // creating a card layout to handle switching between panels
        mainPanel = new JPanel(cardLayout);
        // mainPanel will be used as a container that
        // will hold the other panels (or pages/frames)
        // a panel can hold multiple components
    }

    private JPanel openingPage;

    private void setupOpeningPage()
    {
        openingPage = new JPanel();
        openingPage.setLayout(null);

        LoginButton();
        SignUpButton();

    }

    private void LoginButton()
    {
        JButton LogInButton = new JButton("Log in");
        LogInButton.setBounds(50, 600, 300, 50);
        LogInButton.setBackground(Color.WHITE);
        LogInButton.setFont(new Font("Arial", Font.BOLD, 18));

        LogInButton.addActionListener(new ActionListener()
        // attaches an action listener to the LogInButton 
        {
            @Override
            public void actionPerformed(ActionEvent login)
            {
                cardLayout.show(mainPanel, "loginPage");
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
         *  aboutthe event 
         *  (login is not used here but we usually use the obect when we need to know which button is
         *  pressed if there are more buttons)
         */

        openingPage.add(LogInButton);
    }

    private void SignUpButton()
    {
        JButton SignUpButton = new JButton("Sign Up");
        SignUpButton.setBounds(50, 700, 300, 50);
        SignUpButton.setBackground(Color.WHITE);
        SignUpButton.setFont(new Font("Arial", Font.BOLD, 18));

        SignUpButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.show(mainPanel, "signUpPage");
            }
        });

        openingPage.add(SignUpButton);
    }

}

public class Banking_app
{
    public static void main(String[] args) 
    {
        new MainFrame();
    }
}