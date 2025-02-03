package controller.menu;

import controller.difficulty.DifficultySelectionControllerImpl;
import controller.rules.RulesControllerImpl;
import controller.statistics.StatsControllerImpl;
import view.View;
import view.scenes.DifficultySelectionScene;
import view.scenes.RulesScene;
import view.scenes.StatsScene;

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

}
