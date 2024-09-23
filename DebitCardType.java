import javax.swing.*;
import java.awt.*;

class DebitCardType extends CardType
{
    public DebitCardType()
    {
        removeAll();
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        addTitle("Debit Card");
        addInstructions("Debit card instructions...");
    }

    @Override
    public String getTypeName()
    {
        return "Debit Card";
    }
}