package main.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;
import model.game.HandImpl;
import model.game.StateImpl;
import model.game.api.Hand;
import model.game.api.State;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;

public class TestHandImpl {

    private static final int INITIAL_CHIPS = 500;
    private static final int INITIAL_NUM_PLAYERS = 4;

    private static AIPlayerFactory playerFactory;
    private static List<Player> players;
    private static State gameState;
    private static Hand hand;
    private static Deck<Card> deck;

    @BeforeAll
    public static void setUp() {
        playerFactory = new AIPlayerFactoryImpl();
    }

    @BeforeEach
    public void newHand() {
        Player player1 = playerFactory.easy(INITIAL_CHIPS, Role.DEALER);
        Player player2 = playerFactory.easy(INITIAL_CHIPS, Role.SMALL_BLIND);
        Player player3 = playerFactory.easy(INITIAL_CHIPS, Role.BIG_BLIND);
        Player player4 = playerFactory.easy(INITIAL_CHIPS, Role.REGULAR);

        players = new ArrayList<>(List.of(player1, player2, player3, player4));
        gameState = new StateImpl(INITIAL_CHIPS / 100, INITIAL_NUM_PLAYERS);
        deck = new DeckFactoryImpl().simplePokerDeck();
        hand = new HandImpl(players, gameState);
        
        hand.getHandPlayers()
            .forEach(p -> p.setCards(deck.getSomeCards(2).stream().collect(Collectors.toSet())));
    }


    @Test
    public void testCreation() {
        Player player1 = playerFactory.easy(INITIAL_CHIPS, Role.DEALER);
        Player player2 = playerFactory.easy(INITIAL_CHIPS, Role.SMALL_BLIND);
        Player player3 = playerFactory.easy(INITIAL_CHIPS, Role.BIG_BLIND);
        Player player4 = playerFactory.easy(INITIAL_CHIPS, Role.REGULAR);
        var players = new ArrayList<>(List.of(player1, player2, player3, player4));

        var hand1 = new HandImpl(players, gameState);
        assertEquals(List.of(player2, player3, player4, player1), hand1.getHandPlayers());

        player2.setRole(Role.REGULAR);
        player3.setRole(Role.SMALL_BLIND);
        player4.setRole(Role.BIG_BLIND);

        var hand2 = new HandImpl(List.of(player2, player3, player4), gameState);
        assertEquals(List.of(player3, player4, player2), hand2.getHandPlayers());

        player3.setRole(Role.BIG_BLIND);
        player4.setRole(Role.SMALL_BLIND);

        var hand3 = new HandImpl(List.of(player3, player4), gameState);
        assertEquals(List.of(player4, player3), hand3.getHandPlayers());

        player2.setRole(Role.BIG_BLIND);
        player3.setRole(Role.REGULAR);
        player4.setRole(Role.SMALL_BLIND);

        var hand4 = new HandImpl(List.of(player2, player3, player4), gameState);
        assertEquals(List.of(player4, player2, player3), hand4.getHandPlayers());

    }

    @Test
    public void testDeterminateWinnnerOfTheHand() {
        gameState.addToPot(INITIAL_CHIPS);
        hand.determinateWinnerOfTheHand();

        assertEquals( 1, 
                    (int) players.stream().filter(p -> p.getChips() == (INITIAL_CHIPS + INITIAL_CHIPS)).count());
        assertEquals( hand.getHandPlayers().size(), 
                    (int) players.stream().filter(p -> p.getChips() == INITIAL_CHIPS).count());

    }

    @Test
    public void testManageAction() {
        var iterator = players.iterator();
        var currentPlayer = iterator.next();

        hand.manageAction(iterator, currentPlayer);
        switch (currentPlayer.getAction(gameState)) {
            case Action.FOLD:
                assertEquals(INITIAL_NUM_PLAYERS - 1, players.size());
                assertEquals(INITIAL_NUM_PLAYERS - 1, gameState.getRemainingPlayers());
                break;
            case Action.RAISE:
                assertEquals(currentPlayer.getTotalPhaseBet(), gameState.getCurrentBet());
                break;
            default:
                break;
        }
    }

    @Test
    public void testStartPhase() {

    }

}
