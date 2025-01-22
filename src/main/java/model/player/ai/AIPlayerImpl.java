package model.player.ai;

import java.util.Optional;
import java.util.function.Function;

import model.player.AbstractPlayer;
import model.player.ai.api.AIPlayer;
import model.player.api.Action;
import model.player.api.Role;
import model.temp.Combination;
import model.temp.State;

public class AIPlayerImpl extends AbstractPlayer implements AIPlayer {

    private final Function<Combination, Double> chanceFromCombination;
    private final Function<Double, Double> chanceAfterRaise;
    private final double bluffingChance;

    AIPlayerImpl(
        int initialChips, 
        Role initialRole, 
        Function<Combination, Double> chanceFromCombination, 
        Function<Double, Double> chanceAfterRaise, 
        double bluffingChance
    ) {
        super(initialChips, initialRole);
        this.chanceFromCombination = chanceFromCombination;
        this.chanceAfterRaise = chanceAfterRaise;
        this.bluffingChance = bluffingChance;
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

}
