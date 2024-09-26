package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ExistentCardPage extends JPanel
{
    public String user_first_name = "";
    public String user_last_name = "";
    public String user_tel_nr = ""; // phone number
    // data refering to home adress
    public String street_name = "";
    public String home_nr = ""; // or apt. nr
    public String city = ""; 
    public String county = "";
    public String country = "";

    public ExistentCardPage(MainFrame mainFrame)
    {
        setBackground(new Color(110, 20, 90));
        setLayout(null);

        instructions(); 
        user_fields();
    }

    private void instructions()
    {
        JTextArea acc_details_instr = new JTextArea();
        acc_details_instr.setText("We need some information about your bank account first");
        acc_details_instr.setEditable(false);
        acc_details_instr.setFont(new Font("Arial", Font.BOLD, 18));
        acc_details_instr.setForeground(Color.WHITE);
        acc_details_instr.setBackground(new Color(0,0,0,0));
        acc_details_instr.setLineWrap(true);
        acc_details_instr.setWrapStyleWord(true);
        acc_details_instr.setBounds(50, 50, 300, 50);

        // first name is the name given at birth and last name is the family name

        JTextArea fnm_instr = new JTextArea(); // instructions to ask the user to introduce their first name
        fnm_instr.setText("> Please introduce your first name(s): ");
        fnm_instr.setEditable(false);
        fnm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        fnm_instr.setForeground(Color.WHITE);
        fnm_instr.setBackground(new Color(0,0,0,0));
        fnm_instr.setLineWrap(true);
        fnm_instr.setWrapStyleWord(true);
        fnm_instr.setBounds(50, 110, 300, 50);

        JTextArea lstnm_instr = new JTextArea(); // instructions to ask the user to introduce their last name
        lstnm_instr.setText("> Please introduce your last name: ");
        lstnm_instr.setEditable(false);
        lstnm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        lstnm_instr.setForeground(Color.WHITE);
        lstnm_instr.setBackground(new Color(0,0,0,0));
        lstnm_instr.setLineWrap(true);
        lstnm_instr.setWrapStyleWord(true);
        lstnm_instr.setBounds(50, 210, 300, 50);

        JTextArea tel_nr_instr = new JTextArea(); // instructions to ask the user to introduce their phone number
        tel_nr_instr.setText("> Please introduce your phone number: ");
        tel_nr_instr.setEditable(false);
        tel_nr_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        tel_nr_instr.setForeground(Color.WHITE);
        tel_nr_instr.setBackground(new Color(0,0,0,0));
        tel_nr_instr.setLineWrap(true);
        tel_nr_instr.setWrapStyleWord(true);
        tel_nr_instr.setBounds(50, 310, 300, 50);

        JTextArea street_nm_instr = new JTextArea(); // instructions to ask the user to introduce their phone number
        street_nm_instr.setText("> Please introduce the name of the street where your home is located: ");
        street_nm_instr.setEditable(false);
        street_nm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        street_nm_instr.setForeground(Color.WHITE);
        street_nm_instr.setBackground(new Color(0,0,0,0));
        street_nm_instr.setLineWrap(true);
        street_nm_instr.setWrapStyleWord(true);
        street_nm_instr.setBounds(50, 310, 300, 50);

        add(acc_details_instr);
        add(fnm_instr);
        add(lstnm_instr);
        add(tel_nr_instr);
    }

    private JTextField fnm_field; // first name field
    private JTextField lstnm_field; // last name field
    private JTextField tel_nr_field; // phone number field
    private JTextField street_nm_field; // street name field
    private JTextField home_nr_field; // nome or apartment number
    private JTextField city_field; 
    private JTextField county_field;
    private JTextField country_field;

    private void user_fields()
    {
        // field to let the user introduce their first name (fnm)
        fnm_field = new JTextField();
        fnm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        fnm_field.setBounds(50, 170, 300, 30);

        lstnm_field = new JTextField();
        lstnm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        lstnm_field.setBounds(50, 270, 300, 30);

        tel_nr_field = new JTextField();
        tel_nr_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        tel_nr_field.setBounds(50, 370, 300, 30);

        add(fnm_field);
        add(lstnm_field);
        add(tel_nr_field);
    }
}