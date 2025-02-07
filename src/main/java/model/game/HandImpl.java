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
    private final List<Player> handPlayers;
    private final State gameState;
    private Phase currentPhase;

    public HandImpl(final GameController controller, final List<Player> handPlayers, final State gameState) {
        this.currentPhase = FIRST_PHASE;
        this.gameState = gameState;
        this.controller = controller;
        this.handPlayers = new LinkedList<>(handPlayers);
        this.comparator = new CombinationComparator();
        this.sortFromRole(FIRST_ROLE);
    } 
        
    /**
     * {@inheritDoc}
     */
    @Override
    public void sortFromRole(final Role firstPlayerRole) {
        var originalList = List.copyOf(this.handPlayers);
        this.handPlayers.clear();
        var index  = Iterables.indexOf(originalList, p -> p.getRole().equals(Optional.of(firstPlayerRole)));
        this.handPlayers.add(originalList.get(index));
        this.handPlayers.addAll(originalList.subList(index + 1, originalList.size()));
        this.handPlayers.addAll(originalList.subList(0, index));
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
        var playersIterator = Iterables.cycle(this.handPlayers).iterator();
        while (!this.isPhaseOver() && playersIterator.hasNext() && !controller.isTerminated()) {
            this.controller.waitIfPaused();
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
        return this.handPlayers.size() < MIN_PLAYERS || 
               this.handPlayers.stream()
                   .allMatch(p -> p.getTotalPhaseBet() == this.gameState.getCurrentBet() || 
                        !p.hasChipsLeft());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHandOver() {
        return handPlayers.size() < MIN_PLAYERS || 
               this.currentPhase.equals(FIRST_PHASE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void determinesWinnerOfTheHand() {
        this.handPlayers.forEach(p -> this.controller.setPlayerCards(p.getId(), p.getCards()));
        this.handPlayers.sort((p1, p2) -> this.comparator.compare(p1.getCombination(), p2.getCombination()));
        var winner = this.handPlayers.removeLast();
        winner.handWon(this.gameState.getPot());
        if (!this.handPlayers.isEmpty()) {
            this.handPlayers.forEach(p -> p.handLost());
        }
        this.halt();
        this.controller.showWinner(winner.getId(), winner.getChips(), this.gameState.getPot());
        this.halt();
        this.controller.setWinnerData(winner.getId(), winner.getChips());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getHandPlayers() {
        return handPlayers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phase getCurrentPhase() {
        return currentPhase;
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
