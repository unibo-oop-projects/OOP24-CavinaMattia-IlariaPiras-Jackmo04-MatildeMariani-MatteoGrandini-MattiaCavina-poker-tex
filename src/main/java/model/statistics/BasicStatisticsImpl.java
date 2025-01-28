package model.statistics;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import model.statistics.api.BasicStatistics;
import model.combination.api.CombinationType;

/**
 * Class to store statistics of the game. The statistics kept by this implementation are:
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
 * Provides methods to update the aforementioned statistics.
 */
public class BasicStatisticsImpl implements BasicStatistics, Serializable {

    private static final long serialVersionUID = 2L;

    private int numOfHandsPlayed;
    private int numOfHandsWon;
    private int numOfGamesPlayed;
    private int numOfGamesWon;
    private int biggestWin;
    private CombinationType bestCombination;

    /**
     * Default constructor. Initializes all statistics to 0 and best combination to <i>null</i>.
     */
    public BasicStatisticsImpl() {
        this(0, 0, 0, 0, 0, null);
    }

    /**
     * Constructor to initialize the statistics with the given values.
     * @param numOfHandsPlayed The number of hands played
     * @param numOfHandsWon The number of hands won
     * @param numOfGamesPlayed The number of games played
     * @param numOfGamesWon The number of games won
     * @param biggestWin The biggest win
     * @param bestCombination The best combination achieved
     */
    public BasicStatisticsImpl(
        int numOfHandsPlayed, 
        int numOfHandsWon, 
        int numOfGamesPlayed, 
        int numOfGamesWon,
        int biggestWin,
        CombinationType bestCombination
    ) {
        this.numOfHandsPlayed = numOfHandsPlayed;
        this.numOfHandsWon = numOfHandsWon;
        this.numOfGamesPlayed = numOfGamesPlayed;
        this.numOfGamesWon = numOfGamesWon;
        this.biggestWin = biggestWin;
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
    public void setBiggestWinIfSo(int winnings) {
        if (winnings > this.biggestWin) {
            this.biggestWin = winnings;
        }
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
        return this.numOfHandsPlayed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumOfHandsWon() {
        return this.numOfHandsWon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumOfGamesPlayed() {
        return this.numOfGamesPlayed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumOfGamesWon() {
        return this.numOfGamesWon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBiggestWin() {
        return this.biggestWin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CombinationType> getBestCombination() {
        return Optional.ofNullable(this.bestCombination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHandWinRate() {
        return this.numOfHandsPlayed == 0 ? 0 : (double) this.numOfHandsWon / this.numOfHandsPlayed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getGameWinRate() {
        return this.numOfGamesPlayed == 0 ? 0 : (double) this.numOfGamesWon / this.numOfGamesPlayed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.numOfHandsPlayed = 0;
        this.numOfHandsWon = 0;
        this.numOfGamesPlayed = 0;
        this.numOfGamesWon = 0;
        this.biggestWin = 0;
        this.bestCombination = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numOfHandsPlayed;
        result = prime * result + numOfHandsWon;
        result = prime * result + numOfGamesPlayed;
        result = prime * result + numOfGamesWon;
        result = prime * result + biggestWin;
        result = prime * result + ((bestCombination == null) ? 0 : bestCombination.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BasicStatisticsImpl other = (BasicStatisticsImpl) obj;
        if (numOfHandsPlayed != other.numOfHandsPlayed)
            return false;
        if (numOfHandsWon != other.numOfHandsWon)
            return false;
        if (numOfGamesPlayed != other.numOfGamesPlayed)
            return false;
        if (numOfGamesWon != other.numOfGamesWon)
            return false;
        if (biggestWin != other.biggestWin)
            return false;
        if (bestCombination != other.bestCombination)
            return false;
        return true;
    }

}
