package pokertexas.controller.start;

import pokertexas.controller.scene.SceneControllerImpl;
import pokertexas.view.View;

/**
 * Implementation of the start controller.
 * This controller handles the logic for transitioning from the start scene to the main menu scene.
 */
public class StartControllerImpl extends  SceneControllerImpl implements StartController {

    /**
     * Creates a new start controller.
     * @param mainView the main view of the application.
     */
    public StartControllerImpl(final View mainView) {
        super(mainView);
    }

}
