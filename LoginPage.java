import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel
// the class LoginPage inherits from JPanel class
// that means LoginPage will acquire properties of the JPanel class
{
    public LoginPage(MainFrame mainFrame) 
    // constructor for the LoginPage class
    // it accepts and object of type MainFrame, which will allow the class
    // to interact with the MainFrame class
    {
        setBackground(new Color(110, 20, 90));
        setLayout(null);

        // Initializing components for the login page
        instructions_fields();
        user_fields();
    }

    private void instructions_fields()
    {
        JTextArea mail_instr = new JTextArea("Please introduce your e-mail adress: ");
        mail_instr.setBounds(50, 200, 300, 50);
        mail_instr.setEditable(false);
        mail_instr.setFont(new Font("Arial", Font.BOLD, 18));
        mail_instr.setForeground(Color.WHITE);// text color
        mail_instr.setBackground(new Color(0,0,0,0));// transparent background
        mail_instr.setLineWrap(true);
        mail_instr.setWrapStyleWord(true);// this is to wrap by word, not by character

        JTextArea pass_instr = new JTextArea("Please introduce your password: ");
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

    private void user_fields()
    {
        JTextField user_mail = new JTextField();
        user_mail.setBounds(50, 260 , 300, 40);
        user_mail.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        JPasswordField pass_field = new JPasswordField();
        pass_field.setBounds(50, 390, 300, 40);
        pass_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));

        user_mail.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent press)
            {
                String input_mail = user_mail.getText();
                // get the text from the text field
                System.out.println("user: " + input_mail);
                //user_mail.setText(""); // clears the text field after pressing enter

                // enter focuses the pass field
                pass_field.requestFocusInWindow();
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
                String password = new String(passwordChars);
                System.out.println("password: " + password);
                
                // clear the password field
                //pass_field.setText("");
                
                // clear the char array (for security reasons)
                //java.util.Arrays.fill(passwordChars, ' ');
            }
        });

        add(user_mail);
        add(pass_field);
    }

}