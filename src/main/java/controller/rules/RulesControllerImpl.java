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

    /**
     * Creates a new rules controller.
     * @param mainView the main view of the application
     */
    public RulesControllerImpl(final View mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }

}
