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

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        instructions_fields();
    }

    private void instructions_fields()
    {
        JTextArea intro_text = new JTextArea();
        intro_text.setText("Hello, " + signUpPage.new_username + "!");
        intro_text.setEditable(false);
        intro_text.setFont(new Font("Arial", Font.BOLD, 20));
        intro_text.setForeground(Color.WHITE);
        intro_text.setBackground(new Color(0,0,0,0));
        intro_text.setLineWrap(true);
        intro_text.setWrapStyleWord(true);

        // operations needed to know how many lines are needed, accoring to username set by the user
        FontMetrics fontMetrics = intro_text.getFontMetrics(intro_text.getFont());
        String text = intro_text.getText();
        int text_width = fontMetrics.stringWidth(text);
        int lines_num = (int)Math.ceil((double)text_width/300);
        int text_height = lines_num* fontMetrics.getHeight();

        intro_text.setBounds(50, 50, 300, text_height);


        

        add(intro_text);
    }
}