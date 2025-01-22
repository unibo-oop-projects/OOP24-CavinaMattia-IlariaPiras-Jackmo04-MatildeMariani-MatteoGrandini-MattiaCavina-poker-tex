package model.player.ai;

import java.util.function.Function;

import model.player.ai.api.AIPlayer;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Role;
import model.temp.Combination;

public class AIPlayerFactoryImpl implements AIPlayerFactory {

    private static final double EASY_BLUFFING_CHANCE = 0.05;
    private static final double EASY_RAISE_MODIFIER = 0.50;
    private static final double MEDIUM_BLUFFING_CHANCE = 0.20;
    private static final double MEDIUM_RAISE_MODIFIER = 0.75;
    private static final double HARD_BLUFFING_CHANCE = 0.60;
    private static final double HARD_RAISE_MODIFIER = 0.90;

    @Override
    public AIPlayer easy(int initialChips, Role initialRole) {
        return new AbstractAIPlayer(initialChips, initialRole, EASY_BLUFFING_CHANCE, c -> c * EASY_RAISE_MODIFIER) {
            @Override
            protected double getChanceFromCombination(Combination combination) {
                return switch(combination) {
                    default -> 0.0; // TODO when Combination is ready
                };
            }
        };
    }

    @Override
    public AIPlayer medium(int initialChips, Role initialRole) {
        return new AbstractAIPlayer(initialChips, initialRole, MEDIUM_BLUFFING_CHANCE, c -> c * MEDIUM_RAISE_MODIFIER) {
            @Override
            protected double getChanceFromCombination(Combination combination) {
                return switch(combination) {
                    default -> 0.0; // TODO when Combination is ready
                };
            }
        };
    }

    @Override
    public AIPlayer hard(int initialChips, Role initialRole) {
        return new AbstractAIPlayer(initialChips, initialRole, HARD_BLUFFING_CHANCE, c -> c * HARD_RAISE_MODIFIER) {
            @Override
            protected double getChanceFromCombination(Combination combination) {
                return switch(combination) {
                    default -> 0.0; // TODO when Combination is ready
                };
            }
        };
    }

    @Override
    public AIPlayer custom(int initialChips, Role initialRole, Function<Combination, Double> chanceFromCombination,
            Function<Double, Double> chanceAfterRaise, double bluffingChance) {
        return new AbstractAIPlayer(initialChips, initialRole, bluffingChance, chanceAfterRaise) {
            @Override
            protected double getChanceFromCombination(Combination combination) {
                return chanceFromCombination.apply(combination);
            }
        };
    }

}
