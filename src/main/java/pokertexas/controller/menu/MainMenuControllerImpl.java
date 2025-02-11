package pokertexas.controller.menu;

import pokertexas.controller.difficulty.DifficultySelectionControllerImpl;
import pokertexas.controller.rules.RulesControllerImpl;
import pokertexas.controller.statistics.BasicStatisticsControllerImpl;
import pokertexas.view.View;
import pokertexas.view.scenes.DifficultySelectionScene;
import pokertexas.view.scenes.RulesScene;
import pokertexas.view.scenes.StatisticsScene;

/**
 * Implementation of the main menu controller.
 * Manages the switching between the main menu and the other game scenes.
 */
public class MainMenuControllerImpl implements MainMenuController {

    private final View mainView;

    /**
     * Creates a new main menu controller.
     * @param mainView the main view of the application.
     */
    public MainMenuControllerImpl(final View mainView) {
        this.mainView = mainView;
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
     * Method to get mainView.
     * @return the main view of the application.
     */
    protected View getView() {
        return this.mainView;
    }

}
