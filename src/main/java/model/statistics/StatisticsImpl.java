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
     * {@inheritDoc}
     */
    public void incrementHandsPlayed() {
        this.numOfHandsPlayed++;
    }

    /**
     * {@inheritDoc}
     */
    public void incrementHandsWon() {
        this.numOfHandsWon++;
    }

    /**
     * {@inheritDoc}
     */
    public void incrementGamesPlayed() {
        this.numOfGamesPlayed++;
    }

    /**
     * {@inheritDoc}
     */
    public void incrementGamesWon() {
        this.numOfGamesWon++;
    }

    /**
     * {@inheritDoc}
     */
    public void setBestCombinationIfSo(CombinationType combination) {
        if (this.bestCombination.isEmpty() || combination.compareTo(this.bestCombination.get()) > 0) {
            this.bestCombination = Optional.of(combination);
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getNumOfHandsPlayed() {
        return numOfHandsPlayed;
    }

    /**
     * {@inheritDoc}
     */
    public int getNumOfHandsWon() {
        return numOfHandsWon;
    }

    /**
     * {@inheritDoc}
     */
    public int getNumOfGamesPlayed() {
        return numOfGamesPlayed;
    }

    /**
     * {@inheritDoc}
     */
    public int getNumOfGamesWon() {
        return numOfGamesWon;
    }

    /**
     * {@inheritDoc}
     */
    public Optional<CombinationType> getBestCombination() {
        return bestCombination;
    }
}
