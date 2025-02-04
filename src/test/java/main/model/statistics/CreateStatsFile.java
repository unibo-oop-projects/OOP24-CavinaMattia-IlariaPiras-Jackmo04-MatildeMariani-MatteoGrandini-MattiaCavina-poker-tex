package main.model.statistics;

import model.combination.api.CombinationType;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;

public class CreateStatsFile {

    public static void main(String[] args) {
        final var stats = new BasicStatisticsImpl();
        stats.setHandsPlayed(10);
        stats.setHandsWon(5);
        stats.setGamesPlayed(3);
        stats.setGamesWon(1);
        stats.setBestCombinationIfSo(CombinationType.HIGH_CARD);
        stats.setBiggestWinIfSo(0);
        final var statsManager = new StatisticsManagerImpl<BasicStatisticsImpl>(stats);
        final var fileName = "stats.bin";
        // Save the statistics
        try {
            statsManager.saveStatistics(fileName);
        } catch (Exception e) {
            System.err.println("Failed to save statistics to file");
        }
    }
}
