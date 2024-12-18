package Card_types;

import pages.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VirtualCardType extends TypesOfCards
{
    public VirtualCardType(MainFrame mainFrame) throws IOException
    {
        super(mainFrame);
        addTitle("Virtual Card");
        addInstructions("Virtual cards are digital cards that are stored on your phone and don't have a physical form. " +
        "Similar to in-person cards but are only available online.\n" + 
        "> Purpose: Commonly used for ease of customer transactions and improved security.\n" + 
        "> Key Features: \n" + 
        " - improved security and immediate activation.\n" + 
        " - used for primary customer.\n" + 
        " - customizations such as transactional limit, appearance, currency type.\n");

        chooseCardButton("BkAccIdentity");
    }

    @Override
    public String getTypeName()
    {
        return "VirtualCard";
    }

    @Override
    public String getCardType()
    {
        return "Virtual";
    }
}