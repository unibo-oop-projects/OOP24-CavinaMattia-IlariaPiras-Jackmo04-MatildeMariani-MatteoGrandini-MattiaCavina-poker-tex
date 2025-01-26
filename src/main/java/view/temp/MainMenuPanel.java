package view.temp;

import javax.swing.*;
import java.awt.*;

/**
 * Temporanea, da sostituire con quella di Mati :)
 */
public class MainMenuPanel extends JPanel {

    public MainMenuPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Poker Texas Hols'em", SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);

        // Menu buttons TODO add other buttons (Play, settings, rules, etc.)
        JPanel menuButtons = new JPanel();
        JButton goToStats = new JButton("Statistiche");
        menuButtons.add(goToStats);
        this.add(menuButtons, BorderLayout.CENTER);        

        // Button listeners TODO add other listeners
        goToStats.addActionListener(e -> cardLayout.show(mainPanel, "statistics"));

    }
}

