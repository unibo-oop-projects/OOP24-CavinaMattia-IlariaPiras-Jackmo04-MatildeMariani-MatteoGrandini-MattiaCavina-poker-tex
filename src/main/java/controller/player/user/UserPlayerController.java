package controller.player.user;

import model.game.api.State;
import model.player.api.Action;
import model.player.user.UserPlayer;
import view.player.user.PokerGUI;

/**
 * Controller class for managing user player actions and interactions with the GUI.
 */
public class UserPlayerController {
    
    private final UserPlayer userPlayer;
    private final PokerGUI pokerGUI;
    private int raiseAmount;
    private State state;
    private Action action;
    private boolean actionReceived=false;

    /**
     * Constructs a UserPlayerController with the specified user player.
     * Initializes the PokerGUI associated with this controller.
     * @param userPlayer the user player associated with this controller.
     */
    public UserPlayerController(final UserPlayer userPlayer) {
        this.userPlayer = userPlayer;
        this.pokerGUI = new PokerGUI(this);
    }

    /**
     * Receives and handles the action from the GUI.
     * Sets the action and marks it as received.
     * @param action the action received from the GUI.
     * It should be one of the following: "CHECK", "CALL", "RAISE", "FOLD", "ALL_IN".
     * @throws IllegalArgumentException if the action is not one of the expected values.
     */
    public void receiveUserAction(final String action) {
        this.action = switch (action) {
            case "CHECK" -> Action.CHECK;
            case "CALL" -> Action.CALL;
            case "RAISE" -> Action.RAISE;
            case "FOLD" -> Action.FOLD;
            case "ALL_IN" -> Action.ALL_IN; 
            default -> throw new IllegalArgumentException();
        };
        this.actionReceived=true;
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
        return this.state.getCurrentBet() == this.userPlayer.getTotalPhaseBet();
    }

    /**
     * Checks if the user player can perform a call action.
     * @return true if the user player can call, false otherwise.
     */
    public boolean canCall() {
        return true;
    }

    /**
     * Checks if the user player can perform a raise action.
     * @return true if the user player can raise, false otherwise.
     */
    public boolean canRaise() {
        return userPlayer.getChips() > this.state.getCurrentBet();
    }

    /**
     * Checks if the user player can perform a fold action.
     * @return true if the user player can fold, false otherwise.
     */
    public boolean canFold() {
        return true;
    }

    /**
     * Checks if the user player can perform an all-in action.
     * @return true if the user player can all-in, false otherwise.
     */
    public boolean canAllIn() {
        return true;
    }

    /**
     * Sets the current state of the game.
     * @param state the current state of the game.
     */
    public void setCurrentState(final State state) {
        this.state = state;
    }

    /**
     * Gets the current state of the game.
     * @return the current state of the game.
     */
    public State getCurrentState() {
        return this.state;
    }

    /**
     * Gets the action from the user player.
     * Updates the button states and waits for an action to be received.
     * @return the action received from the user player.
     */
    public Action getUserAction() {
        pokerGUI.updateButtonStates();
        /*while(this.actionReceived == false) {
            //questa parte è da modificare
        }*/
        this.actionReceived = false; 
        pokerGUI.disableAllButtons();
        return this.action;
    }

    /**
     * Checks if the given amount is valid.
     * @param text the amount as a string.
     * @return true if the amount is valid, false otherwise.
     */
    public boolean isAmountOK(String text) {
        int amount;
        amount = Integer.parseInt(text);
        return this.userPlayer.getChips() > amount;
    } 
}