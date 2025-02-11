package pokertexas.view.gamepanels.api;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class with methods to create a label with specific characteristics and set its icon. 
 */
public class CustomLabel {

    private static final int FONT_SIZE = 12;
    private static final String FONT_FAMILY = "Roboto";

    /**
     * Returns a new label with specific characteristics.
     * @param text the label text.
     * @return a new label.
     */
    public JLabel getCustomLabel(final String text) {
        final var label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        return label;
    }

    /**
     * Set the label icon from the path of an image.
     * @param label the label.
     * @param image the image's path.
     */
    public void setIconFromPath(final JLabel label, final String image) {
        this.setIconFromIcon(label, new ImageIcon(ClassLoader.getSystemResource(image)));
    }

    /**
     * Sets the label icon from an {@link ImageIcon}, scaling it to the width and height of the label.
     * @param label the label.
     * @param image the ImageIcon.
     */
    public void setIconFromIcon(final JLabel label, final ImageIcon image) {
        final Image img = image.getImage()
            .getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(img));
    }

}
