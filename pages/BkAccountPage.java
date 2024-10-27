// the page that will show the account if the chosen option at the beginning is "Sign up"

package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class BkAccountPage extends JPanel
{
    private MainFrame mainFrame;
    private BankAccount bankAccount;

    public BkAccountPage(MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;
        bankAccount = new BankAccount();

        setBackground(new Color(110, 20, 90));
        setLayout(null);
    }

    /* 
    [username]'s Account:
    [sum of money] [currency]

    menu: [history] [account info] [card info] [functions]

    - history -> transaction history
    - account info -> type, owner (names), iban
    - card info -> illustrative photo, card number, card names, cvv
    - functions -> new manual transfer, converter (refers to currency), settings
    */

    private String username;

    private int sum_of_money; // sum of money in the bank account
    private String currency; // currency based on the chosen country
    private String chosen_country;

    private String accType; // banking account type
    private String first_name;
    private String last_name;
    private String IBAN;

    private String card_number;
    private String CVV;

    // utilitary method to update the fields according to user's choices/input
    public void updateFields()
    {
        username = mainFrame.getUsername();
        chosen_country = mainFrame.getCountry();
        currency = bankAccount.chooseCurrency(chosen_country);
        IBAN = mainFrame.getIBAN();
    }

    JPanel head;
    JTextArea owner_acc; // a text area to contain "[username]'s Account:"

    private void header()
    {
        head = new JPanel();
        head.setLayout(null);
        head.setBackground(Color.cyan);
        head.setBounds(50, 50, 300, 150);
        // this needs border

        owner_acc = new JTextArea();
        owner_acc.setText(username + "'s Account:");
        owner_acc.setEditable(false);
        owner_acc.setFont(new Font("Arial", Font.BOLD, 18));
        owner_acc.setForeground(Color.BLUE);
        owner_acc.setBackground(new Color(165, 202, 255));
        owner_acc.setLineWrap(true);
        owner_acc.setWrapStyleWord(true);
        owner_acc.setBounds(0, 0, 300, 50);

        

        head.add(owner_acc);

        add(head);
    }
}
