package main.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.game.api.Phase;
import model.dealer.DealerImpl;
import model.dealer.api.Dealer;
import model.game.StateImpl;
import model.game.api.State;

public class TestState {

    private static final int INITIAL_POT = 0;
    private static final int INITIAL_HAND_NUMBER = 0;
    private static final int INITIAL_PLAYERS = 4;
    private static int INITIAL_CHIPS = 2000;

    private static State gameState;
    private static Dealer dealer;

    @BeforeAll
    public static void setUp() {
        gameState = new StateImpl(INITIAL_CHIPS, INITIAL_PLAYERS);
    }

    @Test
    public void testCreation() {
        assertEquals(INITIAL_CHIPS, gameState.getCurrentBet());
        assertEquals(INITIAL_PLAYERS, gameState.getRemainingPlayers());
        assertEquals(INITIAL_HAND_NUMBER, gameState.getHandNumber());
        assertEquals(INITIAL_POT, gameState.getPot());
        assertEquals(Phase.PREFLOP, gameState.getHandPhase());
        assertTrue(gameState.getCommunityCards().isEmpty());
    }
    
    @Test
    public void testUpdating() {
        dealer = new DealerImpl();
        gameState.addToPot(250);
        gameState.setCurrentBet(400);
        gameState.setRemainingPlayers(INITIAL_PLAYERS - 1);
        gameState.addCommunityCards(dealer.giveCardsToTheGame(gameState.getHandPhase().getNumCards()));
        assertTrue(gameState.getCommunityCards().isEmpty());
        assertEquals(250, gameState.getPot());
        assertEquals(400 , gameState.getCurrentBet());
        assertEquals(INITIAL_PLAYERS - 1, gameState.getRemainingPlayers());

        gameState.addToPot(500);
        assertEquals(250 + 500, gameState.getPot());

        gameState.nextHandPhase();
        gameState.addCommunityCards(dealer.giveCardsToTheGame(gameState.getHandPhase().getNumCards()));
        assertEquals(Phase.FLOP, gameState.getHandPhase());
        assertEquals(Phase.FLOP.getNumCards(), gameState.getCommunityCards().size());

        gameState.nextHandPhase();
        gameState.addCommunityCards(dealer.giveCardsToTheGame(gameState.getHandPhase().getNumCards()));
        assertEquals(Phase.TURN, gameState.getHandPhase());
        assertEquals((Phase.TURN.getNumCards() + Phase.FLOP.getNumCards()), gameState.getCommunityCards().size());

        gameState.newHand(INITIAL_CHIPS, INITIAL_PLAYERS);
        assertEquals(INITIAL_CHIPS, gameState.getCurrentBet());
        assertEquals(INITIAL_PLAYERS, gameState.getRemainingPlayers());
        assertEquals(INITIAL_HAND_NUMBER + 1, gameState.getHandNumber());
        assertEquals(INITIAL_POT, gameState.getPot());
        assertEquals(Phase.PREFLOP, gameState.getHandPhase());
        assertTrue(gameState.getCommunityCards().isEmpty());
    }

}
