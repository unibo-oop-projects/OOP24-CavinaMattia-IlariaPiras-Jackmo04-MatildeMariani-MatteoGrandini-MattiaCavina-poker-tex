package controller.scene;

import controller.menu.MainMenuControllerImpl;
import controller.rules.RulesControllerImpl;
import controller.statistics.StatsControllerImpl;
import view.View;
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
        this.mainView.changeScene(new StatsScene(new StatsControllerImpl(mainView)));
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'goToDifficultySelectionScene'");
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
    
}
