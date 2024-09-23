import javax.swing.*;
import java.awt.*;

class CreditCardType extends CardType
{
    public CreditCardType()
    {
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        addTitle("Credit Card");
        addInstructions("Credit card instructions...");
    }

    @Override
    public String getTypeName()
    {
        return "Credit Card";
    }
}