package model.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Iterables;

import controller.game.api.GameController;
import model.combination.CombinationComparator;
import model.game.api.Hand;
import model.game.api.Phase;
import model.game.api.State;
import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;

public class HandImpl implements Hand {

    private static final Role FIRST_ROLE = Role.SMALL_BLIND;
    private static final Phase FIRST_PHASE = Phase.PREFLOP;
    private static final int MIN_PLAYERS = 2;
    private static final int WAIT_TIME = 5000;

    private final CombinationComparator comparator;
    private final GameController controller;
    private final List<Player> remainingPlayers;
    private final List<Player> playersWhoLost;
    private final State gameState;
    private Phase currentPhase;
    private int numPlayerWhoPlayedInPhase;

    public HandImpl(final GameController controller, final List<Player> handPlayers, final State gameState) {
        this.numPlayerWhoPlayedInPhase = 0;
        this.currentPhase = FIRST_PHASE;
        this.gameState = gameState;
        this.controller = controller;
        this.playersWhoLost = new LinkedList<>();
        this.remainingPlayers = new LinkedList<>(handPlayers);
        this.comparator = new CombinationComparator();
        this.sortFromRole(FIRST_ROLE);
    } 
        
    /**
     * {@inheritDoc}
     */
    @Override
    public void sortFromRole(final Role firstPlayerRole) {
        var originalList = List.copyOf(this.remainingPlayers);
        this.remainingPlayers.clear();
        var index  = Iterables.indexOf(originalList, p -> p.getRole().equals(Optional.of(firstPlayerRole)));
        this.remainingPlayers.add(originalList.get(index));
        this.remainingPlayers.addAll(originalList.subList(index + 1, originalList.size()));
        this.remainingPlayers.addAll(originalList.subList(0, index));
    }
    
    /**
     * {@inheritDoc}         
     */
    @Override
    public void manageAction(final Iterator<Player> playersIterator, final Player player) {
        var action = player.getAction(this.gameState);
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
                break;
        }
        this.controller.setPlayerAction(player.getId(), String.valueOf(action));
        if (!action.equals(Action.FOLD) &&  !action.equals(Action.CHECK)) {
            this.controller.setPlayerBet(player.getId(), player.getTotalPhaseBet());
            this.controller.setPlayerChips(player.getId(), player.getChips());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startPhase() {
        this.halt();
        this.numPlayerWhoPlayedInPhase = 0;
        var playersIterator = Iterables.cycle(this.remainingPlayers).iterator();
        while (!this.isPhaseOver() && playersIterator.hasNext() && !controller.isTerminated()) {
            this.controller.waitIfPaused();
            this.numPlayerWhoPlayedInPhase++;
            var currentPlayer = playersIterator.next();
            if (currentPlayer.hasChipsLeft()) {
                this.manageAction(playersIterator, currentPlayer);
            }
            this.halt();
        }
        this.currentPhase = this.currentPhase.next();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPhaseOver() {
        return (this.remainingPlayers.size() < MIN_PLAYERS || 
               this.remainingPlayers.stream().allMatch(p -> p.getTotalPhaseBet() == this.gameState.getCurrentBet() || 
                    !p.hasChipsLeft())) && (this.numPlayerWhoPlayedInPhase >= this.remainingPlayers.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHandOver() {
        return remainingPlayers.size() < MIN_PLAYERS || 
               this.currentPhase.equals(FIRST_PHASE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void determinesWinnerOfTheHand() {
        this.remainingPlayers.forEach(p -> this.controller.setPlayerCards(p.getId(), p.getCards()));
        this.remainingPlayers.sort((p1, p2) -> this.comparator.compare(p1.getCombination(), p2.getCombination()));
        var winner = this.remainingPlayers.removeLast();
        winner.handWon(this.gameState.getPot());
        this.playersWhoLost.addAll(this.remainingPlayers);
        this.playersWhoLost.forEach(p -> p.handLost());
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
        return this.remainingPlayers;
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
        }
    }
}
