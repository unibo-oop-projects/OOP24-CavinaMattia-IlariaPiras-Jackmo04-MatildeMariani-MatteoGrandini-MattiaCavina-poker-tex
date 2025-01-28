package controller.rules;

import controller.menu.MainMenuControllerImpl;
import view.View;
import view.scenes.MainMenuScene;

/**
 * Implementation of the rules controller.
 * Manages the return to the main menu scene.
 */
public class RulesControllerImpl implements RulesController {

    private View mainView;

    public RulesControllerImpl(View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }  

}
