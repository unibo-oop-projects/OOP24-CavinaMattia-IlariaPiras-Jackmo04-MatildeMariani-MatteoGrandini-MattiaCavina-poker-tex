package view.menu;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.menu.MainMenuController;
import view.commons.Scene;

/**
 * The {@link Scene} that represents the main menu of the game.
 */
public class MainMenuScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "menu";

    private final MainMenuController controller;

    /**
     * Creates a new {@link MainMenuScene}.
     * @param controller the controller for the main menu
     */
    public MainMenuScene(MainMenuController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Poker Texas Hols'em", SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);

        // Menu buttons panel
        JPanel menuButtons = new JPanel();

        // TODO add other buttons to panel (Play, settings, rules, etc.)
        JButton goToStats = new JButton("Statistiche");
        JButton goToRules = new JButton("Regole");
        menuButtons.add(goToStats);
        menuButtons.add(goToRules);

        this.add(menuButtons, BorderLayout.CENTER);        

        // Button listeners TODO add other listeners
        goToStats.addActionListener(e -> this.controller.goToStatsScene());
        goToRules.addActionListener(e -> this.controller.goToRulesScene());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSceneName() {
        return SCENE_NAME;
    }
}
