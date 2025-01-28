package controller;

import java.util.Map;

/**
 * The main controller of the application.
 */
public interface MainController {

    /**
     * Returns a map containing the statistics of the game.
     * The statistics are loaded from a file in the $HOME/poker directory.
     * @return A map the statistics of the game.
     */
    Map<String, String> getStatistics();
}
