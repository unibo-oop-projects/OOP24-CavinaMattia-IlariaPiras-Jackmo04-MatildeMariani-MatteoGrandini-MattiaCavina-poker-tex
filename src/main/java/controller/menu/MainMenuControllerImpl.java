package controller.menu;

import controller.rules.RulesControllerImpl;
import controller.statistics.StatsControllerImpl;
import view.View;
import view.rules.RulesScene;
import view.statistics.StatsScene;

public class MainMenuControllerImpl implements MainMenuController {

    private final View mainView;

    public MainMenuControllerImpl(View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void goToStatsScene() {
        this.mainView.changeScene(new StatsScene(new StatsControllerImpl(mainView)));
    }

    @Override
    public void goToRulesScene() {
        this.mainView.changeScene(new RulesScene(new RulesControllerImpl(mainView)));
    }

}
