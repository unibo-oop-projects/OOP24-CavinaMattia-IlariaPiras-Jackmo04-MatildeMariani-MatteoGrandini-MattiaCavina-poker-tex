package it.unibo.deck; // NOPMD suppressed as it is a false positive

import java.util.List;

/**
 * Inteface to build a deck.
 * @param <X>
 * 
 */
public interface PokerCard<X> {

    /**
     * 
     * @return Deck implemeted in yhis class.
     */
    List<X> buildDeck();

}
