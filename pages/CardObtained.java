package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.*;

public class CardObtained extends JPanel
{
    private MainFrame mainFrame;
    private BkAccIdentity bkAccIdentity;
    private IBAN_creator iban_creator;
    private UserManager userManager;

    public CardObtained(MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;
        bkAccIdentity = new BkAccIdentity(mainFrame);
        iban_creator = new IBAN_creator();
        userManager = new UserManager();

        setBackground(new Color(250, 243, 221));
        setLayout(null);
    }

    // Your [type] card is 
    // image
    // Beneficiary: [first name, last name]
    // Connected account: [IBAN]
    // Card number: [card number]
    // Valid until [exp_month / exp_year]
    // CVV: [CVV number]

    private String CardType; // chosen card type
    private String first_name; // beneficiary's first name
    private String last_name; // beneficiary's last name

    private String chosen_country; // will be used to create the IBAN
    private String AccIBAN; // IBAN of the connected account
    private String currency; // will be used to put the information in the file

    private String cardNumber; // card number of the generated card
    
    private int current_month;
    private int current_year;

    private String exp_month; // expiration month of the card
    private String exp_year; // expiration year of the card

    private String CVV_num; // CVV number for the generated card

    private String email; // email will be used to add information to the database file

    // utilitary method to update the needed fields according to user's choices
    public void updateField() throws IOException
    {
        email = mainFrame.getEmail();
        CardType = mainFrame.getSelectedCardType();
        first_name = mainFrame.getFirstName();
        last_name = mainFrame.getLastName();
        chosen_country = mainFrame.getCountry();

        displayTypeChosen();
        displayBeneficiary();
        displayAccount();
        displayCardNr();
        calcAndDisplayDates();
        displayCVV();
        nextButton();
    }


    JTextArea card_type_instr; // text area to show the type of card chosen
    JTextArea owner_instr; // text area to show the name of the beneficiary
    JTextArea account_instr; // a text area to show the associated IBAN
    JTextArea cardNr_instr; // a text area to show the card number of the card
    JTextArea validity_instr; // a text area to show the date of expiration of the card
    JTextArea CVV_instr; // a text area to show the CVV number of the card

    private void displayTypeChosen()
    {
        card_type_instr = new JTextArea();
        card_type_instr.setText("Your " + CardType + " card is");
        card_type_instr.setEditable(false);
        card_type_instr.setFont(new Font("Arial", Font.BOLD, 18));
        card_type_instr.setForeground(Color.BLUE);
        card_type_instr.setBackground(new Color(165, 202, 255));
        card_type_instr.setLineWrap(true);
        card_type_instr.setWrapStyleWord(true);
        card_type_instr.setBounds(50, 50, 300, 50);

        add(card_type_instr);
    }

    private void displayBeneficiary()
    {
        owner_instr = new JTextArea();
        owner_instr.setText("Beneficiary: " + first_name + " " + last_name);
        owner_instr.setEditable(false);
        owner_instr.setFont(new Font("Arial", Font.BOLD, 18));
        owner_instr.setForeground(Color.BLUE);
        owner_instr.setBackground(new Color(165, 202, 255));
        owner_instr.setLineWrap(true);
        owner_instr.setWrapStyleWord(true);
        owner_instr.setBounds(50, 110, 300, 50);

        add(owner_instr);
    }

    private void displayAccount()
    {
        AccIBAN = iban_creator.IBAN_composer(chosen_country);

        account_instr = new JTextArea();
        account_instr.setText("Connected account: " + AccIBAN);
        account_instr.setEditable(false);
        account_instr.setFont(new Font("Arial", Font.BOLD, 18));
        account_instr.setForeground(Color.BLUE);
        account_instr.setBackground(new Color(165, 202, 255));
        account_instr.setLineWrap(true);
        account_instr.setWrapStyleWord(true);
        account_instr.setBounds(50, 170, 300, 50);

        add(account_instr);
    }

    private void displayCardNr()
    {
        cardNumber = iban_creator.generateCardNumber(chosen_country, AccIBAN);
        
        String arrangedCardNumber = "";
        for(int i = 0; i < cardNumber.length() - 1; i += 4)
        {
            String subStr = cardNumber.substring(i, i+4);
            arrangedCardNumber = arrangedCardNumber + subStr + " ";
        }

        cardNr_instr = new JTextArea();
        cardNr_instr.setText("Card number: " + arrangedCardNumber);
        cardNr_instr.setEditable(false);
        cardNr_instr.setFont(new Font("Arial", Font.BOLD, 18));
        cardNr_instr.setForeground(Color.BLUE);
        cardNr_instr.setBackground(new Color(165, 202, 255));
        cardNr_instr.setLineWrap(true);
        //cardNr_instr.setWrapStyleWord(true);
        cardNr_instr.setBounds(50, 230, 300, 50);

        add(cardNr_instr);
    }

