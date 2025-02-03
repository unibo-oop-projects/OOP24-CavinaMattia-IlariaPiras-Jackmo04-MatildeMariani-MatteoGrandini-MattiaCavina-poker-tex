package model.statistics;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import commons.Pair;
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
        final int numOfHandsPlayed, 
        final int numOfHandsWon, 
        final int numOfGamesPlayed, 
        final int numOfGamesWon,
        final int biggestWin,
        final CombinationType bestCombination
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
    public void incrementHandsPlayed(final int increment) {
        this.numOfHandsPlayed += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementHandsWon(final int increment) {
        this.numOfHandsWon += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementGamesPlayed(final int increment) {
        this.numOfGamesPlayed += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementGamesWon(final int increment) {
        this.numOfGamesWon += increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandsPlayed(final int value) {
        this.numOfHandsPlayed = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandsWon(final int value) {
        this.numOfHandsWon = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGamesPlayed(final int value) {
        this.numOfGamesPlayed = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGamesWon(final int value) {
        this.numOfGamesWon = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBiggestWinIfSo(final int winnings) {
        this.biggestWin = Math.max(this.biggestWin, winnings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBestCombinationIfSo(final CombinationType combination) {
        if (this.bestCombination == null || combination != null && combination.compareTo(this.bestCombination) > 0) {
            this.bestCombination = combination;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void append(final BasicStatistics other) {
        this.incrementHandsPlayed(other.getNumOfHandsPlayed());
        this.incrementHandsWon(other.getNumOfHandsWon());
        this.incrementGamesPlayed(other.getNumOfGamesPlayed());
        this.incrementGamesWon(other.getNumOfGamesWon());
        this.setBiggestWinIfSo(other.getBiggestWin());
        this.setBestCombinationIfSo(other.getBestCombination().orElse(null));
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

    /**
     * Hash code default method.
     */
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

    /**
     * Equals for all fields.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BasicStatisticsImpl other = (BasicStatisticsImpl) obj;
        if (numOfHandsPlayed != other.numOfHandsPlayed) {
            return false;
        }
        if (numOfHandsWon != other.numOfHandsWon) {
            return false;
        }
        if (numOfGamesPlayed != other.numOfGamesPlayed) {
            return false;
        }
        if (numOfGamesWon != other.numOfGamesWon) {
            return false;
        }
        if (biggestWin != other.biggestWin) {
            return false;
        }
        if (bestCombination != other.bestCombination) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, String>> getAsList() {
        return List.of(
            new Pair<>("Hands played", String.valueOf(this.getNumOfHandsPlayed())),
            new Pair<>("Hands won", String.valueOf(this.getNumOfHandsWon())),
            new Pair<>("Games played", String.valueOf(this.getNumOfGamesPlayed())),
            new Pair<>("Games won", String.valueOf(this.getNumOfGamesWon())),
            new Pair<>("Best Combination", this.getBestCombination().map(CombinationType::name).orElse("None")),
            new Pair<>("Biggest win", String.valueOf(this.getBiggestWin())),
            new Pair<>("Hands win rate", String.valueOf(this.getHandWinRate())),
            new Pair<>("Games win rate", String.valueOf(this.getGameWinRate()))
        );
    }

}
