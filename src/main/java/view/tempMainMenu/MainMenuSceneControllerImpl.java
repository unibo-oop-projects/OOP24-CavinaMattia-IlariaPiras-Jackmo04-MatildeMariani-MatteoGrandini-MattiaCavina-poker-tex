package view.tempMainMenu;

import controller.MainController;
import view.View;
import view.statistics.StatsScene;
import view.statistics.StatsSceneControllerImpl;
import view.tempMainMenu.api.MainMenuSceneController;

public class MainMenuSceneControllerImpl implements MainMenuSceneController {

    private final View mainView;
    private final MainController mainController;

    public MainMenuSceneControllerImpl(View mainView, MainController mainController) {
        this.mainView = mainView;
        this.mainController = mainController;
    }

    @Override
    public void goToStatsScene() {
        this.mainView.changeScene(new StatsScene(new StatsSceneControllerImpl(mainView, mainController)));
    }

}
