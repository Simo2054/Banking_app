package managers;

import pages.*;
import Card_types.*;

import javax.swing.*;
import java.awt.*;

public class ImageManager 
{
    // method to return an image as a JLabel for easy image manipulation
    // path = path to the image
    // width - width of the resized image
    // height - height of the resized image 
    public JLabel createImgLabel(String path, int width, int height)
    {
        ImageIcon photo = new ImageIcon(path);
        if(photo != null)
        {
            Image img = photo.getImage();
            Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);// resize the img
            photo = new ImageIcon(resizedImg);
        }
        // the order is important
        // first, we resize the image and then we cast it to label
        JLabel photoLabel = new JLabel(photo);
        return photoLabel;
    }
}
