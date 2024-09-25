package managers;

import pages.*;
import Card_types.*;

import javax.swing.*;
import java.awt.*;

// Custom JButton with rounded corners and background
// RoundedButton class inherits from JButton
public class RoundedButton extends JButton 
{
    private int radius;
    // variable that will store the radius of the custom rounded corners

    public RoundedButton(String text, int radius) 
    {
        super(text);// calls the constructor of the parent class (JButton) and passes the text to it
        this.radius = radius;
        setOpaque(false); // To ensure the background is not painted as a normal rectangle
        setContentAreaFilled(false); // To make the background transparent
        setFocusPainted(false); // To remove the focus outline
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g; // casts the Graphics object g to Graphics2D (a more advanced version)
        g2.setColor(getBackground());// Set the button's background color
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        // Draw the rounded rectangle with the specified radius
        // This will act as the background for the button
        // Starts at (0,0) - top left corner of the button
        // getWidth and getHeight are getters for the button's dimensions
        // radius value is used to specify how rounded the corners should be (horizontally and vertically)
        super.paintComponent(g2);
        // Call the superclass method (paintComponent of the JButton) to handle text and other features
        // because we want Swing to handle thos features :)
    }
}
