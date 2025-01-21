package deck;  // NOPMD suppressed as it is a false positive

import java.util.List;

/**
 * Inteface to deck.
 * @param <X>
 * 
 */
public interface Deck<X> {

    /**
     * abstract method to generate deck.
     */
    void shuffled();

    /**
     * 
     * @param numberOfCard
     * @return List of card to assign.
     */
    List<X> getSomeCards(int numberOfCard);


}
