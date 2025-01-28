package view.statistics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.statistics.StatsController;
import view.commons.Scene;

public class StatsScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "stats";

    private final JPanel statsPanel;
    private final StatsController controller;

    public StatsScene(StatsController statsController) {
        this.controller = statsController;

        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Statistics", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(title, BorderLayout.NORTH);

        this.statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        this.updateStats();
        
        this.add(statsPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> this.controller.goToMainMenuScene());
        this.add(backButton, BorderLayout.SOUTH);
    }

    public void updateStats() {
        var statsMap = this.controller.getStatistics();
        this.statsPanel.removeAll();
        statsMap.forEach(p -> 
            this.statsPanel.add(new StatPanel(p.elem1(), p.elem2()))
        );
    }

    // Inner class to create a panel for each statistic (not definitive)
    private class StatPanel extends JPanel {
        public StatPanel(String name, String value) {
            JLabel nameLabel = new JLabel(name + ": ");
            JLabel valueLabel = new JLabel(value);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            valueLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            this.add(nameLabel);
            this.add(valueLabel);
            this.setPreferredSize(new Dimension(200, 50));
        }
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public String getSceneName() {
        return SCENE_NAME;
    }

}
