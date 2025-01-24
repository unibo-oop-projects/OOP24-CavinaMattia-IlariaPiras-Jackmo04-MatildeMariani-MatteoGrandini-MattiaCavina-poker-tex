package model.dealer.api;

import java.util.Set;

import model.deck.api.Card;
import model.deck.api.Deck;
import model.game.api.Phase;

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
    * @param phase
    * @return a set consisting of a different number of cards depending on the phase.
    */
    public Set<Card> giveCardsToTheGame(Phase phase);

    /**
    * It shuffles the deck. 
    */
    public void shuffle();

}
