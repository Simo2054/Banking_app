import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class SignUpPage extends JPanel 
{
    public String new_input_mail;
    public String new_user_password;
    public String new_safety_user_password;
    public String new_username;

    private UserManager userManager;

    public SignUpPage(MainFrame mainFrame) throws IOException
    {
        userManager = new UserManager();
        // initializing user manager to handle users

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        // components of the Sign up page:
        instructions_fields();
        user_input_fields();
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

        JTextArea pass_instr = new JTextArea("Please introduce a new password for your account:");
        pass_instr.setBounds(50, 350, 300, 50);
        pass_instr.setEditable(false);
        pass_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        pass_instr.setForeground(Color.WHITE);
        pass_instr.setBackground(new Color(0,0,0,0));
        pass_instr.setLineWrap(true);
        pass_instr.setWrapStyleWord(true);

        JTextArea safety_pass_instr = new JTextArea("Please introduce your new password one more time:");
        safety_pass_instr.setBounds(50, 460, 300, 50);
        safety_pass_instr.setEditable(false);
        safety_pass_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        safety_pass_instr.setForeground(Color.WHITE);
        safety_pass_instr.setBackground(new Color(0,0,0,0));
        safety_pass_instr.setLineWrap(true);
        safety_pass_instr.setWrapStyleWord(true);

        add(mail_instr);
        add(pass_instr);
        add(safety_pass_instr);
    }

    private JTextField input_mail;
    private JPasswordField pass_field;
    private JPasswordField safety_pass_field;

    private void user_input_fields()
    {
        input_mail = new JTextField();
        input_mail.setBounds(50, 260 , 300, 40);
        input_mail.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        pass_field = new JPasswordField();
        pass_field.setBounds(50, 410, 300, 40);
        pass_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        safety_pass_field = new JPasswordField();
        safety_pass_field.setBounds(50, 520 , 300, 40);
        safety_pass_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
    
        input_mail.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent press)
            {
                new_input_mail = input_mail.getText();
                System.out.println("user: " + new_input_mail);
                pass_field.requestFocusInWindow();
            }
        });

        input_mail.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                new_input_mail = input_mail.getText();
                System.out.println("user: " + new_input_mail);
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

        add(input_mail);
        add(pass_field);
        add(safety_pass_field);
    }

    public void signUpCheck()
    {
        JButton signUpButton = new JButton("Sign up");
        signUpButton.setBounds(50, 500, 100, 40);

        signUpButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(); System.out.println();
                System.out.println("username: " + new_input_mail);
                System.out.println("password: " + new_user_password);
                System.out.println("Safe pass: " + new_safety_user_password);

                if(new_user_password.equals(new_safety_user_password))
                {
                    System.out.println("great! now how would you like us to call you?");
                    System.out.println("please introduce your username:");
                    // gets to the next page
                }
                else
                {
                    safety_pass_field.setText(""); // clears the second password field\
                    System.out.println("please reintroduce your password!");
                    safety_pass_field.requestFocusInWindow();//focuses the second password field
                }
            }
        });
    }
}