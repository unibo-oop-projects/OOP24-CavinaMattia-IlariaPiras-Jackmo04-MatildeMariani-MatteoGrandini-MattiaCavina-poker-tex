package controller.difficulty;

import controller.game.GameControllerImpl;
import controller.game.api.Difficulty;
import controller.menu.MainMenuControllerImpl;
import view.View;
import view.scenes.GameScene;
import view.scenes.MainMenuScene;

public class DifficultySelectionControllerImpl implements DifficultySelectionController {

    private final View mainView;
    private Difficulty difficulty;
    private int initialChips;
    

    public DifficultySelectionControllerImpl(final View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }

    @Override
    public void goToGameScene() {
        if (this.difficulty == null || this.initialChips <= 0) {
            throw new IllegalStateException("Difficulty or initial chips not set!");
        }
        this.mainView.changeScene(new GameScene(new GameControllerImpl(this.mainView, this.difficulty, this.initialChips)));
    }

    @Override
    public void setDifficulty(final Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void setInitialChips(final int initialChips) {
        this.initialChips = initialChips;
    }   
}