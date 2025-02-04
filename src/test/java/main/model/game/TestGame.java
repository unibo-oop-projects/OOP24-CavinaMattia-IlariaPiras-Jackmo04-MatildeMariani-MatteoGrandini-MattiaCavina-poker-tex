package main.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.game.GameFactoryImpl;
import model.game.api.GameFactory;
public class TestGame {

    private static final int INITIAL_CHIPS = 500;
    private static final int INITIAL_NUM_PLAYERS = 4;

    private static GameFactory gameFactory;

    @BeforeAll
    public static void setUp() {
        gameFactory = new GameFactoryImpl();
    }

    @Test
    public void testCreation() {
        var game = gameFactory.easyGame(INITIAL_CHIPS);
        assertFalse(game.isOver());
        assertFalse(game.isWon());

        assertEquals(INITIAL_NUM_PLAYERS, game.getPlayers().size());
        assertEquals(INITIAL_CHIPS, game.getPlayers().getFirst().getChips());
        assertEquals(INITIAL_CHIPS, game.getPlayers().get(1).getChips());
        assertEquals(INITIAL_CHIPS, game.getPlayers().get(2).getChips());
        assertEquals(INITIAL_CHIPS / 100, game.getGameState().getCurrentBet());
        assertEquals(INITIAL_NUM_PLAYERS, game.getGameState().getRemainingPlayers());

    }


}
