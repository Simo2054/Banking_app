/* the page where user can introduce their identity 
    (first name, last name, phone number, country)
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
import java.util.Arrays;

public class BkAccIdentity extends JPanel
{
    public String user_first_name = "";
    public String user_last_name = "";
    public String user_tel_nr = ""; // phone number
    public String country = "";

    private MainFrame mainFrame;
    private AdressManager adressManager;

    public BkAccIdentity(MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;
        adressManager = new AdressManager();

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        instructions(); 
        user_fields();

        nextAndBackButtons();
    }

    private JTextArea fnm_instr;
    private JTextArea lstnm_instr;
    private JTextArea tel_nr_instr;
    private JTextArea country_instr;

    private void instructions()
    {
        JTextArea acc_details_instr = new JTextArea();
        acc_details_instr.setText("Please introduce your data for creating a bank account first: ");
        acc_details_instr.setEditable(false);
        acc_details_instr.setFont(new Font("Arial", Font.BOLD, 18));
        acc_details_instr.setForeground(Color.BLUE);
        acc_details_instr.setBackground(new Color(165, 202, 255));
        acc_details_instr.setLineWrap(true);
        acc_details_instr.setWrapStyleWord(true);
        acc_details_instr.setBounds(50, 50, 300, 50);

        // first name is the name given at birth and last name is the family name

        // instructions to ask the user to introduce their first name
        fnm_instr = new JTextArea(); 
        fnm_instr.setText("> Please introduce your first name(s): ");
        fnm_instr.setEditable(false);
        fnm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        fnm_instr.setForeground(Color.WHITE);
        fnm_instr.setBackground(new Color(0,0,0,0));
        fnm_instr.setLineWrap(true);
        fnm_instr.setWrapStyleWord(true);
        fnm_instr.setBounds(50, 160, 300, 50);

        // instructions to ask the user to introduce their last name
        lstnm_instr = new JTextArea(); 
        lstnm_instr.setText("> Please introduce your last name: ");
        lstnm_instr.setEditable(false);
        lstnm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        lstnm_instr.setForeground(Color.WHITE);
        lstnm_instr.setBackground(new Color(0,0,0,0));
        lstnm_instr.setLineWrap(true);
        lstnm_instr.setWrapStyleWord(true);
        lstnm_instr.setBounds(50, 260, 300, 20);

        // instructions to ask the user to introduce their phone number
        tel_nr_instr = new JTextArea(); 
        tel_nr_instr.setText("> Please introduce your phone number: ");
        tel_nr_instr.setEditable(false);
        tel_nr_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        tel_nr_instr.setForeground(Color.WHITE);
        tel_nr_instr.setBackground(new Color(0,0,0,0));
        tel_nr_instr.setLineWrap(true);
        tel_nr_instr.setWrapStyleWord(true);
        tel_nr_instr.setBounds(50, 320, 300, 50);

        // instructions to ask the user to select their country
        country_instr = new JTextArea();
        country_instr.setText("> Please pick the name of your country: ");
        country_instr.setEditable(false);
        country_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        country_instr.setForeground(Color.WHITE);
        country_instr.setBackground(new Color(0,0,0,0));
        country_instr.setLineWrap(true);
        country_instr.setWrapStyleWord(true);
        country_instr.setBounds(50, 420, 300, 50);

        add(acc_details_instr);
        add(fnm_instr);
        add(lstnm_instr);
        add(tel_nr_instr);
        add(country_instr);
    }

    // ----------------------------------------------------------------------------------------------------------------
    private JTextField fnm_field; // first name field
    private JTextField lstnm_field; // last name field
    private JTextField tel_nr_field; // phone number field
    private JComboBox<String> country_option; // lets user select their country from a dropdown list
    // "<String>" means that this combo box will only allow options of type string
    private String[] options; // an array that will hold all available countries
    // ----------------------------------------------------------------------------------------------------------------

    private void user_fields() throws IOException
    {
        // field to let the user introduce their first name (fnm)
        fnm_field = new JTextField();
        fnm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        fnm_field.setBounds(50, 220, 300, 30);

        // field to let the user introduce their last name (lstnm)
        lstnm_field = new JTextField();
        lstnm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        lstnm_field.setBounds(50, 290, 300, 30);

        tel_nr_field = new JTextField();
        tel_nr_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        tel_nr_field.setBounds(50, 380, 300, 30);

        options = adressManager.getCountries();// initializing the options array using getCountries method
        Arrays.sort(options); // sort the array alphabetically
        country_option = new JComboBox<>(options); // using the array to create a dropdown list of all countries available
        country_option.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        country_option.setBounds(50, 480, 300, 30);

        fnm_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                user_first_name = fnm_field.getText();
                lstnm_field.requestFocusInWindow();
            }
        });

        fnm_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
                warning.setVisible(false);
            }
            public void focusLost(FocusEvent e)
            {
                user_first_name = fnm_field.getText();
                
                //lstnm_field.requestFocusInWindow();
            }
        });

        lstnm_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                user_last_name = lstnm_field.getText();
                tel_nr_field.requestFocusInWindow();
            }
        });

        lstnm_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
                warning.setVisible(false);
            }
            public void focusLost(FocusEvent e)
            {
                user_last_name = lstnm_field.getText();
                
                //tel_nr_field.requestFocusInWindow();
            }
        });

        tel_nr_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                user_tel_nr = tel_nr_field.getText();
                country_option.requestFocusInWindow();
            }
        });

        tel_nr_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
                warning.setVisible(false);
            }
            public void focusLost(FocusEvent e)
            {
                user_tel_nr = tel_nr_field.getText();
                
                //country_option.requestFocusInWindow();
            }
        });

        country_option.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // get the selected item
                String selectedOption = (String) country_option.getSelectedItem();

                country = selectedOption;
                System.out.println("option: " + country);
            }
        });

        add(fnm_field);
        add(lstnm_field);
        add(tel_nr_field);
        add(country_option);
    }

    private JButton backButton;
    private JButton nextButton;

    private JTextArea warning; 
    // a warning to let the user know they still have to complete fields

    private void nextAndBackButtons() throws IOException
    {
        backButton = new JButton("Back");
        backButton.setBounds(20, 730, 80, 50);

        nextButton = new JButton("Next");
        nextButton.setBounds(300, 730, 80, 50);

        warning = new JTextArea();
        warning.setText("Please complete all fields!");// display the warning message
        warning.setEditable(false);
        warning.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        warning.setForeground(Color.PINK);
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
                mainFrame.cardLayout.show(mainFrame.mainPanel, "cardTypesPanel");
            }
        });

        // goes to the page 2 of the bank account creation
        nextButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!(user_first_name.isEmpty()) && !(user_last_name.isEmpty()) && !(user_tel_nr.isEmpty()) && !(country.isEmpty()))
                // checking if all of the fields are completed
                {
                    try
                    {
                        // method to update the aspects linked to country when a country is chosen
                        mainFrame.BkAccAdress.updateField();

                        String email = mainFrame.getEmail();
                        sechiule.DatabaseManager.getInstance().updateBkAccDataIdentity(email, user_first_name, user_last_name, user_tel_nr, country);

                        // shows the next page
                        mainFrame.cardLayout.show(mainFrame.mainPanel, "BkAccAdress");
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                else
                {
                    System.out.println("first name: " + user_first_name);
                    System.out.println("last name: " + user_last_name);
                    System.out.println("phone number: " + user_tel_nr);
                    System.out.println("country chosen: " + country);
                    warning.setVisible(true);
                    System.out.println("altceva");
                }
                
            }
        });

        add(warning);
        add(backButton);
        add(nextButton);
    }
}