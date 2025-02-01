package view.player.user;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * MyLabel class for creating customized labels with specific styles and images.
 * This class extends JLabel and provides a constructor to initialize the label
 * with specific image properties and an image path.
 */
public class MyLabel extends JLabel {

    private static final int R_BACKGROUND = 220;
    private static final int G_BACKGROUND = 186;
    private static final int B_BACKGROUND = 133;
    private static final int IMAGE_WIDTH = 60;
    private static final int IMAGE_HEIGHT = 80;
    
    /**
     * Constructs a MyLabel with the specified image path.
     * Initializes the label with specific styles and sets the image.
     * @param imagePath the path to the image to be displayed on the label.
     */
    public MyLabel(String imagePath) {
        super(); 
        this.setImageProperties();
        this.setImageLabel(imagePath);
    }

    /**
     * Sets the properties of the label, including background color and size.
     */
    private void setImageProperties() {
        this.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        this.setOpaque(true);
        this.setSize(IMAGE_WIDTH, IMAGE_HEIGHT); //da modificare poi successivamente
    }

    /**
     * Sets the image on the label.
     * @param imagePath the path to the image to be displayed on the label.
     */
    private void setImageLabel(String imagePath) {
        Image image = new ImageIcon(imagePath).getImage()
                        .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(image));

    }
}
    