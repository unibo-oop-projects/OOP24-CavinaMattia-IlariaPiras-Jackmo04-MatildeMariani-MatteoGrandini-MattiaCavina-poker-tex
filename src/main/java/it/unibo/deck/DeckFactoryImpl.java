package it.unibo.deck;

import java.util.LinkedList;
import java.util.List;

import it.unibo.deck.TexasCard.SeedCard;
import it.unibo.deck.TexasCard.SimpleCard;

/**
 * Implementation of deck.
 */
public class DeckFactoryImpl<X> implements DeckFactory {

    @Override
    public Deck<Card> simplePokerDeck() {

        return new DeckGeneric<>() {

            @Override
            public void generateDeck() {
                final List<Card> deckNew = new LinkedList<>();
                for (final var elemSimple : SimpleCard.values()) {
                    for (final var elemSeed : SeedCard.values()) {
                        deckNew.add(new Card(elemSimple.name(), elemSimple.getValueOfCard(), elemSeed.name()));
                    }
                }
                setDeck(deckNew);
            }
        };
    }

}
