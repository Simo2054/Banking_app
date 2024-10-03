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
import java.util.*;

public class BkAccAdress extends JPanel
{
    public String city = ""; 
    public String county = "";
    public String street_name = "";
    public String home_nr = ""; // or apt. nr

    private MainFrame mainFrame;
    private AdressManager adressManager;
    private BkAccIdentity bkAccIdentity;

    public BkAccAdress(MainFrame mainFrame, BkAccIdentity bkAccIdentity) throws IOException
    {
        this.mainFrame = mainFrame;
        adressManager = new AdressManager();
        this.bkAccIdentity = bkAccIdentity;

        setBackground(new Color(110, 20, 90));
        setLayout(null);
    }

    private String country_chosen;

    // method to update the page when a county is chosen
    public void updateField() throws IOException
    {
        country_chosen = bkAccIdentity.country;
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

        county_instr = new JTextArea(); // instructions to ask the user to introduce their residency city
        county_instr.setText("> Please introduce the name of your county or district:");
        county_instr.setEditable(false);
        county_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        county_instr.setForeground(Color.WHITE);
        county_instr.setBackground(new Color(0,0,0,0));
        county_instr.setLineWrap(true);
        county_instr.setWrapStyleWord(true);
        county_instr.setBounds(50, 150, 300, 50);

        street_nm_instr = new JTextArea(); // instructions to ask the user to introduce their phone number
        street_nm_instr.setText("> Please introduce the name of the street where your home is located: ");
        street_nm_instr.setEditable(false);
        street_nm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        street_nm_instr.setForeground(Color.WHITE);
        street_nm_instr.setBackground(new Color(0,0,0,0));
        street_nm_instr.setLineWrap(true);
        street_nm_instr.setWrapStyleWord(true);
        street_nm_instr.setBounds(50, 250, 300, 50);

        home_nr_instr = new JTextArea(); // instructions to ask the user to introduce the number of their house/appartment
        home_nr_instr.setText("> Please introduce the number of your home: ");
        home_nr_instr.setEditable(false);
        home_nr_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        home_nr_instr.setForeground(Color.WHITE);
        home_nr_instr.setBackground(new Color(0,0,0,0));
        home_nr_instr.setLineWrap(true);
        home_nr_instr.setWrapStyleWord(true);
        home_nr_instr.setBounds(50, 350, 300, 50);

        add(city_instr);
        add(county_instr);
        add(street_nm_instr);
        add(home_nr_instr);
    }

    private JComboBox<String> city_field; 
    private JComboBox<String> county_field;
    private JTextField street_nm_field; // street name field
    private JTextField home_nr_field; // nome or apartment number

    private String[] citiesArray; // an array that will hold all available 
                            //cities for the previously selected country
    private String[] countiesArray;
    
    private void user_fields() throws IOException
    {
        initCityField();
        initCountyField();

        street_nm_field = new JTextField();
        street_nm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        street_nm_field.setBounds(50, 310, 300, 30);

        home_nr_field = new JTextField();
        home_nr_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        home_nr_field.setBounds(50, 410, 300, 30);

        add(street_nm_field);
        add(home_nr_field);
    }

    // method to initialize the dropdown list for cities depending on the country chosen previously
    private void initCityField()
    {
        citiesArray = adressManager.getCitiesByCountry(country_chosen);
        Arrays.sort(citiesArray);
        city_field = new JComboBox<>(citiesArray);
        city_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        city_field.setBounds(50, 110, 300, 30);

        city_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedOption = (String) city_field.getSelectedItem();

                city = selectedOption;
                System.out.println("city: " + city);

                county_field.setEnabled(true); // making the field for county available when a city is chosen
                updateCountyField(country_chosen, city); // method used to refresh the field each time a city is chosen
            }
        });

        add(city_field);
    }

    // method to initialize the dropdown list of counties depending on the city chosen previously
    private void initCountyField()
    {
        countiesArray = adressManager.getDistrictsByCity(country_chosen, city);
        Arrays.sort(countiesArray);
        county_field = new JComboBox<>(countiesArray);
        county_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        county_field.setBounds(50, 210, 300, 30);
        county_field.setEnabled(false); // since there will be no city chosen initially, the field will be unavailable

        county_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedOption = (String) county_field.getSelectedItem();

                county = selectedOption;
                System.out.println("county: " + county);
            }
        });

        add(county_field);
    }

    // method used to update the county field each time a city is chosen
    private void updateCountyField(String country, String city)
    {   
        county_field.removeAllItems(); // removes all counties from the previous city
        Arrays.fill(countiesArray, null); // removes all elements from the array
        countiesArray = adressManager.getDistrictsByCity(country, city); // reinitialize the array of counties available
        Arrays.sort(countiesArray);

        for(String county : countiesArray)
        {
            county_field.addItem(county); 
            // for each element in the array (county available in the file), we add it to the county field
        }

        // revalidate and repaint the GUI elements
        revalidate();
        repaint();
    }
}