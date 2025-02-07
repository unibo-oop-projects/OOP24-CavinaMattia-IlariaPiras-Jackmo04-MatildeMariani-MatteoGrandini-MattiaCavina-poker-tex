package controller.start;

import controller.menu.MainMenuControllerImpl;
import view.View;
import view.scenes.MainMenuScene;

/**
 * Implementation of the start controller.
 * This controller handles the logic for transitioning from the start scene to the main menu scene.
 */
public class StartControllerImpl implements StartController {

    private final View mainView;

    /**
     * Creates a new start controller.
     * @param mainView the main view of the application.
     */
    public StartControllerImpl(final View mainView) {
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
