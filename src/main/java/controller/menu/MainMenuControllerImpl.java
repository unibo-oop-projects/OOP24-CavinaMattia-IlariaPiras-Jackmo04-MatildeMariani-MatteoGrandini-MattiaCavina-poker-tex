package controller.menu;

import controller.statistics.StatsControllerImpl;
import view.View;
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

}
