package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.difficulty.DifficultySelectionController;
import controller.game.api.Difficulty;
import view.scenes.api.Scene;

/**
 * The DifficultySelectionScene class represents the scene where the user can select 
 * the game difficulty and initial chips.
 * It provides radio buttons for selecting the difficulty level 
 * and a text field for entering the initial number of chips.
 * The user can proceed to the game scene by pressing the "Play" button.
 */
public class DifficultySelectionScene extends JPanel implements Scene {

    private static final int R_BUTTONS_PANEL = 236;
    private static final int G_BUTTONS_PANEL = 205;
    private static final int B_BUTTONS_PANEL = 153;
    private static final int R_BORDER = 0;  
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int FONT_SIZE_TITLE = 50; 
    private static final int FONT_SIZE_LABEL = 22; 
    private static final int FONT_SIZE_BUTTON = 22; 
    private static final int TEXT_FIELD_SIZE = 15; 
    private static final int THICKNESS = 2;
    private static final int R_BACKGROUND = 220;
    private static final int G_BACKGROUND = 186;
    private static final int B_BACKGROUND = 133;
    private static final int R_INPUT_PANEL = 236;
    private static final int G_INPUT_PANEL = 230;
    private static final int B_INPUT_PANEL = 208;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final String SCENE_NAME = "difficulty selection";

    private final DifficultySelectionController controller;

    /**
     * Constructs a new DifficultySelectionScene.
     * @param controller the controller that handles the difficulty selection logic.
     */
    public DifficultySelectionScene(final DifficultySelectionController controller) { 
        this.controller = controller;
        this.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        JLabel title = new JLabel("DIFFICULTY");
        title.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_TITLE));
        titlePanel.add(title);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,1));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3,1));
        buttonsPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        JRadioButton easy = new JRadioButton("EASY");
        easy.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_BUTTON));
        easy.setHorizontalAlignment(SwingConstants.CENTER);

        JRadioButton medium = new JRadioButton("MEDIUM");
        medium.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_BUTTON));
        medium.setHorizontalAlignment(SwingConstants.CENTER);

        JRadioButton hard = new JRadioButton("HARD");
        hard.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_BUTTON));
        hard.setHorizontalAlignment(SwingConstants.CENTER);

        ActionListener difficultyListener = e -> {
            JRadioButton source = (JRadioButton) e.getSource();
            switch (source.getText()) {
                case "EASY" -> this.controller.setDifficulty(Difficulty.EASY);
                case "MEDIUM" -> this.controller.setDifficulty(Difficulty.MEDIUM);
                case "HARD" -> this.controller.setDifficulty(Difficulty.HARD);
            }
        };

        easy.addActionListener(difficultyListener);
        medium.addActionListener(difficultyListener);
        hard.addActionListener(difficultyListener);

        ButtonGroup group = new ButtonGroup();
        group.add(easy);
        group.add(medium);
        group.add(hard);

        buttonsPanel.add(easy);
        buttonsPanel.add(medium);
        buttonsPanel.add(hard);

        JPanel initialChipsPanel = new JPanel();
        initialChipsPanel.setLayout(new GridLayout(2,1));

        JLabel initialChipsLabel = new JLabel("How many chips do you want to start with?");
        initialChipsLabel.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_LABEL));
        initialChipsLabel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        initialChipsLabel.setOpaque(true);
        initialChipsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField input = new JTextField("", TEXT_FIELD_SIZE);
        input.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_LABEL));
        input.setBackground(new Color(R_INPUT_PANEL, G_INPUT_PANEL, B_INPUT_PANEL));
        input.addActionListener(e -> {
            this.controller.setInitialChips(Integer.parseInt(input.getText()));
        });
        input.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));

        initialChipsPanel.add(initialChipsLabel);
        initialChipsPanel.add(input);

        JPanel playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout());
        playPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        MyButton play = new MyButton("PLAY");

        play.addActionListener(e -> {
            this.controller.goToGameScene();
        });
        
        playPanel.add(play);

        inputPanel.add(buttonsPanel);
        inputPanel.add(initialChipsPanel);
        inputPanel.add(playPanel);

        centerPanel.add(titlePanel);
        centerPanel.add(inputPanel);
        mainPanel.add(centerPanel);
        this.add(mainPanel, BorderLayout.CENTER);

        MyButton backButton = new MyButton("Back to Menu");
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

    /**
     * Custom button class for the DifficultySelectionScene.
     * This class extends JButton and provides a style for buttons in this scene.
     */
    private class MyButton extends JButton {

        public MyButton(String text) {
            super(text);
            this.setBackground(new Color(R_BUTTONS_PANEL, G_BUTTONS_PANEL, B_BUTTONS_PANEL));
            this.setForeground(Color.BLACK);
            this.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_BUTTON));
            this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
                BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
            this.setOpaque(true);
            this.setContentAreaFilled(true);
            this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        }
    }
}