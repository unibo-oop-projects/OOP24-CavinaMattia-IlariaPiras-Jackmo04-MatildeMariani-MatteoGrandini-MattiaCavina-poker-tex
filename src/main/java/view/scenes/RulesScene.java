package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JEditorPane;
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
    private static final int SCROLL_INCREMENT = 16;
    private static final int BACK_BTN_FONT_SIZE = 18;
    private static final int BG_COLOR_HEX = 0xDCBA85;

    private final RulesController controller;

    /**
     * Creates a new {@link RulesScene}.
     * @param controller the controller for the game rules
     */
    public RulesScene(final RulesController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        // Editor pane for displaying the rules loaded from an HTML file (Decidere su usare o no)
        JEditorPane container = htmlEditorPane(this.controller.getRulesHtml());
        container.setFocusable(false);
        container.setBackground(new Color(BG_COLOR_HEX));

        // Add a scroll bar to the rules editor pane
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        // Always start at the top of the pane
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
        this.add(scrollPane, BorderLayout.CENTER);

        // Back to menu button, TODO change style to match other buttons
        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, BACK_BTN_FONT_SIZE));
        backButton.addActionListener(e -> this.controller.goToMainMenuScene());
        this.add(backButton, BorderLayout.SOUTH);
    }

    private JEditorPane htmlEditorPane(final String html) {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        editorPane.setText(this.controller.getRulesHtml());
        return editorPane;
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
