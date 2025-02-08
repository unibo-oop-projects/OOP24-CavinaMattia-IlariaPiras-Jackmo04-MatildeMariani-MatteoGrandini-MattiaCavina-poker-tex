package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class DifficultySelectionScene implements Scene {
    private static final int COLOR_BUTTONS_PANEL = 0xECCD99;
    private static final int R_BORDER = 0;
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int FONT_SIZE_TITLE = 50; 
    private static final int FONT_SIZE_LABEL = 22; 
    private static final int FONT_SIZE_BUTTON = 22; 
    private static final int TEXT_FIELD_SIZE = 15; 
    private static final int THICKNESS = 2;
    private static final int COLOR_BACKGROUND = 0xDCBA85;
    private static final int COLOR_INPUT_PANEL = 0xECE6D0;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final String FONT = "Roboto";
    private static final String MESSAGE = "Enter your chips and then press enter";
    private static final int MIN = 1000;
    private static final int MAX = 1_000_000;
    private static final String SCENE_NAME = "difficulty selection";
    private static final Logger LOGGER = LoggerFactory.getLogger(DifficultySelectionScene.class);

    private final DifficultySelectionController controller;
    private final JPanel diffSelPanel;
    private final DiffSelButton play = new DiffSelButton("PLAY");
    private boolean difficultySelected;
    private boolean chipsValid;

    /**
     * Constructs a new DifficultySelectionScene.
     * @param controller the controller that handles the difficulty selection logic.
     */
    public DifficultySelectionScene(final DifficultySelectionController controller) { 
        this.diffSelPanel = new JPanel(new BorderLayout());
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(COLOR_BACKGROUND));

        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(new Color(COLOR_BACKGROUND));

        final JLabel title = new JLabel("DIFFICULTY");
        title.setFont(new Font(FONT, Font.BOLD, FONT_SIZE_TITLE));
        titlePanel.add(title);

        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(COLOR_BACKGROUND));

        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1));
        inputPanel.setBackground(new Color(COLOR_BACKGROUND));

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1));
        buttonsPanel.setBackground(new Color(COLOR_BACKGROUND));

        final JRadioButton easy = new JRadioButton("EASY");
        easy.setFont(new Font(FONT, Font.BOLD, FONT_SIZE_BUTTON));
        easy.setHorizontalAlignment(SwingConstants.CENTER);

        final JRadioButton medium = new JRadioButton("MEDIUM");
        medium.setFont(new Font(FONT, Font.BOLD, FONT_SIZE_BUTTON));
        medium.setHorizontalAlignment(SwingConstants.CENTER);

        final JRadioButton hard = new JRadioButton("HARD");
        hard.setFont(new Font(FONT, Font.BOLD, FONT_SIZE_BUTTON));
        hard.setHorizontalAlignment(SwingConstants.CENTER);

        final ActionListener difficultyListener = e -> {
            final JRadioButton source = (JRadioButton) e.getSource();
            switch (source.getText()) {
                case "EASY" -> this.controller.setDifficulty(Difficulty.EASY);
                case "MEDIUM" -> this.controller.setDifficulty(Difficulty.MEDIUM);
                case "HARD" -> this.controller.setDifficulty(Difficulty.HARD);
                default -> {
                    LOGGER.info("Invalid difficulty, default: EASY");
                    this.controller.setDifficulty(Difficulty.EASY);
                }
            }
            this.difficultySelected = true;
            updatePlayButtonState(play.getButton());
        };

        easy.addActionListener(difficultyListener);
        medium.addActionListener(difficultyListener);
        hard.addActionListener(difficultyListener);

        final ButtonGroup group = new ButtonGroup();
        group.add(easy);
        group.add(medium);
        group.add(hard);

        buttonsPanel.add(easy);
        buttonsPanel.add(medium);
        buttonsPanel.add(hard);

        final JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new FlowLayout());
        errorPanel.setBackground(new Color(COLOR_BACKGROUND));

        final JLabel errorLabel = new JLabel("Enter a number between 1000 and 1000000!!");
        errorLabel.setFont(new Font("Roboto", Font.PLAIN, FONT_SIZE_LABEL));
        errorLabel.setBackground(new Color(COLOR_INPUT_PANEL));
        errorLabel.setOpaque(true);
        errorLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        errorLabel.setVisible(false);
        errorPanel.add(errorLabel);

        final JPanel initialChipsPanel = new JPanel();
        initialChipsPanel.setLayout(new GridLayout(2, 1));
        initialChipsPanel.setBackground(new Color(COLOR_BACKGROUND));

        final JLabel initialChipsLabel = new JLabel("How many chips do you want to start with?");
        initialChipsLabel.setFont(new Font(FONT, Font.BOLD, FONT_SIZE_LABEL));
        initialChipsLabel.setBackground(new Color(COLOR_BACKGROUND));
        initialChipsLabel.setOpaque(true);
        initialChipsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        final JTextField input = new JTextField(MESSAGE, TEXT_FIELD_SIZE);
        input.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE_LABEL));
        input.setBackground(new Color(COLOR_INPUT_PANEL));
        input.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                if (MESSAGE.equals(input.getText())) {
                    input.setText(""); 
                }
            }

            @Override
            public void focusLost(final FocusEvent e) {
                if (input.getText().isEmpty()) {
                    input.setText(MESSAGE);
                }
            }
        });
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
                final String text = input.getText() + c;
                try {
                    final long value = Long.parseLong(text);
                    if (value > Integer.MAX_VALUE) {
                        e.consume(); 
                    }
                } catch (NumberFormatException ex) {
                    LOGGER.error("Number conversion error: ", ex);
                }
            }
        });
        input.addActionListener(e -> {
            if (Integer.parseInt(input.getText()) < MIN || Integer.parseInt(input.getText()) > MAX) {
                errorLabel.setVisible(true);
                this.chipsValid = false;
            } else {
                errorLabel.setVisible(false);
                this.controller.setInitialChips(Integer.parseInt(input.getText()));
                this.chipsValid = true;
            }
            updatePlayButtonState(play.getButton()); 
        });
        input.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));

        initialChipsPanel.add(initialChipsLabel);
        initialChipsPanel.add(input);

        final JPanel playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout());
        playPanel.setBackground(new Color(COLOR_BACKGROUND));

        play.getButton().setEnabled(false);
        play.getButton().addActionListener(e -> {
            this.controller.goToGameScene();
        });

        playPanel.add(play.getButton());

        inputPanel.add(buttonsPanel);
        inputPanel.add(errorPanel);
        inputPanel.add(initialChipsPanel);
        inputPanel.add(playPanel);

        centerPanel.add(titlePanel);
        centerPanel.add(inputPanel);
        mainPanel.add(centerPanel);
        this.diffSelPanel.add(mainPanel, BorderLayout.CENTER);

        final DiffSelButton backButton = new DiffSelButton("Back to Menu");
        backButton.getButton().addActionListener(e -> this.controller.goToMainMenuScene());
        this.diffSelPanel.add(backButton.getButton(), BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        final var wrapper = new JPanel(new BorderLayout());
        wrapper.add(this.diffSelPanel, BorderLayout.CENTER);
        return wrapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSceneName() {
        return SCENE_NAME;
    }

    private void updatePlayButtonState(final JButton button) {
    if (this.difficultySelected && this.chipsValid) {
        button.setEnabled(true);
    } else {
        button.setEnabled(false);
    }
}

    /**
     * Custom button class for the DifficultySelectionScene.
     * This class extends JButton and provides a style for buttons in this scene.
     */
    public static class DiffSelButton {

        private final JButton button;

        DiffSelButton(final String text) {
            button = new JButton(text);
            initializeButton();
        }

        private void initializeButton() {
            this.button.setBackground(new Color(COLOR_BUTTONS_PANEL));
            this.button.setForeground(Color.BLACK);
            this.button.setFont(new Font(FONT, Font.BOLD, FONT_SIZE_BUTTON));
            this.button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
                BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
            this.button.setOpaque(true);
            this.button.setContentAreaFilled(true);
            this.button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            this.button.setFocusable(false);
        }

        /**
         * Gets the JButton associated with this DiffSelButton.
         * This method returns the JButton component that is styled and initialized
         * by the DiffSelButton class. 
         * @return the JButton associated with this DiffSelButton.
         */
        public JButton getButton() {
            return this.button;
        }
    }
}
