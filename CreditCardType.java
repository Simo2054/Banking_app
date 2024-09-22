import javax.swing.*;
import java.awt.*;

public class CreditCardType extends JPanel
{
    public CreditCardType()
    {
        setLayout(null);
        addTitle();
        addInstructions();
    }

    public void addTitle()
    {
        JTextArea debit_card_title = new JTextArea("Credit card");
        debit_card_title.setEditable(false);
        debit_card_title.setFont(new Font("Arial", Font.BOLD, 20));
        debit_card_title.setForeground(Color.BLACK);
        debit_card_title.setBackground(new Color(0,0,0,0));
        debit_card_title.setLineWrap(true);
        debit_card_title.setWrapStyleWord(true);
        debit_card_title.setBounds(147, 270, 105, 25);

        add(debit_card_title);
    }

    public void addInstructions()
    {
        JTextArea debit_card_instructions;
        debit_card_instructions = new JTextArea();
        debit_card_instructions.setBounds(75, 460, 250, 150);
        debit_card_instructions.setEditable(false);
        debit_card_instructions.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        debit_card_instructions.setForeground(Color.WHITE);
        debit_card_instructions.setBackground(Color.pink);
        debit_card_instructions.setLineWrap(true);
        debit_card_instructions.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(debit_card_instructions);

        add(scrollPane);
    }
}