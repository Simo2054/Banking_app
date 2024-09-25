import javax.swing.*;
import java.awt.*;

// class to handle the card pick page
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

        cardTypesPanel.setBounds(0, 0, 400, 800); // the panel will be as big as the frame
    }

    public void initCardTypesPanel()
    {
        cardTypesArray = new TypesOfCards[] // populating the array of bank card types
        {
            new DebitCardType(mainFrame),
            new CreditCardType(mainFrame),
            new KidsCardType(mainFrame)
        };

        for (TypesOfCards card : cardTypesArray)
        {
            cardTypesPanel.add(card, card.getTypeName()); // adding all the bank card types to the container that will hold them
        }
    }

    public JPanel getCardTypesPanel() // a getter method to provide access to the card types panel from outside the class
    {
        return cardTypesPanel;
    }

    public void switchCard(int direction) // a method to switch cards based on positive and negative directions (-1 and 1)
    {
        currentIndex += direction; // incrementing the currentIndex accoring to the direction selected by user

        if (currentIndex < 0) 
        {
            currentIndex = cardTypesArray.length - 1; // for the case in which user is shuffling using "previous" button :)
        }

        if (currentIndex >= cardTypesArray.length)
        {
            currentIndex = 0; // resetting the currentIndex (for the case in which user went through all types)
        }

        cardLayout.show(cardTypesPanel, cardTypesArray[currentIndex].getTypeName()); 
        // the card shown is the one according to the currentIndex
    }
}
