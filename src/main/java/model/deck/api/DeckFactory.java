package model.deck.api;

/**
 * DeckFactory to generate varius deck.
 */
public interface DeckFactory {

    /**
     * 
     * @return
     *         a simple deck to play poker.
     */
    Deck<Card> simplePokerDeck();
}
