package controller.gameover;

import controller.scene.SceneController;

/**
 * Inteface to controll {@link view.scenes.GameOverScene} and its feuture like
 * the player is winner and looser.
 * This class extend {@link MainMenuControllor}.
 */
public interface GameOverMenu extends SceneController {

    /**
     * Method to change scene to {@link view.scenes.MainMenuScene}.
     */
    void goToMainScene();

    /**
     * Method to change result pannel to from win to lose.
     */
    void changeResultPannel();

    /**
     * Method to get status of end game, if player is winner or looser.
     * 
     * @return
     *         boolean that identify status true is winner and false is looser
     */
    boolean isEndGameStatus();
}
