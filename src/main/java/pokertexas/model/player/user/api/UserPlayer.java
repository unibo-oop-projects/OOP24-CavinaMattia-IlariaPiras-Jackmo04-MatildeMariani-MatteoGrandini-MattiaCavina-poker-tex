package pokertexas.model.player.user.api;

import pokertexas.controller.player.user.UserPlayerController;
import pokertexas.model.player.api.Player;

/**
 * This interface defines the User Player.
 * @see Player.
 */
public interface UserPlayer extends Player {

    /**
     * Gets the controller associated with this user player.
     * @return the controller associated with this user player.
     */
    UserPlayerController getController();

}
