package model.statistics.api;

import java.util.Map;

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

    /**
     * Returns a map containing entries organized as follows:
     * <ul>
     * <li><b>Key:</b> The name of the statistic
     * <li><b>Value:</b> The string representation of the stat value
     * </ul>
     * For each statistic kept by the implementation
     * @return A map containing all statistics kept by the class
     */
    Map<String,String> getAsMap();
}
