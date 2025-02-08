package controller.difficulty;

import controller.game.GameControllerImpl;
import controller.game.api.Difficulty;
import controller.menu.MainMenuControllerImpl;
import view.View;
import view.scenes.GameScene;
import view.scenes.MainMenuScene;

/**
 * Implementation of the difficulty selection controller.
 * Manages the selection of game difficulty and initial chips, and handles navigation to different scenes.
 */
public class DifficultySelectionControllerImpl implements DifficultySelectionController {

    private final View mainView;
    private Difficulty difficulty;
    private int initialChips;

    /**
     * Creates a new difficulty selection controller.
     * @param mainView the main view of the application.
     */
    public DifficultySelectionControllerImpl(final View mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToGameScene() {
        if (this.difficulty == null) {
            throw new IllegalStateException("Difficulty not set!");
        }
        if (this.initialChips <= 0) {
            throw new IllegalStateException("Initial chips not set!");
        }
        this.mainView.changeScene(new GameScene(new GameControllerImpl(this.mainView, this.difficulty, this.initialChips)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDifficulty(final Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInitialChips(final int initialChips) {
        this.initialChips = initialChips;
    } 
}
