package pokertexas.controller.scene;

import pokertexas.controller.difficulty.DifficultySelectionControllerImpl;
import pokertexas.controller.gameover.GameOverMenuImpl;
import pokertexas.controller.menu.MainMenuControllerImpl;
import pokertexas.controller.rules.RulesControllerImpl;
import pokertexas.controller.statistics.BasicStatisticsControllerImpl;
import pokertexas.view.View;
import pokertexas.view.scenes.DifficultySelectionScene;
import pokertexas.view.scenes.GameOverScene;
import pokertexas.view.scenes.MainMenuScene;
import pokertexas.view.scenes.RulesScene;
import pokertexas.view.scenes.StatisticsScene;

/**
 * Class that implements {@link SceneController} to
 * controll change scene in other controller.
 */
public class SceneControllerImpl implements SceneController {

    private final View mainView;

    /**
     * Creates a new main menu controller.
     * 
     * @param mainView the main view of the application.
     */
    public SceneControllerImpl(final View mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(mainView)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToStatsScene() {
        this.mainView.changeScene(new StatisticsScene(new BasicStatisticsControllerImpl(mainView)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToRulesScene() {
        this.mainView.changeScene(new RulesScene(new RulesControllerImpl(mainView)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToDifficultySelectionScene() {
        this.mainView.changeScene(new DifficultySelectionScene(new DifficultySelectionControllerImpl(mainView)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exitGame() {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void goToGameOverScene(final boolean endGameStatus) {
        this.mainView.changeScene(new GameOverScene(new GameOverMenuImpl(mainView, endGameStatus)));
    }

    /**
     * Method to get mainView.
     * 
     * @return the main view of the application
     */
    protected View getView() {
        return this.mainView;
    }

}
