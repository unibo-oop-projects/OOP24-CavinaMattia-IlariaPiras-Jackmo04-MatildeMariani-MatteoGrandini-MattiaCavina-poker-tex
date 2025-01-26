package main.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.game.GameFactoryImpl;
import model.game.api.GameFactory;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Player;
import model.player.api.Role;

public class TestGame {

    private static final int INITIAL_CHIPS = 500;
    private static final int INITIAL_NUM_PLAYERS = 3;

    private static GameFactory gameFactory;
    private static AIPlayerFactory playerFactory;

    @BeforeAll
    public static void setUp() {
        gameFactory = new GameFactoryImpl();
        playerFactory = new AIPlayerFactoryImpl();
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

    @Test
    public void testSortFromRole() {
        var game = gameFactory.easyGame(500);
        Player player1 = playerFactory.easy(INITIAL_CHIPS, Role.DEALER);
        Player player2 = playerFactory.easy(INITIAL_CHIPS, Role.SMALL_BLIND);
        Player player3 = playerFactory.easy(INITIAL_CHIPS, Role.BIG_BLIND);
        Player player4 = playerFactory.easy(INITIAL_CHIPS, Role.REGULAR);

        var players = new ArrayList<>(List.of(player1, player2, player3, player4));
        game.sortFromRole(players, Role.SMALL_BLIND);
        assertEquals(List.of(player2, player3, player4, player1), players);

        game.sortFromRole(players, Role.BIG_BLIND);
        assertEquals(List.of(player3, player4, player1, player2), players);
    }

}
