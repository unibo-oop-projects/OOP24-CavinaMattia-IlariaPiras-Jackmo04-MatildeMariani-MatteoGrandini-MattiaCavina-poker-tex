package view.panels.api;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel {
        
    public MyLabel(final String text) {
        this.setText(text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    public void setImageFromPath(String image) {
        this.setImageFromIcon(new ImageIcon(image));
    }

    public void setImageFromIcon(ImageIcon image) {
        Image Img = image.getImage()
                        .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(Img));
    }

}