package model.deck;

/**
 * DeckFactory to generate varius deck.
 */
public interface DeckFactory {

    /**
     * 
     * @return a simple deck to play poker.
     */
    Deck<Card> simplePokerDeck();
}
