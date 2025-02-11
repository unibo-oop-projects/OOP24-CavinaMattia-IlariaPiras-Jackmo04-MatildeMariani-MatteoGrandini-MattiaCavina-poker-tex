package pokertexas.model.deck.api;

import java.util.List;

/**
 * Functional Interface to create generic deck.
 * Can be used passed like argumnet do generate {@link Deck},with
 * support of
 * {@link DeckFactory} can be create differte deck.
 * 
 * @param <X> Generic parapeter to reuse this interface for differt deck type.
 */
@FunctionalInterface
public interface DeckBuild<X> {

    /**
     * Method to define the costruction of Deck , usable in
     * {@link Deck#shuffled()} method of {@link Deck}
     * to shuffled and recreate deck.
     * 
     * @return List of card that form deck implemeted in this class.
     */
    List<X> buildDeck();
}
