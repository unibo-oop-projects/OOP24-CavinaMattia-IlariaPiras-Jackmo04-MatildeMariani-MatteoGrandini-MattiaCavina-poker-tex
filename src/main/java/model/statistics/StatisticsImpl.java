package model.statistics;

import java.util.Optional;

import model.statistics.api.Statistics;
import model.temp.CombinationType;

/**
 * Class to store statistics of the game. The statistics kept by this implementation are:
 * <ul>
 * <li> Number of hands played
 * <li> Number of hands won
 * <li> Number of games played 
 * <li> Number of games won
 * <li> Best combination achieved
 * </ul>
 * Provides methods to update the aforementioned statistics.
 */
public class StatisticsImpl implements Statistics {

    private int numOfHandsPlayed;
    private int numOfHandsWon;
    private int numOfGamesPlayed;
    private int numOfGamesWon;
    private Optional<CombinationType> bestCombination;

    /**
     * The statistics kept by this implementation are:
     * <ul>
     * <li> Number of hands played
     * <li> Number of hands won
     * <li> Number of games played 
     * <li> Number of games won
     * <li> Best combination achieved
     * </ul>
     * Initialized to 0 for numbers and to {@link Optional#empty()} for non-numbers.
     */
    public StatisticsImpl() {
        this(0, 0, 0, 0, Optional.empty());
    }

    /**
     * The statistics kept by this implementation are:
     * <ul>
     * <li> Number of hands played
     * <li> Number of hands won
     * <li> Number of games played 
     * <li> Number of games won
     * <li> Best combination achieved
     * </ul>
     * @param numOfHandsPlayed Initial value for number of hands played
     * @param numOfHandsWon Initial value for number of hands won
     * @param numOfGamesPlayed Initial value for number of games played
     * @param numOfGamesWon Initial value for number of games won
     * @param bestCombination Initial value for best combination achieved
     */
    public StatisticsImpl(
        int numOfHandsPlayed, 
        int numOfHandsWon, 
        int numOfGamesPlayed, 
        int numOfGamesWon,
        Optional<CombinationType> bestCombination
    ) {
        this.numOfHandsPlayed = numOfHandsPlayed;
        this.numOfHandsWon = numOfHandsWon;
        this.numOfGamesPlayed = numOfGamesPlayed;
        this.numOfGamesWon = numOfGamesWon;
        this.bestCombination = bestCombination;
    }

    /**
     * Increment the number of hands played by 1.
     */
    public void incrementHandsPlayed() {
        this.numOfHandsPlayed++;
    }

    /**
     * Increment the number of hands won by 1.
     */
    public void incrementHandsWon() {
        this.numOfHandsWon++;
    }

    /**
     * Increment the number of games played by 1.
     */
    public void incrementGamesPlayed() {
        this.numOfGamesPlayed++;
    }

    /**
     * Increment the number of games won by 1.
     */
    public void incrementGamesWon() {
        this.numOfGamesWon++;
    }

    /**
     * If the given combination is better than the current best combination, 
     * set the given combination as the best combination. Otherwise, do nothing.
     * @param combination The candidate combination to be set as the best combination
     */
    public void setBestCombinationIfSo(CombinationType combination) {
        if (this.bestCombination.isEmpty() || combination.compareTo(this.bestCombination.get()) > 0) {
            this.bestCombination = Optional.of(combination);
        }
    }

    /**
     * Returns the number of hands played.
     * @return The number of hands played
     */
    public int getNumOfHandsPlayed() {
        return numOfHandsPlayed;
    }

    /**
     * Returns the number of hands won.
     * @return The number of hands won
     */
    public int getNumOfHandsWon() {
        return numOfHandsWon;
    }

    /**
     * Returns the number of games played.
     * @return The number of games played
     */
    public int getNumOfGamesPlayed() {
        return numOfGamesPlayed;
    }

    /**
     * Returns the number of games won.
     * @return The number of games won
     */
    public int getNumOfGamesWon() {
        return numOfGamesWon;
    }

    /**
     * Returns an {@link Optional} describing the best combination achieved.
     * @return An {@link Optional} describing the best combination achieved
     */
    public Optional<CombinationType> getBestCombination() {
        return bestCombination;
    }
}
