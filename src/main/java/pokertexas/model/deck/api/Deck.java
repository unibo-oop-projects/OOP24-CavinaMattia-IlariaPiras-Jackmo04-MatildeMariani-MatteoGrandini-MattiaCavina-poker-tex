package pokertexas.model.deck.api;

import java.util.List;

/**
 * Inteface to Generate and manage a single deck.
 * 
 * @param <X> Parameter to manage diffirent type of card , to reuse this
 *            inteface for more game.
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
     * @param numberOfCard Number of card that a need to keep from top of my deck.
     * @throws IllegalStateException Exception throws if there aren't many card in
     *                            deck.
     * @return {@link List} of card keeped from top fron deck.
     */
    List<X> getSomeCards(int numberOfCard);

}
