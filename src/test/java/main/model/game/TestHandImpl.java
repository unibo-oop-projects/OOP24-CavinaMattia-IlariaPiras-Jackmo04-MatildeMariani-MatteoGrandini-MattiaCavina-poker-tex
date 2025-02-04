package main.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.game.GameControllerImpl;
import controller.game.api.Difficulty;
import controller.game.api.GameController;
import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.game.HandImpl;
import model.game.StateImpl;
import model.game.api.Hand;
import model.game.api.State;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Player;
import model.player.api.Role;
import view.ViewImpl;
import view.scenes.GameScene;

public class TestHandImpl {

    private static final int INITIAL_CHIPS = 500;
    private static final int INITIAL_NUM_PLAYERS = 4;

    private static AIPlayerFactory playerFactory;
    private static GameController controller;
    private static GameScene gameScene;
    private static List<Player> players;
    private static State gameState;
    private static Hand hand;
    private static Deck<Card> deck;

    @BeforeAll
    public static void setUp() {
        playerFactory = new AIPlayerFactoryImpl();
        controller = new GameControllerImpl(new ViewImpl(), Difficulty.EASY, INITIAL_CHIPS);
        gameScene = new GameScene(controller);
    }

    @BeforeEach
    public void newHand() {
        Player player1 = playerFactory.easy(1, INITIAL_CHIPS);
        Player player2 = playerFactory.easy(2, INITIAL_CHIPS);
        Player player3 = playerFactory.easy(3, INITIAL_CHIPS);
        Player player4 = playerFactory.easy(4, INITIAL_CHIPS);
        player2.setRole(Role.SMALL_BLIND);
        player3.setRole(Role.BIG_BLIND);

        players = new ArrayList<>(List.of(player1, player2, player3, player4));
        gameState = new StateImpl(INITIAL_CHIPS / 100, INITIAL_NUM_PLAYERS);
        deck = new DeckFactoryImpl().simplePokerDeck();
        hand = new HandImpl(controller, players, gameState);
        
        hand.getHandPlayers()
            .forEach(p -> p.setCards(deck.getSomeCards(2).stream().collect(Collectors.toSet())));
    }


    @Test
    public void testCreation() {
        Player player1 = playerFactory.easy(1, INITIAL_CHIPS);
        Player player2 = playerFactory.easy(2, INITIAL_CHIPS);
        Player player3 = playerFactory.easy(3, INITIAL_CHIPS);
        Player player4 = playerFactory.easy(4, INITIAL_CHIPS);
        var players = new ArrayList<>(List.of(player1, player2, player3, player4));
        player2.setRole(Role.SMALL_BLIND);
        player3.setRole(Role.BIG_BLIND);

        var hand1 = new HandImpl(controller, players, gameState);
        assertEquals(List.of(player2, player3, player4, player1), hand1.getHandPlayers());

        player2.setRole(null);
        player3.setRole(Role.SMALL_BLIND);
        player4.setRole(Role.BIG_BLIND);

        var hand2 = new HandImpl(controller, List.of(player2, player3, player4), gameState);
        assertEquals(List.of(player3, player4, player2), hand2.getHandPlayers());

        player3.setRole(Role.BIG_BLIND);
        player4.setRole(Role.SMALL_BLIND);

        var hand3 = new HandImpl(controller, List.of(player3, player4), gameState);
        assertEquals(List.of(player4, player3), hand3.getHandPlayers());

        player2.setRole(Role.BIG_BLIND);
        player3.setRole(null);
        player4.setRole(Role.SMALL_BLIND);

        var hand4 = new HandImpl(controller, List.of(player2, player3, player4), gameState);
        assertEquals(List.of(player4, player2, player3), hand4.getHandPlayers());

    }

    @Test
    public void testDeterminateWinnnerOfTheHand() {
        gameState.addToPot(INITIAL_CHIPS);
        hand.determinesWinnerOfTheHand();

        assertEquals( 1, 
                    (int) players.stream().filter(p -> p.getChips() == (INITIAL_CHIPS + INITIAL_CHIPS)).count());
        assertEquals( hand.getHandPlayers().size(), 
                    (int) players.stream().filter(p -> p.getChips() == INITIAL_CHIPS).count());

    }

    @Test
    public void testManageAction() {
        var iterator = players.iterator();
        var currentPlayer = iterator.next();
        var currentBetBeforePlayerAction = gameState.getCurrentBet();
        
        hand.manageAction(iterator, currentPlayer);
        switch (currentPlayer.getAction(gameState)) {
            case FOLD:
                assertEquals(INITIAL_NUM_PLAYERS - 1, players.size());
                assertEquals(INITIAL_NUM_PLAYERS - 1, gameState.getRemainingPlayers());
                break;
            case RAISE:
                assertEquals(currentPlayer.getTotalPhaseBet(), gameState.getCurrentBet());
                break;
            case ALL_IN:
                if (currentBetBeforePlayerAction < currentPlayer.getTotalPhaseBet()) {
                    assertEquals(currentPlayer.getTotalPhaseBet(), gameState.getCurrentBet());
                }
            default:
                break;
        }
    }

    @Test
    public void testStartPhase() {

    }

}
