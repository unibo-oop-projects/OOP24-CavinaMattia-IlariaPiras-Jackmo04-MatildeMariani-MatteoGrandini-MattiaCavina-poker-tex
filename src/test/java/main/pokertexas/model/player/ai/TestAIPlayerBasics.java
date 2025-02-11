package main.pokertexas.model.player.ai;

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

import pokertexas.model.deck.DeckFactoryImpl;
import pokertexas.model.deck.api.Card;
import pokertexas.model.deck.api.Deck;
import pokertexas.model.game.StateImpl;
import pokertexas.model.player.ai.AIPlayerFactoryImpl;
import pokertexas.model.player.ai.api.AIPlayerFactory;
import pokertexas.model.player.api.Action;
import pokertexas.model.player.api.Role;

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
        final var player = factory.createEasy(PLAYER_ID, STARTING_CHIPS);
        assertEquals(Optional.empty(), player.getRole());
        assertEquals(STARTING_CHIPS, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(0, player.getTotalPhaseBet());
        // Cannot get action without a game state
        assertThrows(IllegalStateException.class, player::getAction);
        player.setGameState(new StateImpl(BET_1000, NUM_OF_PLAYERS));
        // Cannot get action without cards
        assertThrows(IllegalStateException.class, player::getAction);
        assertTrue(player.isAI());
    }

    /**
     * Test betting for non-small-blind players.
     */
    @RepeatedTest(REPEAT_TESTS)
    void testBettingRegular() {
        final var player = factory.createEasy(PLAYER_ID, STARTING_CHIPS);
        assertEquals(0, player.getTotalPhaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.setGameState(new StateImpl(BET_1000, NUM_OF_PLAYERS));
        final var action = player.getAction();
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
        final var player = factory.createEasy(PLAYER_ID, STARTING_CHIPS);
        player.setRole(Role.SMALL_BLIND);
        assertEquals(0, player.getTotalPhaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        final var state = new StateImpl(BET_1000, NUM_OF_PLAYERS);
        player.setGameState(state);
        var action = player.getAction();
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
        action = player.getAction();
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
        final var player = factory.createHard(PLAYER_ID, STARTING_CHIPS);
        assertEquals(0, player.getTotalPhaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        final var state = new StateImpl(0, NUM_OF_PLAYERS);
        player.setGameState(state);
        state.addToPot(POT_2000);
        final var action = player.getAction();
        LOGGER.info(action.toString());
        assertTrue(action == Action.CHECK || action == Action.RAISE);
    }

    /**
     * Test the AI player winning a hand.
     */
    @Test
    void testWinning() {
        final var player = factory.createMedium(PLAYER_ID, STARTING_CHIPS);
        player.setRole(Role.BIG_BLIND);
        player.handWon(BET_1000);
        assertEquals(STARTING_CHIPS + BET_1000, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(0, player.getTotalPhaseBet());
        assertEquals(Optional.empty(), player.getRole());
    }

    /**
     * Test the AI player losing a hand.
     */
    @Test
    void testLosing() {
        final var player = factory.createHard(PLAYER_ID, STARTING_CHIPS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.setRole(Role.SMALL_BLIND);
        final var state = new StateImpl(BET_1000, NUM_OF_PLAYERS);
        player.setGameState(state);
        state.addToPot(POT_2000);
        player.getAction();
        final var bet = player.getTotalPhaseBet();
        assertEquals(BET_1000 / 2, bet);
        player.handLost();
        assertEquals(STARTING_CHIPS - bet, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(0, player.getTotalPhaseBet());
        assertEquals(Optional.empty(), player.getRole());
    }
}
