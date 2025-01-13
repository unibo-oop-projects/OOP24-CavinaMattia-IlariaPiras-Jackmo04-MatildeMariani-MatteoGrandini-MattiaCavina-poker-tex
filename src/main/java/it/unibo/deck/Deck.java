package it.unibo.deck;

import java.util.List;

/**
 * Inteface to deck.
 */
public interface Deck<X> {

    /**
     * abstract method to generate deck.
     */
    void generateDeck();

    /**
     * 
     * @param numberOfCard
     * @return List of card to assign.
     */
    List<X> getCards(int numberOfCard);


}