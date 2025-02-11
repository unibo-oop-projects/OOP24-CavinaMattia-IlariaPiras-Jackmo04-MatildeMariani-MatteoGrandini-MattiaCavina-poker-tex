package pokertexas.view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Action;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import pokertexas.controller.gameover.GameOverMenu;
import pokertexas.view.scenes.api.Scene;

/**
 * Class to create Game Over pannel.
 * It have different configuration if the player is winner or loser.
 * Extend {@link Jpannel} and implement {@link pokertexas.view.scenes.api.Scene}
 * 
 */
public final class GameOverScene implements Scene {

    private static final String SCENE_NAME = "GameOver";
    private static final String RESOURCE_PATH = "endgame/";
    private static final int TEXT_DIMENSION_BUTTON = 30;
    private static final int TEXT_DIMENSION_RESULT = 50;
    private static final String VICTORY_STRING = "YOU WIN!";
    private static final String LOSE_STRING = "YOU LOSE!";
    private static final String KEY_BORD_SWITCH = "Tab";
    private static final String VICTORY_IMG_NAME = "Victory";
    private static final String LOSE_IMG_NAME = "Lose";
    private static final String IMG_EXTENSION = ".jpg";
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Integer TIME_TO_LAMP = 500;

    private Boolean setLamp = true;

    private final GameOverMenu controller;

    private final JPanel gameOverPanel;

    private final JPanel labelPannel = new JPanel(new BorderLayout());

    private final JLabel endImmage = new JLabel();
    private final JLabel textFinalResult = new JLabel();
    private final Font buttonFont = new Font("BottonFont", Font.BOLD, TEXT_DIMENSION_BUTTON);
    private final Font stringResultFont = new Font("StringResultFont", Font.ROMAN_BASELINE, TEXT_DIMENSION_RESULT);

    /**
     * Creates a new {@link GameOverScene}.
     * 
     * @param controller the controller of the game over menu.
     */
    public GameOverScene(final GameOverMenu controller) {
        this.controller = controller;
        this.gameOverPanel = new JPanel(new BorderLayout());
        initialize();
    }

    /**
     * Initializes the components of the GameOverScene.
     * Sets up the layout, styles, and event listeners for the components.
     */
    private void initialize() {
        final Timer timer = new Timer(TIME_TO_LAMP, getLampAction(textFinalResult));
        timer.start();

        endImmage.setHorizontalAlignment(JLabel.CENTER);
        endImmage.setVerticalAlignment(JLabel.CENTER);

        textFinalResult.setFont(stringResultFont);
        textFinalResult.setHorizontalAlignment(JLabel.CENTER);

        final JButton goToMainMenu = getButtomFeuture("Menu", Color.LIGHT_GRAY, Color.BLACK, buttonFont,
                e -> this.controller.goToMainScene());

        // To change Pannel from win to lose from keyboard.
        final KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        this.gameOverPanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(keyStroke, KEY_BORD_SWITCH);
        this.gameOverPanel.getActionMap().put(KEY_BORD_SWITCH, getChangePannelAction());

        // Add botton at game over pannel.
        this.gameOverPanel.add(endImmage, BorderLayout.CENTER);
        this.gameOverPanel.add(labelPannel, BorderLayout.NORTH);
        this.gameOverPanel.add(goToMainMenu, BorderLayout.SOUTH);

        // add north pannel.
        labelPannel.add(textFinalResult, BorderLayout.CENTER);
        labelPannel.setBackground(Color.GRAY);

        setFinalPannel(controller.isEndGameStatus());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
         final var wrapper = new JPanel(new BorderLayout());
        wrapper.add(this.gameOverPanel, BorderLayout.CENTER);
        return wrapper;
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
            this.gameOverPanel.setBackground(Color.GREEN);
            endImmage.setIcon(
                    new ImageIcon(ClassLoader.getSystemResource(RESOURCE_PATH + VICTORY_IMG_NAME + IMG_EXTENSION)));
            textFinalResult.setText(VICTORY_STRING);
        } else {
            this.gameOverPanel.setBackground(Color.BLACK);
            endImmage.setIcon(
                    new ImageIcon(ClassLoader.getSystemResource(RESOURCE_PATH + LOSE_IMG_NAME + IMG_EXTENSION)));
            textFinalResult.setText(LOSE_STRING);
        }
    }

    private Action getChangePannelAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
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

    private ActionListener getLampAction(final JComponent component) {
        return new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (setLamp) {
                    component.setForeground(TEXT_COLOR);
                } else {
                    component.setForeground(component.getParent().getBackground());
                }
                setLamp = !setLamp;
            }

        };
    }
}
