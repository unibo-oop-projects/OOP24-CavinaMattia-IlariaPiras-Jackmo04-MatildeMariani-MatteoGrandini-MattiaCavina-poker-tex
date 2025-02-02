package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.gameover.GameOverMenu;
import view.scenes.api.Scene;

/**
 * The {@link Scene} that represents the main menu of the game.
 */
public class EndGameScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "endgame";
    private static final String RESOURCE_PATH = "endgame/";

    private final GameOverMenu controller;

    private boolean endGameStatus;
    private final JPanel victoryPanel;
    private final JLabel endImmage = new JLabel();
    private final JLabel textFinalResult = new JLabel();
    private final Font standardFont = new Font("PROVA", Font.ROMAN_BASELINE, 20);

    /**
     * Creates a new {@link MainMenuScene}.
     * 
     * @param controller the controller for the main menu
     */
    public EndGameScene(final GameOverMenu controller, Boolean endGameStatus) {
        this.controller = controller;
        this.endGameStatus = endGameStatus;
        this.setLayout(new BorderLayout());

        endImmage.setMaximumSize(new Dimension(50, 50));
        endImmage.setHorizontalAlignment(JLabel.CENTER);
        endImmage.setVerticalAlignment(JLabel.CENTER);

        textFinalResult.setFont(new Font("PROVA", Font.ITALIC, 50));
        textFinalResult.setHorizontalAlignment(JLabel.CENTER);

        
        // Lose and Win panel
        victoryPanel = new JPanel(new BorderLayout());
        getFinalPannel(this.endGameStatus);

        // Choose button pannel
        final JPanel menuButtons = new JPanel();

        // TODO add other buttons to panel (Play, settings, rules, etc.)
        JButton goToStats = new JButton("Statistics");

        JButton goToExitGame = new JButton("Exit Game");
        goToExitGame.setBackground(Color.DARK_GRAY);
        goToExitGame.setFont(standardFont);
        goToExitGame.setForeground(Color.WHITE);

        JButton goToMainMenu = new JButton("Main Menu");
        goToMainMenu.setFont(standardFont);
        goToMainMenu.setBackground(Color.LIGHT_GRAY);

        /** BUttuon to test both pannel */
        JButton changeresul = new JButton("Change");

        changeresul.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setEndGameStatus(!getEndGameStatus());
                getFinalPannel(getEndGameStatus());
            }
        });

        victoryPanel.add(goToExitGame, BorderLayout.EAST);
        victoryPanel.add(goToMainMenu, BorderLayout.WEST);
        victoryPanel.add(endImmage, BorderLayout.CENTER);
        victoryPanel.add(textFinalResult, BorderLayout.NORTH);

        menuButtons.add(goToStats);
        menuButtons.add(changeresul);

        this.add(menuButtons, BorderLayout.SOUTH);
        this.add(victoryPanel, BorderLayout.CENTER);

        goToStats.addActionListener(e -> this.controller.goToStatsScene());
        goToExitGame.addActionListener(e -> this.controller.exitGame());
        goToMainMenu.addActionListener(e -> this.controller.goToMainScene());
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

    public void getFinalPannel(Boolean status) {
        if (status) {
            victoryPanel.setBackground(Color.GREEN);
            endImmage.setIcon(new ImageIcon(ClassLoader.getSystemResource(RESOURCE_PATH + "Victory.jpg")));
            textFinalResult.setText("YOU WIN!");
            textFinalResult.setForeground(Color.BLACK);
        } else {
            victoryPanel.setBackground(Color.BLACK);
            endImmage.setIcon(new ImageIcon(ClassLoader.getSystemResource(RESOURCE_PATH + "Lose.jpg")));
            textFinalResult.setText("YOU LOSE!");
            textFinalResult.setForeground(Color.WHITE);
        }
    }

    private void setEndGameStatus(boolean status) {
        this.endGameStatus = status;
    }

    private boolean getEndGameStatus() {
        return this.endGameStatus;
    }

}
