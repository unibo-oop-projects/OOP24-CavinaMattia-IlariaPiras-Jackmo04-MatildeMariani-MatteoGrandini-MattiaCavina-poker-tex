package model.player.ai;

import model.player.AbstractPlayer;
import model.player.ai.api.AIPlayer;
import model.player.api.Action;
import model.player.api.Role;
import model.temp.Blind;
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
        if (shouldRaise(currentState)) {
            var currentBet = maxBetToReach((int) (requiredBet(currentState) * raisingFactor)) - this.getTotalFaseBet();
            this.makeBet(currentBet);
            return Action.RAISE;
        } else if (canCheck(currentState)) {
            return Action.CHECK;
        } else if (shouldCall(currentState)) {
            var currentBet = maxBetToReach(requiredBet(currentState)) - this.getTotalFaseBet();
            this.makeBet(currentBet);
            return Action.CALL;
        } else {
            return Action.FOLD;
        }
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
        // this.setRole(getRole().next()); // TODO Ancora decidere come gestire i ruoli
    }

}
