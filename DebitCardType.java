import javax.swing.*;
import java.awt.*;

public class DebitCardType extends JPanel
{
    public DebitCardType()
    {
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        addTitle();
        addInstructions();
    }

    public void addTitle()
    {
        JTextArea debit_card_title = new JTextArea("Debit card");
        debit_card_title.setEditable(false);
        debit_card_title.setFont(new Font("Arial", Font.BOLD, 20));
        debit_card_title.setForeground(Color.WHITE);
        debit_card_title.setBackground(new Color(0,0,0,0));
        debit_card_title.setLineWrap(true);
        debit_card_title.setWrapStyleWord(true);
        debit_card_title.setBounds(147, 0, 105, 25);

        add(debit_card_title);
    }

    public void addInstructions()
    {
        JTextArea debit_card_instructions;
        debit_card_instructions = new JTextArea();
        debit_card_instructions.setText("this is debit card instructions...");
        debit_card_instructions.setBounds(75, 190, 250, 150);
        debit_card_instructions.setEditable(false);
        debit_card_instructions.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        debit_card_instructions.setForeground(Color.BLACK);
        debit_card_instructions.setBackground(Color.WHITE);
        debit_card_instructions.setLineWrap(true);
        debit_card_instructions.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(debit_card_instructions);
        scrollPane.setBounds(75, 190, 250, 150);

        add(scrollPane);
    }
}