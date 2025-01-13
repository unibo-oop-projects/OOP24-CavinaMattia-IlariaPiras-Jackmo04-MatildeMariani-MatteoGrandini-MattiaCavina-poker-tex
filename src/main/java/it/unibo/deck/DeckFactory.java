package it.unibo.deck;

/**
 * DeckFactory to generate varius deck.
 */
public interface DeckFactory {

    /**
     * 
     * @return Deck<Card>.
     */
    public Deck<Card> simplePokerDeck();
    
}
