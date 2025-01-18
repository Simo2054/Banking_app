package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class BkAccountPage extends JPanel
{
    private MainFrame mainFrame;
    private TransactionsManager tscMan;

    public BkAccountPage(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        tscMan = new TransactionsManager();

        setBackground(new Color(250, 243, 221));
        setLayout(null);
    }

    private String email;
    private int cardID;
    private String username; 
    private int sum_of_money;
    private String currency;

    // utilitary method to update the fields according to user's choices/input
    public void updateFields() throws Exception
    {
        email = mainFrame.getEmail();
        username = sechiule.DatabaseManager.getInstance().getUsername(email);
        
        cardID = mainFrame.getCurrentCardID();
        sum_of_money = sechiule.DatabaseManager.getInstance().getSum(cardID);
        currency = sechiule.DatabaseManager.getInstance().getCurrency(cardID);

        header();
        menu_for_user();
    }

    JPanel head;
    JTextArea owner_acc; // a text area to contain "[username]'s Account:"
    JTextArea money_and_currency; // a text area to show the user how much money they have in their account

    private void header()
    {
        head = new JPanel();
        head.setLayout(null);
        head.setBackground(Color.cyan);
        head.setBounds(50, 50, 300, 150);
        // this needs border

        owner_acc = new JTextArea();
        owner_acc.setText(username + "'s Account:");
        owner_acc.setEditable(false);
        owner_acc.setFont(new Font("Arial", Font.BOLD, 18));
        owner_acc.setForeground(Color.BLUE);
        owner_acc.setBackground(new Color(165, 202, 255));
        owner_acc.setLineWrap(true);
        owner_acc.setWrapStyleWord(true);
        owner_acc.setBounds(0, 0, 300, 50);

        money_and_currency = new JTextArea();
        money_and_currency.setText(sum_of_money + " " + currency);
        money_and_currency.setEditable(false);
        money_and_currency.setFont(new Font("Arial", Font.BOLD, 18));
        money_and_currency.setForeground(Color.BLUE);
        money_and_currency.setBackground(new Color(165, 202, 255));
        money_and_currency.setLineWrap(true);
        money_and_currency.setWrapStyleWord(true);
        money_and_currency.setBounds(0, 55, 300, 50);

        head.add(owner_acc);
        head.add(money_and_currency);

        add(head);
    }

    JMenuBar menuBar; 
    JMenu history, account_info, card_info, functions;

    private ArrayList<Integer> transcIDs;
    private List<Product> prodList;

    private void menu_for_user()
    {
        menuBar = new JMenuBar();
        menuBar.setBounds(50, 200, 300, 50);

        history = new JMenu();
        history.setText("History");
        history.setFont(new Font("Arial", Font.BOLD, 18));
        history.setOpaque(true); // make background visible
        history.setBackground(Color.blue);
        history.setForeground(Color.cyan);
        history.setBorder(new LineBorder(Color.pink, 2, true));

        account_info = new JMenu();
        account_info.setText("Account");
        account_info.setFont(new Font("Arial", Font.BOLD, 18));
        account_info.setOpaque(true); // make background visible
        account_info.setBackground(Color.blue);
        account_info.setForeground(Color.cyan);
        account_info.setBorder(new LineBorder(Color.pink, 2, true));

        card_info = new JMenu();
        card_info.setText("Card");
        card_info.setFont(new Font("Arial", Font.BOLD, 18));
        card_info.setOpaque(true); // make background visible
        card_info.setBackground(Color.blue);
        card_info.setForeground(Color.cyan);
        card_info.setBorder(new LineBorder(Color.pink, 2, true));

        functions = new JMenu();
        functions.setText("Functions");
        functions.setFont(new Font("Arial", Font.BOLD, 18));
        functions.setOpaque(true); // make background visible
        functions.setBackground(Color.blue);
        functions.setForeground(Color.cyan);
        functions.setBorder(new LineBorder(Color.PINK, 2, true));

        history.addMenuListener(new MenuListener() 
        {
            private JPopupMenu popupMenu;

            @Override
            public void menuSelected(MenuEvent e)
            {
                // openHistory()
                try
                {
                    transcIDs = sechiule.DatabaseManager.getInstance().tscIDs(cardID);
                    System.out.println("marimea este: " + transcIDs.size());

                    prodList = sechiule.DatabaseManager.getInstance().getTransactionDetails(1);
                    System.out.println("marimea array-ului este " + prodList.size());

                    if(transcIDs.isEmpty())
                    {
                        System.out.println("no transactions yet!");
                    }
                    else
                    {
                        System.out.println("ia arata");
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

                // ------------------------------------------------------------------------------------------

                if(popupMenu == null)
                {
                    popupMenu = createPopupMenu(prodList);
                }

                JMenu menu = (JMenu) e.getSource();
                // e.getSource() retrieves the source of the event
                // that triggered the menuSelected method
                // since it returns an item of type Object,
                // we cast it to Jmenu to use specific methods

                // Match the popup menu's width to the menu's width
                JMenuBar menuBar = (JMenuBar) menu.getParent(); // Get the parent JMenuBar
                // getParent() returns an item of type Container
                // so we cast it to JmenuBar to use specific methods

                // Match the popup menu's width to the menu bar's width
                popupMenu.setPreferredSize(new Dimension(menuBar.getWidth(), popupMenu.getPreferredSize().height));


                popupMenu.show(menu, 0, menu.getHeight());
                // displays the popupMenu relative to the menu 
                // (component invoker, int x, int y)
                // displays the popup menu at the specified position relative to the invoker
                // 
                
            }

            @Override
            public void menuDeselected(MenuEvent e)
            {
                if (popupMenu != null) 
                {
                    popupMenu.setVisible(false);
                }
            }

            @Override
            public void menuCanceled(MenuEvent e)
            {
                if (popupMenu != null) 
                {
                    popupMenu.setVisible(false);
                }
            }

        });

        menuBar.add(history);
        menuBar.add(account_info);
        menuBar.add(card_info);
        menuBar.add(functions);

        add(menuBar);
    }

    // popup menu needs to be positioned
    // for the no transactions, the popupmenu will contain just a text area 
    // for the populated array, i will need to have each pair from the arraylist paired with methods from dbman 

    private JPopupMenu createPopupMenu(List<Product> prodTrans) 
    {
        JPopupMenu popupMenu = new JPopupMenu();

        if(prodTrans.isEmpty())
        {
            JLabel noTransactions = new JLabel("No transactions yet!");
            noTransactions.setHorizontalAlignment(SwingConstants.CENTER);
            noTransactions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            popupMenu.add(noTransactions);
        }
        else
        {
        // panel to hold the name-price pairs
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS)); // vertical stacking

        
        
        // loop through the list of transactions
        for(Product prod : prodTrans)
        {
        // panel for a single transaction
        JPanel prodPanel = new JPanel();
        prodPanel.setLayout(new BoxLayout(prodPanel, BoxLayout.Y_AXIS));
        // vertical stacking for name, price and description
        prodPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel namePricePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        // two columns for name and price
        // on a single row


        // transaction title
        JTextArea names;
        names = new JTextArea();
        names.setText(prod.getName());
        names.setEditable(false);
        names.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        names.setForeground(Color.black);
        names.setLineWrap(true);
        names.setWrapStyleWord(true);
        
        // transaction price
        JTextArea prices;
        prices = new JTextArea();
        prices.setText(String.format("%.2f", prod.getPrice()));
        prices.setEditable(false);
        prices.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        prices.setForeground(Color.black);
        prices.setLineWrap(true);
        prices.setWrapStyleWord(true);

        namePricePanel.add(names);
        namePricePanel.add(prices);

        prodPanel.add(namePricePanel);
        
        // transaction description
        if(!prod.getDescription().isEmpty())
        // if there is a description
        {
            JTextArea details;
            details = new JTextArea();
            details.setText(prod.getDescription());
            details.setEditable(false);
            details.setFont(new Font("Arial", Font.ITALIC, 15));
            details.setForeground(Color.DARK_GRAY);
            details.setLineWrap(true);
            details.setWrapStyleWord(true);

            prodPanel.add(details);
        }
        
            JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
            sep.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            historyPanel.add(prodPanel);
            historyPanel.add(sep);
        // end for
        }

        JScrollPane scrollPane = new JScrollPane(historyPanel);
        scrollPane.setPreferredSize(new Dimension(300, 400));

        popupMenu.add(scrollPane);
        // end else
        }

        return popupMenu;
        
    }

    

}