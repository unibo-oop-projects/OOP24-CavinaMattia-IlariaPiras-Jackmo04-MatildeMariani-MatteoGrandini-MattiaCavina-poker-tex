package main.model.player.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Action;
import model.player.api.Role;
import model.temp.HandFase;
import model.temp.State;

public class TestAIPlayer {

    private static final int POT_2000 = 2000;
    private static final int NUM_OF_PLAYERS = 4;
    private static final int BET_1000 = 1000;
    private static final int STARTING_CHIPS = 10000;
    private static AIPlayerFactory factory;
    private static Deck<Card> deck;

    @BeforeAll
    public static void setUp() {
        factory = new AIPlayerFactoryImpl();
    }

    @BeforeEach
    public void newDeck() {
        deck = new DeckFactoryImpl().simplePokerDeck();
    }

    @Test
    public void testCreation() {
        var player = factory.easy(STARTING_CHIPS, Role.REGULAR);
        assertEquals(Role.REGULAR, player.getRole());
        assertEquals(STARTING_CHIPS, player.getChips());
        assertEquals(Set.of(), player.getCards());
        assertEquals(0, player.getTotalFaseBet());
        assertThrows(IllegalStateException.class, 
            () -> player.getAction(new State(0, BET_1000, NUM_OF_PLAYERS, Set.of(), HandFase.PREFLOP)));
        assertTrue(player.isAI());
    }

    @Test
    public void testBettingRegular() {
        var player = factory.easy(STARTING_CHIPS, Role.REGULAR);
        assertEquals(0, player.getTotalFaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        var action = player.getAction(new State(0, BET_1000, NUM_OF_PLAYERS, Set.of(), HandFase.PREFLOP));
        System.out.println(action);
        switch (action) {
            case CHECK:
                fail("Cannot check with a bet of 1000");
                break;
            case CALL:
                assertEquals(BET_1000, player.getTotalFaseBet());
                break;
            case RAISE:
                assertTrue(player.getTotalFaseBet() > BET_1000);
                break;
            case FOLD:
                assertEquals(0, player.getTotalFaseBet());
                break;
            default:
                fail("Invalid action");
                break;
        }
        assertEquals(STARTING_CHIPS - player.getTotalFaseBet(), player.getChips());
    }

    @Test
    public void testBettingWithBlind() {
        var player = factory.easy(STARTING_CHIPS, Role.SMALL_BLIND);
        assertEquals(0, player.getTotalFaseBet());
        var deck = new DeckFactoryImpl().simplePokerDeck();
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        var action = player.getAction(new State(0, BET_1000, NUM_OF_PLAYERS, Set.of(), HandFase.PREFLOP));
        System.out.println(action);
        switch (action) {
            case CHECK:
                fail("Cannot check with a bet of 1000");
                break;
            case CALL:
                assertEquals(BET_1000, player.getTotalFaseBet());
                break;
            case RAISE:
                assertTrue(player.getTotalFaseBet() > BET_1000 / 2);
                break;
            case FOLD:
                assertEquals(BET_1000 / 2, player.getTotalFaseBet());
                break;
            default:
                fail("Invalid action");
                break;
        }
        assertEquals(STARTING_CHIPS - player.getTotalFaseBet(), player.getChips());
    }

    @Test
    public void testChecking() {
        var player = factory.hard(STARTING_CHIPS, Role.REGULAR);
        assertEquals(0, player.getTotalFaseBet());
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        var action = player.getAction(new State(POT_2000, 0, NUM_OF_PLAYERS, Set.of(), HandFase.PREFLOP));
        System.out.println(action);
        assertTrue(action == Action.CHECK || action == Action.RAISE);
    }

    @Test
    public void testWinning() {
        var player = factory.medium(STARTING_CHIPS, Role.REGULAR);
        player.handWon(BET_1000);
        assertEquals(STARTING_CHIPS + BET_1000, player.getChips());
        assertEquals(Set.of(), player.getCards());
    }

    @Test
    public void testLosing() {
        var player = factory.hard(STARTING_CHIPS, Role.REGULAR);
        player.setCards(new HashSet<>(deck.getSomeCards(2)));
        var action = player.getAction(new State(POT_2000, 0, NUM_OF_PLAYERS, Set.of(), HandFase.PREFLOP));
        while (action != Action.RAISE) {
            action = player.getAction(new State(POT_2000, 0, NUM_OF_PLAYERS, Set.of(), HandFase.PREFLOP));
        }
        player.handLost();
        assertEquals(STARTING_CHIPS - player.getTotalFaseBet(), player.getChips());
        assertEquals(Set.of(), player.getCards());
    }
}
