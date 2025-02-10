package controller.scene;

/**
 * Interface for the main menu controller.
 */
public interface SceneController {

    /**
     * Method to change scene to MainScene.
     */
    void goToMainScene();

    /**
     * Changes the scene to the statistics scene.
     */
    void goToStatsScene();

    /**
     * Changes the scene to the game rules scene.
     */
    void goToRulesScene();

    /**
     * Changes the scene to the difficulty selection scene.
     */
    void goToDifficultySelectionScene();

    /**
     * Changes the scene to the difficulty selection scene.
     * 
     * @param endGameStatus Boolean that identify if the player is winner or looser,
     *                      used to change final pannel.
     */
    void goToGameOverScene(boolean endGameStatus);

    /**
     * Exits the game.
     */
    void exitGame();

}
