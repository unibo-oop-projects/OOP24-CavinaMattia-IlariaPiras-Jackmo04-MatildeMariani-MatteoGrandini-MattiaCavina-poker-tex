package model.statistics.api;

import java.util.Optional;

import model.temp.CombinationType;

/**
 * Interface modelling general statistics for the game. 
 * The statistics that can be kept by implementations of this interface are:
 * <ul>
 * <li> Number of hands played
 * <li> Number of hands won
 * <li> Number of games played 
 * <li> Number of games won
 * <li> Best combination achieved
 * </ul>
 */
public interface GeneralStatistics extends Statistics {

    /**
     * Increment the number of hands played by 1.
     */
    public void incrementHandsPlayed();

    /**
     * Increment the number of hands won by 1.
     */
    public void incrementHandsWon();

    /**
     * Increment the number of games played by 1.
     */
    public void incrementGamesPlayed();

    /**
     * Increment the number of games won by 1.
     */
    public void incrementGamesWon();

    /**
     * If the given combination is better than the current best combination, 
     * set the given combination as the best combination. Otherwise, do nothing.
     * @param combination The candidate combination to be set as the best combination
     */
    public void setBestCombinationIfSo(CombinationType combination);

    /**
     * Returns the number of hands played.
     * @return The number of hands played
     */
    public int getNumOfHandsPlayed();

    /**
     * Returns the number of hands won.
     * @return The number of hands won
     */
    public int getNumOfHandsWon();

    /**
     * Returns the number of games played.
     * @return The number of games played
     */
    public int getNumOfGamesPlayed();

    /**
     * Returns the number of games won.
     * @return The number of games won
     */
    public int getNumOfGamesWon();

    /**
     * Returns an {@link Optional} describing the best combination achieved.
     * @return An {@link Optional} describing the best combination achieved
     */
    public Optional<CombinationType> getBestCombination();
}
