package main.pokertexas.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pokertexas.controller.game.GameControllerImpl;
import pokertexas.controller.game.api.Difficulty;
import pokertexas.controller.game.api.GameController;
import pokertexas.model.game.GameFactoryImpl;
import pokertexas.model.game.api.GameFactory;
import pokertexas.view.ViewImpl;

/**
 * Class that implements a basic test for the Game.
 */
final class TestGame {

    private static final int INITIAL_CHIPS = 500;
    private static final int INITIAL_NUM_PLAYERS = 4;

    private static GameFactory gameFactory;
    private static GameController controller;

    /**
     * Initialize gameFactory and GameController before all the tests.
     */
    @BeforeAll
    public static void setUp() {
        gameFactory = new GameFactoryImpl();
        controller = new GameControllerImpl(new ViewImpl().hidden(), Difficulty.EASY, INITIAL_CHIPS);
    }

    /**
     * Test the creation of a game.
     */
    @Test
    void testCreation() {
        final var game = gameFactory.easyGame(controller, INITIAL_CHIPS);
        game.setInitialPlayers(INITIAL_CHIPS);
        assertFalse(game.isOver());
        assertFalse(game.isWon());

        assertEquals(INITIAL_NUM_PLAYERS, game.getPlayers().size());
        assertEquals(INITIAL_CHIPS, game.getPlayers().getFirst().getChips());
        assertEquals(INITIAL_CHIPS, game.getPlayers().get(1).getChips());
        assertEquals(INITIAL_CHIPS, game.getPlayers().get(2).getChips());

    }


}
