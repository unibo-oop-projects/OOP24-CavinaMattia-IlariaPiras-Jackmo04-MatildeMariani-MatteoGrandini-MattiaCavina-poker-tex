package controller.start;

import controller.menu.MainMenuControllerImpl;
import view.View;
import view.scenes.MainMenuScene;

public class StartControllerImpl implements StartController {

    private final View mainView;

    public StartControllerImpl(final View mainView) {
        this.mainView = mainView;
    }
    
    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }
    
}