package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.statistics.StatisticsController;
import view.scenes.api.Scene;

/**
 * The {@link Scene} that displays the statistics of the game.
 */
public class StatisticsScene implements Scene {

    private static final String FONT_FAMILY = "Roboto";
    private static final String SCENE_NAME = "stats";
    private static final Border TITLE_BORDER = BorderFactory.createEmptyBorder(20, 0, 10, 0);
    private static final Border STATS_CONTAINER_BORDER = BorderFactory.createEmptyBorder(10, 100, 10, 100);
    private static final Dimension STAT_PANEL_DIMENSION = new Dimension(200, 50);
    private static final int TITLE_FONT_SIZE = 30;
    private static final int STATS_FONT_SIZE = 20;
    private static final int BTN_FONT_SIZE = 18;
    private static final int BOTTOM_SPACING = 20;
    private static final int LIGHT_GREEN_HEX = 0x88e378;
    private static final int DARK_GREEN_HEX = 0x0cac64;
    private static final int DARKER_GREEN_HEX = 0x2e603f;

    private final JPanel panel;
    private final JPanel statsContainer;
    private final StatisticsController controller;

    /**
     * Creates a new {@link Scene} that displays the statistics of the game.
     * @param statsController the controller for the statistics
     */
    public StatisticsScene(final StatisticsController statsController) {
        this.panel = new JPanel();
        this.controller = statsController;

        this.panel.setBackground(new Color(DARKER_GREEN_HEX));
        this.panel.setLayout(new BorderLayout());
        final JLabel title = new JLabel("Statistics", JLabel.CENTER);
        title.setFont(new Font(FONT_FAMILY, Font.BOLD, TITLE_FONT_SIZE));
        title.setBorder(TITLE_BORDER);
        title.setForeground(Color.WHITE);
        this.panel.add(title, BorderLayout.NORTH);

        this.statsContainer = new JPanel();
        statsContainer.setLayout(new BoxLayout(statsContainer, BoxLayout.Y_AXIS));
        statsContainer.setBorder(STATS_CONTAINER_BORDER);
        statsContainer.setBackground(new Color(DARKER_GREEN_HEX));

        this.updateStats();

        this.panel.add(statsContainer, BorderLayout.CENTER);

        final JButton backButton = new JButton("Back to Menu"); // TODO change style
        backButton.setFont(new Font(FONT_FAMILY, Font.BOLD, BTN_FONT_SIZE));
        backButton.addActionListener(e -> this.controller.goToMainScene());
        this.panel.add(backButton, BorderLayout.SOUTH);
    }

    private void updateStats() {
        final var statsMap = this.controller.getStatistics();
        this.statsContainer.removeAll();
        var count = 0;
        for (final var stat : statsMap) {
            final var panel = this.getStatJPanel(stat.getKey(), stat.getValue());
            panel.setBackground(count++ % 2 == 0 ? new Color(LIGHT_GREEN_HEX) : new Color(DARK_GREEN_HEX));
            this.statsContainer.add(panel);
        }
        this.statsContainer.add(Box.createVerticalStrut(BOTTOM_SPACING));
        final JButton resetButton = new JButton("Reset"); // TODO change style
        resetButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        resetButton.setFont(new Font(FONT_FAMILY, Font.BOLD, BTN_FONT_SIZE));
        resetButton.addActionListener(e -> this.resetStats());
        this.statsContainer.add(resetButton);
    }

    private void resetStats() {
        if (!this.confirmReset()) {
            return;
        }
        this.controller.resetStatistics();
        this.updateStats();
        this.panel.revalidate();
        this.panel.repaint();
    }

    private JPanel getStatJPanel(final String name, final String value) {
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel(name + ": " + value, JLabel.CENTER);
        label.setFont(new Font(FONT_FAMILY, Font.BOLD, STATS_FONT_SIZE));
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.setPreferredSize(STAT_PANEL_DIMENSION);
        return panel;
    }

    private boolean confirmReset() {
        final int confirm = JOptionPane.showOptionDialog(
            this.panel, 
            """
                Are you sure you want to reset the statistics?
                This action cannot be undone.
            """,
            "Reset statistics?",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            null, 
            null
        );
        return confirm == JOptionPane.YES_OPTION;
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
