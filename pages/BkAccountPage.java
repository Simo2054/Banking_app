package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BkAccountPage extends JPanel
{
    private MainFrame mainFrame;

    public BkAccountPage(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        header();
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

    private String accType; // banking account type
    private String first_name;
    private String last_name;
    private String IBAN;

    private String card_number;
    private String CVV;

    private void updateFields()
    {

    }

    JPanel head;
    JTextArea owner_acc; // a text area to contain "[username]'s Account:"

    private void header()
    {
        head = new JPanel();
        head.setLayout(null);
        head.setBackground(Color.cyan);
        head.setBounds(50, 50, 300, 300);

        owner_acc = new JTextArea();
        owner_acc.setText("'s Account:");
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
