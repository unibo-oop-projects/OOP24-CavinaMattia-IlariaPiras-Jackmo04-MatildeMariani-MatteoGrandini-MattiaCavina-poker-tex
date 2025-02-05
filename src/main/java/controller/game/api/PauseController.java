package controller.game.api;

import view.scenes.DifficultySelectionScene;
import view.scenes.MainMenuScene;

/**
 * Interface that models a {@link PauseController}.
 */
public interface PauseController {

    /**
     * Goes to the {@link DifficultySelectionScene}.
     */
    void goToDifficultySelectionScene(); 

    /**
     * Goes to the {@link MainMenuScene}.
     */
    void goToMainMenuScene(); 

}
