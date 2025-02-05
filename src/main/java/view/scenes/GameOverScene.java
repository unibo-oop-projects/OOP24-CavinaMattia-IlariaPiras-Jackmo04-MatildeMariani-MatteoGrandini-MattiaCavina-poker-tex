package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.gameover.GameOverMenu;
import view.scenes.api.Scene;

/**
 * Class to create Game Over pannel.
 * It have different configuration if the player is winner or loser.
 * Extend {@link Jpannel} and implement {@link view.scenes.api.Scene}
 * 
 */
public class GameOverScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "GameOver";
    private static final String RESOURCE_PATH = "endgame/";
    private static final int TEXT_DIMENSION_BUTTON = 20;
    private static final int TEXT_DIMENSION_RESULT = 50;
    private static final String VICTORY_STRING = "YOU WIN!";
    private static final String LOSE_STRING = "YOU LOSE!";

    private final GameOverMenu controller;

    private final JLabel endImmage = new JLabel();
    private final JLabel textFinalResult = new JLabel();
    private final Font buttonFont = new Font("BottonFont", Font.ROMAN_BASELINE, TEXT_DIMENSION_BUTTON);
    private final Font stringResultFont = new Font("StringResultFont", Font.ROMAN_BASELINE, TEXT_DIMENSION_RESULT);

    /**
     * Creates a new {@link GameOverScene}.
     * 
     * @param controller
     *                   the controller of the game over menu.
     */
    public GameOverScene(final GameOverMenu controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());

        endImmage.setHorizontalAlignment(JLabel.CENTER);
        endImmage.setVerticalAlignment(JLabel.CENTER);

        textFinalResult.setFont(stringResultFont);
        textFinalResult.setHorizontalAlignment(JLabel.CENTER);

        setFinalPannel(controller.isEndGameStatus());

        final JButton goToStats = getButtomFeuture("Statistics", Color.GRAY, Color.BLACK, buttonFont,
                e -> this.controller.goToStatsScene());

        final JButton goToExitGame = getButtomFeuture("Exit Game", Color.DARK_GRAY, Color.WHITE, buttonFont,
                e -> this.controller.exitGame());

        final JButton goToMainMenu = getButtomFeuture("Main Menu", Color.LIGHT_GRAY, Color.BLACK, buttonFont,
                e -> this.controller.goToMainScene());

        // To change Pannel from win to lose from keyboard.
        final String key = "Tab";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(keyStroke, key);
        this.getActionMap().put(key, getChangePannelAction());

        // Add botton at game over pannel.
        this.add(goToExitGame, BorderLayout.EAST);
        this.add(goToMainMenu, BorderLayout.WEST);
        this.add(endImmage, BorderLayout.CENTER);
        this.add(textFinalResult, BorderLayout.NORTH);
        this.add(goToStats, BorderLayout.SOUTH);

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

    private void setFinalPannel(final Boolean status) {
        if (status) {
            this.setBackground(Color.GREEN);
            endImmage.setIcon(new ImageIcon(ClassLoader.getSystemResource(RESOURCE_PATH + "Victory.jpg")));
            textFinalResult.setText(VICTORY_STRING);
            textFinalResult.setForeground(Color.BLACK);
        } else {
            this.setBackground(Color.BLACK);
            endImmage.setIcon(new ImageIcon(ClassLoader.getSystemResource(RESOURCE_PATH + "Lose.jpg")));
            textFinalResult.setText(LOSE_STRING);
            textFinalResult.setForeground(Color.WHITE);
        }
    }

    private Action getChangePannelAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeResultPannel();
                setFinalPannel(controller.isEndGameStatus());
            }
        };
    }

    private JButton getButtomFeuture(final String name, final Color backgroud, final Color foreGround,
            final Font font, final ActionListener action) {

        final JButton button = new JButton(name);
        button.setBackground(backgroud);
        button.setForeground(foreGround);
        button.setFont(font);
        button.addActionListener(action);
        return button;

    }
}
