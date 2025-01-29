package model.deck.api;

import java.util.List;

/**
 * Interface to create generic deck.
 * @param <X>
 */
public interface DeckBuild<X> {

    /**
     * Method to define the costruction of Deck , usable in costructors of Deck to shuffled
     * method.
     * 
     * @return Deck implemeted in this class.
     */
    List<X> buildDeck();
}
