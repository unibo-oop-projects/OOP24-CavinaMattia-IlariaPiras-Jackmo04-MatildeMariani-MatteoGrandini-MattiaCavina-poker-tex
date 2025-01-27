package model.game.api;

import java.util.List;

import model.dealer.api.Dealer;
import model.player.api.Player;
import model.player.api.Role;

/**
 * Interface that models a generic a Game.
 * A Game has a {@link Dealer}, a list of {@link Player}s and a {@link State} which must always be updated. 
 * It provides methods to start the game, to check if it's over and if the user player won.
 */
public interface Game {

    /**
     * @return true if the user Player is the only one still in the game or if he lost, 
     * false otherwise. 
    */
    public boolean isOver();

    /**
     * @return whether the User Player won. 
    */
    public boolean isWon();

    /**
     * A new hand begins until isOver returns true, checking which {@link Player}s are still in the
     * game, sorting them by their {@link Role}s and asking the {@link Dealer} to deal cards to
     * each of them. It then goes through each {@link Phase} of the hand until it reaches the last 
     * one or until all but one player folds, asking the dealer for the community cards and the players for
     * their action. At the end of each hand it declares the winner.   
    */
    public void start();
    
    /**
     * Returns the list of players in the game.
     * @return the list of players in the game.
     */
    public List<Player> getPlayers();

    /**
     * Returns the game {@link State}.
     * @return the game State.
     */
    public State getGameState();

    /**
     * Sort the given list of {@link Player}s, placing the player with the given {@link Role} first, 
     * then the players that were after him in the original list, and lastly those who were before him,
     * in the original order.
     * @param players the list of players to order.
     * @param firstPlayerRole the role of the player that must be set first of the list.
     */
    void sortFromRole(final List<Player> players, final Role firstPlayerRole);

}
