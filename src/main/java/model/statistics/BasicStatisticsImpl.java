package model.statistics;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import model.statistics.api.BasicStatistics;
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
public class BasicStatisticsImpl implements BasicStatistics, Serializable {

    private static final long serialVersionUID = 1L;

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
    public BasicStatisticsImpl() {
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
    public BasicStatisticsImpl(
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
    @Override
    public void incrementHandsPlayed(int increment) {
        this.numOfHandsPlayed += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementHandsWon(int increment) {
        this.numOfHandsWon += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementGamesPlayed(int increment) {
        this.numOfGamesPlayed += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementGamesWon(int increment) {
        this.numOfGamesWon += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandsPlayed(int value) {
        this.numOfHandsPlayed = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandsWon(int value) {
        this.numOfHandsWon = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGamesPlayed(int value) {
        this.numOfGamesPlayed = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGamesWon(int value) {
        this.numOfGamesWon = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBestCombinationIfSo(CombinationType combination) {
        Objects.requireNonNull(combination);
        if (this.bestCombination == null || combination.compareTo(this.bestCombination) > 0) {
            this.bestCombination = combination;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumOfHandsPlayed() {
        return numOfHandsPlayed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumOfHandsWon() {
        return numOfHandsWon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumOfGamesPlayed() {
        return numOfGamesPlayed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumOfGamesWon() {
        return numOfGamesWon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CombinationType> getBestCombination() {
        return Optional.ofNullable(this.bestCombination);
    }

    @Override
    public void reset() {
        this.numOfHandsPlayed = 0;
        this.numOfHandsWon = 0;
        this.numOfGamesPlayed = 0;
        this.numOfGamesWon = 0;
        this.bestCombination = null;
    }

}
