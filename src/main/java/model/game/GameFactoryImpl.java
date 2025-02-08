package model.game;

import controller.game.api.GameController;
import model.game.api.Game;
import model.game.api.GameFactory;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayer;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Player;

/**
 * Implementation of the {@link GameFactory} interface.
 * Provides methods to create a {@link Game} of different difficulty level, implementing 
 * accordingly the getAIPlayer method to create an {@link AIPlayer} of the specified difficulty.
 */
public final class GameFactoryImpl implements GameFactory {

    private final AIPlayerFactory playerFactory;

    /**
     * Constructor for the GameFactoryImpl class.
     */
    public GameFactoryImpl() {
        this.playerFactory = new AIPlayerFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game easyGame(final GameController controller, final int initialChips) {
        return new AbstractGame(controller, initialChips) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected Player getAIPlayer(final int id, final int initialChips) {
                return playerFactory.createEasy(id, initialChips);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game mediumGame(final GameController controller, final int initialChips) {
        return new AbstractGame(controller, initialChips) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected Player getAIPlayer(final int id, final int initialChips) {
                return playerFactory.createMedium(id, initialChips);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game hardGame(final GameController controller, final int initialChips) {
        return new AbstractGame(controller, initialChips) {

            /**
             * {@inheritDoc}
             */
            @Override
            protected Player getAIPlayer(final int id, final int initialChips) {
                return playerFactory.createHard(id, initialChips);
            }

        };
    }

}
