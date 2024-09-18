/* code belings to user @Lalchand on StackOverflow site
 * https://stackoverflow.com/questions/423950/rounded-swing-jbutton-using-java
 */

import javax.swing.border.Border;
import java.awt.*;

// Custom class for the custom RoundedBorder
// implements the Border interface
public class RoundedBorder implements Border 
{
    private int radius;
    private int borderThickness;
    private Color borderColor;

    public RoundedBorder(int radius, Color borderColor, int borderThickness) 
    // constructor that takes the "radius" integer
    {
        this.radius = radius;
        // assigns the passed value to the class's radius field
        this.borderThickness = borderThickness;
        this.borderColor = borderColor;
    }

    public Insets getBorderInsets(Component c) 
    // method that defines how much padding (Insets)
    // should be around the component
    {
        return new Insets(this.radius + this.borderThickness, this.radius + this.borderThickness, 
        this.radius + this.borderThickness, this.radius + this.borderThickness);
        // top, left, bottom, right insets
    }

    public boolean isBorderOpaque() 
    {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) 
    // method responsible for drawing the border of the component
    // Component c - the Swing component the border is being applied to
    // The Graphics object used to perform the drawing
    // int x, int y - top-left corner where the border will start
    // int width, int height - the domensions of the component (without the border thickness)
    {
        Graphics2D g2 = (Graphics2D) g;
        // casts Graphics to Graphics2D for better control
        g2.setColor(borderColor);
        // sets the border color
        g2.setStroke(new BasicStroke(borderThickness));
        // sets the border thickness
        g2.drawRoundRect(x + borderThickness / 2, y + borderThickness / 2, 
        width - borderThickness, height - borderThickness, 
        radius, radius);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        // draws a rounded rectangle using the specified coordinates and dimensions
        // x, y - starting point
        // width-1, height-1 - reduced by 1 to account for the stroke width
        // radius, radius - the horizontal and vertical rounding for the corners -> defined by radius
        // +- borderThickness trial and error :))
    }
}
