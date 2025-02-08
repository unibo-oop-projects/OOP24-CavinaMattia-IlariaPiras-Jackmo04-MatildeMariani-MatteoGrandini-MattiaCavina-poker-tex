package model.statistics.api;

import java.io.IOException;
import java.util.Collection;

/**
 * Interface for managing {@link Statistics} coming from different sources.
 * Such sources are called contributors and they must implement the {@link StatisticsContributor} interface.
 * @param <S> The type of statistics to manage
 */
public interface StatisticsManager<S extends Statistics> {

    /**
     * Returns the total statistics managed by the manager.
     * @return The total statistics
     */
    S getTotalStatistics();

    /**
     * Adds a contributor to the manager.
     * The contributor must be contributing to the same type of statistics as the manager.
     * @param contributor The contributor to add
     */
    void addContributor(StatisticsContributor<S> contributor);

    /**
     * Adds all given contributors to the manager.
     * All contributors must be contributing to the same type of statistics as the manager.
     * @param contributors The collection of contributors to add
     */
    void addAllContributors(Collection<StatisticsContributor<S>> contributors);

    /**
     * Updates the total statistics by calling the <b>updateStatistics</b> method of all contributors added.
     * The frequency at which this method should be called depends on the statistics being managed
     * and the implementation of the contributors.
     */
    void updateTotalStatistics();

    /**
     * Saves the total statistics to the specified file.
     * <p><b>Note:</b> The {@link Statistics} class used must implement the {@link java.io.Serializable} interface.
     * @param fileName The name of the file to save the statistics to
     * @throws IOException If an I/O error occurs while saving the statistics
     */
    void saveStatistics(String fileName) throws IOException;

    /**
     * Loads the total statistics from the specified file.
     * <p><b>Note:</b> The {@link Statistics} class used must implement the {@link java.io.Serializable} interface.
     * @param fileName The name of the file to load the statistics from
     * @throws IOException If an I/O error occurs while loading the statistics
     * @throws ClassNotFoundException If the class of the serialized object cannot be found
     */
    void loadStatistics(String fileName) throws IOException, ClassNotFoundException;

}
