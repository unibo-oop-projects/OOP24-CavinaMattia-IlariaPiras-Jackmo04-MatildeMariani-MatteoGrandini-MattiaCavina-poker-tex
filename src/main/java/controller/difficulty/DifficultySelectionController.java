package controller.difficulty;

import controller.game.api.Difficulty;

public interface DifficultySelectionController {

    void goToMainMenuScene();

    void goToGameScene();

    void setDifficulty(Difficulty difficulty);

    void setInitialChips(int initialChips);
}