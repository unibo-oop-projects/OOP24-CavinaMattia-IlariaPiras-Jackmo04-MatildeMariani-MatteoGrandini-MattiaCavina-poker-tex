package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
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
public class RulesScene implements Scene {

    private static final String FONT_FAMILY = "Roboto";
    private static final String SCENE_NAME = "rules";
    private static final int SCROLL_INCREMENT = 16;
    private static final int BG_COLOR_HEX = 0xDCBA85;
    private static final int BACK_BTN_FONT_SIZE = 22;
    private static final int BTN_BORDER_SIZE = 2;
    private static final int BTN_HEIGHT = 50;

    private final JPanel panel;
    private final RulesController controller;

    /**
     * Creates a new {@link RulesScene}.
     * @param controller the controller for the game rules
     */
    public RulesScene(final RulesController controller) {
        this.panel = new JPanel();
        this.controller = controller;
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(new Color(BG_COLOR_HEX));

        // Editor pane for displaying the rules loaded from an HTML file
        final JEditorPane container = htmlEditorPane(this.controller.getRulesHtml());
        container.setFocusable(false);
        container.setBackground(new Color(BG_COLOR_HEX));

        // Add a scroll bar to the rules editor pane
        final JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        // Always start at the top of the pane
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
        this.panel.add(scrollPane, BorderLayout.CENTER);

        // Back to menu button
        final JButton backButton = getCustomButton("Back to Menu");
        backButton.addActionListener(e -> this.controller.goToMainScene());
        this.panel.add(backButton, BorderLayout.SOUTH);
    }

    private JEditorPane htmlEditorPane(final String html) {
        final JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        editorPane.setText(html);
        return editorPane;
    }

    private JButton getCustomButton(final String text) {
        final JButton button = new JButton(text);
        button.setBackground(new Color(0xeccd99));
        button.setForeground(Color.BLACK);
        button.setFont(new Font(FONT_FAMILY, Font.BOLD, BACK_BTN_FONT_SIZE));
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(Color.BLACK, BTN_BORDER_SIZE, true)));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(button.getPreferredSize().width, BTN_HEIGHT));
        return button;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        final var wrapper = new JPanel(new BorderLayout());
        wrapper.add(this.panel, BorderLayout.CENTER);
        return wrapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSceneName() {
        return SCENE_NAME;
    }

}
