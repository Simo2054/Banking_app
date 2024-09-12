import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MainFrame
{
    private JFrame frame;
    public JPanel mainPanel;
    public CardLayout cardLayout;

    // creating an instance for the opening page
    OpeningPage openingPage = new OpeningPage(this);

    // create instances of Login and Signup pages
    LoginPage loginPage = new LoginPage(this);
    SignUpPage signUpPage = new SignUpPage(this);
    /*
     * we create instances to keep the code for each page 
     * in a separate class and to use them for the card layout
     * 
     * this is refering to the instance of MainFrame class
    */

    MainFrame() throws IOException // constructor
    {
        openingPage = new OpeningPage(this);
        loginPage = new LoginPage(this);
        signUpPage = new SignUpPage(this);

        startFrame();
        initMainPanel();

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
}

public class Banking_app
{
    public static void main(String[] args) 
    {
        try
        {
            new MainFrame();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}