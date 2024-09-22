import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardTypePickPage extends JPanel
{
    private UserManager userManager;
    private SignUpPage signUpPage;

    private MainFrame mainFrame;
    private JPanel cardPanel; // dynamic card (refering to cardLayout) type
    private DebitCardType debitCardType;
    private CreditCardType creditCardType;

    public CardTypePickPage(MainFrame mainFrame, SignUpPage signUpPage) //throws IOException
    {
        this.mainFrame = mainFrame;
        //userManager = new UserManager();
        this.signUpPage = signUpPage;

        setBackground(new Color(110, 20, 90));
        setLayout(null);

        instructions_fields();

        debitCardType = new DebitCardType();
        // instance of Debit card panel
        creditCardType = new CreditCardType();

        // initialize card panel with debit card panel
        cardPanel = new JPanel(null);
        cardPanel.setBounds(0, 270, 400, 400);
        cardPanel.add(debitCardType);
        add(cardPanel);

        switch_buttons();
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


        JTextArea card_propose = new JTextArea();
        card_propose.setText("Please choose a plan for your new card:");
        card_propose.setBounds(50, 200, 300, 55);
        card_propose.setEditable(false);
        card_propose.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        card_propose.setForeground(Color.WHITE);
        card_propose.setBackground(new Color(0,0,0,0));
        card_propose.setLineWrap(true);
        card_propose.setWrapStyleWord(true);

        add(intro_text);
        add(card_propose);
    }

    private void switch_buttons()
    {
        JButton prev_type = new JButton();
        prev_type.setBounds(25, 300, 50, 150);
        prev_type.setText("《");
        prev_type.setForeground(Color.BLACK);
        prev_type.setFont(new Font("Arial", Font.BOLD, 16));

        JButton next_type = new JButton();
        next_type.setBounds(325, 300, 50, 150);
        next_type.setText("》");
        next_type.setForeground(Color.BLACK);
        next_type.setFont(new Font("Arial", Font.BOLD, 16));

        next_type.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // remove the current card type 
            cardPanel.removeAll();
            cardPanel.add(creditCardType);
            cardPanel.revalidate();
            cardPanel.repaint();
            }
        });

        add(prev_type);
        add(next_type);
    }
}