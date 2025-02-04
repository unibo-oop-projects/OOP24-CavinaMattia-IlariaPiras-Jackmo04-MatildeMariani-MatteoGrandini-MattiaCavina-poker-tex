package model.deck.api;

import java.util.List;

/**
 * Functional Interface to create generic deck.
 * Can be used passed like argumnet do generate {@link model.deck.api.Deck},with
 * support of
 * {@link model.deck.api.DeckFactory} can be create differte deck.
 * 
 * @param <X>
 *            Generic parapeter to reuse this interface for differt deck type.
 */
@FunctionalInterface
public interface DeckBuild<X> {

    /**
     * Method to define the costruction of Deck , usable in
     * {@link model.deck.api.Deck#shuffled()} method of {@link model.deck.api.Deck}
     * to shuffled and recreate deck.
     * 
     * @return
     *         List of card that form deck implemeted in this class.
     */
    List<X> buildDeck();
}
