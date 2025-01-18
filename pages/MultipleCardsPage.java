package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MultipleCardsPage extends JPanel
{
    private MainFrame mainFrame;

    public MultipleCardsPage(MainFrame mainFrame) throws Exception
    {
        this.mainFrame = mainFrame;

        setBackground(new Color(250, 243, 221));
        setLayout(null);
    }

    private ArrayList<Integer> card_IDs;

    private String email;
    private int userID;

    public void updateFields()
    {
        try
        {
            email = mainFrame.getEmail();
            System.out.println("emailul gasit: " + email);
            userID = sechiule.DatabaseManager.getInstance().getUserID(email);

            card_IDs = sechiule.DatabaseManager.getInstance().getCardIdByUserID(userID);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        

        displayCards();
    }

    public void displayCards()
    {
        if(card_IDs.size() == 1)
        {
            int currCID = card_IDs.get(0);
            mainFrame.setCurrentCardID(currCID);
            
            try
            {
                mainFrame.bkAccountPage.updateFields();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            mainFrame.cardLayout.show(mainFrame.mainPanel, "bkAccountPage");
        }
    }
}
