package model.game;

import model.game.api.Game;
import model.game.api.GameFactory;
import model.player.ai.AIPlayerFactoryImpl;
import model.player.ai.api.AIPlayer;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Player;
import model.player.api.Role;

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
    public Game easyGame(int initialChips) {
        return new AbstractGame(initialChips) {

            @Override
            protected Player getAIPlayer(final int initialChips, final Role startingRole) {
                return playerFactory.easy(initialChips, startingRole);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game mediumGame(int initialChips) {
        return new AbstractGame(initialChips) {

            @Override
            protected Player getAIPlayer(int initialChips, Role startingRole) {
                return playerFactory.medium(initialChips, startingRole);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game hardGame(int initialChips) {
        return new AbstractGame(initialChips) {

            @Override
            protected Player getAIPlayer(int initialChips, Role startingRole) {
                return playerFactory.hard(initialChips, startingRole);
            }

        };
    }

}
