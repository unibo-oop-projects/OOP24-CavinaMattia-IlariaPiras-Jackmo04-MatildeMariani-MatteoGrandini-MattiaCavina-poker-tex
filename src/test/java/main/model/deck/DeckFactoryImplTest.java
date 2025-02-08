package main.model.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test di {@link DeckFactoryImpl} and the class to generate and manage Deck
 * like {@link Deck} and {@link Card}.
 */
class DeckFactoryImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeckFactoryImplTest.class);
    private static final int ALL_DECKS = 52;
    private static final int TWO_CARD = 2;

    /**
     * Test of simple deck.
     */
    @Test
    void simpleDeck() {
        final Deck<Card> t = new DeckFactoryImpl().simplePokerDeck();
        assertEquals(TWO_CARD, t.getSomeCards(TWO_CARD).size());
        assertEquals(ALL_DECKS - TWO_CARD, t.getSomeCards(ALL_DECKS - TWO_CARD).size());
        try {
            t.getSomeCards(1);

        } catch (IllegalStateException e) {
            LOGGER.error("Deck is empty");
        }
        t.shuffled();
        assertEquals(ALL_DECKS, t.getSomeCards(ALL_DECKS).size());

        t.shuffled();
        assertTrue(t.getSomeCards(ALL_DECKS)
                .contains(new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES)));

    }

}
