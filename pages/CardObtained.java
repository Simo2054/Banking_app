package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.time.*;

public class CardObtained extends JPanel
{
    private MainFrame mainFrame;
    private BkAccIdentity bkAccIdentity;
    private IBAN_creator iban_creator;

    public CardObtained(MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;
        bkAccIdentity = new BkAccIdentity(mainFrame);
        iban_creator = new IBAN_creator();

        setBackground(new Color(110, 20, 90));
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

    private String cardNumber; // card number of the generated card
    
    private int current_month;
    private int current_year;

    private String exp_month; // expiration month of the card
    private String exp_year; // expiration year of the card

    private String CVV_num; // CVV number for the generated card

    // utilitary method to update the needed fields according to user's choices
    public void updateField() //throws IOException
    {
        CardType = mainFrame.getSelectedCardType();
        first_name = mainFrame.getFirstName();
        last_name = mainFrame.getLastName();
        chosen_country = mainFrame.getCountry();

        System.out.println("first name: " + first_name + " last name: " + last_name);
        System.out.println("country: " + chosen_country);

        displayTypeChosen();
        displayBeneficiary();
        displayAccount();
        displayCardNr();
        calcAndDisplayDates();
        displayCVV();
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

    private JButton nextButton;

    private void nextButton()
    {
        nextButton = new JButton("Got it !");
        nextButton.setBounds(300, 730, 80, 50);

        nextButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // next page 
                mainFrame.cardLayout.show(mainFrame.mainPanel, "bkAccountPage");
            }
        });

        add(nextButton);
    }
}
