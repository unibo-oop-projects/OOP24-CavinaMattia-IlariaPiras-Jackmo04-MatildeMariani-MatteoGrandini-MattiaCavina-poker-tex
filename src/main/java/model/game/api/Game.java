package model.game.api;

import java.util.List;

import model.dealer.api.Dealer;
import model.player.api.Player;

/**
 * Interface that models a generic a Game.
 * A Game has a {@link Dealer}, a list of {@link Player}s and a {@link State} which must always be updated. 
 * It's divided into several hands, in which it goes through the {@link Phase}s asking the players for their action. 
 * At the end it declares the winner, awarding him the winnings.
 */
public interface Game {

    /**
    * @return true if the user Player is the only one still in the game or if he lost, 
    * false otherwise. 
    */
    public boolean isOver();

    /**
    * @return whether the UserPlayer won. 
    */
    public boolean isWon();

    /**
    * @param players the players still in the game.
    * @return the player who won the hand (the one with the best combination if there  
    * is more than one still in the game or the only one left otherwise).  
    */
    public Player getWinnerOfTheHand(List<Player> players);

    /**
    * Starts a new hand until isOver returns true, checking which player are still in the
    * game and asking the Dealer to deal the cards for each one of them.
    * It then goes through each phase of the hand until it reaches the last 
    * one or until all but one player folds, asking the Dealer for its card and the players for
    * their action.    
    */
    public void start();

}
