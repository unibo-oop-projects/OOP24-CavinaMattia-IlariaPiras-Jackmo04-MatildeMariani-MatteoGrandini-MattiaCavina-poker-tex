package model.game.api;

import model.deck.api.Card;

/**
 * Interface that models a State of a game.
 * A State keeps a certain amount of money given by the sum of the bets made by the players, 
 * the current minimun bet, a set of {@link Card}s common to all players, the number of the hand
 * and the current {@link Phase}. It must be updated regulary.
 */
public interface State {
    
    /**
     * @param playerBet the amount of money a player betted.
     * It adds a bet to the pot.   
    */
    public void addToPot(int playerBet);
    
    /**
     * @param initialBet
     * @param remainingPlayers
     * Informs the State that a new hand is starting. It resets all its component accordingly.   
    */
    public void newHand(int initialBet, int remainingPlayers);

    /**
     * Informs the State a new phase is starting. It sets the current phase accordingly.   
    */
    public void nextHandPhase();
}
