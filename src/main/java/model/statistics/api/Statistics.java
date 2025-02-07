package model.statistics.api;

/**
 * Interface that represents a statistics containing object.
 * Designed to be extended by other interfaces modeling specific statistics.
 * @see BasicStatistics Example of an extension of this interface.
 */
public interface Statistics {

    /**
     * Resets all statistics to their default values.
     */
    void reset();

}
