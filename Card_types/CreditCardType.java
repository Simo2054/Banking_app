package Card_types;

import pages.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CreditCardType extends TypesOfCards
{
    public CreditCardType(MainFrame mainFrame) throws IOException
    {
        super(mainFrame);
        addTitle("Credit Card");
        addInstructions("Credit cards allow you to borrow money from the bank up to a certain limit " + 
        "(credit limit), and you pay it back later, either in full or in installments.\n" + 
        "> Purpose: Commonly used for larger purchases or for building a credit history. \n" +
        "> Key features: \n" + 
        " - Borrowed money must be repaid (with interest if not paid in full by due date).\n" + 
        " - Offers rewards like cash-back, points or travel miles.\n" + 
        " - Helps build credit scores.\n");
        
        chooseCardButton("BkAccIdentity");
    }

    @Override
    public String getTypeName()
    {
        return "CreditCard";
    }

    @Override
    public String getCardType()
    {
        return "Credit";
    }
}