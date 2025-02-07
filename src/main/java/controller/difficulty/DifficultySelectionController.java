package controller.difficulty;

import controller.game.api.Difficulty;

/**
 * Interface for the Difficulty Selection Controller.
 */
public interface DifficultySelectionController {

    /**
     * Goes back to the main menu scene.
     */
    void goToMainMenuScene();

    /**
     * Changes the scene to the game scene.
     */
    void goToGameScene();

    /**
     * Sets the difficulty level for the game.
     * @param difficulty the difficulty level to set.
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * Sets the initial number of chips for the game.
     * @param initialChips the initial number of chips to set.
     */
    void setInitialChips(int initialChips);
}
