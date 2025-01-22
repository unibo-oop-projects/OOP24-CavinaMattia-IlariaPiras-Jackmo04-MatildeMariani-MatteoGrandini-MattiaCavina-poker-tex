package model.player.ai;

import java.util.function.Function;

import model.player.ai.api.AIPlayer;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Role;
import model.temp.Combination;

public class AIPlayerFactoryImpl implements AIPlayerFactory {

    @Override
    public AIPlayer easy(int initialChips, Role initialRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'easy'");
    }

    @Override
    public AIPlayer medium(int initialChips, Role initialRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'medium'");
    }

    @Override
    public AIPlayer hard(int initialChips, Role initialRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hard'");
    }

    @Override
    public AIPlayer custom(int initialChips, Role initialRole, Function<Combination, Double> chanceFromCombination,
            Function<Double, Double> chanceAfterRaise, double bluffingChance) {
        return new AIPlayerImpl(initialChips, initialRole, chanceFromCombination, chanceAfterRaise, bluffingChance);
    }

}
