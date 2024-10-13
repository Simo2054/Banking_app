package Card_types;

import pages.*;
import managers.*;

import javax.swing.*;
import java.awt.*;

public class DebitCardType extends TypesOfCards
// each card type has its implementation in different classes (for code organization)
{
    public DebitCardType(MainFrame mainFrame)
    {
        super(mainFrame); // pass the MainFrame to the superclass 
        // for invoking the constructor of the parent class and accessing its variables

        // personalized titles and descriptions for each card type
        addTitle("Debit Card");
        addInstructions("Debit cards are directly linked to your bank account. The money is deducted immediately when you make a purchase or withdraw.\n" +
        "> Purpose: User for everyday transactions, such as paying for goods, services or withdrawing cash.\n" + 
        "> Key features: \n" +
        " - Uses money from your account.\n" +
        " - Can be used for both in-person and online transactions.\n" + 
        " - Limited to the balance in your bank account.\n");

        chooseCardButton("BkAccIdentity");
    }

    // since the method is general for each type and it's abstract, it has its implementation in each card type class
    @Override
    public String getTypeName()
    {
        return "DebitCard";
    }

    @Override
    public String getCardType()
    {
        return "Debit";
    }
}