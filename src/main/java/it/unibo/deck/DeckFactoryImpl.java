package it.unibo.deck;

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
        return new DeckImpl<>(new PokerCardImpl()) {
        };
    }

}
