package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.statistics.StatsPanel;
import view.temp.MainMenuPanel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GameFrame extends JFrame {

    private static final double SCREEN_SIZE_FACTOR = 0.75;

    private final int screenWidth;
    private final int screenHeight;

    public GameFrame() {
        super("Poker Texas Hold'em");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = (int) (screenSize.width * SCREEN_SIZE_FACTOR);
        this.screenHeight = (int) (screenSize.height * SCREEN_SIZE_FACTOR);
        this.setSize(new Dimension(screenWidth, screenHeight));

        // CardLayout for switching between panels
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Game screen panels TODO add other panels
        JPanel menuPanel = new MainMenuPanel(cardLayout, mainPanel);
        JPanel statsPanel = new StatsPanel(cardLayout, mainPanel);

        mainPanel.add(menuPanel, "mainMenu");
        mainPanel.add(statsPanel, "statsScreen");

        this.setContentPane(mainPanel);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

}
