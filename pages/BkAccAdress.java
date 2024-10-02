/* the page where user can introduce their home adress 
    (city, county, street name, home number)
    for the bank account information
    if the user wants to create a card
    (supported by Credit, Debit, Kids, Virtual card types)

    BkAcc - bank account
*/

package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BkAccAdress extends JPanel
{
    public String city = ""; 
    public String county = "";
    public String street_name = "";
    public String home_nr = ""; // or apt. nr

    private MainFrame mainFrame;
    private AdressManager adressManager;
    private BkAccIdentity bkAccIdentity;

    public BkAccAdress(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        adressManager = new AdressManager();

        setBackground(new Color(110, 20, 90));
        setLayout(null);
        
        instructions();
        user_fields();
    }

    private JTextArea city_instr;
    private JTextArea county_instr;
    private JTextArea street_nm_instr;
    private JTextArea home_nr_instr;

    private void instructions()
    {
        city_instr = new JTextArea(); // instructions to ask the user to introduce their residency city
        city_instr.setText("> Please introduce the name of your city of residency:");
        city_instr.setEditable(false);
        city_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        city_instr.setForeground(Color.WHITE);
        city_instr.setBackground(new Color(0,0,0,0));
        city_instr.setLineWrap(true);
        city_instr.setWrapStyleWord(true);
        city_instr.setBounds(50, 50, 300, 50);

        street_nm_instr = new JTextArea(); // instructions to ask the user to introduce their phone number
        street_nm_instr.setText("> Please introduce the name of the street where your home is located: ");
        street_nm_instr.setEditable(false);
        street_nm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        street_nm_instr.setForeground(Color.WHITE);
        street_nm_instr.setBackground(new Color(0,0,0,0));
        street_nm_instr.setLineWrap(true);
        street_nm_instr.setWrapStyleWord(true);
        street_nm_instr.setBounds(50, 50, 300, 50);

        home_nr_instr = new JTextArea(); // instructions to ask the user to introduce the number of their house/appartment
        home_nr_instr.setText("> Please introduce the number of your home: ");
        home_nr_instr.setEditable(false);
        home_nr_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        home_nr_instr.setForeground(Color.WHITE);
        home_nr_instr.setBackground(new Color(0,0,0,0));
        home_nr_instr.setLineWrap(true);
        home_nr_instr.setWrapStyleWord(true);
        home_nr_instr.setBounds(50, 480, 300, 50);

        add(street_nm_instr);
        add(home_nr_instr);
    }

    private JComboBox<String> city_field; 
    //private JComboBox<String> county_field;
    private JTextField street_nm_field; // street name field
    private JTextField home_nr_field; // nome or apartment number

    private String[] cities; // an array that will hold all available 
                            //cities for the previously selected country
    //private String[] counties;
    
    private void user_fields() throws IOException
    {   
        cities = adressManager.getCitiesByCountry();

        street_nm_field = new JTextField();
        street_nm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        street_nm_field.setBounds(50, 440, 300, 30);

        home_nr_field = new JTextField();
        home_nr_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        home_nr_field.setBounds(50, 540, 300, 30);

        add(street_nm_field);
        add(home_nr_field);
    }


}