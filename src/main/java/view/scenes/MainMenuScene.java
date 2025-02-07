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
public class MainMenuScene implements Scene {

    private static final int R_BUTTONS_PANEL = 236;
    private static final int G_BUTTONS_PANEL = 205;
    private static final int B_BUTTONS_PANEL = 153;
    private static final int R_BORDER = 0;
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int FONT_SIZE = 30;
    private static final int FONT_SIZE_TITLE = 50;
    private static final int THICKNESS = 4;
    private static final int R_BACKGROUND = 220;
    private static final int G_BACKGROUND = 186;
    private static final int B_BACKGROUND = 133;
    private static final int V_GAP = 5;
    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 60;
    private static final String SCENE_NAME = "menu";

    private final MainMenuController controller;
    private final JPanel mainMenuPanel;

    /**
     * Creates a new {@link MainMenuScene}.
     * @param controller the controller for the main menu
     */
    public MainMenuScene(final MainMenuController controller) {
        this.controller = controller;
        this.mainMenuPanel = new JPanel(new BorderLayout());
        initialize();
    }

    private void initialize() {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        final JLabel title = new JLabel("MENU", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_TITLE));

        titlePanel.add(title);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        final JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 0, V_GAP));
        menuPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        final MenuButton goToStats = new MenuButton("Statistics");
        final MenuButton goToRules = new MenuButton("How to play");
        final MenuButton goToDifficultySelection = new MenuButton("New game");
        final MenuButton exit = new MenuButton("Exit");
       
        menuPanel.add(goToDifficultySelection.getButton());
        menuPanel.add(goToRules.getButton());
        menuPanel.add(goToStats.getButton());
        menuPanel.add(exit.getButton());

        centerPanel.add(titlePanel);
        centerPanel.add(menuPanel);
        mainPanel.add(centerPanel);

        this.mainMenuPanel.add(mainPanel, BorderLayout.CENTER);

        goToStats.getButton().addActionListener(e -> this.controller.goToStatsScene());
        goToRules.getButton().addActionListener(e -> this.controller.goToRulesScene());
        goToDifficultySelection.getButton().addActionListener(e -> this.controller.goToDifficultySelectionScene());
        exit.getButton().addActionListener(e -> this.controller.exitGame());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        final var wrapper = new JPanel(new BorderLayout());
        wrapper.add(this.mainMenuPanel, BorderLayout.CENTER);
        return wrapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSceneName() {
        return SCENE_NAME;
    }

    /**
     * Custom button class for the MainMenuScene.
     * This class extends JButton and provides a style for buttons in this scene.
     */
    private class MenuButton {

        private final JButton button;

        MenuButton(final String text) {
            button = new JButton(text);
            initializeButton();
        }

        private void initializeButton() {
            this.button.setBackground(new Color(R_BUTTONS_PANEL, G_BUTTONS_PANEL, B_BUTTONS_PANEL));
            this.button.setForeground(Color.BLACK);
            this.button.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE));
            this.button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
                            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
            this.button.setOpaque(true);
            this.button.setContentAreaFilled(true);
            this.button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        }

        public JButton getButton() {
            return this.button;
        }
    }
}
