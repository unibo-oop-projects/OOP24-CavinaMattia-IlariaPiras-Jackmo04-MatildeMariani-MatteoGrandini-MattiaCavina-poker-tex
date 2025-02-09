package model.dealer.api;

import java.util.Set;

import model.deck.api.Card;
import model.deck.api.Deck;

/**
 * Interface that models a Dealer.
 * A Dealer has a {@link Deck} of {@link Card}s that it can deal to a {@link Player} 
 * or to the {@link Game}.
 */
public interface Dealer {

    /**
     * Returns a set of two {@link Card}s from the {@link Deck}.
     * @return a set of two cards from the Deck.
    */
    Set<Card> giveCardsToPlayer();

    /**
     * Returns a set consisting of a different number of {@link Card}s depending on the phase.
     * @param numCardPhase the number of cards to be dealt in a particular phase.
     * @return a set of numCardPhase cards.
    */
    Set<Card> giveCardsToTheGame(int numCardPhase);

    /**
    * It shuffles the deck. 
    */
    void shuffle();

}
