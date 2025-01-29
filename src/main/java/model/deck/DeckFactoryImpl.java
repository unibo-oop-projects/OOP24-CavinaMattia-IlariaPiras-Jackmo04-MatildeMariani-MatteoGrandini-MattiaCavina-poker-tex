package model.deck;

import model.deck.api.Card;
import model.deck.api.Deck;
import model.deck.api.DeckFactory;

/**
 * Factory to generate deck.
 * 
 */
public class DeckFactoryImpl implements DeckFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Card> simplePokerDeck() {
        return new DeckImpl<>(new DeckBuildImpl()) {
        };
    }

}
