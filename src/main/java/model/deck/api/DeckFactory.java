package model.deck.api;

/**
 * DeckFactory to generate varius deck.
 */
public interface DeckFactory {

    /**
     * Class to generate Standard Deck of Poker.
     * Merged feuture of {@link model.deck.api.SimpleCard} and
     * {@link model.deck.api.SeedCard} create all
     * type of poker's Card.
     */
    Deck<Card> simplePokerDeck();
}
