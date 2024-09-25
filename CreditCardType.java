import javax.swing.*;
import java.awt.*;

class CreditCardType extends TypesOfCards
{
    public CreditCardType(MainFrame mainFrame)
    {
        super(mainFrame);
        addTitle("Credit Card");
        addInstructions("this is a Credit card ...");
    }

    @Override
    public String getTypeName()
    {
        return "CreditCard";
    }
}