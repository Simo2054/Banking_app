import javax.swing.*;
import java.awt.*;

public class CardManager
{
    private JPanel cardTypesPanel;     // a panel(container) to hold the bank card types
    private CardLayout cardLayout;     // CardLayout to switch between types

    private TypesOfCards[] cardTypesArray;  // an array that will keep the pages of different bank cards
    private int currentIndex = 0;      // Tracks the current card index

    private MainFrame mainFrame;       // Reference to MainFrame for accessing username

    public CardManager(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;

        this.cardLayout = new CardLayout();
        this.cardTypesPanel = new JPanel(cardLayout); // initalizing cardTypesPanel without populating it

        cardTypesPanel.setBounds(0, 0, 400, 800);
    }

    public void initCardTypesPanel()
    {
        cardTypesArray = new TypesOfCards[]
        {
            new DebitCardType(mainFrame),
            new CreditCardType(mainFrame),
            new KidsCardType(mainFrame)
        };

        for (TypesOfCards card : cardTypesArray)
        {
            cardTypesPanel.add(card, card.getTypeName());
        }
    }

    public JPanel getCardTypesPanel()
    {
        return cardTypesPanel;
    }

    public void switchCard(int direction)
    {
        currentIndex += direction;

        if (currentIndex < 0)
        {
            currentIndex = cardTypesArray.length - 1;
        }

        if (currentIndex >= cardTypesArray.length)
        {
            currentIndex = 0;
        }

        cardLayout.show(cardTypesPanel, cardTypesArray[currentIndex].getTypeName());
    }
}
