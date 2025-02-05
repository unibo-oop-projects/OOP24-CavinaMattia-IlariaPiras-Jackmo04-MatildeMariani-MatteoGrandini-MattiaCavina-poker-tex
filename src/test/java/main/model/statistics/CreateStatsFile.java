package main.model.statistics;

import model.combination.api.CombinationType;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;

/**
 * Test class used to create a file containing statistics.
 */
public final class CreateStatsFile {

    private static final String FILE_NAME = "stats.bin";
    private static final int HANDS_PLAYED = 10;
    private static final int HANDS_WON = 5;
    private static final int GAMES_PLAYED = 3;
    private static final int GAMES_WON = 1;
    private static final int BEST_WINNINGS = 0;
    private static final CombinationType BEST_COMBINATION = CombinationType.HIGH_CARD;

    private CreateStatsFile() {
    }

    /**
     * Main
     * @param args unused
     */
    public static void main(final String[] args) {
        final var stats = new BasicStatisticsImpl();
        stats.setHandsPlayed(HANDS_PLAYED);
        stats.setHandsWon(HANDS_WON);
        stats.setGamesPlayed(GAMES_PLAYED);
        stats.setGamesWon(GAMES_WON);
        stats.setBestCombinationIfSo(BEST_COMBINATION);
        stats.setBiggestWinIfSo(BEST_WINNINGS);
        final var statsManager = new StatisticsManagerImpl<BasicStatisticsImpl>(stats);
        final var fileName = FILE_NAME;
        // Save the statistics
        try {
            statsManager.saveStatistics(fileName);
        } catch (Exception e) {
            System.err.println("Failed to save statistics to file");
        }
    }
}
