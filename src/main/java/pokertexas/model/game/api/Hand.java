package pokertexas.model.game.api;

import java.util.Iterator;
import java.util.List;

import pokertexas.model.player.api.Action;
import pokertexas.model.player.api.Player;

/**
 * Interface that models a Hand.
 * A Hand has a list of the remaining {@link Player}s, one of those who lost the hand, 
 * a game {@link State} which must always be updated, and a current {@link Phase}.
 */
public interface Hand {

    /**
     * Sorts its list of {@link Player}s, placing the small blind player first, 
     * then the players that were after him in the original list, and lastly those who were before him,
     * in the original order.
     */
    void sortFromRole();

    /**
     * Asks the given {@link Player} for his {@link Action} and updates the given iterator and the game
     * {@link State} accordingly.
     * @param playersIterator the iterator of the list of players still playing.
     * @param player the player whose turn it is.
     */
    void manageAction(Iterator<Player> playersIterator, Player player);

    /**
     * Starts a new Phase in which it iterates through each player of its list until all but one folds or
     * isPhaseOver returns true.
     */
    void startPhase();

    /**
     * Checks if the {@link Phase} is over. Returns true if there is less than the minimum number of 
     * {@link Player}s still playing or if every player either went all-in or betted the current bet, 
     * after at least having gone once through the player list.
     * @return whether the phase is over.
     */
    boolean isPhaseOver();

    /**
     * Checks if the Hand is over. Returns true if there is less than the minimum number of 
     * {@link Player}s still playing or if all {@link Phase}s are completed.
     * @return whether the hand is over.
     */
    boolean isHandOver();

    /**
     * Checks who won the hand (the one with the best combination if there is more than one {@link Player}
     * still in the game or the only one left otherwise) and tells players whether they lost or won accordingly.
    */
    void determinesWinnerOfTheHand();

    /**
     * Returns the list of players in the hand.
     * @return the list of players in the hand.
     */
    List<Player> getRemainingPlayers();

    /**
     * Returns the current Phase.
     * @return the current Phase.
     */
    Phase getCurrentPhase();

}
