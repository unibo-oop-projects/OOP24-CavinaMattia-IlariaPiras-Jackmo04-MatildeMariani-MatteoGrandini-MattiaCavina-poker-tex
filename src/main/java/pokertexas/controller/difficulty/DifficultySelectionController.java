package pokertexas.controller.difficulty;

import pokertexas.controller.game.api.Difficulty;
import pokertexas.controller.scene.SceneController;

/**
 * Interface for the Difficulty Selection Controller.
 */
public interface DifficultySelectionController extends SceneController {

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

    /**
     * Gets the difficulty.
     * This method returns the difficulty.
     * @return the difficulty.
     */
    Difficulty getDifficulty();

    /**
     * Gets the initial chips.
     * This method returns the initial chips.
     * @return the initial chips.
     */
    int getInitialChips();

}
