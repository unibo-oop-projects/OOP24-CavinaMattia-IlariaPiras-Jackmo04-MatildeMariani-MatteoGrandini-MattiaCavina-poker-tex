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
    void incrementHandsPlayed();

    /**
     * Increment the number of hands won by 1.
     */
    void incrementHandsWon();

    /**
     * Increment the number of games played by 1.
     */
    void incrementGamesPlayed();

    /**
     * Increment the number of games won by 1.
     */
    void incrementGamesWon();

    /**
     * Increment the number of hands played by the given amount.
     * @param increment The amount by which the number of hands played should be incremented
     */
    void addHandsPlayed(int increment);

    /**
     * Increment the number of hands won by the given amount.
     * @param increment The amount by which the number of hands won should be incremented
     */
    void addHandsWon(int increment);

    /**
     * Increment the number of games played by the given amount.
     * @param increment The amount by which the number of games played should be incremented
     */
    void addGamesPlayed(int increment);

    /**
     * Increment the number of games won by the given amount.
     * @param increment The amount by which the number of games won should be incremented
     */
    void addGamesWon(int increment);

    /**
     * If the given combination is better than the current best combination, 
     * set the given combination as the best combination. Otherwise, do nothing.
     * @param combination The candidate combination to be set as the best combination
     */
    void setBestCombinationIfSo(CombinationType combination);

    /**
     * Returns the number of hands played.
     * @return The number of hands played
     */
    int getNumOfHandsPlayed();

    /**
     * Returns the number of hands won.
     * @return The number of hands won
     */
    int getNumOfHandsWon();

    /**
     * Returns the number of games played.
     * @return The number of games played
     */
    int getNumOfGamesPlayed();

    /**
     * Returns the number of games won.
     * @return The number of games won
     */
    int getNumOfGamesWon();

    /**
     * Returns an optional containing the best combination ever achieved, 
     * or an empty optional if no combination has been achieved yet
     * @return an optional containing the best combination ever achieved
     */
    Optional<CombinationType> getBestCombination();
}
