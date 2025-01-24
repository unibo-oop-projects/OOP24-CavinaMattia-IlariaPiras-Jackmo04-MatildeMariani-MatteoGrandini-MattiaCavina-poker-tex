package model.statistics.api;

/**
 * Interface for contributing to the game's statistics.
 * The classes that implement this interface will be able to update the statistics
 * by implementing the updateStatistics method, which will be called by a {@link StatisticsManager}.
 */
public interface StatisticsContributor<S extends Statistics> {

    /**
     * Method to update the game's statistics.
     * @param stats The statistics object to update.
     */
    void updateStatistics(S stats);
}
