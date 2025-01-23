package model.statistics;

import java.util.Optional;

import model.statistics.api.Statistics;
import model.temp.CombinationType;

public class StatisticsImpl implements Statistics {
    private int numOfHandsPlayed;
    private int numOfHandsWon;
    private int numOfGamesPlayed;
    private int numOfGamesWon;
    private Optional<CombinationType> bestCombination;

    public StatisticsImpl() {
        this.numOfHandsPlayed = 0;
        this.numOfHandsWon = 0;
        this.numOfGamesPlayed = 0;
        this.numOfGamesWon = 0;
        this.bestCombination = Optional.empty();
    }

    public void incrementHandsPlayed() {
        this.numOfHandsPlayed++;
    }

    public void incrementHandsWon() {
        this.numOfHandsWon++;
    }

    public void incrementGamesPlayed() {
        this.numOfGamesPlayed++;
    }

    public void incrementGamesWon() {
        this.numOfGamesWon++;
    }

    public void setBestCombination(CombinationType combination) {
        this.bestCombination = Optional.of(combination);
    }

    public int getNumOfHandsPlayed() {
        return numOfHandsPlayed;
    }

    public int getNumOfHandsWon() {
        return numOfHandsWon;
    }

    public int getNumOfGamesPlayed() {
        return numOfGamesPlayed;
    }

    public int getNumOfGamesWon() {
        return numOfGamesWon;
    }

    public Optional<CombinationType> getBestCombination() {
        return bestCombination;
    }
}
