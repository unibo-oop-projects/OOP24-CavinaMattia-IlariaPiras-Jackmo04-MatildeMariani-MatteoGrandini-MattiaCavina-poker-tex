package controller.statistics;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import controller.scene.SceneController;

/**
 * Interface for the statistics controller.
 */
public interface StatisticsController extends SceneController {

    /**
     * Returns a list of pairs of strings, where the first string is the name of the
     * statistic and the second string is the value of the statistic.
     * @return the list of statistics
     */
    List<ImmutablePair<String, String>> getStatistics();

}
