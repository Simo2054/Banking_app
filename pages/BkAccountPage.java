// the page that will show the account if the chosen option at the beginning is "Sign up"

package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

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

        menu_for_user();
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
        sum_of_money = bankAccount.showMoney(IBAN);

        header();
    }

    JPanel head;
    JTextArea owner_acc; // a text area to contain "[username]'s Account:"
    JTextArea money_and_currency; // a text area to show the user how much money they have in their account

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

        money_and_currency = new JTextArea();
        money_and_currency.setText(sum_of_money + " " + currency);
        money_and_currency.setEditable(false);
        money_and_currency.setFont(new Font("Arial", Font.BOLD, 18));
        money_and_currency.setForeground(Color.BLUE);
        money_and_currency.setBackground(new Color(165, 202, 255));
        money_and_currency.setLineWrap(true);
        money_and_currency.setWrapStyleWord(true);
        money_and_currency.setBounds(0, 55, 300, 50);

        head.add(owner_acc);
        head.add(money_and_currency);

        add(head);
    }

    JMenuBar menuBar; 
    JMenu history, account_info, card_info, functions;

    private void menu_for_user()
    {
        menuBar = new JMenuBar();
        menuBar.setBounds(50, 200, 300, 50);
        

        history = new JMenu();
        history.setText("History");
        history.setFont(new Font("Arial", Font.BOLD, 18));
        history.setOpaque(true); // make background visible
        history.setBackground(Color.blue);
        history.setForeground(Color.cyan);
        history.setBorder(new LineBorder(Color.pink, 2, true));

        account_info = new JMenu();
        account_info.setText("Account");
        account_info.setFont(new Font("Arial", Font.BOLD, 18));
        account_info.setOpaque(true); // make background visible
        account_info.setBackground(Color.blue);
        account_info.setForeground(Color.cyan);
        account_info.setBorder(new LineBorder(Color.pink, 2, true));

        card_info = new JMenu();
        card_info.setText("Card");
        card_info.setFont(new Font("Arial", Font.BOLD, 18));
        card_info.setOpaque(true); // make background visible
        card_info.setBackground(Color.blue);
        card_info.setForeground(Color.cyan);
        card_info.setBorder(new LineBorder(Color.pink, 2, true));

        functions = new JMenu();
        functions.setText("Functions");
        functions.setFont(new Font("Arial", Font.BOLD, 18));
        functions.setOpaque(true); // make background visible
        functions.setBackground(Color.blue);
        functions.setForeground(Color.cyan);
        functions.setBorder(new LineBorder(Color.PINK, 2, true));

        history.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });

        menuBar.add(history);
        menuBar.add(account_info);
        menuBar.add(card_info);
        menuBar.add(functions);

        add(menuBar);
    }

    /* 
    [merchant_name] -[sum] for purchases
    [merchant_name] +[sum] for receivings
    */

    private void openHistory()
    {
        JTextArea transactionHistory;
        
    }
}
