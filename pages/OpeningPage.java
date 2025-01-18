package pages;

import Card_types.*;
import managers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OpeningPage extends JPanel
{
    private MainFrame mainFrame; // a field to hold the reference (connection) to the MainFrame class
    // this is allowing the OpeningPage to store the MainFrame instance and use it in the class

    private ImageManager imageManager;

    public OpeningPage(MainFrame mainFrame) // constructor
    // an object/parameter (mainFrame) of type MainFrame is being passed to OpeningPage class
    {
        this.mainFrame = mainFrame; // storing the reference to MainFrame class 
        // this.mainframe referes to the field mentioned first and mainFrame is the parameter
        // elements from the MainFrame class will be used in the methods of OpeningPage class

        imageManager = new ImageManager();

        setLayout(null);
        setBackground(new Color(250, 243, 221));

        LoginButton();
        SignUpButton();
        image();
    }

    private void LoginButton()
    {
        RoundedButton LogInButton = new RoundedButton("Log in", 30);
        LogInButton.setBounds(50, 600, 300, 70);
        LogInButton.setFont(new Font("Arial", Font.BOLD, 18));
        LogInButton.setBackground(new Color(107, 170, 239));
        LogInButton.setForeground(Color.WHITE);
        LogInButton.setBorder(new RoundedBorder(30, Color.GRAY, 4));

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

        // using mouseAdaper to change colors of the button at hover
        LogInButton.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                LogInButton.setBackground(new Color(90, 152, 214)); // Hover color
            }
        
            @Override
            public void mouseExited(MouseEvent e) 
            {
                LogInButton.setBackground(new Color(107, 170, 239)); // Original color
            }
        });
        

        add(LogInButton);
    }

    private void SignUpButton() 
    {
        RoundedButton SignUpButton = new RoundedButton("Sign Up", 30);
        SignUpButton.setBounds(50, 700, 300, 70);
        SignUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        SignUpButton.setBackground(new Color(143, 214, 148));
        SignUpButton.setForeground(Color.WHITE);
        SignUpButton.setBorder(new RoundedBorder(30, Color.gray, 4));

        SignUpButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                mainFrame.cardLayout.show(mainFrame.mainPanel, "signUpPage");
                // testing purposes:

                //mainFrame.cardLayout.show(mainFrame.mainPanel, "CardDataPage");
                //mainFrame.cardLayout.show(mainFrame.mainPanel, "cardTypesPanel");
                //mainFrame.cardLayout.show(mainFrame.mainPanel, "PrepCardPage");
                //mainFrame.cardLayout.show(mainFrame.mainPanel, "bkAccountPage");
            }
        });

        // using mouseAdaper to change colors of the button at hover
        SignUpButton.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                SignUpButton.setBackground(new Color(122, 203, 128)); // Hover color
            }
        
            @Override
            public void mouseExited(MouseEvent e) 
            {
                SignUpButton.setBackground(new Color(143, 214, 148)); // Original color
            }
        });

        add(SignUpButton);
    }

    private void image()
    {
        JLabel logoPhoto;
        logoPhoto = imageManager.createImgLabel("images/circle.png", 300, 300);
        logoPhoto.setBounds(50, 50, 300, 300);
        add(logoPhoto);
    }
}