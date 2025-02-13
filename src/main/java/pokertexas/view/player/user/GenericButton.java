package pokertexas.view.player.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * GenericButton class for creating customized buttons with specific styles and actions.
 * This class extends JButton and provides a constructor to initialize the button
 * with specific text, action command, action listener, and adds the button to a specified panel.
 */
public class GenericButton extends JButton {

    private static final long serialVersionUID = 2L;
    private static final int COLOR_BUTTONS_PANEL = 0xECCD99;
    private static final int R_BORDER = 0;
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int FONT_SIZE = 15; 
    private static final int THICKNESS = 2;

    /**
     * Constructs a GenericButton with the specified text.
     * Initializes the button with specific styles.
     * @param text the text to be displayed on the button.
     */
    public GenericButton(final String text) { 
        super(text);
    }

    /**
     * Initializes the button with specific styles and adds it to the specified panel.
     * @param command the action command for the button.
     * @param listener the action listener to handle button click events.
     * @param panel the panel to which the button will be added.
     */
    public void initializeButton(final String command, final ActionListener listener, final JPanel panel) {
        this.setBackground(new Color(COLOR_BUTTONS_PANEL));
        this.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE));
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.setActionCommand(command);
        this.addActionListener(listener);
        panel.add(this);
        this.setFocusable(false);
    }

}
