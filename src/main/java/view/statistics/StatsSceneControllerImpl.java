package view.statistics;

import controller.MainController;
import view.View;
import view.tempMainMenu.MainMenuScene;
import view.tempMainMenu.MainMenuSceneControllerImpl;

public class StatsSceneControllerImpl implements StatsSceneController {

    private final View mainView;
    private final MainController mainController;

    public StatsSceneControllerImpl(View mainView, MainController mainController) {
        this.mainView = mainView;
        this.mainController = mainController;
    }

    @Override
    public void goToMainMenu() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuSceneControllerImpl(mainView, mainController)));
    }

}
