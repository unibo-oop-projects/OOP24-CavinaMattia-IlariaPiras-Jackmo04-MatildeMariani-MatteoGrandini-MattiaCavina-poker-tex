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
     * @param playerBet the amount of money a player betted.
     * It adds a bet to the pot.   
    */
    void addToPot(int playerBet);
    
    /**
     * @param cards 
     * It adds cards to the set of community cards for the hand.   
    */
    void addCommunityCards(Set<Card> cards);

    /**
     * @param initialBet
     * @param remainingPlayers
     * Informs the State that a new hand is starting. It sets all its component accordingly.   
    */
    void newHand(int initialBet, int remainingPlayers);

    /**
     * Informs the State a new phase is starting. It sets the current phase accordingly.   
    */
    void nextHandPhase();

    /**
     * @return the pot, or the winnings of the hand. 
    */
    int getPot();

    /**
     * Returns the current minimum bet players must place if they want to continue playing.
     * @return the current bet. 
    */
    int getCurrentBet();

    /**
     * @return the number of players still in the game. 
    */
    int getRemainingPlayers();

    /**
     * @return the hand's number. 
    */
    int getHandNumber();

    /**
     * @return the current {@link Phase}. 
    */
    Phase getHandPhase();

    /**
     * @return the community cards for the current hand. 
    */
    Set<Card> getCommunityCards();

    /**
     * Sets the current bet.
     * @param currentBet
    */
    void setCurrentBet(int currentBet);

    /**
     * Sets the number of players still in the game.
     * @param remainingPlayers
    */
    void setRemainingPlayers(int remainingPlayers);
}
