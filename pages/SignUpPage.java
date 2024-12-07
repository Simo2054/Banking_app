package pages;

import Card_types.*;
import managers.*;
import sechiule.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class SignUpPage extends JPanel 
{
    public String new_user_email = "";
    public String new_username = "";
    public String new_user_password = "";
    public String new_safety_user_password = "";

    private UserManager userManager;

    private MainFrame mainFrame;

    public SignUpPage(MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;

        userManager = new UserManager();
        // initializing user manager to handle users

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        // components of the Sign up page:
        instructions_fields();
        user_input_fields();

        signUpCheck(); // checking the input credentials

        BackButton(); // a button to go back to previous page
    }

    private void instructions_fields()
    {
        JTextArea mail_instr = new JTextArea("Please introduce an e-mail adress for your account:");
        mail_instr.setBounds(50, 200, 300, 50);
        mail_instr.setEditable(false);
        mail_instr.setFont(new Font("Arial", Font.BOLD, 18));
        mail_instr.setForeground(Color.WHITE);
        mail_instr.setBackground(new Color(0,0,0,0));
        mail_instr.setLineWrap(true);
        mail_instr.setWrapStyleWord(true);

        JTextArea username_instr = new JTextArea("How would you like us to call you? Please introduce your username:");
        username_instr.setBounds(50, 340, 300, 55);
        username_instr.setEditable(false);
        username_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        username_instr.setForeground(Color.WHITE);
        username_instr.setBackground(new Color(0,0,0,0));
        username_instr.setLineWrap(true);
        username_instr.setWrapStyleWord(true);

        JTextArea pass_instr = new JTextArea("Please introduce a new password for your account:");
        pass_instr.setBounds(50, 450, 300, 50);
        pass_instr.setEditable(false);
        pass_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        pass_instr.setForeground(Color.WHITE);
        pass_instr.setBackground(new Color(0,0,0,0));
        pass_instr.setLineWrap(true);
        pass_instr.setWrapStyleWord(true);

        JTextArea safety_pass_instr = new JTextArea("Please introduce your new password one more time:");
        safety_pass_instr.setBounds(50, 560, 300, 50);
        safety_pass_instr.setEditable(false);
        safety_pass_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        safety_pass_instr.setForeground(Color.WHITE);
        safety_pass_instr.setBackground(new Color(0,0,0,0));
        safety_pass_instr.setLineWrap(true);
        safety_pass_instr.setWrapStyleWord(true);

        add(mail_instr);
        add(username_instr);
        add(pass_instr);
        add(safety_pass_instr);
    }

    private JTextField email_field;
    private JTextField username_field;
    private JPasswordField pass_field;
    private JPasswordField safety_pass_field;

    private void user_input_fields()
    {
        email_field = new JTextField();
        email_field.setBounds(50, 260 , 300, 40);
        email_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        username_field = new JTextField();
        username_field.setBounds(50, 400, 300, 40);
        username_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        pass_field = new JPasswordField();
        pass_field.setBounds(50, 510, 300, 40);
        pass_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        safety_pass_field = new JPasswordField();
        safety_pass_field.setBounds(50, 620, 300, 40);
        safety_pass_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
    
        email_field.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent press)
            {
                new_user_email = email_field.getText();
                System.out.println("act. perf. -> user: " + new_user_email);
                warning.setVisible(false);
                username_field.requestFocusInWindow();
            }
        });

        email_field.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                new_user_email = email_field.getText();
                System.out.println("focus lost -> user: " + new_user_email);

                try
                {
                    //checking if an account with the same e-mail adress already exists
                    if(sechiule.DatabaseManager.getInstance().userExists(new_user_email))
                    {
                        warning.setBounds(50, 300, 300, 40);
                        warning.setText("An account with this e-mail adress already exists!");// display the warning message
                        warning.setVisible(true);
                        email_field.requestFocusInWindow();
                    }

                    // checks if the introduced email contains a domain
                    if(!(userManager.checkValidMail(new_user_email)))
                    {
                        warning.setBounds(50, 300, 300, 40);
                        warning.setText("E-Mail adress not available");// display the warning message
                        warning.setVisible(true);
                        email_field.requestFocusInWindow();
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        username_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent press)
            {
                new_username = username_field.getText();
                System.out.println("username: " + new_username);
                warning.setVisible(false);
                pass_field.requestFocusInWindow();
            }
        });

        username_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                new_username = username_field.getText();
                System.out.println("username: " + new_username);
                //warning.setVisible(false); // for the case where one or more data is missing
            }
        });

        pass_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent press)
            {
                char[] passwordChars = pass_field.getPassword();
                new_user_password = new String(passwordChars);
                System.out.println("password: " + new_user_password);
                warning.setVisible(false);
                safety_pass_field.requestFocusInWindow();
            }
        });

        pass_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                char[] passwordChars = pass_field.getPassword();
                new_user_password = new String(passwordChars);
                System.out.println("password: " + new_user_password);
            }
        });

        safety_pass_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent press)
            {
                char[] safetyPassChars = safety_pass_field.getPassword();
                new_safety_user_password = new String(safetyPassChars);
                System.out.println("2nd pass: " + new_safety_user_password);
                warning.setVisible(false);
                
            }
        });

        safety_pass_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                char[] safetyPassChars = safety_pass_field.getPassword();
                new_safety_user_password = new String(safetyPassChars);
                System.out.println("2nd pass: " + new_safety_user_password);
            }
        });

        add(email_field);
        add(username_field);
        add(pass_field);
        add(safety_pass_field);
    }

    private JTextArea warning; 
    // will be used to display warnings about the correctness of user input

    public void signUpCheck() throws IOException
    {
        JButton NextButton = new JButton("Next");
        NextButton.setBounds(300, 730, 80, 50);
        
        warning = new JTextArea();
        warning.setEditable(false);
        warning.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        warning.setForeground(Color.PINK);
        warning.setBackground(new Color(0,0,0,0));
        warning.setLineWrap(true);
        warning.setWrapStyleWord(true);
        warning.setVisible(false);

        NextButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(); System.out.println();
                System.out.println("username: " + new_user_email);
                System.out.println("password: " + new_user_password);
                System.out.println("Safe pass: " + new_safety_user_password);


                if(!(new_user_email.isEmpty()) && !(new_username.isEmpty()) && 
                !(new_user_password.isEmpty()) && !(new_safety_user_password.isEmpty()))
                // checking if all of the fields are completed
                {
                    if(new_user_password.equals(new_safety_user_password))
                    // checking if first and second passwords match and if they are completed in the field
                    {
                        try
                        {
                            // add user to the database
                            sechiule.DatabaseManager.getInstance().insertUserStep1(new_user_email, new_username, new_user_password);
                            // set email in MainFrame (will be used for linking user to information in the DB)
                            mainFrame.setEmail(new_user_email);
                            // set username in MainFrame (that will be used in file "TypesOfCards.java")
                            mainFrame.setUsername(new_username);
                            // switching to the card pick screen
                            mainFrame.cardLayout.show(mainFrame.mainPanel, "cardTypesPanel");
                        }
                        catch (Exception ex) 
                        {
                            ex.printStackTrace(); // printing the error for debugging purposes
                            warning.setBounds(50, 700, 300, 20);
                            warning.setText("Error saving user credentials!");
                            warning.setVisible(true);
                        }
                    }
                    else
                    // the case where first pass field doesn't corespond with the second pass field
                    {
                        safety_pass_field.setText(null); // clears the second password field
                    
                        warning.setBounds(50, 660, 300, 20);
                        warning.setText("Please reintroduce your password!"); // display the warning message
                        warning.setVisible(true);
                    
                        safety_pass_field.requestFocusInWindow();//focuses the second password field
                    }
                }
                else // if one or more of the credentials are missing
                {
                    warning.setBounds(70, 685, 300, 40);
                    warning.setText("Please introduce all your credentials!");
                    warning.setVisible(true);
                }
            }
        });

        add(NextButton);
        add(warning);
    }

    private void BackButton()
    {
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 730, 80, 50);

        backButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainFrame.cardLayout.show(mainFrame.mainPanel, "openingPage");
            }
        });

        add(backButton);
    }
}