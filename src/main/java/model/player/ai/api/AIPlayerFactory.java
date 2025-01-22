package model.player.ai.api;

import java.util.function.Function;

import model.player.api.Role;
import model.temp.Combination;

public interface AIPlayerFactory {

    AIPlayer easy(int initialChips, Role initialRole);

    AIPlayer medium(int initialChips, Role initialRole);

    AIPlayer hard(int initialChips, Role initialRole);

    AIPlayer custom(
        int initialChips, 
        Role initialRole, 
        Function<Combination, Double> chanceFromCombination,
        Function<Double, Double> chanceAfterRaise,
        double bluffingChance
    );
}
