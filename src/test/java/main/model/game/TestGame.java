package main.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.game.GameControllerImpl;
import controller.game.api.Difficulty;
import controller.game.api.GameController;
import model.game.GameFactoryImpl;
import model.game.api.GameFactory;
import view.ViewImpl;
public class TestGame {

    private static final int INITIAL_CHIPS = 500;
    private static final int INITIAL_NUM_PLAYERS = 4;

    private static GameFactory gameFactory;
    private static GameController controller;

    @BeforeAll
    public static void setUp() {
        gameFactory = new GameFactoryImpl();
        controller = new GameControllerImpl(new ViewImpl(), Difficulty.EASY, INITIAL_CHIPS);
    }

    @Test
    public void testCreation() {
        var game = gameFactory.easyGame(controller, INITIAL_CHIPS);
        assertFalse(game.isOver());
        assertFalse(game.isWon());

        assertEquals(INITIAL_NUM_PLAYERS, game.getPlayers().size());
        assertEquals(INITIAL_CHIPS, game.getPlayers().getFirst().getChips());
        assertEquals(INITIAL_CHIPS, game.getPlayers().get(1).getChips());
        assertEquals(INITIAL_CHIPS, game.getPlayers().get(2).getChips());
        assertEquals(INITIAL_CHIPS / 10, game.getGameState().getCurrentBet());
        assertEquals(INITIAL_NUM_PLAYERS, game.getGameState().getRemainingPlayers());

    }


}
