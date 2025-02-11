package main.pokertexas.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pokertexas.controller.game.GameControllerImpl;
import pokertexas.controller.game.api.Difficulty;
import pokertexas.controller.game.api.GameController;
import pokertexas.model.deck.DeckFactoryImpl;
import pokertexas.model.deck.api.Card;
import pokertexas.model.deck.api.Deck;
import pokertexas.model.game.HandImpl;
import pokertexas.model.game.StateImpl;
import pokertexas.model.game.api.Hand;
import pokertexas.model.game.api.State;
import pokertexas.model.player.ai.AIPlayerFactoryImpl;
import pokertexas.model.player.ai.api.AIPlayerFactory;
import pokertexas.model.player.api.Player;
import pokertexas.model.player.api.Role;
import pokertexas.view.ViewImpl;
import pokertexas.view.scenes.GameScene;
/**
 * Class that implements basic tests for HandImpl.
 */
final class TestHandImpl {

    private static final int NUM_PLAYER_CARDS = 2;
    private static final int NUM_COMMUNITY_CARDS = 5;
    private static final int INITIAL_CHIPS = 1000;
    private static final int INITIAL_BET_DIVISION_FACT = 10;
    private static final int INITIAL_NUM_PLAYERS = 4;

    private static AIPlayerFactory playerFactory;
    private static GameController controller;
    private List<Player> players;
    private State gameState;
    private Hand hand;
    private Deck<Card> deck;

    /**
     * Initialize the playerFactory, the controller and the gameScene before all the tests.
     */
    @BeforeAll
    public static void setUp() {
        playerFactory = new AIPlayerFactoryImpl();
        controller = new GameControllerImpl(new ViewImpl(false), Difficulty.EASY, INITIAL_CHIPS);
        controller.setGameScene(new GameScene(controller));
    }

    /**
     * Initialize the players list, the gameState, the deck and the hand before each test.
     */
    @BeforeEach
    public void newHand() {
        final Player player1 = playerFactory.createEasy(0, INITIAL_CHIPS);
        final Player player2 = playerFactory.createEasy(1, INITIAL_CHIPS);
        final Player player3 = playerFactory.createEasy(2, INITIAL_CHIPS);
        final Player player4 = playerFactory.createEasy(3, INITIAL_CHIPS);
        player2.setRole(Role.SMALL_BLIND);
        player3.setRole(Role.BIG_BLIND);

        players = new ArrayList<>(List.of(player1, player2, player3, player4));
        gameState = new StateImpl(INITIAL_CHIPS / INITIAL_BET_DIVISION_FACT, INITIAL_NUM_PLAYERS);
        deck = new DeckFactoryImpl().simplePokerDeck();
        hand = new HandImpl(controller, players, gameState);

        hand.getRemainingPlayers()
            .forEach(p -> p.setCards(deck.getSomeCards(NUM_PLAYER_CARDS).stream().collect(Collectors.toSet())));
    }


    @Test
    void testCreation() {
        final Player player1 = playerFactory.createEasy(0, INITIAL_CHIPS);
        final Player player2 = playerFactory.createEasy(1, INITIAL_CHIPS);
        final Player player3 = playerFactory.createEasy(2, INITIAL_CHIPS);
        final Player player4 = playerFactory.createEasy(3, INITIAL_CHIPS);
        final var players = new ArrayList<>(List.of(player1, player2, player3, player4));
        player2.setRole(Role.SMALL_BLIND);
        player3.setRole(Role.BIG_BLIND);

        final var hand1 = new HandImpl(controller, players, gameState);
        assertEquals(List.of(player2, player3, player4, player1), hand1.getRemainingPlayers());

        player2.setRole(null);
        player3.setRole(Role.SMALL_BLIND);
        player4.setRole(Role.BIG_BLIND);

        final var hand2 = new HandImpl(controller, List.of(player2, player3, player4), gameState);
        assertEquals(List.of(player3, player4, player2), hand2.getRemainingPlayers());

        player3.setRole(Role.BIG_BLIND);
        player4.setRole(Role.SMALL_BLIND);

        final var hand3 = new HandImpl(controller, List.of(player3, player4), gameState);
        assertEquals(List.of(player4, player3), hand3.getRemainingPlayers());

        player2.setRole(Role.BIG_BLIND);
        player3.setRole(null);
        player4.setRole(Role.SMALL_BLIND);

        final var hand4 = new HandImpl(controller, List.of(player2, player3, player4), gameState);
        assertEquals(List.of(player4, player2, player3), hand4.getRemainingPlayers());

    }

    @Test
    void testManageAction() {
        final var iterator = players.iterator();
        final var currentPlayer = iterator.next();
        final var currentBetBeforePlayerAction = gameState.getCurrentBet();
        currentPlayer.setGameState(gameState);

        hand.manageAction(iterator, currentPlayer);
        switch (currentPlayer.getAction()) {
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
                break;
            case CALL:
            case CHECK:
            default:
                break;
        }
    }

    @Test
    void testDeterminateWinnerOfTheHand() {
        gameState.addToPot(INITIAL_CHIPS);
        gameState.addCommunityCards(deck.getSomeCards(NUM_COMMUNITY_CARDS).stream().collect(Collectors.toSet()));
        hand.determinesWinnerOfTheHand();

        assertEquals(1, (int) players.stream().filter(p -> p.getChips() == (INITIAL_CHIPS + INITIAL_CHIPS)).count());
        assertEquals(hand.getRemainingPlayers().size(), 
            (int) players.stream().filter(p -> p.getChips() == INITIAL_CHIPS).count());
    }
}
