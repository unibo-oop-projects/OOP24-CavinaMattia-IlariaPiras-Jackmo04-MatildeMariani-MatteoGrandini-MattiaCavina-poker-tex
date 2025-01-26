package model.game.api;

import java.util.Set;

import model.deck.api.Card;

/**
 * Interface that models the State of a game.
 * A State keeps a certain amount of money given by the sum of the bets made by the players in each hand, 
 * the current minimun bet, a set of {@link Card}s common to all players, the number of players
 * still in the game, the number of the hand and the current {@link Phase}. It must be updated regulary.
 */
public interface State {
    
    /**
     * Adds a bet to the pot.
     * @param playerBet the amount of money a player betted.  
    */
    void addToPot(int playerBet);
    
    /**
     * Adds cards to the set of community cards for the hand. 
     * @param cards the set of cards to add.
    */
    void addCommunityCards(Set<Card> cards);

    /**
     * Informs the State that a new hand is starting. It sets all its component accordingly. 
     * @param initialBet the initial bet required to play in the hand.
     * @param remainingPlayers the number of players still in the game at the start of the hand.
    */
    void newHand(int initialBet, int remainingPlayers);

    /**
     * Informs the State a new phase is starting. It sets the current phase accordingly.   
    */
    void nextHandPhase();

    /**
     * Returns the pot, or the winnings of the hand.
     * @return the pot. 
    */
    int getPot();

    /**
     * Returns the current minimum bet players must place if they want to continue playing.
     * @return the current bet. 
    */
    int getCurrentBet();

    /**
     * Returns the number of players still in the game.
     * @return the number of players still in the game. 
    */
    int getRemainingPlayers();

    /**
     * Returns the current hand number.
     * @return the hand's number. 
    */
    int getHandNumber();

    /**
     * Returns the current {@link Phase}.
     * @return the current phase. 
    */
    Phase getHandPhase();

    /**
     * Returns the community cards for the current hand.
     * @return the community cards for the current hand. 
    */
    Set<Card> getCommunityCards();

    /**
     * Sets the current minimum bet players must place if they want to continue playing.
     * @param currentBet the new minimum bet.
    */
    void setCurrentBet(int currentBet);

    /**
     * Sets the number of players still in the game.
     * @param remainingPlayers the number of players still in the game.
    */
    void setRemainingPlayers(int remainingPlayers);

    /**
     * Sets the hand phase.
     * @param handPhase the current phase.
    */
    void setHandPhase(final Phase handPhase);
}
