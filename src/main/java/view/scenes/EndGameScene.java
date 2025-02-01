package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import controller.gameover.GameOverMenu;
import view.scenes.api.Scene;

/**
 * The {@link Scene} that represents the main menu of the game.
 */
public class EndGameScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "endgame";

    private final GameOverMenu controller;

    private boolean endGameStatus = false;
    final JPanel victoryPanel;
    final JPanel losePanel;

    /**
     * Creates a new {@link MainMenuScene}.
     * 
     * @param controller the controller for the main menu
     */
    public EndGameScene(final GameOverMenu controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        // Lose and Win panel
        victoryPanel = new JPanel(new BorderLayout());
        losePanel = new JPanel(new BorderLayout());
        this.setBackground(Color.GREEN);

        // Choose button pannel
        final JPanel menuButtons = new JPanel();

        // TODO add other buttons to panel (Play, settings, rules, etc.)
        JButton goToStats = new JButton("Statistics");
        JButton goToExitGame = new JButton("Exit Game");
        JButton goToMainMenu = new JButton("Main Menu");

        JButton changeresul = new JButton("Change");
        changeresul.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                endGameStatus = !endGameStatus;
                getFinalPannel(endGameStatus);
            }
            
        });

        menuButtons.add(goToStats);
        menuButtons.add(goToExitGame);
        menuButtons.add(goToMainMenu);
        menuButtons.add(changeresul);

        this.add(menuButtons, BorderLayout.SOUTH);

        // Button listeners TODO add other listeners
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
       if(status){
        this.setBackground(Color.GREEN);
       } 
       else{
        this.setBackground(Color.BLACK);
       }
    }

    private void setEndGameStatus(boolean status) {
        this.endGameStatus = status;
    }

    private boolean getEndGameStatus() {
        return this.endGameStatus;
    }

    private void setSizeofInternal(JPanel pannel){
        var initWidth = (int) (300);
        var initHeight = (int) (300);
        pannel.setSize(new Dimension(initWidth, initHeight));
    }

}
