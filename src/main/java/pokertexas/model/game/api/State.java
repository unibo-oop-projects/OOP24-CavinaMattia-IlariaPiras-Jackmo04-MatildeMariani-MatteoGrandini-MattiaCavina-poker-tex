package pokertexas.model.game.api;

import java.util.Set;

import pokertexas.model.deck.api.Card;
import pokertexas.model.player.api.Player;

/**
 * Interface that models the State of a game.
 * A State keeps a certain amount of money given by the sum of the bets made by the players in each hand, 
 * the current minimun bet, a set of {@link Card}s common to all players and the current {@link Phase}.
 * It must be updated regulary.
 */
public interface State {

    /**
     * Adds a bet to the pot.
     * @param playerBet the amount of money a player betted.
    */
    void addToPot(int playerBet);

    /**
     * Adds cards to the set of community {@link Card}s for the {@link Hand}. 
     * @param cards the set of cards to add.
    */
    void addCommunityCards(Set<Card> cards);

    /**
     * Informs the State that a new {@link Hand} is starting. It sets all its component accordingly. 
     * @param initialBet the initial bet required to play in the hand.
    */
    void newHand(int initialBet);

    /**
     * Informs the State a new {@link Phase} is starting. It sets the current phase and the 
     * current bet accordingly.
    */
    void nextHandPhase();

    /**
     * Returns the pot, or the winnings of the {@link Hand}.
     * @return the pot. 
    */
    int getPot();

    /**
     * Returns the current minimum bet {@link Player}s must place if they want to continue playing.
     * @return the current bet. 
    */
    int getCurrentBet();

    /**
     * Returns the current {@link Phase}.
     * @return the current phase. 
    */
    Phase getHandPhase();

    /**
     * Returns the community cards for the current {@link Hand}.
     * @return the community cards for the current hand. 
    */
    Set<Card> getCommunityCards();

    /**
     * Sets the current minimum bet {@link Player}s must place if they want to continue playing.
     * @param currentBet the new minimum bet.
    */
    void setCurrentBet(int currentBet);

    /**
     * Sets the {@link Hand} {@link Phase}.
     * @param handPhase the current phase.
    */
    void setHandPhase(Phase handPhase);
}
