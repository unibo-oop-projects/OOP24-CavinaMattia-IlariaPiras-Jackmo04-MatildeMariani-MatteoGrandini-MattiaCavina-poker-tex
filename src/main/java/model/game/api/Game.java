package model.game.api;

import model.dealer.api.Dealer;
import model.player.api.Player;

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

}
