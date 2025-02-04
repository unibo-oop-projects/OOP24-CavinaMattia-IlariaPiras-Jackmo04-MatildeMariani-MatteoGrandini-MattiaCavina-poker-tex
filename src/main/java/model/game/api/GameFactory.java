package model.game.api;

import controller.game.api.GameController;
import model.player.ai.api.AIPlayer;

/**
 * Factory to create a {@link Game}. 
 * It can be of three types depending on the difficulty level of the {@link AIPlayer}s. 
 */
public interface GameFactory {

    /**
     * Returns a new {@link Game} with easy difficulty level.
     * @param controller the game controller.
     * @param initialChips initial amount of chips of players.
     * @return a new game.
     */
    Game easyGame(GameController controller, int initialChips);

    /**
     * Returns a new {@link Game} with medium difficulty level.
     * @param controller the game controller.
     * @param initialChips initial amount of chips of players.
     * @return a new game.
     */
    Game mediumGame(GameController controller, int initialChips);

    /**
     * Returns a new {@link Game} with hard difficulty level.
     * @param controller the game controller.
     * @param initialChips initial amount of chips of players.
     * @return a new game.
     */
    Game hardGame(GameController controller, int initialChips);

}
