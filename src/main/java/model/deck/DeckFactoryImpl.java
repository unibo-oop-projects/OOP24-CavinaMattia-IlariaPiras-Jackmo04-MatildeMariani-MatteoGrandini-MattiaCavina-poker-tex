package model.deck;

import model.deck.api.Card;
import model.deck.api.Deck;
import model.deck.api.DeckFactory;

/**
 * Implementation of deck.
 * 
 */
public class DeckFactoryImpl implements DeckFactory {

    /**
     * @return simple poker's deck.
     */
    @Override
    public Deck<Card> simplePokerDeck() {
        return new DeckImpl<>(new DeckBuildImpl()) {
        };
    }

}