    private void calcAndDisplayDates()
    {
        // get the current date
        LocalDate currentDate = LocalDate.now();
        // variable currentDate of type LocalDate
        // can store a date in the format YYYY-MM-DD
        // the currentDate will get the value of current system date

        // extract month and year
        current_month = currentDate.getMonthValue();
        // returns month as an integer

        current_year = currentDate.getYear();
        // returns the year as an integer

        exp_month = Integer.toString(current_month);
        exp_year = Integer.toString(current_year + 10);

        validity_instr = new JTextArea();
        validity_instr.setText("Valid until " + exp_month + "/" + exp_year);
        validity_instr.setEditable(false);
        validity_instr.setFont(new Font("Arial", Font.BOLD, 18));
        validity_instr.setForeground(Color.BLUE);
        validity_instr.setBackground(new Color(165, 202, 255));
        validity_instr.setLineWrap(true);
        validity_instr.setWrapStyleWord(true);
        validity_instr.setBounds(50, 290, 300, 50);

        add(validity_instr);
    }
    
    private void displayCVV()
    {
        int hash = Math.abs((cardNumber + exp_month + exp_year).hashCode());
        // generating a cimple CVV based on card number and expiration date
        // we use hashcode to get an integer representing the hash value of the input object
        // and we used Math.abs() because the method hashCode() can return negative values

        CVV_num = String.valueOf(hash % 1000);
        // truncating the hash to get the last 3 digits

        while(CVV_num.length() < 3)
        // checking is CVV is less than 3 digits
        {
            CVV_num = "0" + CVV_num;
            // pad with 0 to fill in
        }
        // for example if the value is "7", then make it "007"

        CVV_instr = new JTextArea();
        CVV_instr.setText("CVV: " + CVV_num);
        CVV_instr.setEditable(false);
        CVV_instr.setFont(new Font("Arial", Font.BOLD, 18));
        CVV_instr.setForeground(Color.BLUE);
        CVV_instr.setBackground(new Color(165, 202, 255));
        CVV_instr.setLineWrap(true);
        CVV_instr.setWrapStyleWord(true);
        CVV_instr.setBounds(50, 350, 300, 50);

        add(CVV_instr);
    }

    private ArrayList<Integer> card_IDs;
    private JButton nextButton;

    private void nextButton() throws IOException
    {
        RoundedButton nextButton = new RoundedButton("Finish", 20);
        nextButton.setBounds(280, 730, 100, 50);
        nextButton.setFont(new Font("Arial", Font.BOLD, 13));
        nextButton.setBackground(new Color(127, 200, 214));
        nextButton.setForeground(new Color(51, 51, 51));
        nextButton.setBorder(new RoundedBorder(20, Color.GRAY, 3));

        nextButton.setHorizontalAlignment(SwingConstants.CENTER);
        nextButton.setVerticalAlignment(SwingConstants.CENTER);

        nextButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try 
                {
                    int userID = mainFrame.getCurrentUserID();
                    sechiule.DatabaseManager.getInstance().dataCompleted(userID);
                    System.out.println("data completed for user: " + userID);

                    String expdate = exp_month + "/" + exp_year;
                    System.out.println("expir. date: " + expdate);

                    String type = sechiule.DatabaseManager.getInstance().getCardType(userID);
                    System.out.println("the requested card type is: " + type);

                    String currency = userManager.chooseCurrency(chosen_country);
                    System.out.println("currecy: " + currency);

                    sechiule.DatabaseManager.getInstance().insertCardInfo(userID, type, AccIBAN, currency, cardNumber, expdate, CVV_num);
                    System.out.println("inserted a new card with number: " + cardNumber);

                    mainFrame.setCNr(cardNumber);

                    card_IDs = sechiule.DatabaseManager.getInstance().getCardIdByUserID(userID);
                    System.out.println("debug la array cu marimea: " + card_IDs.size() );
                    if(card_IDs.size() == 1)
                    {
                        int currCID = card_IDs.get(0);
                        mainFrame.setCurrentCardID(currCID);

                        mainFrame.bkAccountPage.updateFields();

                        mainFrame.cardLayout.show(mainFrame.mainPanel, "bkAccountPage");
                    }
                    /* 
                    else
                    {
                        // when there are more cards, there will be an extra page where user can select
                        // a card to show

                        //mainFrame.cardLayout.show(mainFrame.mainPanel, "multipleCardsPage");
                    }
                    */
                } 
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                }
                System.out.println("ceva4");

                // next page 
                //mainFrame.cardLayout.show(mainFrame.mainPanel, "bkAccountPage");
            }
        });

        nextButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                nextButton.setBackground(new Color(107, 175, 195)); // Hover color
            }
        
            @Override
            public void mouseExited(MouseEvent e) 
            {
                nextButton.setBackground(new Color(127, 200, 214)); // Original color
            }
        });

        add(nextButton);
    }
}
