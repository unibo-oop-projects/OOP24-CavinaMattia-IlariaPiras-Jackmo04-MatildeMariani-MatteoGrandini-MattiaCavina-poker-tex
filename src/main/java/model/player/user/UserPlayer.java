package model.player.user;

import model.player.AbstractPlayer;
import model.player.api.Action;
import model.player.api.Role;
import model.temp.State;

/**
 * Class representing a human player in the game.
 */
public class UserPlayer extends AbstractPlayer {

    /**
     * Constructor for the UserPlayer class.
     * @param initialChips the initial amount of chips that the player has.
     * @param initialRole the initial role of the player.
     */
    public UserPlayer(final int initialChips, final Role initialRole) {
        super(initialChips, initialRole);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(final State currentState) {
        // Implement the logic to get the action from the user input
        // For now, we'll just return a default action
        return Action.CALL;
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

    private void endHand() {
        this.setRole(getRole().next());
    }
}