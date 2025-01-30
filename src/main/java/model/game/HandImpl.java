package model.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Iterables;

import model.game.api.Hand;
import model.game.api.Phase;
import model.game.api.State;
import model.player.api.Player;
import model.player.api.Action;
import model.player.api.Role;

public class HandImpl implements Hand {

    private static final Role FIRST_ROLE = Role.SMALL_BLIND;
    private static final Phase FIRST_PHASE = Phase.PREFLOP;
    private static final int MIN_PLAYERS = 2;

    private final List<Player> handPlayers;
    private final State gameState;
    private Phase currentPhase;

    public HandImpl(final List<Player> handPlayers, final State gameState) {
        this.currentPhase = FIRST_PHASE;
        this.gameState = gameState;
        this.handPlayers = new LinkedList<>(handPlayers);
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
        this.handPlayers.addAll(originalList.subList(index, originalList.size()));
        this.handPlayers.addAll(originalList.subList(0, index));
    }
    
    /**
     * {@inheritDoc}         
     */
    @Override
    public void manageAction(final Iterator<Player> playersIterator, final Player player) {
        var action = player.getAction(this.gameState);
        switch (action) {
            case Action.FOLD:
                playersIterator.remove();
                this.gameState.setRemainingPlayers(this.gameState.getRemainingPlayers() - 1);
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
     * {@inheritDoc}
     */
    @Override
    public void startPhase() {
        var playersIterator = Iterables.cycle(this.handPlayers).iterator();
        while (playersIterator.hasNext() || this.isPhaseOver()) {
            var currentPlayer = playersIterator.next();
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
     * TODO: Change the comparator, (need to wait after Mattia implemented the Combination interface).
    */

    /**
     * {@inheritDoc}
     */
    @Override
    public void determinateWinnerOfTheHand() {
        //this.handPlayers.sort();
        this.handPlayers.removeFirst().handWon(this.gameState.getPot());
        if (!this.handPlayers.isEmpty()) {
            this.handPlayers.forEach(p -> p.handLost());
        }
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


}
