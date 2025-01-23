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
    * It resets all its component to 0 (or an empty set) and increases the number of the hand.   
    */
    public void restart();
}
