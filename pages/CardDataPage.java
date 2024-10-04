package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CardDataPage extends JPanel
{
    public String card_nr; // the series of numbers on the card
    public String card_name; // the name on the card
    public String exp_date; // expiration date on the card
    public String CVC_code; // cvc code found on card's back

    private MainFrame mainFrame;

    public CardDataPage(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        instructions();
        user_fields();
        expDateInteraction();
    }

    private JTextArea cardNr_instr;
    private JTextArea cardNm_instr;
    private JTextArea expDate_instr;
    private JTextArea CVCCode_instr;

    private void instructions()
    {
        // instructions to ask the user to introduce the series of numbers from their card
        cardNr_instr = new JTextArea();
        cardNr_instr.setText("> Please introduce the series of numbers on your card: ");
        cardNr_instr.setEditable(false);
        cardNr_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        cardNr_instr.setForeground(Color.WHITE);
        cardNr_instr.setBackground(new Color(0,0,0,0));
        cardNr_instr.setLineWrap(true);
        cardNr_instr.setWrapStyleWord(true);
        cardNr_instr.setBounds(50, 200, 300, 50);

        // instructions to ask the user to introduce the name from their card
        cardNm_instr = new JTextArea(); 
        cardNm_instr.setText("> Please introduce the name on your card: ");
        cardNm_instr.setEditable(false);
        cardNm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        cardNm_instr.setForeground(Color.WHITE);
        cardNm_instr.setBackground(new Color(0,0,0,0));
        cardNm_instr.setLineWrap(true);
        cardNm_instr.setWrapStyleWord(true);
        cardNm_instr.setBounds(50, 300, 300, 50);

        // instructions to ask the user to introduce the expiration date from their card
        expDate_instr = new JTextArea(); 
        expDate_instr.setText("> Please introduce the expiration date on your card: ");
        expDate_instr.setEditable(false);
        expDate_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        expDate_instr.setForeground(Color.WHITE);
        expDate_instr.setBackground(new Color(0,0,0,0));
        expDate_instr.setLineWrap(true);
        expDate_instr.setWrapStyleWord(true);
        expDate_instr.setBounds(50, 400, 300, 50);

        // instructions to ask the user to introduce the CVC/CVV code from their card
        CVCCode_instr = new JTextArea(); 
        CVCCode_instr.setText("> Please introduce the CVC code on your card: ");
        CVCCode_instr.setEditable(false);
        CVCCode_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        CVCCode_instr.setForeground(Color.WHITE);
        CVCCode_instr.setBackground(new Color(0,0,0,0));
        CVCCode_instr.setLineWrap(true);
        CVCCode_instr.setWrapStyleWord(true);
        CVCCode_instr.setBounds(50, 500, 300, 50);

        add(cardNr_instr);
        add(cardNm_instr);
        add(expDate_instr);
        add(CVCCode_instr);
    }

    private JTextField cardNr_field; // series of numbers field
    private JTextField cardNm_field; // name on card field
    private JTextField CVCCode_field; // CVC code on card field

    private JComboBox<String> expDate_month; // expiration date month
    private JComboBox<String> expDate_year; // expiration date year

    private String[] months = {"Month", "January", "February", "March", "April", 
                                "May", "June", "July", "August", "September", 
                                "October", "November", "December"}; // array to hold months
    private String[] years; // array to hold years

    private void user_fields()
    {
        cardNr_field = new JTextField();
        cardNr_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        cardNr_field.setBounds(50, 260, 300, 30);

        cardNm_field = new JTextField();
        cardNm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        cardNm_field.setBounds(50, 360, 300, 30);

        CVCCode_field = new JTextField();
        CVCCode_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        CVCCode_field.setBounds(50, 560, 300, 30);

        add(cardNr_field);
        add(cardNm_field);
        add(CVCCode_field);
    }

    private void expDateInteraction()
    {
        years = new String[1101];
        years[0] = "Year";
        int index = 1;
        for(int i=1900; i<3000; i++)
        {
            years[index] = String.valueOf(i);
            index++;
        }

        expDate_month = new JComboBox<>(months); 
        // using the months array to create a dropdown list of months
        expDate_year = new JComboBox<>(years);
        // using the years array to create a dropdown list of years

        expDate_month.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        expDate_month.setBounds(50, 460, 150, 30);
        expDate_year.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        expDate_year.setBounds(250, 460, 100, 30);

        add(expDate_month);
        add(expDate_year);
    }

    

}