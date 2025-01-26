package model.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Iterables;

import model.dealer.DealerImpl;
import model.dealer.api.Dealer;
import model.game.api.Game;
import model.game.api.Phase;
import model.game.api.State;
import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;

/**
 * This class provides an implementation of the Game interface, abstracting the choice of players.
 */
public abstract class AbstractGame implements Game{

    private static final int MIN_PLAYERS = 2;
    private static final int INITIAL_BET_DIVISION_FACT = 10;
    protected static final int NUM_AI_PLAYERS = 3;
    protected static final int NUM_INITIAL_PLAYERS = 4;

    private final Dealer dealer;
    private final State gameState;
    private final int startingBet;
    private final List<Player> players = new LinkedList<>();
    
    /**
     * Constructor for the AbstractGame. 
     * @param initialChips initial amount of chips of players.
     */
    public AbstractGame(final int initialChips) {
        this.startingBet = (int) initialChips / INITIAL_BET_DIVISION_FACT;
        this.dealer = new DealerImpl();
        this.gameState = new StateImpl(startingBet, NUM_INITIAL_PLAYERS);
        this.setPlayers(initialChips);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.players.stream().allMatch(p -> p.isAI()) || 
               isWon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWon() {
        return this.players.stream().allMatch(p -> !p.isAI());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        while (!isOver()) {
            this.players.removeIf(p -> !p.hasChipsLeft());
            var firstRole = this.players.size() > MIN_PLAYERS ? Role.REGULAR : Role.SMALL_BLIND;
            this.sortFromRole(this.players, firstRole);

            this.players.stream().forEachOrdered(p -> p.setCards(this.dealer.giveCardsToPlayer()));
            this.gameState.newHand(startingBet, this.players.size());
            var handPlayers = new LinkedList<>(this.players);
            do {
                this.gameState.addCommunityCards(this.dealer.giveCardsToTheGame(gameState.getHandPhase().getNumCards()));
                if (this.gameState.getHandPhase().equals(Phase.FLOP)) {
                    this.sortFromRole(handPlayers, Role.SMALL_BLIND);
                }

                var playersIterator = Iterables.cycle(handPlayers).iterator();
                while (playersIterator.hasNext() || this.isPhaseOver(handPlayers)) {
                    var currentPlayer = playersIterator.next();
                    if (currentPlayer.hasChipsLeft()) {
                        this.manageAction(playersIterator, currentPlayer);
                    }
                }
                this.players.forEach(p -> this.gameState.addToPot(p.getTotalPhaseBet()));
                this.gameState.nextHandPhase();
                
            } while (!(handPlayers.size() < MIN_PLAYERS) || !this.gameState.getHandPhase().equals(Phase.PREFLOP));

            this.determinateWinnerOfTheHand(handPlayers);
        }
    }

    /**
     * Sort the given list of {@link Player}s, placing the player with the given {@link Role} first, 
     * then the players that were after him in the original list, and lastly those who were before him,
     * in the original order.
     * @param players the list of players to order.
     * @param firstPlayerRole the role of the player that must be set first of the list.
     */
    private void sortFromRole(final List<Player> players, final Role firstPlayerRole) {
        var index  = Iterables.indexOf(players, p -> p.getRole().equals(firstPlayerRole));
        var orderedPlayers = players.subList(index, players.size());
        orderedPlayers.addAll(players.subList(0, index));
        players.clear();
        players.addAll(orderedPlayers);
    }

    /**
     * Asks the given player for his {@link Action} and updates the given iterator and the game
     * {@link State} accordingly.
     * @param playersIterator the iterator of the list of players still playing.
     * @param player the player whose turn it is.
     */
    private void manageAction(final Iterator<Player> playersIterator, final Player player) {
        var action = player.getAction(this.gameState);
        switch (action) {
            case Action.FOLD:
                playersIterator.remove();
                this.gameState.setRemainingPlayers(gameState.getRemainingPlayers() - 1);
                break;
            case Action.RAISE:
                this.gameState.setCurrentBet(player.getTotalPhaseBet());
                break;
            case Action.CALL:
            case Action.CHECK:
                break;
        }
    }

    /**
     * Checks if the phase is over. Returns true if there is less than the minimum number of player
     * still playing or if every player either went all-in or betted the current bet.
     * @param phasePlayers the list of player still playing in the phase.
     * @return whether the phase is over.
     */
    private boolean isPhaseOver(final List<Player> phasePlayers) {
        return phasePlayers.size() < MIN_PLAYERS || 
               phasePlayers.stream()
                          .allMatch(p -> p.getTotalPhaseBet() == this.gameState.getCurrentBet() || 
                                !p.hasChipsLeft());
    }

    /** 
     * TODO: Change the comparator, if the priority is equal than check the values.
     * (need to wait after Mattia implemented the Combination interface).
    */

    /**
     * Checks who won the hand (the one with the best combination if there is more than one player still
     * in the game or the only one left otherwise) and tells players whether they lost or won accordingly.
     * @param players the players still in the game. 
    */
    private void determinateWinnerOfTheHand(final List<Player> players) {
        players.sort((p1, p2) -> p1.getCombination().value() - p2.getCombination().value());
        players.removeFirst().handWon(this.gameState.getPot());
        if (!players.isEmpty()) {
            players.forEach(p -> p.handLost());
        }
    }

    /** 
     * Used to set the initial list of {@link Player}s. It's a template method.
     * @param initialChips initial amount of chips of players.
     */
    private void setPlayers(final int initialChips) {
        Random rand = new Random();
        var startingRole = Role.values()[rand.nextInt(Role.values().length)];

        for (var i = 0; i < NUM_AI_PLAYERS; i++) {
            this.players.add(this.getAIPlayer(initialChips, startingRole.next()));
        }
        //this.players.add(new UserPlayer(initialChips, startingRole.next()));
    }

    /**
     * Returns a different type of AI player based on the difficulty level of the game.
     * @param initialChips initial amount of chips of players.
     * @return an AI player.
     */
    protected abstract Player getAIPlayer(int initialChips, Role startingRole);

}
