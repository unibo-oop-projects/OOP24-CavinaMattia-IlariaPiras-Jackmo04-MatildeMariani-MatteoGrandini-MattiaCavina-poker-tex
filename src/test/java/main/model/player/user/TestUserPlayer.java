package main.model.player.user;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.game.StateImpl;
import model.player.api.Action;
import model.player.api.Role;
import model.player.user.UserPlayer;

/**
 * Tests for the UserPlayer implementation.
 */
class TestUserPlayer {
    private static final int PLAYER_ID = 1;
    private static final int INITIAL_TOTAL_PHASE_BET = 0;
    private static final int INITIAL_CHIPS = 2000;
    private static final int NUM_OF_PLAYERS = 4;
    private static final int INITIAL_BET = 1000;
    private static final int INITIAL_BET_500 = 500;
    private static final double MULTIPLIER_SMALL_BLIND = 0.5;
    private static final int MULTIPLIER_RAISE = 3;
    private static final int BET_3000 = 3000;

    private UserPlayer player;
    private static Deck<Card> deck;

    @BeforeEach
    public void initializeUserPlayer() {
        player = new UserPlayer(PLAYER_ID, INITIAL_CHIPS);
    }

    @BeforeAll
    public static void setUp() {
        deck = new DeckFactoryImpl().simplePokerDeck();
    }

    @Test
    void testCreation() {
        assertTrue(player.getRole().isEmpty());
        assertEquals(INITIAL_CHIPS, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(INITIAL_TOTAL_PHASE_BET, player.getTotalPhaseBet());
        player.setGameState(new StateImpl(BET_3000, NUM_OF_PLAYERS));
        assertThrows(IllegalStateException.class, 
            player::getAction);
        assertFalse(player.isAI());
    }

    @Test
    void testCheck() throws InterruptedException {
        final var state = new StateImpl(INITIAL_BET, NUM_OF_PLAYERS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.setGameState(state);
        player.getController().receiveUserAction("CALL");
        assertEquals(Action.CALL, player.getAction());
        state.setCurrentBet(INITIAL_BET);
        assertEquals(INITIAL_CHIPS - INITIAL_BET, player.getChips());
        assertEquals(INITIAL_BET, player.getTotalPhaseBet());
        player.getController().receiveUserAction("CHECK");
        assertEquals(Action.CHECK, player.getAction());
        assertEquals(INITIAL_CHIPS - INITIAL_BET, player.getChips());
        assertEquals(INITIAL_BET, player.getTotalPhaseBet()); 
    }

    @Test
    void testPreFlopSmallBlind() {
        player.setRole(Role.SMALL_BLIND);
        final var state = new StateImpl(INITIAL_BET, NUM_OF_PLAYERS);
        player.setGameState(state);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        assertEquals(Action.CALL, player.getAction());
        assertEquals(INITIAL_CHIPS - (INITIAL_BET * MULTIPLIER_SMALL_BLIND), player.getChips());
        assertEquals(INITIAL_BET * MULTIPLIER_SMALL_BLIND, player.getTotalPhaseBet());
    }

    @Test
    void testTextField() throws InterruptedException {
        final var state = new StateImpl(INITIAL_BET_500, NUM_OF_PLAYERS);
        player.setGameState(state);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.getController().setRaiseAmount(INITIAL_BET_500 * MULTIPLIER_RAISE);
        player.getController().receiveUserAction("RAISE");
        assertEquals(Action.RAISE, player.getAction());
        assertEquals(INITIAL_CHIPS - (INITIAL_BET_500 * MULTIPLIER_RAISE), player.getChips());
        assertEquals(INITIAL_BET_500 * MULTIPLIER_RAISE, player.getTotalPhaseBet());
        state.setCurrentBet(INITIAL_BET_500 * MULTIPLIER_RAISE);
        player.getController().receiveUserAction("FOLD");
        assertEquals(Action.FOLD, player.getAction());
        assertEquals(INITIAL_CHIPS - (INITIAL_BET_500 * MULTIPLIER_RAISE), player.getChips());
        assertEquals(INITIAL_BET_500 * MULTIPLIER_RAISE, player.getTotalPhaseBet());
    }

    @Test
    void testAllIn() {
        final var state = new StateImpl(INITIAL_BET, NUM_OF_PLAYERS);
        player.setGameState(state);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.getController().receiveUserAction("ALL_IN");
        assertEquals(Action.ALL_IN, player.getAction());
        assertEquals(0, player.getChips());
        assertEquals(INITIAL_CHIPS, player.getTotalPhaseBet());
    }

    @Test
    void testWinning() {
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.handWon(BET_3000);
        assertEquals(INITIAL_CHIPS + BET_3000, player.getChips());
        assertEquals(Set.of(), player.getCards());
    }

    @Test
    void testLosing() {
        final var state = new StateImpl(INITIAL_BET_500, NUM_OF_PLAYERS);
        player.setGameState(state);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.getController().setRaiseAmount(INITIAL_BET_500 * MULTIPLIER_RAISE);
        player.getController().receiveUserAction("RAISE");
        assertEquals(Action.RAISE, player.getAction());
        assertEquals(INITIAL_CHIPS - (INITIAL_BET_500 * MULTIPLIER_RAISE), player.getChips());
        assertEquals(INITIAL_BET_500 * MULTIPLIER_RAISE, player.getTotalPhaseBet());
        player.handLost();
        assertEquals(INITIAL_CHIPS - (INITIAL_BET_500 * MULTIPLIER_RAISE), player.getChips());
        assertEquals(Set.of(), player.getCards());
    }
}
