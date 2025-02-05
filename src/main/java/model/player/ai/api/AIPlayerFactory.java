package model.player.ai.api;

import java.util.function.Function;

import model.combination.api.CombinationType;

/**
 * Factory for creating AI players.
 * There are three levels of AI players: easy, medium, and hard.
 */
public interface AIPlayerFactory {

    /**
     * Create an easy difficulty AI player with the given initial chips and role.
     * An easy AI player is less likely to call or raise than a medium one.
     * @param id the player's id
     * @param initialChips initial chips
     * @return an {@link AIPlayer} object with easy difficulty
     */
    AIPlayer easy(int id, int initialChips);

    /**
     * Create a medium difficulty AI player with the given initial chips and role.
     * @param id the player's id
     * @param initialChips initial chips
     * @return an {@link AIPlayer} object with medium difficulty
     */
    AIPlayer medium(int id, int initialChips);

    /**
     * Create a hard difficulty AI player with the given initial chips and role.
     * A hard AI player is more likely to call or raise than a medium one.
     * @param id the player's id
     * @param initialChips initial chips
     * @return an {@link AIPlayer} object with hard difficulty
     */
    AIPlayer hard(int id, int initialChips);

    /**
     * Create a custom difficulty AI player with the given initial chips, role, raising factor, and difficulty modifier.
     * @param id the player's id
     * @param initialChips initial chips
     * @param raisingFactor raising factor
     * @param difficultyModifier the higher the value, the more likely the AI player is to call or raise
     * @param callChance a function that maps a combination type to its base call chance
     * @param raiseChance a function that maps a combination type to its base raise chance
     * @return an {@link AIPlayer} object with custom difficulty
     */
    AIPlayer custom(
        int id,
        int initialChips, 
        double raisingFactor, 
        double difficultyModifier,
        Function<CombinationType, Double> callChance,
        Function<CombinationType, Double> raiseChance
    );

}
