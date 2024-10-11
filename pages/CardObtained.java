package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class CardObtained extends JPanel
{
    public CardObtained(MainFrame mainFrame)
    {
        setBackground(new Color(110, 20, 90));
        setLayout(null);
    }

    // "Congratulations! you are now the proud owner of a [type] card with the number [number]
    // on the name [first name, last name]. Expiration date: [exp_month/exp_year]"

    private void ceva()
    {
        JTextArea instructions = new JTextArea();
        instructions.setText("Congratulations! You are the proud owner of a ");
        instructions.setEditable(false);
        instructions.setFont(new Font("Arial", Font.BOLD, 18));
        instructions.setForeground(Color.BLUE);
        instructions.setBackground(new Color(165, 202, 255));
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setBounds(50, 50, 300, 50);
    }
}
