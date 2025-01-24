package model.player.ai.api;

import model.player.api.Role;

/**
 * Factory for creating AI players.
 * There are three levels of AI players: easy, medium, and hard.
 */
public interface AIPlayerFactory {

    /**
     * Create an easy difficulty AI player with the given initial chips and role.
     * An easy AI player is less likely to call or raise than a medium one.
     * @param initialChips initial chips
     * @param initialRole initial role
     * @return an {@link AIPlayer} object with easy difficulty
     */
    AIPlayer easy(int initialChips, Role initialRole);

    /**
     * Create a medium difficulty AI player with the given initial chips and role.
     * @param initialChips initial chips
     * @param initialRole initial role
     * @return an {@link AIPlayer} object with medium difficulty
     */
    AIPlayer medium(int initialChips, Role initialRole);

    /**
     * Create a hard difficulty AI player with the given initial chips and role.
     * A hard AI player is more likely to call or raise than a medium one.
     * @param initialChips initial chips
     * @param initialRole initial role
     * @return an {@link AIPlayer} object with hard difficulty
     */
    AIPlayer hard(int initialChips, Role initialRole);

}
