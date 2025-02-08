package model.deck.api;

/**
 * DeckFactory to generate varius type of deck.
 */
public interface DeckFactory {

    /**
     * Class to generate Standard Deck of Poker.
     * Merged feuture of {@link SimpleCard} and
     * {@link SeedCard} create all
     * type of poker's Card.
     */
    Deck<Card> simplePokerDeck();
}
