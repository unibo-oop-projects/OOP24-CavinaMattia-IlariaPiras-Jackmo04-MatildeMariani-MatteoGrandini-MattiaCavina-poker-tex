package pokertexas.controller.menu;

import pokertexas.controller.scene.SceneControllerImpl;
import pokertexas.view.View;

/**
 * Implementation of the main menu controller.
 * Manages the switching between the main menu and the other game scenes.
 */
public class MainMenuControllerImpl extends SceneControllerImpl implements MainMenuController {

    /**
     * Creates a new main menu controller.
     * @param mainView the main view of the application.
     */
    public MainMenuControllerImpl(final View mainView) {
        super(mainView);
    }

}
