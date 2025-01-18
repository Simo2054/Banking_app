package pages;

import Card_types.*;
import managers.*;
import sechiule.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

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

    CardDataPage CardDataPage;
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

    PrepCardPage PrepCardPage;
    // instance for a page where user will need to introduce some data 
    // (first name, last name, sum of money wanted) for the prepaid card
    // (response for Prepaid card type)

    public CardObtained cardObtained;
    // instance for a page where the app will let the user know that they 
    // got the card type they selected

    BkAccountPage bkAccountPage;
    // instance for a page where the user will see their bank account
    // and information about it
    // (money, currency, details about card, functions, transaction history)

    MultipleCardsPage multipleCardsPage;
    // instance for a page where the user will choose which card to show
    // if they have multiple cards linked to the current account

    MainFrame() throws Exception // constructor
    {
        try
        {
            sechiule.DatabaseManager.getInstance().createUsersTable();
            sechiule.DatabaseManager.getInstance().createCardsTable();
            sechiule.DatabaseManager.getInstance().createTransactionsTable();
        }
        catch (Exception e)
        {
            e.printStackTrace();//handles any database realted issues
        }
        

        openingPage = new OpeningPage(this);
        loginPage = new LoginPage(this);
        signUpPage = new SignUpPage(this);
        successfulLogIn = new LogInSuccess(this);
        cardManager = new CardManager(this);
        CardDataPage = new CardDataPage(this);
        BkAccIdentity = new BkAccIdentity(this);
        BkAccAdress = new BkAccAdress(this, BkAccIdentity);
        PrepCardPage = new PrepCardPage(this);
        cardObtained = new CardObtained(this);
        bkAccountPage = new BkAccountPage(this);
        multipleCardsPage = new MultipleCardsPage(this);

        startFrame();
        initMainPanel();

        mainPanel.add(openingPage, "openingPage");
        mainPanel.add(loginPage, "loginPage");
        mainPanel.add(signUpPage, "signUpPage");
        mainPanel.add(successfulLogIn, "successfulLogIn");
        mainPanel.add(CardDataPage, "CardDataPage");
        mainPanel.add(BkAccIdentity, "BkAccIdentity");
        mainPanel.add(BkAccAdress, "BkAccAdress");
        mainPanel.add(PrepCardPage, "PrepCardPage");
        mainPanel.add(cardObtained, "cardObtained");
        mainPanel.add(bkAccountPage, "bkAccountPage");
        mainPanel.add(multipleCardsPage, "multipleCardsPage");

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

    // ------------------------------- getters and setters -------------------------------

    private String introducedEmail;

    public void setEmail(String email) // setter for e-mail (used in SignUpPage.java)
    {
        this.introducedEmail = email;
    }

    public String getEmail() // getter for e-mail (used in BkAccAdress.java, CardObtained.java)
    {
        return introducedEmail;
    }

    private String introducedPassword;

    public void setPass(String pass) // setter for password (used in LogInSuccess.java)
    {
        this.introducedPassword = pass;
    }

    public String getPass()
    {
        return introducedPassword;
    }

    private int currentUserID;

    public void setCurrentUserID(int userID) // setter for the current user ID (used in SignUpPage.java)
    {
        this.currentUserID = userID;
    }

    public int getCurrentUserID() // getter for the current user ID (used in )
    {
        return currentUserID;
    }

    public void setUsername(String username) throws IOException// setter for username (used in SignUpPage.java)
    {
        this.username = username;

        cardManager.initCardTypesPanel(); // init cardTypesPanel only after username is set
    }

    public String getUsername() // getter for username (used in TypesOfCards.java)
    {
        return username;
    }

    public CardManager getCardManager() // getter for CardManager class (returns an instance of the class)
    {
        return cardManager;
    }

    private String selectedCardType; // string to store the selected card type

    // setter for the card type (used in TypesOfCards.java)
    public void setSelectedCardType(String type)
    {
        this.selectedCardType = type;
    }

    // getter for the selected card type  (used in CardObtained.java)
    public String getSelectedCardType()
    {
        return selectedCardType;
    }

    private String first_name;
    private String last_name;

    // setter for the first name (used in "BkAccAdress.java")
    public void setFirstName(String fsnm)
    {
        this.first_name = fsnm;
    }

    // setter for the last name (used in "BkAccAdress.java")
    public void setLastName(String lsnm)
    {
        this.last_name = lsnm;
    }

    // getter for the first name (used in "CardObtained.java")
    public String getFirstName()
    {
        return first_name;
    }
    
    // getter for the last name (used in "CardObtained.java")
    public String getLastName()
    {
        return last_name;
    }

    private String chosenCountry;

    // setter for the chosen country (used in "BkAccAdress.java")
    public void setChosenCountry(String country)
    {
        this.chosenCountry = country;
    }

    // getter for the chosen country (used in "CardObtained.java", "BkAccountPage.java")
    public String getCountry()
    {
        return chosenCountry;
    }

    private String card_nr;

    // setter for the generated card number (used in "CardObtainedPage")
    public void setCNr(String cardNr)
    {
        this.card_nr = cardNr;
    }

    // getter for the generated card number (used in "BkAccountPage.java")
    public String getCNr()
    {
        return card_nr;
    }
    
    private int current_card_ID;

    public void setCurrentCardID(int currCID)
    {
        this.current_card_ID = currCID;
    }

    public int getCurrentCardID()
    {
        return current_card_ID;
    }
    

}

