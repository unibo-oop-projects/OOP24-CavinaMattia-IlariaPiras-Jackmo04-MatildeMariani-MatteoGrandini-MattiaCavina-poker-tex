package controller.menu;

import controller.rules.RulesControllerImpl;
import controller.statistics.StatsControllerImpl;
import view.View;
import view.rules.RulesScene;
import view.statistics.StatsScene;

/**
 * Implementation of the main menu controller.
 * Manages the switching between the main menu and the other game scenes.
 */
public class MainMenuControllerImpl implements MainMenuController {

    private final View mainView;

    /**
     * Creates a new main menu controller.
     * @param mainView the main view of the application
     */
    public MainMenuControllerImpl(View mainView) {
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

}
