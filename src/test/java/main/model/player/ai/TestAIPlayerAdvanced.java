package main.model.player.ai;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayerFactory;

/**
 * More in depth testing class for AI players.
 */
class TestAIPlayerAdvanced {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestAIPlayerAdvanced.class);
    private static final int REPEAT_TESTS = 15;
    
    private static AIPlayerFactory factory;
    private Deck<Card> deck;

    /**
     * Set up the factory for the tests.
     */
    @BeforeAll
    public static void setUp() {
        factory = new AIPlayerFactoryImpl();
    }

    /**
     * Create a new deck for each test.
     */
    @BeforeEach
    void newDeck() {
        deck = new DeckFactoryImpl().simplePokerDeck();
    }
}
