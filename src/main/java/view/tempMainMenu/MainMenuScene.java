package view.tempMainMenu;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.commons.Scene;
import view.tempMainMenu.api.MainMenuSceneController;

public class MainMenuScene implements Scene {

    private static final String NAME = "menu";

    private final MainMenuSceneController controller;
    private final JPanel panel;

    public MainMenuScene(MainMenuSceneController controller) {
        this.controller = controller;
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        JLabel title = new JLabel("Poker Texas Hols'em", SwingConstants.CENTER);
        this.panel.add(title, BorderLayout.NORTH);

        // Menu buttons
        JPanel menuButtons = new JPanel();

        // TODO add other buttons (Play, settings, rules, etc.)
        JButton goToStats = new JButton("Statistiche");
        menuButtons.add(goToStats);

        this.panel.add(menuButtons, BorderLayout.CENTER);        

        // Button listeners TODO add other listeners
        goToStats.addActionListener(e -> this.controller.goToStatsScene());
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
