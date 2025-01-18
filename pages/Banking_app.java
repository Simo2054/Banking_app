package pages;

import Card_types.*;
import managers.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Banking_app
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            new MainFrame();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}