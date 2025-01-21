package main.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import deck.Card;
import deck.Deck;
import deck.DeckFactoryImpl;

/**
 * Test della factory.
 */
public class DeckFactoryImplTest { // NOPMD suppressed as it is a false positive

    /**
     * test of simple deck.
     */
    @Test
    // CHECKSTYLE: MagicNumber OFF
    protected void simpleDeck() { // NOPMD suppressed as it is a false positive
        final Deck<Card> t = new DeckFactoryImpl().simplePokerDeck();
        assertEquals(2, t.getSomeCards(2).size());
        assertEquals(50, t.getSomeCards(50).size()); 
        try {
            t.getSomeCards(1);

        } catch (IllegalAccessError e) {
            System.out.println("Deck is Empty"); // NOPMD suppressed as it is a false positive
        }
        t.shuffled();
        assertEquals(52, t.getSomeCards(52).size()); 

    }
    // CHECKSTYLE: MagicNumber ON

}
