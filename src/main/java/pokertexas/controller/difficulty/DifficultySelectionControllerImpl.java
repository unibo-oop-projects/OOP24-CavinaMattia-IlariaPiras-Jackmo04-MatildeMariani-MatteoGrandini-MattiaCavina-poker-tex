package pokertexas.controller.difficulty;

import pokertexas.controller.game.api.Difficulty;
import pokertexas.controller.scene.SceneControllerImpl;
import pokertexas.view.View;

/**
 * Implementation of the difficulty selection controller.
 * Manages the selection of game difficulty and initial chips, and handles navigation to different scenes.
 */
public class DifficultySelectionControllerImpl extends SceneControllerImpl implements DifficultySelectionController {

    private Difficulty difficulty;
    private int initialChips;

    /**
     * Creates a new difficulty selection controller.
     * @param mainView the main view of the application.
     */
    public DifficultySelectionControllerImpl(final View mainView) {
        super(mainView);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInitialChips() {
        return this.initialChips;
    }

}
