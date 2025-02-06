package controller.statistics;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Interface for the statistics controller.
 */
public interface StatsController {

    /**
     * Returns a list of pairs of strings, where the first string is the name of the
     * statistic and the second string is the value of the statistic.
     * @return the list of statistics
     */
    List<ImmutablePair<String, String>> getStatistics();

    /**
     * Goes back to the main menu scene.
     */
    void goToMainMenuScene();

}
