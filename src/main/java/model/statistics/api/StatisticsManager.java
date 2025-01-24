package model.statistics.api;

/**
 * Interface for managing {@link Statistics} coming from different sources.
 * Such sources are called contributors and they must implement the {@link StatisticsContributor} interface.
 * @param <S> The type of statistics to manage
 */
public interface StatisticsManager<S extends Statistics> {

    /**
     * Adds a contributor to the manager.
     * The contributor must be contributing to the same type of statistics as the manager.
     * @param contributor The contributor to add.
     */
    void addContributor(StatisticsContributor<S> contributor);

    /**
     * Updates the total statistics by calling the <b>updateStatistics</b> method of all contributors added.
     * The frequency at which this method should be called depends on the statistics being managed
     * and the implementation of the contributors.
     */
    void updateTotalStatistics();

}
