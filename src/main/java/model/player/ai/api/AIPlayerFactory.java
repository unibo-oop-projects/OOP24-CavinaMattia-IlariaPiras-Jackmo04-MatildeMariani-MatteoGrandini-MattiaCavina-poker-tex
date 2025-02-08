package model.player.ai.api;

import java.util.function.Function;

import model.combination.api.CombinationType;

/**
 * Factory for creating AI players.
 * There are three fixed levels of AI players: easy, medium, and hard;
 * however, it is also possible to create a custom AI player with a custom difficulty.
 */
public interface AIPlayerFactory {

    /**
     * Create an <b>easy</b> difficulty AI player with the given id and initial chips.
     * An easy AI player is less likely to call or raise than a medium one.
     * @param id the player's id.
     * @param initialChips the amount of initial chips.
     * @return an easy difficulty {@link AIPlayer} object.
     */
    AIPlayer createEasy(int id, int initialChips);

    /**
     * Create a <b>medium</b> difficulty AI player with the given id and initial chips.
     * @param id the player's id.
     * @param initialChips the amount of initial chips.
     * @return a medium difficulty {@link AIPlayer} object.
     */
    AIPlayer createMedium(int id, int initialChips);

    /**
     * Create a <b>hard</b> difficulty AI player with the given id initial chips.
     * A hard AI player is more likely to call or raise than a medium one.
     * @param id the player's id.
     * @param initialChips the amount of initial chips.
     * @return a hard difficulty {@link AIPlayer} object.
     */
    AIPlayer createHard(int id, int initialChips);

    /**
     * Create a custom AI player with the given id, initial chips, raising factor, difficulty modifier,
     * as well as custom call and raise chances for each combination type.
     * @param id the player's id.
     * @param initialChips the amount of initial chips.
     * @param raisingFactor the higher the value, the more chips the AI player will raise.
     * @param difficultyModifier the higher the value, the more likely the AI player is to call or raise.
     * @param callChance a function that maps a combination type to its base call chance.
     * @param raiseChance a function that maps a combination type to its base raise chance.
     * @return a custom difficulty {@link AIPlayer} object.
     */
    AIPlayer createCustom(
        int id,
        int initialChips, 
        double raisingFactor, 
        double difficultyModifier,
        Function<CombinationType, Double> callChance,
        Function<CombinationType, Double> raiseChance
    );

}
