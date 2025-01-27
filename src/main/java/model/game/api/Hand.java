package model.game.api;

import java.util.List;

import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;

/**
 * Interface that models a Hand.
 * A Hand has a list of {@link Player}s it works on, methods for managing a {@link Phase} and for
 * determining the winner.
 */
public interface Hand {

    /**
     * Sets its list of {@link Player}s and sorts them from their {@link Role}s.
     * @param players the list of players.
     */
    void setPlayers(List<Player> players);

    /**
     * Sets each {@link Player}'s {@link Role} for the hand, assigning them the role 
     * that the player before them in the list had in the previous hand.
     */
    void setRoles();

    /**
     * Sorts its list of {@link Player}s, placing the player with the given {@link Role} first, 
     * then the players that were after him in the original list, and lastly those who were before him,
     * in the original order.
     * @param firstPlayerRole the role of the player that must be set first of the list.
     */
    void sortFromRole(Role firstPlayerRole);

    /**
     * Asks the given player for his {@link Action} and updates the given game
     * {@link State} accordingly.
     * @param gameState the game state.
     * @param player the player whose turn it is.
     */
    void manageAction(State gameState, Player player);
    
    /**
     * Starts a new Phase in which it iterates through each player of its list until all but one folds or
     * isPhaseOver returns true.
     */
    void startPhase();

    /**
     * Checks if the phase is over. Returns true if there is less than the minimum number of players
     * still playing or if every player either went all-in or betted the current bet.
     * @return whether the phase is over.
     */
    boolean isPhaseOver();

    /**
     * Checks who won the hand (the one with the best combination if there is more than one player still
     * in the game or the only one left otherwise) and tells players whether they lost or won accordingly.
     * @param players the players still in the game. 
    */
    void determinateWinnerOfTheHand(List<Player> players);
    
}
