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
import javax.swing.border.Border;

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

    private String first_name;
    private String last_name;
    private String country_chosen;

    private String LIPemail; // email from log in page
    private String SUPemail; // email from sign up page

    private MainFrame mainFrame;
    private AdressManager adressManager;
    private BkAccIdentity bkAccIdentity;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    public BkAccAdress(MainFrame mainFrame, BkAccIdentity bkAccIdentity) throws Exception
    {
        this.mainFrame = mainFrame;
        adressManager = new AdressManager();
        this.bkAccIdentity = bkAccIdentity;
        loginPage = new LoginPage(mainFrame);
        signUpPage = new SignUpPage(mainFrame);

        setBackground(new Color(250, 243, 221));
        setLayout(null);
    }

    // method to update the page when a country is chosen
    public void updateField() throws IOException
    {
        LIPemail = loginPage.input_mail; // remember to correct this!!!
        SUPemail = mainFrame.getEmail();
        System.out.println("sign up: " + SUPemail + " and log in: " + LIPemail);

        first_name = bkAccIdentity.user_first_name;
        last_name = bkAccIdentity.user_last_name;
        country_chosen = bkAccIdentity.country;

        instructions();
        user_fields();
        nextAndBackButtons();
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
        city_instr.setForeground(Color.BLACK);
        city_instr.setBackground(new Color(0,0,0,0));
        city_instr.setLineWrap(true);
        city_instr.setWrapStyleWord(true);
        city_instr.setBounds(50, 50, 300, 50);

        county_instr = new JTextArea(); // instructions to ask the user to introduce their residency city
        county_instr.setText("> Please introduce the name of your county or district:");
        county_instr.setEditable(false);
        county_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        county_instr.setForeground(Color.BLACK);
        county_instr.setBackground(new Color(0,0,0,0));
        county_instr.setLineWrap(true);
        county_instr.setWrapStyleWord(true);
        county_instr.setBounds(50, 150, 300, 50);

        street_nm_instr = new JTextArea(); // instructions to ask the user to introduce their phone number
        street_nm_instr.setText("> Please introduce the name of the street where your home is located: ");
        street_nm_instr.setEditable(false);
        street_nm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        street_nm_instr.setForeground(Color.BLACK);
        street_nm_instr.setBackground(new Color(0,0,0,0));
        street_nm_instr.setLineWrap(true);
        street_nm_instr.setWrapStyleWord(true);
        street_nm_instr.setBounds(50, 250, 300, 50);

        home_nr_instr = new JTextArea(); // instructions to ask the user to introduce the number of their house/appartment
        home_nr_instr.setText("> Please introduce the number of your home: ");
        home_nr_instr.setEditable(false);
        home_nr_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        home_nr_instr.setForeground(Color.BLACK);
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

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK,4);

        street_nm_field = new JTextField();
        street_nm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        street_nm_field.setBounds(50, 310, 300, 30);
        street_nm_field.setBorder(lineBorder);

        home_nr_field = new JTextField();
        home_nr_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        home_nr_field.setBounds(50, 410, 300, 30);
        home_nr_field.setBorder(lineBorder);

        street_nm_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                street_name = street_nm_field.getText();
                home_nr_field.requestFocusInWindow();
            }
        });

        street_nm_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                street_name = street_nm_field.getText();
            }
        });

        home_nr_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                home_nr = home_nr_field.getText();
            }
        });

        home_nr_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                home_nr = home_nr_field.getText();
            }
        });

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

    private JButton backButton;
    private JButton nextButton;

    private JTextArea warning; 
    // a warning to let the user know they still have to complete fields

    private void nextAndBackButtons() throws IOException
    {
        RoundedButton backButton = new RoundedButton("Back", 20);
        backButton.setBounds(20, 730, 100, 50);
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setBackground(new Color(217, 217, 217));
        backButton.setForeground(new Color(51, 51, 51));
        backButton.setBorder(new RoundedBorder(20, Color.black, 3));

        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setVerticalAlignment(SwingConstants.CENTER);


        RoundedButton nextButton = new RoundedButton("Next", 20);
        nextButton.setBounds(280, 730, 100, 50);
        nextButton.setFont(new Font("Arial", Font.BOLD, 13));
        nextButton.setBackground(new Color(127, 200, 214));
        nextButton.setForeground(new Color(51, 51, 51));
        nextButton.setBorder(new RoundedBorder(20, Color.GRAY, 3));

        nextButton.setHorizontalAlignment(SwingConstants.CENTER);
        nextButton.setVerticalAlignment(SwingConstants.CENTER);

        warning = new JTextArea();
        warning.setText("Please complete all fields!");// display the warning message
        warning.setEditable(false);
        warning.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        warning.setForeground(Color.RED);
        warning.setBackground(new Color(0,0,0,0));
        warning.setLineWrap(true);
        warning.setWrapStyleWord(true);
        warning.setVisible(false);
        warning.setBounds(110, 730, 180, 50);

        // goes back to card type selection page
        backButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainFrame.cardLayout.show(mainFrame.mainPanel, "BkAccIdentity");
            }
        });

        backButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                backButton.setBackground(new Color(194, 194, 194)); // Hover color
            }
        
            @Override
            public void mouseExited(MouseEvent e) 
            {
                backButton.setBackground(new Color(217, 217, 217)); // Original color
            }
        });

        // goes to the page 2 of the bank account creation
        nextButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!(city.isEmpty()) && !(county.isEmpty()) && !(street_name.isEmpty()) && !(home_nr.isEmpty()))
                // checking if all of the fields are completed
                {
                    System.out.println("ceva");
                    // used in signing up for the first time
                    if(LIPemail.isEmpty())
                    {
                        try
                        {
                            System.out.println("log in empty");
                            System.out.println("the introduced email is: " + SUPemail);
                            //userManager.AddBankAcc(SUPemail, first_name, last_name, tel_nr, country_chosen, city, county, street_name, home_nr);
                            // method to add the bank account credentials to the database (a file)

                            // setters for data used in other pages
                            mainFrame.setFirstName(first_name);
                            mainFrame.setLastName(last_name);
                            mainFrame.setChosenCountry(country_chosen);

                            mainFrame.cardObtained.updateField();

                            int userID = mainFrame.getCurrentUserID();
                            sechiule.DatabaseManager.getInstance().updateBkAccAddress(userID, city, county, street_name, home_nr);
                            
                            // next page
                            mainFrame.cardLayout.show(mainFrame.mainPanel, "cardObtained");
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    // will be used when user already logged in but wants to add a new card/account
                    else if(SUPemail.isEmpty())
                    {
                        try
                        {
                            System.out.println("sign up empty");
                            //userManager.AddBankAcc(LIPemail, first_name, last_name, tel_nr, country_chosen, city, county, street_name, home_nr);
                            // method to add the bank account credentials to the database (a file)

                            // setters for the first name and last name to use them for other pages
                            mainFrame.setFirstName(first_name);
                            mainFrame.setLastName(last_name);

                            mainFrame.cardObtained.updateField();

                            // next page
                            mainFrame.cardLayout.show(mainFrame.mainPanel, "cardObtained");
                        }
                        catch(IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
                else
                {
                    warning.setVisible(true);
                    System.out.println("please introduce your data");
                }
                
            }
        });

        nextButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                nextButton.setBackground(new Color(107, 175, 195)); // Hover color
            }
        
            @Override
            public void mouseExited(MouseEvent e) 
            {
                nextButton.setBackground(new Color(127, 200, 214)); // Original color
            }
        });

        add(backButton);
        add(nextButton);
    }
}