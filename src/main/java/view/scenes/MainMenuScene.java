package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.menu.MainMenuController;
import view.scenes.api.Scene;

/**
 * The {@link Scene} that represents the main menu of the game.
 */
public class MainMenuScene extends JPanel implements Scene {

    private static final int R_BUTTONS_PANEL = 236;
    private static final int G_BUTTONS_PANEL = 205;
    private static final int B_BUTTONS_PANEL = 153;
    private static final int R_BORDER = 0;  
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int FONT_SIZE = 30; 
    private static final int THICKNESS = 4;
    private static final int R_BACKGROUND = 220;
    private static final int G_BACKGROUND = 186;
    private static final int B_BACKGROUND = 133;
    private static final String SCENE_NAME = "menu";

    private final MainMenuController controller;

    /**
     * Creates a new {@link MainMenuScene}.
     * @param controller the controller for the main menu
     */
    public MainMenuScene(final MainMenuController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        JLabel title = new JLabel("MENU", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 50));

        titlePanel.add(title);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4,1,0,5));
        menuPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        MenuButton goToStats = new MenuButton("Statistics");
        MenuButton goToRules = new MenuButton("Rules");
        MenuButton goToDifficultySelection = new MenuButton("Select difficulty");
        MenuButton exit = new MenuButton("Exit");
       
        menuPanel.add(goToStats);
        menuPanel.add(goToRules);
        menuPanel.add(goToDifficultySelection);
        menuPanel.add(exit);

        centerPanel.add(titlePanel);
        centerPanel.add(menuPanel);
        mainPanel.add(centerPanel);

        this.add(mainPanel, BorderLayout.CENTER);

        goToStats.addActionListener(e -> this.controller.goToStatsScene());
        goToRules.addActionListener(e -> this.controller.goToRulesScene());
        goToDifficultySelection.addActionListener(e -> this.controller.goToDifficultySelectionScene());
        exit.addActionListener(e -> this.controller.exitGame());
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

    private class MenuButton extends JButton {
        public MenuButton(String text) {
            super(text);
            this.setBackground(new Color(R_BUTTONS_PANEL, G_BUTTONS_PANEL, B_BUTTONS_PANEL));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE));
            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
                            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
            this.setOpaque(true);
            this.setContentAreaFilled(true);
            this.setPreferredSize(new Dimension(250, 60));
        }
    }

}
