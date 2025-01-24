package model.statistics;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import model.statistics.api.GeneralStatistics;
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
public class GeneralStatisticsImpl implements GeneralStatistics, Serializable {

    private int numOfHandsPlayed;
    private int numOfHandsWon;
    private int numOfGamesPlayed;
    private int numOfGamesWon;
    private CombinationType bestCombination;

    /**
     * The statistics kept by this implementation are:
     * <ul>
     * <li> Number of hands played
     * <li> Number of hands won
     * <li> Number of games played 
     * <li> Number of games won
     * <li> Best combination achieved
     * </ul>
     * Initialized to 0 for numbers and to <i>null</i> for non-numbers.
     */
    public GeneralStatisticsImpl() {
        this(0, 0, 0, 0, null);
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
     * @param bestCombination Initial value for best combination achieved or <i>null</i> if none
     */
    public GeneralStatisticsImpl(
        int numOfHandsPlayed, 
        int numOfHandsWon, 
        int numOfGamesPlayed, 
        int numOfGamesWon,
        CombinationType bestCombination
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
        Objects.requireNonNull(combination);
        if (this.bestCombination == null || combination.compareTo(this.bestCombination) > 0) {
            this.bestCombination = combination;
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
        return Optional.ofNullable(this.bestCombination);
    }
}
