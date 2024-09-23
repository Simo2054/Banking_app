import javax.swing.*;
import java.awt.*;

abstract class CardType extends JPanel
{
    public abstract String getTypeName();
    // common methods for card type panels
    protected void addTitle(String title)
    {
        this.removeAll();
        this.setLayout(null);

        System.out.println("sters");

        JTextArea whiteBand = new JTextArea(".");
        whiteBand.setBackground(Color.white);
        whiteBand.setForeground(new Color(0,0,0,0));
        whiteBand.setBounds(0,0,400, 25);

        JLabel card_title = new JLabel(title);
        card_title.setFont(new Font("Arial", Font.BOLD, 20));
        card_title.setForeground(Color.RED);
        card_title.setOpaque(true);
        card_title.setBackground(Color.WHITE);

        // getting the panel's width dynamically
        int panelWidth = this.getWidth();

        // calculate the necessary width for the text
        FontMetrics fontMetrics = card_title.getFontMetrics(card_title.getFont());
        int text_width = fontMetrics.stringWidth(title);

        // dynamically position and set the size of the title area
        int titleWidth = Math.min(text_width, panelWidth-20);// with a padding of 10 on each side
        card_title.setBounds((panelWidth - titleWidth)/2, 0, text_width, 25);

        this.add(card_title);
        
        this.revalidate();
        this.repaint();
        System.out.println("ceva!");
    }

    protected void addInstructions(String instructions)
    {
        JTextArea card_instructions;
        card_instructions = new JTextArea();
        card_instructions.setText(instructions);
        card_instructions.setBounds(75, 190, 250, 150);
        card_instructions.setEditable(false);
        card_instructions.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        card_instructions.setForeground(Color.BLACK);
        card_instructions.setBackground(Color.CYAN);
        card_instructions.setLineWrap(true);
        card_instructions.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(card_instructions);
        scrollPane.setBounds(75, 190, 250, 150);

        add(scrollPane);
    }

    
}
