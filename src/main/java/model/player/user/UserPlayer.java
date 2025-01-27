package model.player.user;

import java.util.Set;

import controller.player.user.UserPlayerController;
import model.game.api.State;
import model.player.AbstractPlayer;
import model.player.api.Action;
import model.player.api.Role;

/**
 * Class representing a human player in the game.
 */
public class UserPlayer extends AbstractPlayer {
    
    private final UserPlayerController controller;

    /**
     * Constructor for the UserPlayer class.
     * @param initialChips the initial amount of chips that the player has.
     * @param initialRole the initial role of the player.
     */
    public UserPlayer(final int initialChips, final Role initialRole) {
        super(initialChips, initialRole);
        this.controller = new UserPlayerController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(final State currentState) {
        var action = controller.getUserAction(currentState.getCurrentBet());
        switch (action) {
            case Action.RAISE -> {
                //da implementare successivamente
                return Action.RAISE;
            }
            case Action.CALL -> {
                if(getChips() < currentState.getCurrentBet()) {
                    this.setChips(0);
                } else {  
                    this.setChips(this.getChips() - currentState.getCurrentBet());
                }
                return Action.CALL;
            }
            case Action.CHECK -> {
                return Action.CHECK;
            }
            case Action.FOLD -> {
                return Action.FOLD;
            }
            default -> throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAI() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handWon(final int winnings) {
        this.setChips(this.getChips() + winnings);
        this.endHand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handLost() {
        this.endHand();
    }

    /**
     * Ends the current hand for the player.
     * This method resets the player's cards and updates the player's role to the next role.
     */
    private void endHand() {
        this.setCards(Set.of());
        this.setRole(getRole().next());
    }
}