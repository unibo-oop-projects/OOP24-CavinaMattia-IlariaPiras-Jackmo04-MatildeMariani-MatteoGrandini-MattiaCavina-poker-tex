package controller.gameover;

import controller.menu.MainMenuControllerImpl;
import view.View;
import view.scenes.MainMenuScene;

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
