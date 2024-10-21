package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class CardObtained extends JPanel
{


    private MainFrame mainFrame;
    private BkAccIdentity bkAccIdentity;

    public CardObtained(MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;
        bkAccIdentity = new BkAccIdentity(mainFrame);

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
    private String AccIBAN; // IBAN of the connected account
    private String cardNumber; // card number of the generated card
    private String exp_month; // expiration month of the card
    private String exp_year; // expiration year of the card
    private String CVC_num; // CVC number for the generated card

    // utilitary method to update the needed fields according to user's choices
    public void updateField() //throws IOException
    {
        CardType = mainFrame.getSelectedCardType();
        first_name = mainFrame.getFirstName();
        last_name = mainFrame.getLastName();
        System.out.println("first name: " + first_name + " last name: " + last_name);

        displayTypeChosen();
        displayBeneficiary();
    }


    JTextArea card_type_instr; // text area to show the type of card chosen
    JTextArea owner_instr; // text area to show the name of the beneficiary

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
    
}
