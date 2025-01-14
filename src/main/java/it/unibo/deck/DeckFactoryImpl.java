package it.unibo.deck;

import java.util.LinkedList;
import java.util.List;
import it.unibo.deck.TexasCard.SeedCard;
import it.unibo.deck.TexasCard.SimpleCard;

/**
 * Implementation of deck.
 * 
 */
public class DeckFactoryImpl implements DeckFactory {

    /**
     * @return simplet poker deck.
     */
    @Override
    public Deck<Card> simplePokerDeck() {
        Deck<Card> deck = new DeckGeneric<>() {

            @Override
            public void shuffled() {
                final List<Card> deckNew = new LinkedList<>();
                for (final var elemSimple : SimpleCard.values()) {
                    for (final var elemSeed : SeedCard.values()) {
                        deckNew.add(new Card(elemSimple.name(), elemSimple.getValueOfCard(), elemSeed.name()));
                    }
                }
                setDeck(deckNew);
            }
        };
        deck.shuffled();
        return deck;
    }

}
