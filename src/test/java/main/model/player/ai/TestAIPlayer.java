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
public class TestAIPlayer {

    private static final int REPEAT_TESTS = 15;
    private static final int POT_2000 = 2000;
    private static final int NUM_OF_PLAYERS = 4;
    private static final int BET_1000 = 1000;
    private static final int STARTING_CHIPS = 10000;
    private static AIPlayerFactory factory;
    private static Deck<Card> deck;

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
    public void newDeck() {
        deck = new DeckFactoryImpl().simplePokerDeck();
    }

    /**
     * Test the creation of an AI player.
     */
    @Test
    public void testCreation() {
        var player = factory.easy(STARTING_CHIPS);
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
    public void testBettingRegular() {
        var player = factory.easy(STARTING_CHIPS);
        assertEquals(0, player.getTotalPhaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        var action = player.getAction(new StateImpl(BET_1000, NUM_OF_PLAYERS));
        System.out.println(action);
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
    public void testBettingWithBlind() {
        var player = factory.easy(STARTING_CHIPS);
        player.setRole(Role.SMALL_BLIND);
        assertEquals(0, player.getTotalPhaseBet());
        var deck = new DeckFactoryImpl().simplePokerDeck();
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        var state = new StateImpl(BET_1000, NUM_OF_PLAYERS);
        var action = player.getAction(state);
        System.out.println(action);
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
        System.out.println(action);
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
    public void testChecking() {
        var player = factory.hard(STARTING_CHIPS);
        assertEquals(0, player.getTotalPhaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        var state = new StateImpl(0, NUM_OF_PLAYERS);
        state.addToPot(POT_2000);
        var action = player.getAction(state);
        System.out.println(action);
        assertTrue(action == Action.CHECK || action == Action.RAISE);
    }

    /**
     * Test the AI player winning a hand.
     */
    @Test
    public void testWinning() {
        var player = factory.medium(STARTING_CHIPS);
        player.handWon(BET_1000);
        assertEquals(STARTING_CHIPS + BET_1000, player.getChips());
        assertEquals(Set.of(), player.getCards());
    }

    /**
     * Test the AI player losing a hand.
     */
    @Test
    public void testLosing() {
        var player = factory.hard(STARTING_CHIPS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        var state = new StateImpl(0, NUM_OF_PLAYERS);
        state.addToPot(POT_2000);
        var action = player.getAction(state);
        while (action != Action.RAISE) {
            action = player.getAction(state);
        }
        player.handLost();
        assertEquals(STARTING_CHIPS - player.getTotalPhaseBet(), player.getChips());
        assertEquals(Set.of(), player.getCards());
    }
}
