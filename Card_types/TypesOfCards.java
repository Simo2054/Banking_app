package Card_types;

import pages.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// some common methods for all pages containing types of cards
public abstract class TypesOfCards extends JPanel
{
    private MainFrame mainFrame;
    CardObtained cardObtained;

    public TypesOfCards(MainFrame mainFrame) throws IOException
    {
        this.mainFrame = mainFrame;
        cardObtained = new CardObtained(mainFrame);

        setLayout(null);
        setBackground(new Color(250, 243, 221));

        greeting_fields();
        switch_buttons();
        existent_card();
    }

    // method for creating the persomalized greetings, according to the username set by the user
    protected void greeting_fields()
    {
        JTextArea intro_text = new JTextArea();
        intro_text.setText("Hello, " + mainFrame.getUsername() + "!");
        intro_text.setEditable(false);
        intro_text.setFont(new Font("Arial", Font.BOLD, 20));
        intro_text.setForeground(Color.BLACK);
        intro_text.setBackground(new Color(0,0,0,0));
        intro_text.setLineWrap(true);
        intro_text.setWrapStyleWord(true);

        // operations needed to know how many lines are needed, accoring to username set by the user
        FontMetrics fontMetrics = intro_text.getFontMetrics(intro_text.getFont());
        String text = intro_text.getText();
        int text_width = fontMetrics.stringWidth(text);
        int lines_num = (int)Math.ceil((double)text_width/300);
        int text_height = lines_num* fontMetrics.getHeight();

        intro_text.setBounds(50, 50, 300, text_height); // setting the positioning of the greeting dynamically, 
        // according to the username picked by the user
        // if the username is longer, it might take more lines, therefore the bounds should be bigger

        // instruction for letting the user know what they should do next
        JTextArea card_propose = new JTextArea();
        card_propose.setText("Please choose a plan for your new card:");
        card_propose.setBounds(50, 200, 300, 55);
        card_propose.setEditable(false);
        card_propose.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        card_propose.setForeground(Color.BLACK);
        card_propose.setBackground(new Color(0,0,0,0));
        card_propose.setLineWrap(true);
        card_propose.setWrapStyleWord(true);

        add(intro_text);
        add(card_propose);
    }

    // the buttons will let the user navigate through the available bank card types
    protected void switch_buttons()
    {
        // previous card
        JButton prev_type = new JButton();
        prev_type.setBounds(25, 300, 50, 150);
        prev_type.setText("《");
        prev_type.setForeground(Color.BLACK);
        prev_type.setFont(new Font("Arial", Font.BOLD, 16));

        // next card
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
                mainFrame.getCardManager().switchCard(1);
            }
        });

        prev_type.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainFrame.getCardManager().switchCard(-1);
            }
        });

        add(prev_type);
        add(next_type);
    }

    // getter method for the name of each type (refering to cards)
    public abstract String getTypeName();

    // getter method to get the type name of the chosen card
    // (utilitary, will be used to show the user the type of card they chose)
    public abstract String getCardType();

    protected void addTitle(String title)
    {
        JLabel card_title = new JLabel();
        card_title.setText(title);
        card_title.setFont(new Font("Arial", Font.BOLD, 20));
        card_title.setForeground(Color.BLACK);
        card_title.setOpaque(true);
        card_title.setBackground(new Color(217, 217, 217));

        int panelWidth = 400;

        // calculate the necessary width for the text
        FontMetrics fontMetrics = card_title.getFontMetrics(card_title.getFont());
        int text_width = fontMetrics.stringWidth(title);

        // dynamically position and set the size of the title area
        int titleWidth = Math.min(text_width, panelWidth-20);// with a padding of 10 on each side
        card_title.setBounds((panelWidth - titleWidth)/2, 270, text_width, 25);

        add(card_title);
    }

    // scrollable text area for displaying information about each type of card 
    protected void addInstructions(String instructions)
    {
        JTextArea card_instructions;
        card_instructions = new JTextArea();
        card_instructions.setText(instructions);
        card_instructions.setBounds(75, 460, 250, 150);
        card_instructions.setEditable(false);
        card_instructions.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        card_instructions.setForeground(Color.BLACK);
        card_instructions.setBackground(new Color(175, 175, 220));
        card_instructions.setLineWrap(true);
        card_instructions.setWrapStyleWord(true);

        // adding padding to the text area
        card_instructions.setMargin(new Insets(10, 10, 10, 10));

        // to make text start from the beginning
        card_instructions.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(card_instructions);
        scrollPane.setBounds(75, 460, 250, 150);

        add(scrollPane);
    }

    // method to let the user add a card they already have
    protected void existent_card()
    {
        RoundedButton existsCard = new RoundedButton("I already have a card", 20);
        existsCard.setFont(new Font("Arial", Font.BOLD, 20));
        existsCard.setBounds(50, 730, 300, 50);
        existsCard.setBackground(new Color(127, 200, 214));
        existsCard.setForeground(new Color(51, 51, 51));
        existsCard.setBorder(new RoundedBorder(20, Color.GRAY, 3));
        

        existsCard.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainFrame.cardLayout.show(mainFrame.mainPanel, "CardDataPage");
            }
        });

        existsCard.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                existsCard.setBackground(new Color(107, 175, 195)); // Hover color
            }
        
            @Override
            public void mouseExited(MouseEvent e) 
            {
                existsCard.setBackground(new Color(127, 200, 214)); // Original color
            }
        });

        add(existsCard);
    }

    protected void chooseCardButton(String page)
    {
        RoundedButton chooseCard = new RoundedButton("Choose Card Type", 20);
        chooseCard.setFont(new Font("Arial", Font.BOLD, 20));
        chooseCard.setBounds(75, 620, 250, 50);
        chooseCard.setBackground(new Color(143, 214, 148));
        chooseCard.setForeground(Color.BLACK);
        chooseCard.setBorder(new RoundedBorder(30, Color.gray, 4));

        chooseCard.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String cardType = getCardType();
                System.out.println("Selected card type is: " + cardType);
                mainFrame.setSelectedCardType(getCardType()); // to set the selected card type in MainFrame

                try
                {
                    int UID = mainFrame.getCurrentUserID();
                    sechiule.DatabaseManager.getInstance().updateCardType(UID, cardType);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                // show the next page
                mainFrame.cardLayout.show(mainFrame.mainPanel, page);
            }
        });

        chooseCard.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                chooseCard.setBackground(new Color(122, 203, 128)); // Hover color
            }
        
            @Override
            public void mouseExited(MouseEvent e) 
            {
                chooseCard.setBackground(new Color(143, 214, 148)); // Original color
            }
        });

        add(chooseCard);
    }
}