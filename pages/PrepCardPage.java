package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class PrepCardPage extends JPanel
{
    public String first_name;
    public String last_name;

    public PrepCardPage(MainFrame mainFrame)
    {
        setBackground(new Color(110, 20, 90));
        setLayout(null);

        instructions();
        user_fields();
    }

    private JTextArea fnm_instr; // instructions to introduce first name
    private JTextArea lstnm_instr; // instructions to introduce last name

    private void instructions()
    {
        JTextArea instructions = new JTextArea();
        instructions.setText("Please introduce your data for creating a Prepaid Card: ");
        instructions.setEditable(false);
        instructions.setFont(new Font("Arial", Font.BOLD, 18));
        instructions.setForeground(Color.BLUE);
        instructions.setBackground(new Color(165, 202, 255));
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setBounds(50, 50, 300, 50);

        // instructions to ask the user to introduce their first name
        fnm_instr = new JTextArea(); 
        fnm_instr.setText("> Please introduce your first name(s): ");
        fnm_instr.setEditable(false);
        fnm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        fnm_instr.setForeground(Color.WHITE);
        fnm_instr.setBackground(new Color(0,0,0,0));
        fnm_instr.setLineWrap(true);
        fnm_instr.setWrapStyleWord(true);
        fnm_instr.setBounds(50, 160, 300, 50);

        // instructions to ask the user to introduce their last name
        lstnm_instr = new JTextArea(); 
        lstnm_instr.setText("> Please introduce your last name: ");
        lstnm_instr.setEditable(false);
        lstnm_instr.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        lstnm_instr.setForeground(Color.WHITE);
        lstnm_instr.setBackground(new Color(0,0,0,0));
        lstnm_instr.setLineWrap(true);
        lstnm_instr.setWrapStyleWord(true);
        lstnm_instr.setBounds(50, 260, 300, 20);

        add(instructions);
        add(fnm_instr);
        add(lstnm_instr);
    }

    private JTextField fnm_field; // first name field
    private JTextField lstnm_field; // last name field

    private void user_fields()
    {
        // field to let the user introduce their first name (fnm)
        fnm_field = new JTextField();
        fnm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        fnm_field.setBounds(50, 220, 300, 30);

        // field to let the user introduce their last name (lstnm)
        lstnm_field = new JTextField();
        lstnm_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        lstnm_field.setBounds(50, 290, 300, 30);

        fnm_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                first_name = fnm_field.getText();
                lstnm_field.requestFocusInWindow();
            }
        });

        fnm_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                first_name = fnm_field.getText();
            }
        });

        lstnm_field.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                last_name = lstnm_field.getText();
            }
        });

        lstnm_field.addFocusListener(new FocusListener() 
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                // nothing yet
            }
            public void focusLost(FocusEvent e)
            {
                last_name = lstnm_field.getText();
            }
        });
        
        add(fnm_field);
        add(lstnm_field);
    }
}
