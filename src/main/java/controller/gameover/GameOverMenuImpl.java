package controller.gameover;

import controller.menu.MainMenuController;
import controller.menu.MainMenuControllerImpl;
import controller.rules.RulesControllerImpl;
import controller.statistics.StatsControllerImpl;
import view.View;
import view.scenes.MainMenuScene;
import view.scenes.RulesScene;
import view.scenes.StatsScene;

public class GameOverMenuImpl extends MainMenuControllerImpl implements GameOverMenu {

    private View mainView;
    
    public GameOverMenuImpl(View mainView) {
        super(mainView);
    }

    @Override
    public void goToMainScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(mainView)));
    }

   
}
