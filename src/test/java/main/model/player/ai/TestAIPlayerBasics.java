package main.model.player.ai;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.game.StateImpl;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Action;
import model.player.api.Role;

/**
 * Tests for the AIPlayer implementation.
 */
class TestAIPlayerBasics {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestAIPlayerBasics.class);
    private static final int PLAYER_ID = 1;
    private static final int REPEAT_TESTS = 15;
    private static final int POT_2000 = 2000;
    private static final int NUM_OF_PLAYERS = 4;
    private static final int BET_1000 = 1000;
    private static final int STARTING_CHIPS = 10_000;
    
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

    /**
     * Test the creation of an AI player.
     */
    @Test
    void testCreation() {
        final var player = factory.easy(PLAYER_ID, STARTING_CHIPS);
        assertEquals(Optional.empty(), player.getRole());
        assertEquals(STARTING_CHIPS, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(0, player.getTotalPhaseBet());
        assertThrows(IllegalStateException.class, 
            () -> player.getAction(new StateImpl(BET_1000, NUM_OF_PLAYERS)));
        assertTrue(player.isAI());
    }

    /**
     * Test betting for non-small-blind players.
     */
    @RepeatedTest(REPEAT_TESTS)
    void testBettingRegular() {
        final var player = factory.easy(PLAYER_ID, STARTING_CHIPS);
        assertEquals(0, player.getTotalPhaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        final var action = player.getAction(new StateImpl(BET_1000, NUM_OF_PLAYERS));
        switch (action) {
            case CHECK:
                fail("Cannot check with a bet of 1000");
                break;
            case CALL:
                assertEquals(BET_1000, player.getTotalPhaseBet());
                break;
            case RAISE:
                assertTrue(player.getTotalPhaseBet() > BET_1000);
                break;
            case FOLD:
                assertEquals(0, player.getTotalPhaseBet());
                break;
            default:
                fail("Invalid action");
                break;
        }
        assertEquals(STARTING_CHIPS - player.getTotalPhaseBet(), player.getChips());
    }

    /**
     * Test betting for small-blind players.
     */
    @RepeatedTest(REPEAT_TESTS)
    void testBettingWithBlind() {
        final var player = factory.easy(PLAYER_ID, STARTING_CHIPS);
        player.setRole(Role.SMALL_BLIND);
        assertEquals(0, player.getTotalPhaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        final var state = new StateImpl(BET_1000, NUM_OF_PLAYERS);
        var action = player.getAction(state);
        LOGGER.info(action.toString());
        switch (action) {
            case CALL:
                assertEquals(BET_1000 / 2, player.getTotalPhaseBet());
                break;
            case RAISE:
            case CHECK:
            case FOLD:
                fail("Has to call with the small blind");
                break;
            default:
                fail("Invalid action");
                break;
        }
        assertEquals(STARTING_CHIPS - player.getTotalPhaseBet(), player.getChips());
        action = player.getAction(state);
        LOGGER.info(action.toString());
        switch (action) {
            case RAISE:
                assertTrue(player.getTotalPhaseBet() > BET_1000);
                break;
            case CALL:
                assertEquals(BET_1000, player.getTotalPhaseBet());
                break;
            case CHECK:
                fail("Cannot check with a bet of 1000");
                break;
            case FOLD:
                assertEquals(BET_1000 / 2, player.getTotalPhaseBet());
                break;
            default:
                fail("Invalid action");
                break;
        }
    }

    /**
     * Test checking for AI players.
     */
    @RepeatedTest(REPEAT_TESTS)
    void testChecking() {
        final var player = factory.hard(PLAYER_ID, STARTING_CHIPS);
        assertEquals(0, player.getTotalPhaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        final var state = new StateImpl(0, NUM_OF_PLAYERS);
        state.addToPot(POT_2000);
        final var action = player.getAction(state);
        LOGGER.info(action.toString());
        assertTrue(action == Action.CHECK || action == Action.RAISE);
    }

    /**
     * Test the AI player winning a hand.
     */
    @Test
    void testWinning() {
        final var player = factory.medium(PLAYER_ID, STARTING_CHIPS);
        player.handWon(BET_1000);
        assertEquals(STARTING_CHIPS + BET_1000, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(0, player.getTotalPhaseBet());
    }

    /**
     * Test the AI player losing a hand.
     */
    @Test
    void testLosing() {
        final var player = factory.hard(PLAYER_ID, STARTING_CHIPS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        final var state = new StateImpl(0, NUM_OF_PLAYERS);
        state.addToPot(POT_2000);
        var action = player.getAction(state);
        while (action != Action.RAISE) {
            action = player.getAction(state);
        }
        final var bet = player.getTotalPhaseBet();
        player.handLost();
        assertEquals(STARTING_CHIPS - bet, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(0, player.getTotalPhaseBet());
    }
}
