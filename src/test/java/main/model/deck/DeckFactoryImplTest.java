package main.model.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test della factory Deck and the class to generate new Poker's Deck.
 */
public class DeckFactoryImplTest {
    private final static int ALL_DECKS = 52;
    private final static int TWO_CARD = 2;

    /**
     * test of simple deck.
     */
    @Test

    protected void simpleDeck() {
        final Deck<Card> t = new DeckFactoryImpl().simplePokerDeck();
        assertEquals(TWO_CARD, t.getSomeCards(TWO_CARD).size());
        assertEquals(ALL_DECKS - TWO_CARD, t.getSomeCards(ALL_DECKS - TWO_CARD).size());
        try {
            t.getSomeCards(1);

        } catch (IllegalAccessError e) {
            System.out.println("Deck is Empty"); // NOPMD suppressed as it is a false positive
        }
        t.shuffled();
        assertEquals(52, t.getSomeCards(ALL_DECKS).size());

        t.shuffled();
        assertEquals(true, t.getSomeCards(ALL_DECKS)
                .contains(new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES)));

    }

}
