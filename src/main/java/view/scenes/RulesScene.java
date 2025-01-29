package view.scenes;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import controller.rules.RulesController;
import view.scenes.api.Scene;

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
    public RulesScene(final RulesController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Regole", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Rules
        JPanel rulesPanel = new JPanel();
        rulesPanel.add(title);
        
        // TODO: Add the rules of the game here

        // Add a scroll bar to the rules editor pane
        JScrollPane scrollPane = new JScrollPane(rulesPanel);
        // Always start at the top of the pane
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
        this.add(scrollPane, BorderLayout.CENTER);

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
