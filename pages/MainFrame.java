package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame
// MainFrame is the main class that controls the window and the auth screens 
// such as log in screens and sign up screens
// it uses a card layout to do that
{
    private JFrame frame;
    public JPanel mainPanel; // a panel(container) to hold the pages
    public CardLayout cardLayout; // using a card layout for easy switching between pages

    /*
     * we create instances to keep the code for each page 
     * in a separate class and to use them for the card layout
    */

    // creating an instance for the opening page
    OpeningPage openingPage;

    // create instances of Login and Signup pages
    LoginPage loginPage;
    SignUpPage signUpPage;

    // instance for the successful log in page
    LogInSuccess successfulLogIn;

    CardManager cardManager; // CardManager to handle bank card related logic
    public String username; // storing username after signing up

    ExistCardPage ExistCardPage;
    // instance for a page where user will introduce their card data
    // (number, name, expiring date, CVC code)
    // (response for "I already have a card" button)

    BkAccIdentity BkAccIdentity; 
    // instance for a page where user will need to introduce their identity
    // (first name, last name, phone number, country) for their bank account
    // (response for Credit, Debit, Virtual, Kids card types, page 1)

    BkAccAdress BkAccAdress; 
    // // instance for a page where user will need to introduce their home adress
    // (city, county, street name, home number) for their bank account
    // (response for Credit, Debit, Virtual, Kids card types, page 2)

    MainFrame() throws IOException // constructor
    {
        openingPage = new OpeningPage(this);
        loginPage = new LoginPage(this);
        signUpPage = new SignUpPage(this);
        successfulLogIn = new LogInSuccess(this, loginPage);
        cardManager = new CardManager(this);
        ExistCardPage = new ExistCardPage(this);
        BkAccIdentity = new BkAccIdentity(this);
        BkAccAdress = new BkAccAdress(this);

        startFrame();
        initMainPanel();

        mainPanel.add(openingPage, "openingPage");
        mainPanel.add(loginPage, "loginPage");
        mainPanel.add(signUpPage, "signUpPage");
        mainPanel.add(successfulLogIn, "successfulLogIn");
        mainPanel.add(ExistCardPage, "ExistCardPage");
        mainPanel.add(BkAccIdentity, "BkAccIdentity");
        mainPanel.add(BkAccAdress, "BkAccAdress");

        mainPanel.add(cardManager.getCardTypesPanel(), "cardTypesPanel"); // to add the container for all card types to the mainPanel

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void startFrame()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // when we close application, it will stop running in terminal too
        frame.setSize(400, 800);
        // 400 width and 800 length
        frame.setResizable(false);
        // making the frame unresizable
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

    public void setUsername(String username)
    {
        this.username = username;

        cardManager.initCardTypesPanel(); // init cardTypesPanel only after username is set
    }

    public String getUsername() // getter for username
    {
        return username;
    }

    public CardManager getCardManager() // getter for CardManager class (returns an instance of the class)
    {
        return cardManager;
    }
}

