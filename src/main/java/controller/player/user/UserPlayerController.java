package controller.player.user;

import model.player.api.Action;
import model.player.user.UserPlayer;
import model.temp.State;

public class UserPlayerController {
    private final UserPlayer userPlayer;

    public UserPlayerController(UserPlayer userPlayer) {
        this.userPlayer = userPlayer;
    }

    public void receiveUserAction(Action action) {
        // Logic to handle the action received from the GUI
        // This could involve updating the game state or notifying the UserPlayer
    }

    public Action getUserAction(State currentState) {
        // Logic to get the action from the UserPlayer based on the current game state
        return userPlayer.getAction(currentState);
    }
}