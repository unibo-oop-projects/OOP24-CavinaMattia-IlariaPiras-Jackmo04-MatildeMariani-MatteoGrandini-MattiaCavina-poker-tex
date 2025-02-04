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
public class GameFactoryImpl implements GameFactory{

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
    public Game easyGame(GameController controller, int initialChips) {
        return new AbstractGame(controller, initialChips) {

            @Override
            protected Player getAIPlayer(final int id, final int initialChips) {
                return playerFactory.easy(id, initialChips);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game mediumGame(GameController controller, int initialChips) {
        return new AbstractGame(controller, initialChips) {

            @Override
            protected Player getAIPlayer(final int id, final int initialChips) {
                return playerFactory.medium(id, initialChips);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game hardGame(GameController controller, int initialChips) {
        return new AbstractGame(controller, initialChips) {

            @Override
            protected Player getAIPlayer(final int id, final int initialChips) {
                return playerFactory.hard(id, initialChips);
            }

        };
    }

}
