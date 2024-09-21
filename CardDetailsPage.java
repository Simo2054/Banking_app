import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardDetailsPage extends JPanel
{
    private UserManager userManager;
    private MainFrame mainFrame;
    private SignUpPage signUpPage;

    public CardDetailsPage(MainFrame mainFrame, SignUpPage signUpPage) //throws IOException
    {
        this.mainFrame = mainFrame;
        //userManager = new UserManager();
        this.signUpPage = signUpPage;

        setBackground(new Color(50, 52, 88));
        setLayout(null);


    }

    // add a button to choose if the user already has a card 
    // or if we should make a card digitally

    private void instructions_fields()
    {
        JTextArea intro_text = new JTextArea();
        intro_text.setText("Hello, " + signUpPage.new_user_email + "!" );
        intro_text.setBounds(50, 100, 300, 20);
        intro_text.setEditable(false);
        intro_text.setFont(new Font("Arial", Font.BOLD, 20));
        intro_text.setForeground(Color.WHITE);
        intro_text.setBackground(new Color(0,0,0,0));
        intro_text.setLineWrap(true);
        intro_text.setWrapStyleWord(true);

        add(intro_text);
    }
}