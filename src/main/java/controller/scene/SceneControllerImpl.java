package controller.scene;

import controller.difficulty.DifficultySelectionControllerImpl;
import controller.gameover.GameOverMenuImpl;
import controller.menu.MainMenuControllerImpl;
import controller.rules.RulesControllerImpl;
import controller.statistics.BasicStatisticsControllerImpl;
import view.View;
import view.scenes.DifficultySelectionScene;
import view.scenes.GameOverScene;
import view.scenes.MainMenuScene;
import view.scenes.RulesScene;
import view.scenes.StatsScene;

public class SceneControllerImpl implements SceneController {

        private final View mainView;

    /**
     * Creates a new main menu controller.
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
        this.mainView.changeScene(new StatsScene(new BasicStatisticsControllerImpl(mainView)));
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
     * Method to get mainView.
     * @return
     *          the main view of the application   
     */
    protected View getView() {
        return this.mainView;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void goToGameOverScene(final Boolean endGameStatus) {
        this.mainView.changeScene(new GameOverScene(new GameOverMenuImpl(mainView, endGameStatus)));
    }
    
}
