package view.rules;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.rules.RulesController;
import view.commons.Scene;

/**
 * The {@link Scene} for displaying the rules of the game.
 */
public class RulesScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "rules";

    private final RulesController controller;

    /**
     * Creates a new {@link RulesScene}.
     * @param controller the controller for the game rules
     */
    public RulesScene(RulesController controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Regole", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(title, BorderLayout.NORTH);

        // TODO: Add the rules of the game here

        // Back to menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> this.controller.goToMainMenuScene());
        this.add(backButton, BorderLayout.SOUTH);
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
