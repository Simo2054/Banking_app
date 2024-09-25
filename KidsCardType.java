import javax.swing.*;
import java.awt.*;

class KidsCardType extends TypesOfCards
{
    public KidsCardType(MainFrame mainFrame)
    {
        super(mainFrame);
        addTitle("Kids Card");
        addInstructions("this is a kids card ...");
    }

    @Override
    public String getTypeName()
    {
        return "KidsCard";
    }
}