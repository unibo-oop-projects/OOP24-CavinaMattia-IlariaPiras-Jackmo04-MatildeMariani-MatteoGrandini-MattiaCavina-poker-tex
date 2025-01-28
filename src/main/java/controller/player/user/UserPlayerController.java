package controller.player.user;

import model.player.api.Action;
import model.player.user.UserPlayer;
import view.player.user.PokerGUI;

/**
 * Controller class for managing user player actions and interactions with the GUI.
 */
public class UserPlayerController {
    
    private final UserPlayer userPlayer;
    private final PokerGUI pokerGUI;
    private String action;
    private boolean actionReceived=false;
    private int raiseAmount;

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
     */
    public void receiveUserAction(final String action) {
        this.action = action;
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
     * @param currentBet the current bet in the game.
     * @return true if the user player can check, false otherwise.
     */
    public boolean canCheck(final int currentBet) {
        return currentBet == 0;
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
     * @param currentBet the current bet in the game.
     * @return true if the user player can raise, false otherwise.
     */
    public boolean canRaise(final int currentBet) {
        return userPlayer.getChips() > currentBet;
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
     * Gets the action from the user player based on the current game state.
     * Updates the button states and waits for an action to be received.
     * @param currentBet the current bet in the game.
     * @return the action received from the user player.
     */
    public Action getUserAction(final int currentBet) {
        pokerGUI.updateButtonStates(currentBet);
        while(this.actionReceived == false) {
            //qui farà qualcosa
        }
        this.actionReceived = false; 
        pokerGUI.disableAllButtons();
        switch (this.action) {
            case "CHECK" -> {
                return Action.CHECK;
            } 
            case "CALL" -> {
                return Action.CALL;
            }
            case "RAISE" -> {
                return Action.RAISE;
            }
            case "FOLD" -> {
                return Action.FOLD;
            }
            case "ALL_IN" -> {
                return Action.ALL_IN; 
            }
            default -> throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if the amount entered in the text field is valid.
     * @return true if the amount is valid, false otherwise.
     */
    public boolean isAmountOK(String text) {
        int amount;
        amount = Integer.parseInt(text);
        return this.userPlayer.getChips() > amount;
    } 
}