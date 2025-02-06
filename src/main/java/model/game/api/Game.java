package model.game.api;

import java.util.List;

import model.dealer.api.Dealer;
import model.player.api.Player;
import model.player.api.Role;
import model.player.user.UserPlayer;

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
    boolean isOver();

    /**
     * @return whether the User Player won. 
    */
    boolean isWon();

    /**
     * A new hand begins until isOver returns true, updating the list of {@link Player}s (checking which ones
     * are still in the game) and their {@link Role}s, and asking the {@link Dealer} to deal cards to each of them. 
     * It then goes through each {@link Phase} of the hand until it ends. 
     * The winner of the hand is then declared. 
    */
    void start();

    /**
     * Sets the {@link GameLoop} gameTerminated boolean to true.
     */
    void end();
    
    /**
     * Returns the list of players in the game.
     * @return the list of players in the game.
     */
    List<Player> getPlayers();

    /**
     * Returns the game {@link State}.
     * @return the game State.
     */
    State getGameState();

    /**
     * Returns the {@link UserPlayer}.
     * @return the user player.
     */
    UserPlayer getUserPlayer();

}
