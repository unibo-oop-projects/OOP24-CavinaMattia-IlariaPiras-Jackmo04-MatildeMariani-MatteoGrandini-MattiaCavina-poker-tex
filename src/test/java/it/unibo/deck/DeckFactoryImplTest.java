package it.unibo.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test della factory.
 */
public class DeckFactoryImplTest {

    @Test
    // CHECKSTYLE: MagicNumber OFF
    protected void simpleDeck() {
        // orario delle lezioni "vuoto", diciamo durante il periodo gli esami...
        final Deck<Card> t = new DeckFactoryImpl().simplePokerDeck();
        assertEquals(2, t.getSomeCards(2).size());
        assertEquals(50, t.getSomeCards(50).size()); 
        try {
            t.getSomeCards(1);

        } catch (IllegalAccessError e) {
            System.out.println("Deck is Empty"); 
        }
        t.shuffled();
        assertEquals(52, t.getSomeCards(52).size()); 

    }
    // CHECKSTYLE: MagicNumber ON

}
