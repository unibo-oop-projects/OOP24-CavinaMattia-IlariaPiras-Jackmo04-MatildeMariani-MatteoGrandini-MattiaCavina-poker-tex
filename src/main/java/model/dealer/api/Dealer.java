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
    * @return a set of two cards from the Deck.
    */
    public Set<Card> giveCardsToPlayer();

    /**
    * @param numCardPhase the number of cards to be dealt in a particular phase.
    * @return a set consisting of a different number of cards depending on the phase.
    */
    public Set<Card> giveCardsToTheGame(int numCardPhase);

    /**
    * It shuffles the deck. 
    */
    public void shuffle();

}
