package pokertexas.controller.statistics;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import pokertexas.controller.scene.SceneController;

/**
 * Interface for the statistics controller.
 * Manages the retrieval of the statistics form the statistics manager and the
 * resetting of the statistics.
 */
public interface StatisticsController extends SceneController {

    /**
     * Returns a list of pairs of strings, where the first string is the name of the
     * statistic and the second string is the value of the statistic.
     * @return the list of statistics
     */
    List<ImmutablePair<String, String>> getStatistics();

    /**
     * Resets the statistics and saves the changes.
     */
    void resetStatistics();

}
