package main.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.dealer.DealerImpl;
import model.game.api.Phase;
import model.game.StateImpl;
import model.game.api.State;

/**
 * Class that implements basic tests for the State.
 */
final class TestState {

    private static final int PLAYER_BET = 500;
    private static final int CURRENT_BET = 400;
    private static final int POT = 250;
    private static final int INITIAL_POT = 0;
    private static final int INITIAL_HAND_NUMBER = 0;
    private static final int INITIAL_PLAYERS = 4;
    private static final int INITIAL_CHIPS = 2000;

    private static State gameState;

    /**
     * Initialize gameState before all the tests.
     */
    @BeforeAll
    public static void setUp() {
        gameState = new StateImpl(INITIAL_CHIPS, INITIAL_PLAYERS);
    }

    @Test
    void testCreation() {
        assertEquals(INITIAL_CHIPS, gameState.getCurrentBet());
        assertEquals(INITIAL_PLAYERS, gameState.getRemainingPlayers());
        assertEquals(INITIAL_HAND_NUMBER, gameState.getHandNumber());
        assertEquals(INITIAL_POT, gameState.getPot());
        assertEquals(Phase.PREFLOP, gameState.getHandPhase());
        assertTrue(gameState.getCommunityCards().isEmpty());
    }

    @Test
    void testUpdating() {
        final var dealer = new DealerImpl();
        gameState.addToPot(POT);
        gameState.setCurrentBet(CURRENT_BET);
        gameState.setRemainingPlayers(INITIAL_PLAYERS - 1);
        gameState.addCommunityCards(dealer.giveCardsToTheGame(gameState.getHandPhase().getNumCards()));
        assertTrue(gameState.getCommunityCards().isEmpty());
        assertEquals(POT, gameState.getPot());
        assertEquals(CURRENT_BET, gameState.getCurrentBet());
        assertEquals(INITIAL_PLAYERS - 1, gameState.getRemainingPlayers());

        gameState.addToPot(PLAYER_BET);
        assertEquals(POT + PLAYER_BET, gameState.getPot());

        gameState.nextHandPhase();
        gameState.addCommunityCards(dealer.giveCardsToTheGame(gameState.getHandPhase().getNumCards()));
        assertEquals(Phase.FLOP, gameState.getHandPhase());
        assertEquals(Phase.FLOP.getNumCards(), gameState.getCommunityCards().size());

        gameState.nextHandPhase();
        gameState.addCommunityCards(dealer.giveCardsToTheGame(gameState.getHandPhase().getNumCards()));
        assertEquals(Phase.TURN, gameState.getHandPhase());
        assertEquals(Phase.TURN.getNumCards() + Phase.FLOP.getNumCards(), gameState.getCommunityCards().size());

        gameState.newHand(INITIAL_CHIPS, INITIAL_PLAYERS);
        assertEquals(INITIAL_CHIPS, gameState.getCurrentBet());
        assertEquals(INITIAL_PLAYERS, gameState.getRemainingPlayers());
        assertEquals(INITIAL_HAND_NUMBER + 1, gameState.getHandNumber());
        assertEquals(INITIAL_POT, gameState.getPot());
        assertEquals(Phase.PREFLOP, gameState.getHandPhase());
        assertTrue(gameState.getCommunityCards().isEmpty());
    }

}
