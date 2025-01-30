package main.model.player.user;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.game.StateImpl;
import model.player.api.Action;
import model.player.api.Role;
import model.player.user.UserPlayer;
import model.player.api.Player;

public class TestUserPlayer {
    private static final int INITIAL_TOTAL_PHASE_BET = 0;
    private static final int INITIAL_CHIPS = 2000;
    private static final int NUM_OF_PLAYERS = 4;
    private static final int INITIAL_BET = 1000;
    private static final int INITIAL_BET_500 = 500;
    private static final double MULTIPLIER_SMALL_BLIND = 0.5;
    private static final int MULTIPLIER_RAISE = 3;
    //private static final int POT = 2000;
    //private static final int BET_3000 = 3000;
    private Player player;
    private static Deck<Card> deck;

    @BeforeEach
    public void newDeck() {
        deck = new DeckFactoryImpl().simplePokerDeck();
    }

    @Test
    public void testCreation() {
        this.player = new UserPlayer(INITIAL_CHIPS);
        assertTrue(this.player.getRole().isEmpty());
        assertEquals(INITIAL_CHIPS, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(INITIAL_TOTAL_PHASE_BET, player.getTotalPhaseBet());
        assertThrows(IllegalStateException.class, 
            () -> player.getAction(new StateImpl(INITIAL_BET, NUM_OF_PLAYERS)));
        assertFalse(player.isAI());
    }

    @Test
    public void testCheck() {
        this.player = new UserPlayer(INITIAL_CHIPS);
        var state = new StateImpl(INITIAL_BET, NUM_OF_PLAYERS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.getController().setCurrentState(state);
        player.getController().receiveUserAction("CALL");
        assertEquals(Action.CALL, player.getController().getUserAction());
        player.getAction(state);
        assertEquals(INITIAL_CHIPS - INITIAL_BET, player.getChips());
        assertEquals(INITIAL_BET, player.getTotalPhaseBet());
        player.getController().receiveUserAction("CHECK");
        assertEquals(Action.CHECK, player.getController().getUserAction());
        assertEquals(INITIAL_CHIPS - INITIAL_BET, player.getChips());
        assertEquals(INITIAL_BET, player.getTotalPhaseBet());
        
    }

    @Test
    public void testPreFlopSmallBlind() {
        this.player = new UserPlayer(INITIAL_CHIPS);
        player.setRole(Role.SMALL_BLIND);
        var state = new StateImpl(INITIAL_BET, NUM_OF_PLAYERS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.getController().setCurrentState(state);
        assertEquals(Action.CALL, player.getAction(state));
        assertEquals(INITIAL_CHIPS - (INITIAL_BET * MULTIPLIER_SMALL_BLIND), player.getChips());
        assertEquals(INITIAL_BET * MULTIPLIER_SMALL_BLIND, player.getTotalPhaseBet());
    }

    @Test
    public void testTextField() {
        this.player = new UserPlayer(INITIAL_CHIPS);
        var state = new StateImpl(INITIAL_BET_500, NUM_OF_PLAYERS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.getController().setCurrentState(state);
        player.getController().receiveUserAction("RAISE");
        player.getController().setRaiseAmount(INITIAL_BET_500 * MULTIPLIER_RAISE);
        assertEquals(Action.RAISE, player.getController().getUserAction());
        player.getAction(state);
        assertEquals(INITIAL_CHIPS - (INITIAL_BET_500 * MULTIPLIER_RAISE), player.getChips());
        assertEquals(INITIAL_BET_500 * MULTIPLIER_RAISE, player.getTotalPhaseBet());

    }

    @Test
    public void testAllIn() {
        this.player = new UserPlayer(INITIAL_CHIPS);
        var state = new StateImpl(INITIAL_BET, NUM_OF_PLAYERS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.getController().setCurrentState(state);
        player.getController().receiveUserAction("ALL_IN");
        player.getAction(state);
        assertEquals(0, player.getChips());
        assertEquals(INITIAL_CHIPS, player.getTotalPhaseBet());

    }

    /*@Test
    public void testWinning() {
        this.player = new UserPlayer(INITIAL_CHIPS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        player.handWon(BET_3000);
        assertEquals(INITIAL_CHIPS + BET_3000, player.getChips());
        assertEquals(Set.of(), player.getCards());
    }

    @Test
    public void testLosing() {
        this.player = new UserPlayer(INITIAL_CHIPS);
        var state = new StateImpl(INITIAL_BET_500, NUM_OF_PLAYERS);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        state.addToPot(POT);
        player.getController().setCurrentState(state);
        player.getController().receiveUserAction("RAISE");
        player.getController().setRaiseAmount(INITIAL_BET_500 * MULTIPLIER_RAISE);
        player.getAction(state);
        player.handLost();
        assertEquals(INITIAL_CHIPS - player.getTotalPhaseBet(), player.getChips());
        assertEquals(Set.of(), player.getCards());
    }*/
}