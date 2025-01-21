package model.deck.api; // NOPMD suppressed as it is a false positive

import java.util.List;

/**
 * Inteface to Generate and manage a single deck.
 * 
 * @param <X>
 * 
 */
public interface Deck<X> {

    /**
     * Method to shuffled a deck , can used after a single hand to rebuild and
     * shuffle deck.
     */
    void shuffled();

    /**
     * Method to keep some card from deck , numer of card is arbytrary.
     * 
     * @param numberOfCard
     * @throws IllegalAccessError
     * @return List of card to assign.
     */
    List<X> getSomeCards(int numberOfCard);

}
