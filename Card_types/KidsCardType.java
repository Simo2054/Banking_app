package Card_types;

import pages.*;
import managers.*;

import javax.swing.*;
import java.awt.*;

public class KidsCardType extends TypesOfCards
{
    public KidsCardType(MainFrame mainFrame)
    {
        super(mainFrame);
        addTitle("Kids Card");
        addInstructions("Kids card are similar to adult cards, being linked to a kids bank account " +
        "but with certain limitations when it comes to money spent and control.\n" + 
        "> Puropose: Teaching children about the importance of money and how to use it.\n" + 
        "> Key features: \n" + 
        " - Being in control of your child's account while also allowing them freedom of spending.\n" + 
        " - Age limit.\n" + 
        " - Improved security.\n");

        chooseCardButton("BkAccIdentity");
    }

    @Override
    public String getTypeName()
    {
        return "KidsCard";
    }
}