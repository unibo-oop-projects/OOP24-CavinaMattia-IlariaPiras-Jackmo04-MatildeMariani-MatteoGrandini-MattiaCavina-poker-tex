package model.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;

import controller.game.api.GameController;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.combination.CombinationComparator;
import model.game.api.Hand;
import model.game.api.Phase;
import model.game.api.State;
import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;

/**
 * Class that implements the Hand interface.
 */
public final class HandImpl implements Hand {

    private static final Role FIRST_ROLE = Role.SMALL_BLIND;
    private static final Phase FIRST_PHASE = Phase.PREFLOP;
    private static final int MIN_PLAYERS = 2;
    private static final int WAIT_TIME = 5000;
    private static final Logger LOGGER = LoggerFactory.getLogger(HandImpl.class);

    private final CombinationComparator comparator;
    private final GameController controller;
    private final List<Player> remainingPlayers;
    private final List<Player> playersWhoLost;
    private final State gameState;
    private Phase currentPhase;
    private int numPlayerWhoPlayedInPhase;

    /**
     * Constructor for the class HandImpl.
     * @param controller the game controller.
     * @param handPlayers the list of players in the hand.
     * @param gameState the game State.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Storing GameController mutable object is intented")
    public HandImpl(final GameController controller, final List<Player> handPlayers, final State gameState) {
        this.numPlayerWhoPlayedInPhase = 0;
        this.currentPhase = FIRST_PHASE;
        this.gameState = gameState;
        this.controller = controller;
        this.playersWhoLost = new LinkedList<>();
        this.remainingPlayers = new LinkedList<>(handPlayers);
        this.comparator = new CombinationComparator();
        this.sortFromRole();
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public void sortFromRole() {
        final var originalList = List.copyOf(this.remainingPlayers);
        this.remainingPlayers.clear();
        final var index  = Iterables.indexOf(originalList, p -> p.getRole().equals(Optional.of(FIRST_ROLE)));
        this.remainingPlayers.add(originalList.get(index));
        this.remainingPlayers.addAll(originalList.subList(index + 1, originalList.size()));
        this.remainingPlayers.addAll(originalList.subList(0, index));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageAction(final Iterator<Player> playersIterator, final Player player) {
        player.setGameState(this.gameState);
        this.controller.isTurn(player.getId(), true);
        final var action = player.getAction();
        switch (action) {
            case FOLD:
                playersIterator.remove();
                this.playersWhoLost.add(player);
                this.gameState.setRemainingPlayers(this.gameState.getRemainingPlayers() - 1);
                break;
            case RAISE:
                this.gameState.setCurrentBet(player.getTotalPhaseBet());
                break;
            case ALL_IN: 
                if (this.gameState.getCurrentBet() < player.getTotalPhaseBet()) {
                    this.gameState.setCurrentBet(player.getTotalPhaseBet());
                }
                break;
            case CALL:
            case CHECK:
            default:
                break;
        }
        this.halt();
        this.controller.setPlayerAction(player.getId(), String.valueOf(action));
        if (!action.equals(Action.FOLD) &&  !action.equals(Action.CHECK)) {
            this.controller.setPlayerBet(player.getId(), player.getTotalPhaseBet());
            this.controller.setPlayerChips(player.getId(), player.getChips());
        }
        this.controller.isTurn(player.getId(), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startPhase() {
        this.halt();
        this.numPlayerWhoPlayedInPhase = 0;
        final var playersIterator = Iterables.cycle(this.remainingPlayers).iterator();
        while (!this.isPhaseOver() && playersIterator.hasNext() && !controller.isTerminated()) {
            this.controller.waitIfPaused();
            this.numPlayerWhoPlayedInPhase++;
            final var currentPlayer = playersIterator.next();
            if (currentPlayer.hasChipsLeft()) {
                this.manageAction(playersIterator, currentPlayer);
            }
        }
        this.currentPhase = this.currentPhase.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPhaseOver() {
        return (this.remainingPlayers.size() < MIN_PLAYERS 
            || this.remainingPlayers.stream().allMatch(p -> p.getTotalPhaseBet() == this.gameState.getCurrentBet()
            || !p.hasChipsLeft())) && (this.numPlayerWhoPlayedInPhase 
            >= (int) this.remainingPlayers.stream().filter(Player::hasChipsLeft).count());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHandOver() {
        return remainingPlayers.size() < MIN_PLAYERS || this.currentPhase.equals(FIRST_PHASE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void determinesWinnerOfTheHand() {
        this.remainingPlayers.forEach(p -> this.controller.setPlayerCards(p.getId(), p.getCards()));
        this.remainingPlayers.sort((p1, p2) -> this.comparator.compare(p1.getCombination(), p2.getCombination()));
        final var winner = this.remainingPlayers.removeLast();
        winner.handWon(this.gameState.getPot());
        this.playersWhoLost.addAll(this.remainingPlayers);
        this.playersWhoLost.forEach(Player::handLost);
        this.halt();
        this.controller.showWinner(winner.getId(), winner.getChips(), this.gameState.getPot());
        this.halt();
        this.controller.setWinnerData(winner.getId(), winner.getChips());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getRemainingPlayers() {
        return List.copyOf(this.remainingPlayers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phase getCurrentPhase() {
        return this.currentPhase;
    }

    /**
     * Method that calls the method sleep on the current thread.
     * Used to manage the timing with which the actions of a hand must be carried out.
     */
    private void halt() {
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            LOGGER.info("Thread interrupted.");
        }
    }
}
