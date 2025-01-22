package model.player.ai;

import java.util.Optional;
import java.util.function.Function;

import model.player.AbstractPlayer;
import model.player.ai.api.AIPlayer;
import model.player.api.Action;
import model.player.api.Role;
import model.temp.Combination;
import model.temp.State;

public abstract class AbstractAIPlayer extends AbstractPlayer implements AIPlayer {

    private final double bluffingChance;
    private final Function<Double, Double> afterRaiseModifier;

    AbstractAIPlayer(
        int initialChips, 
        Role initialRole,
        double bluffingChance,
        Function<Double, Double> afterRaiseModifier
    ) {
        super(initialChips, initialRole);
        this.bluffingChance = bluffingChance;
        this.afterRaiseModifier = afterRaiseModifier;
    }

    @Override
    public Optional<Integer> getBet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBet'");
    }

    @Override
    public Action getAction(State currentState) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAction'");
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

    protected abstract double getChanceFromCombination(Combination combination);

}
