package main.model.player.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.game.StateImpl;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Action;

/**
 * More in depth testing class for AI players.
 */
class TestAIPlayerAdvanced {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestAIPlayerAdvanced.class);
    private static final int REPEAT_TESTS = 15;
    private static final int STD_ID = 1;
    private static final int NUM_OF_PLAYERS = 4;
    private static final int CHIPS_800 = 800;
    private static final int CHIPS_10000 = 10_000;
    private static final int BET_1000 = 1000;
    private static final String CANNOT_CHECK = "Cannot check";
    private static final String UNKNOWN_ACTION = "Unknown action";

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
     * Test when the AI player doesn't have enough money to call.
     */
    @RepeatedTest(REPEAT_TESTS)
    void testAllIn() {
        LOGGER.info("Testing all in");
        final var player = factory.hard(STD_ID, CHIPS_800);
        player.setCards(deck.getSomeCards(2).stream().collect(Collectors.toSet()));
        final var state = new StateImpl(BET_1000, NUM_OF_PLAYERS);
        var action = Action.FOLD;
        while (action == Action.FOLD) {
            action = player.getAction(state);
            LOGGER.info("Action: " + action);
            switch (action) {
                case CHECK:
                    fail(CANNOT_CHECK);
                    break;
                case CALL:
                    fail("Cannot call, has to go all in");
                    break;
                case RAISE:
                    fail("Cannot raise, has to go all in");
                    break;
                case FOLD:
                case ALL_IN:
                    break;
                default:
                    fail(UNKNOWN_ACTION);
                    break;
            }
        }
        assertEquals(0, player.getChips());
        assertEquals(CHIPS_800, player.getTotalPhaseBet());
        player.handWon(BET_1000);
        assertEquals(BET_1000, player.getChips());
    }

    /**
     * Test an update in the current bet.
     */
    @RepeatedTest(REPEAT_TESTS)
    void testChangingBet() {
        LOGGER.info("Testing changing bet");
        final var player = factory.hard(STD_ID, CHIPS_10000);
        player.setCards(deck.getSomeCards(2).stream().collect(Collectors.toSet()));
        final var state = new StateImpl(BET_1000, NUM_OF_PLAYERS);
        var action = Action.FOLD;
        while (action == Action.FOLD) {
            action = player.getAction(state);
            LOGGER.info("Action: " + action);
            switch (action) {
                case CHECK:
                    fail(CANNOT_CHECK);
                    break;
                case CALL:
                case RAISE:
                    break;
                case FOLD:
                    assertEquals(0, player.getTotalPhaseBet());
                    break;
                case ALL_IN:
                    assertEquals(player.getChips(), player.getTotalPhaseBet());
                    break;
                default:
                    fail(UNKNOWN_ACTION);
                    break;
            }
        }
        final var bet1 = player.getTotalPhaseBet();
        LOGGER.info("Bet: " + bet1);
        assertTrue(bet1 >= BET_1000);
        state.setCurrentBet(bet1 + BET_1000);
        action = Action.FOLD;
        while (action == Action.FOLD) {
            action = player.getAction(state);
            LOGGER.info("Next action: " + action);
            switch (action) {
                case CHECK:
                    fail(CANNOT_CHECK);
                    break;
                case CALL:
                case RAISE:
                    break;
                case FOLD:
                    assertEquals(bet1, player.getTotalPhaseBet());
                    break;
                case ALL_IN:
                    assertEquals(player.getChips(), player.getTotalPhaseBet());
                    break;
                default:
                    fail(UNKNOWN_ACTION);
                    break;
            }
        }
        final var bet2 = player.getTotalPhaseBet();
        LOGGER.info("Next bet: " + bet2);
        assertTrue(bet2 >= bet1 + BET_1000);
    }

    /**
     * Test the AI player when the phase changes.
     */
    @RepeatedTest(REPEAT_TESTS)
    void testPhaseChange() {
        LOGGER.info("Testing changing phase");
        final var player = factory.hard(STD_ID, CHIPS_10000);
        player.setCards(deck.getSomeCards(2).stream().collect(Collectors.toSet()));
        final var state = new StateImpl(BET_1000, NUM_OF_PLAYERS);
        var action = Action.FOLD;
        while (action == Action.FOLD) {
            action = player.getAction(state);
            LOGGER.info("Action at PRE-FLOP: " + action);
            switch (action) {
                case CHECK:
                    fail(CANNOT_CHECK);
                    break;
                case CALL:
                case RAISE:
                    break;
                case FOLD:
                    assertEquals(0, player.getTotalPhaseBet());
                    break;
                case ALL_IN:
                    assertEquals(player.getChips(), player.getTotalPhaseBet());
                    break;
                default:
                    fail(UNKNOWN_ACTION);
                    break;
            }
        }
        final var bet1 = player.getTotalPhaseBet();
        LOGGER.info("Bet PRE-FLOP: " + bet1);
        state.nextHandPhase();
        player.nextPhase();
        state.setCurrentBet(BET_1000);
        action = Action.FOLD;
        while (action == Action.FOLD) {
            action = player.getAction(state);
            LOGGER.info("Action at FLOP: " + action);
            switch (action) {
                case CHECK:
                    fail(CANNOT_CHECK);
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
                case ALL_IN:
                    assertEquals(player.getChips(), player.getTotalPhaseBet());
                    break;
                default:
                    fail(UNKNOWN_ACTION);
                    break;
            }
        }
        final var bet2 = player.getTotalPhaseBet();
        LOGGER.info("Bet FLOP: " + bet2);
        assertTrue(bet2 >= BET_1000);
    }
}
