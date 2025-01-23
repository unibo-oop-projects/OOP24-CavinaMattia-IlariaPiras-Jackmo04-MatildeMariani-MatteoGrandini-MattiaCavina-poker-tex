package model.player.ai;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.player.AbstractPlayer;
import model.player.ai.api.AIPlayer;
import model.player.api.Action;
import model.player.api.Role;
import model.temp.Blind;
import model.temp.Combinations;
import model.temp.HandFase;
import model.temp.State;

public abstract class AbstractAIPlayer extends AbstractPlayer implements AIPlayer {

    private final double raisingFactor;

    AbstractAIPlayer(int initialChips, Role initialRole, double raisingFactor) {
        super(initialChips, initialRole);
        this.raisingFactor = raisingFactor;
    }

    @Override
    public Action getAction(State currentState) {
        if (this.getCards().size() != 2) {
            throw new IllegalStateException("Player must have 2 cards to play");
        }
        this.updateCombination(currentState);
        if (shouldRaise(currentState)) {
            var bet = maxBetToReach((int) (currentState.currentBet() + 1000 * raisingFactor)) - this.getTotalFaseBet();
            this.makeBet(bet);
            return Action.RAISE;
        }
        if (canCheck(currentState)) {
            return Action.CHECK;
        }
        if (shouldCall(currentState)) {
            var bet = maxBetToReach(currentState.currentBet()) - this.getTotalFaseBet();
            this.makeBet(bet);
            return Action.CALL;
        }
        if (currentState.handFase() == HandFase.PREFLOP) {
            this.makeBet(requiredBet(currentState));            
        }
        return Action.FOLD;
    }

    @Override
    public boolean isAI() {
        return true;
    }

    @Override
    public void handWon(int winnings) {
        this.setChips(this.getChips() + winnings);
        this.endhand();
    }

    @Override
    public void handLost() {
        this.endhand();
    }

    protected abstract boolean shouldCall(State currentState);
    
    protected abstract boolean shouldRaise(State currentState);

    /**
     * Returns the amount of chips required to call or raise in the current state.
     * @param currentState
     * @return the amount of chips required to call or raise in the current state.
     */
    protected int requiredBet(State currentState) {
        if (currentState.handFase() == HandFase.PREFLOP) {
            return (int) (currentState.currentBet() * switch (getRole()) {
                case SMALL_BLIND -> Blind.SMALL.getMultiplier();
                case BIG_BLIND -> Blind.BIG.getMultiplier();
                default -> 1;
            });
        } else {
            return currentState.currentBet();            
        }
    }

    private int maxBetToReach(int amount) {
        return (int) Math.min(getChips(), amount);
    }

    private boolean canCheck(State currentState) {
        return requiredBet(currentState) == getTotalFaseBet();
    }

    private void makeBet(int amount) {
        this.setBet(this.getTotalFaseBet() + amount);
        this.setChips(getChips() - amount);
    }

    private void endhand() {
        this.setCards(Set.of());
        // TODO Ancora decidere come gestire i ruoli, per ora si assume che ci siano sempre 4 giocatori
        this.setRole(getRole().next()); 
    }

    private void updateCombination(State currentState) {
        var usableCards = Stream.concat(currentState.communityCards().stream(), this.getCards().stream())
            .collect(Collectors.toSet());
        this.setCombination(Combinations.getBestCombination(usableCards));
    }

}
