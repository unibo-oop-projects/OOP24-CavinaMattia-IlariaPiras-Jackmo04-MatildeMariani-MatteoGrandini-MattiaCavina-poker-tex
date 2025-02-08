package controller.player.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.game.api.State;
import model.player.api.Action;
import model.player.user.UserPlayer;
import view.player.user.UserPanel;

/**
 * Controller class for managing user player actions and interactions with the UserPanel.
 */
public class UserPlayerController {

    private final UserPlayer userPlayer;
    private final UserPanel userPanel;
    private int raiseAmount;
    private Action action;
    private boolean actionReceived;
    private final Object lock = new Object();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPlayerController.class);

    /**
     * Constructs a UserPlayerController with the specified user player.
     * Initializes the userPanel associated with this controller.
     * @param userPlayer the user player associated with this controller.
     */
    public UserPlayerController(final UserPlayer userPlayer) {
        this.userPlayer = userPlayer;
        this.userPanel = new UserPanel(this);
    }

    /**
     * Receives and handles the action from the GUI.
     * Sets the action and marks it as received.
     * This method is called when an action is performed in the UserPanel. It synchronizes
     * on the lock object to ensure thread safety, sets the action based on the input
     * string, marks the action as received, and notifies any waiting threads.
     * @param action the action received from the GUI.
     * It should be one of the following: "CHECK", "CALL", "RAISE", "FOLD", "ALL_IN".
     * @throws IllegalArgumentException if the action is not one of the expected values.
     */
    public void receiveUserAction(final String action) {
        synchronized (this.lock) {
            this.action = switch (action) {
            case "CHECK" -> Action.CHECK;
            case "CALL" -> Action.CALL;
            case "RAISE" -> Action.RAISE;
            case "FOLD" -> Action.FOLD;
            case "ALL_IN" -> Action.ALL_IN; 
            default -> throw new IllegalArgumentException();
            };
            this.actionReceived = true;
            this.lock.notifyAll();
        }
    }

    /**
     * Gets the action from the user player.
     * Updates the button states and waits for an action to be received.
     * This method updates the states of the buttons in the user interface to reflect
     * the current game state and then waits for the user to perform an action. The method
     * will block until an action is received from the user. Once an action is received,
     * it disables all buttons and returns the action.
     * @return the action received from the user player.
     */
    public Action getUserAction() {
        synchronized (this.lock) {
            while (!this.actionReceived) {
                try {
                    this.lock.wait();
                } catch (InterruptedException ex) {
                    LOGGER.info("Thread was interrupted: ", ex);
                }
            }
        }
        this.actionReceived = false; 
        return this.action;
    }

    /**
     * Checks if the given amount is valid.
     * @param text the amount as a string.
     * @return true if the amount is valid, false otherwise.
     */
    public boolean isAmountOK(final String text) {
        final int amount;
        amount = Integer.parseInt(text);
        return this.userPlayer.getChips() > amount;
    }

    /**
     * Sets the raise amount.
     * @param raiseAmount the amount to raise.
     */
    public void setRaiseAmount(final int raiseAmount) {
        this.raiseAmount = raiseAmount;
    }

    /**
     * Gets the raise amount.
     * @return the raise amount.
     */
    public int getRaiseAmount() {
        return this.raiseAmount;
    }

    /**
     * Checks if the user player can perform a check action.
     * @return true if the user player can check, false otherwise.
     */
    public boolean canCheck() {
        return this.getState().getCurrentBet() == this.userPlayer.getTotalPhaseBet() && this.userPlayer.getChips() > 0;
    }

    /**
     * Checks if the user player can perform a call action.
     * @return true if the user player can call, false otherwise.
     */
    public boolean canCall() {
        return this.userPlayer.getChips() > 0;
    }

    /**
     * Checks if the user player can perform a raise action.
     * @return true if the user player can raise, false otherwise.
     */
    public boolean canRaise() {
        return userPlayer.getChips() > this.getState().getCurrentBet();
    }

    /**
     * Checks if the user player can perform a fold action.
     * @return true if the user player can fold, false otherwise.
     */
    public boolean canFold() {
        return this.userPlayer.getChips() > 0;
    }

    /**
     * Checks if the user player can perform an all-in action.
     * @return true if the user player can all-in, false otherwise.
     */
    public boolean canAllIn() {
        return this.userPlayer.getChips() > 0;
    }

    /**
     * Sets the current state of the game.
     * @param state the current state of the game.
     */
    public State getState() {
        return this.userPlayer.getGameState();
    }

    public UserPanel getUserPanel() {
        return this.userPanel;
    }
}
