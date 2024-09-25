import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DebitCardType extends TypesOfCards
{
    public DebitCardType(MainFrame mainFrame)
    {
        super(mainFrame); // pass the MainFrame to the superclass
        addTitle("Debit Card");
        addInstructions("this is a debit card ...");
    }

    @Override
    public String getTypeName()
    {
        return "DebitCard";
    }
}