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

    public CardObtained(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        displayTypeChosen();
    }

    // Your [type] card is 
    // image
    // Beneficiary: [first name, last name]
    // Connected account: [IBAN]
    // Card number: [card number]
    // Valid until [exp_month / exp_year]
    // CVV: [CVV number]

    JTextArea card_type_instr; // text area to show the type of card chosen
    JTextArea owner_instr; // text area to show the name of the beneficiary

    private void displayTypeChosen()
    {
        card_type_instr = new JTextArea();
        card_type_instr.setEditable(false);
        card_type_instr.setFont(new Font("Arial", Font.BOLD, 18));
        card_type_instr.setForeground(Color.BLUE);
        card_type_instr.setBackground(new Color(165, 202, 255));
        card_type_instr.setLineWrap(true);
        card_type_instr.setWrapStyleWord(true);
        card_type_instr.setBounds(50, 50, 300, 50);

        add(card_type_instr);
    }

    // utilitary method to refresh the display the chosen card type
    public void refreshDisplay()
    {
        String type = mainFrame.getSelectedCardType();
        if(type != null)
        {
            card_type_instr.setText("Your " + type + " card is");
        }
        else
        {
            card_type_instr.setText("no card selected!");
        }
    }

    //public void updateField() throws IOException
    //{
    //    
    //}
//
    //private void displayBeneficiary()
    //{
    //    
    //}
}
