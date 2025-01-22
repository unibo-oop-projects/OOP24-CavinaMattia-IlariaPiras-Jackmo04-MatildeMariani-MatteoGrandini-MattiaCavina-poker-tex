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

    // TODO Da finire
    @Override
    public Action getAction(State currentState) {
        if (shouldRaise(currentState)) {
            var currentBet = maxBetToReach((int) (requiredBet(currentState) * raisingFactor)) - this.getTotalBet();
            this.setBet(this.getTotalBet() + currentBet);
            this.setChips(getChips() - currentBet);
            return Action.RAISE;
        } else if (requiredBet(currentState) == getTotalBet()) {
            return Action.CHECK;
        } else if (shouldCall(currentState)) {
            var currentBet = maxBetToReach(requiredBet(currentState)) - this.getTotalBet();
            this.setBet(this.getTotalBet() + currentBet);
            this.setChips(getChips() - currentBet);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handWon'");
    }

    @Override
    public void handLost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handLost'");
    }

    protected abstract boolean shouldCall(State currentState);
    
    protected abstract boolean shouldRaise(State currentState);

    private int requiredBet(State currentState) {
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

}
