package view.panels;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel {
        
    MyLabel(final String text) {
        this.setText(text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    public void setImage(String image) {
        Image Img = new ImageIcon(image).getImage()
                        .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(Img));
    }

}