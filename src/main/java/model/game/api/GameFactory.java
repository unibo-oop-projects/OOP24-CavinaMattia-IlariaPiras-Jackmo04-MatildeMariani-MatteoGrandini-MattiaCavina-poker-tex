package model.game.api;

import model.player.ai.api.AIPlayer;

/**
 * Factory to create a {@link Game}. 
 * It can be of three types depending on the difficulty level of the {@link AIPlayer}s. 
 */
public interface GameFactory {

    /**
     * Returns a new Game with easy difficulty level.
     * @param initialChips initial amount of chips of players.
     * @return a new game.
     */
    Game easyGame(int initialChips);

    /**
     * Returns a new Game with medium difficulty level.
     * @param initialChips initial amount of chips of players.
     * @return a new game.
     */
    Game mediumGame(int initialChips);

    /**
     * Returns a new Game with hard difficulty level.
     * @param initialChips initial amount of chips of players.
     * @return a new game.
     */
    Game hardGame(int initialChips);

}
