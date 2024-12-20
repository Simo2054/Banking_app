package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginPage extends JPanel
// the class LoginPage inherits from JPanel class
// that means LoginPage will acquire properties of the JPanel class
{
    public String input_mail = "";
    public String user_password = "";

    private UserManager userManager;
    // creating a userManager instance to manage user authentication

    private MainFrame mainFrame;// field to hold the reference to the MainFrame class

    public LoginPage(MainFrame mainFrame) throws IOException
    // constructor for the LoginPage class
    // it accepts and object of type MainFrame, which will allow the class
    // to interact with the MainFrame class
    {
        this.mainFrame = mainFrame;
        // storing the reference to MainFrame class
        
        userManager = new UserManager();
        // initializing user manager to handle users

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        // Adding components for the login page
        instructions_fields();
        user_fields(); // input fields

        loginCheck(); // checks credentials introduced

        BackButton();// button to go back to previus page
    }

    private JTextArea mail_instr;
    private JTextArea pass_instr;

    private void instructions_fields()
    {
        mail_instr = new JTextArea("Please introduce your e-mail adress: ");
        mail_instr.setBounds(50, 200, 300, 50);
        mail_instr.setEditable(false);
        mail_instr.setFont(new Font("Arial", Font.BOLD, 18));
        mail_instr.setForeground(Color.WHITE);// text color
        mail_instr.setBackground(new Color(0,0,0,0));// transparent background
        mail_instr.setLineWrap(true);
        mail_instr.setWrapStyleWord(true);// this is to wrap by word, not by character

        pass_instr = new JTextArea("Please introduce your password: ");
        pass_instr.setBounds(50, 350, 300, 50);
        pass_instr.setEditable(false);
        pass_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        pass_instr.setForeground(Color.WHITE);
        pass_instr.setBackground(new Color(0,0,0,0));
        pass_instr.setLineWrap(true);
        pass_instr.setWrapStyleWord(true);

        add(mail_instr);
        add(pass_instr);
    }

    private JTextField user_mail;
    private JPasswordField pass_field;

    private void user_fields()
    {
        user_mail = new JTextField();
        user_mail.setBounds(50, 260 , 300, 40);
        user_mail.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        pass_field = new JPasswordField();
        pass_field.setBounds(50, 390, 300, 40);
        pass_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        user_mail.addActionListener(new ActionListener() // user_mail textfield will wait for action(click)
        {
            @Override
            public void actionPerformed(ActionEvent press)
            {
                input_mail = user_mail.getText();
                // get the text from the text field
                System.out.println("user: " + input_mail);
                //user_mail.setText(""); // clears the text field after pressing enter

                // enter focuses the pass field
                pass_field.requestFocusInWindow();
            }
        });

        user_mail.addFocusListener(new FocusListener() // checks focus on the user_mail textfield
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                input_mail = user_mail.getText();
                System.out.println("user: " + input_mail);
            }
        });

        pass_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent press)
            {
                // Get the password (as a char array)
                char[] passwordChars = pass_field.getPassword();
                // converting the char array to a string
                user_password = new String(passwordChars);
                System.out.println("password: " + user_password);
                
                // clear the password field
                //pass_field.setText("");
                
                // clear the char array (for security reasons)
                //java.util.Arrays.fill(passwordChars, ' ');
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
                user_password = new String(passwordChars);
                System.out.println("password: " + user_password);
            }
        });

        add(user_mail);
        add(pass_field);
    }

    private JButton loginCheckButton;
    private JTextArea warning;

    private void loginCheck()
    {
        loginCheckButton = new JButton("Log in");
        loginCheckButton.setBounds(50, 500, 100, 40);

        warning = new JTextArea();
        warning.setEditable(false);
        warning.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        warning.setForeground(Color.PINK);
        warning.setBackground(new Color(0,0,0,0));
        warning.setLineWrap(true);
        warning.setWrapStyleWord(true);
        warning.setVisible(false);

        loginCheckButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(); System.out.println();
                System.out.println("username: " + input_mail);
                System.out.println("password: " + user_password);
                if(userManager.authenticate(input_mail, user_password))
                {
                    System.out.println("Login Successful!");
                    mainFrame.cardLayout.show(mainFrame.mainPanel, "successfulLogIn");
                }
                else if((input_mail.isEmpty()) && (user_password.isEmpty()))
                {
                    System.out.println("introduce data!"); // data missing from both fields
                }
                else
                {
                    System.out.println("incorrect data!"); // data missing from one field of data is wrong on one or both fields
                }
            }
        });

        add(loginCheckButton);
    }

    JButton backButton;

    private void BackButton()
    {
        backButton = new JButton("Back");
        backButton.setBounds(300, 730, 80, 50);

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