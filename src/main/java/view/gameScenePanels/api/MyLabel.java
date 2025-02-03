package view.gameScenePanels.api;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class that models a label with specific caratteristics. 
 */
public class MyLabel extends JLabel {
    
    /**
     * Creates a new MyLabel.
     * @param text the label text.
     */
    public MyLabel(final String text) {
        this.setText(text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * Set the label icon from the path of an image.
     * @param image the image's path.
     */
    public void setImageFromPath(String image) {
        this.setImageFromIcon(new ImageIcon(image));
    }

    /**
     * Sets the label icon from an {@link ImageIcon}, scaling it to the width and height of the label.
     * @param image the ImageIcon.
     */
    public void setImageFromIcon(ImageIcon image) {
        Image Img = image.getImage()
                        .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(Img));
    }

}