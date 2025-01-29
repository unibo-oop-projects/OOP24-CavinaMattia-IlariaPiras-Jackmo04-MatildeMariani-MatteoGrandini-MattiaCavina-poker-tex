package model.statistics.api;

import java.util.Optional;

import model.combination.api.CombinationType;

/**
 * Interface modelling general statistics for the game. 
 * The statistics that can be kept by implementations of this interface are:
 * <ul>
 * <li> Number of hands played
 * <li> Number of hands won
 * <li> Hand win rate
 * <li> Number of games played 
 * <li> Number of games won
 * <li> Game win rate
 * <li> Best combination achieved
 * <li> Biggest chips win
 * </ul>
 */
public interface BasicStatistics extends Statistics {

    /**
     * Increment the number of hands played.
     * @param increment The amount by which the number of hands played should be incremented
     */
    void incrementHandsPlayed(int increment);

    /**
     * Increment the number of hands won.
     * @param increment The amount by which the number of hands won should be incremented
     */
    void incrementHandsWon(int increment);

    /**
     * Increment the number of games played.
     * @param increment The amount by which the number of games played should be incremented
     */
    void incrementGamesPlayed(int increment);

    /**
     * Increment the number of games won.
     * @param increment The amount by which the number of games won should be incremented
     */
    void incrementGamesWon(int increment);

    /**
     * Set the number of hands played to the given value.
     * @param value The new value for the number of hands played
     */
    void setHandsPlayed(int value);

    /**
     * Set the number of hands won to the given value.
     * @param value The new value for the number of hands won
     */
    void setHandsWon(int value);

    /**
     * Set the number of games played to the given value.
     * @param value The new value for the number of games played
     */
    void setGamesPlayed(int value);

    /**
     * Set the number of games won to the given value.
     * @param value The new value for the number of games won
     */
    void setGamesWon(int value);

    /**
     * If the given combination is better than the current best combination, 
     * set the given combination as the best combination. Otherwise, do nothing.
     * @param combination The candidate combination to be set as the best combination
     */
    void setBestCombinationIfSo(CombinationType combination);

    /**
     * If the given amount of chips is greater than the current recorded amount,
     * set the given amount as the best winnings. Otherwise, do nothing.
     * @param winnings The candidate amount of chips to be set as the best winnings
     */
    void setBiggestWinIfSo(int winnings);

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
     * or an empty optional if no combination has been achieved yet.
     * @return an optional containing the best combination ever achieved
     */
    Optional<CombinationType> getBestCombination();

    /**
     * Returns the greatest amount of chips ever won.
     * @return The greatest amount of chips ever won
     */
    int getBiggestWin();

    /**
     * Returns the hand win rate of the player, calculated as the number of hands won divided by the number of hands played.
     * @return The hand win rate of the player
     */
    double getHandWinRate();

    /**
     * Returns the win rate of the player, calculated as the number of games won divided by the number of games played.
     * @return The win rate of the player
     */
    double getGameWinRate();
}
