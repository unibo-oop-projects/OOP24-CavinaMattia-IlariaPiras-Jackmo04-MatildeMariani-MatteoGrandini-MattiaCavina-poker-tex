package main.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.game.GameFactoryImpl;
import model.game.api.GameFactory;
public class TestGame {

    private static final int INITIAL_CHIPS = 500;
    private static final int INITIAL_NUM_PLAYERS = 3;

    private static GameFactory gameFactory;

    @BeforeAll
    public static void setUp() {
        gameFactory = new GameFactoryImpl();
    }

    @Test
    public void testCreation() {
        var game = gameFactory.easyGame(INITIAL_CHIPS);
        //When the UserPlayer is implemented and I can add it then they should both return false.
        assertTrue(game.isOver());
        assertFalse(game.isWon());

        assertEquals(INITIAL_NUM_PLAYERS, game.getPlayers().size());
        var role1 = game.getPlayers().getFirst().getRole();
        var role2 = game.getPlayers().get(1).getRole();
        var role3 = game.getPlayers().get(2).getRole();
        assertTrue(role1 != role2 && role1 != role3 && role2 != role3);
        assertEquals(INITIAL_CHIPS, game.getPlayers().getFirst().getChips());
        assertEquals(INITIAL_CHIPS, game.getPlayers().get(1).getChips());
        assertEquals(INITIAL_CHIPS, game.getPlayers().get(2).getChips());
        assertEquals(INITIAL_CHIPS / 10, game.getGameState().getCurrentBet());
        assertEquals(INITIAL_NUM_PLAYERS + 1, game.getGameState().getRemainingPlayers());

    }


}
