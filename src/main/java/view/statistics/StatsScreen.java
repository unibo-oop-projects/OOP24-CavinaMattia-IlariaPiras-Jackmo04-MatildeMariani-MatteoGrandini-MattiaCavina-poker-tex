package view.statistics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsScreen extends JPanel {

    public StatsScreen(CardLayout cardLayout, JPanel mainPanel) {
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Statistics", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(title, BorderLayout.NORTH);


        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        JPanel gamesPlayedPanel = new StatPanel("Games Played: ", 0);
        JPanel gamesWonPanel = new StatPanel("Games Won: ", 0);
        JPanel handsPlayedPanel = new StatPanel("Hands Played: ", 0);
        JPanel handsWonPanel = new StatPanel("Hands Won: ", 0);
        statsPanel.add(gamesPlayedPanel);
        statsPanel.add(gamesWonPanel);
        statsPanel.add(handsPlayedPanel);
        statsPanel.add(handsWonPanel);
        
        this.add(statsPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        this.add(backButton, BorderLayout.SOUTH);
    }

    private class StatPanel extends JPanel {
        public StatPanel(String name, int value) {
            JLabel nameLabel = new JLabel(name);
            JLabel valueLabel = new JLabel(String.valueOf(value));
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            valueLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            this.add(nameLabel);
            this.add(valueLabel);
            this.setPreferredSize(new Dimension(200, 50));
        }
    }

}
