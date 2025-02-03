package controller.difficulty;

import view.scenes.DifficultySelectionScene.Difficulty;

public interface DifficultySelectionController {

    void goToMainMenuScene();

    void goToGameScene();

    void setDifficulty(Difficulty difficulty);

    void setInitialChips(int initialChips);
}