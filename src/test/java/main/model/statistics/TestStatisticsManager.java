package main.model.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import model.statistics.api.StatisticsContributor;

/**
 * Tests for a StatisticsManager managing BasicStatistics.
 */
class TestStatisticsManager {

    private static final int GAMES_PLAYED = 1;
    private static final int HANDS_PLAYED = 20;

    /**
     * Class to simulate a StatisticsContributor that manages hands.
     */
    private final class HandManager implements StatisticsContributor<BasicStatisticsImpl> {

        private int handsPlayed;

        public void playHand() {
            // Logic to play a hand
            handsPlayed++;
        }

        @Override
        public void updateStatistics(final BasicStatisticsImpl stats) {
            stats.setHandsPlayed(handsPlayed);
        }

    }

    /**
     * Class to simulate a StatisticsContributor that manages games.
     */
    private final class GameManager implements StatisticsContributor<BasicStatisticsImpl> {

        private int gamesPlayed;

        public void playGame() {
            // Logic to play a game
            gamesPlayed++;
        }

        @Override
        public void updateStatistics(final BasicStatisticsImpl stats) {
            stats.setGamesPlayed(gamesPlayed);
        }

    }

    /**
     * Test a single contributor updating the statistics.
     */
    @Test
    void testSingleContributor() {
        final var stats = new BasicStatisticsImpl();
        final var statsManager = new StatisticsManagerImpl<BasicStatisticsImpl>(stats);
        final var handManager = new HandManager();
        statsManager.addContributor(handManager);
        // Before playing any hand
        assertEquals(0, stats.getNumOfHandsPlayed());
        // Play a hand
        handManager.playHand();
        // Update the statistics
        statsManager.updateTotalStatistics();
        // After playing a hand
        assertEquals(1, stats.getNumOfHandsPlayed());
    }

    /**
     * Test multiple contributors updating the statistics.
     */
    @Test
    void testMultipleContributors() {
        final var stats = new BasicStatisticsImpl();
        final var statsManager = new StatisticsManagerImpl<BasicStatisticsImpl>(stats);
        final var handManager = new HandManager();
        final var gameManager = new GameManager();
        statsManager.addAllContributors(Set.of(handManager, gameManager));
        // Before playing any hand or game
        assertEquals(0, stats.getNumOfHandsPlayed());
        assertEquals(0, stats.getNumOfGamesPlayed());
        // Play a game
        gameManager.playGame();
        // Play multiple hands
        for (int i = 0; i < HANDS_PLAYED; i++) {
            handManager.playHand();
        }
        // Update the statistics
        statsManager.updateTotalStatistics();
        // After playing a hand and a game
        assertEquals(HANDS_PLAYED, stats.getNumOfHandsPlayed());
        assertEquals(1, stats.getNumOfGamesPlayed());
    }

    /**
     * Test saving and loading the statistics.
     */
    @Test
    void testSaveAndLoad() {
        final var stats = new BasicStatisticsImpl();
        final var statsManager = new StatisticsManagerImpl<BasicStatisticsImpl>(stats);
        statsManager.addContributor(s -> s.setHandsPlayed(HANDS_PLAYED));
        statsManager.addContributor(s -> s.setGamesPlayed(GAMES_PLAYED));
        statsManager.updateTotalStatistics();
        final var fileName = "stats.ser";
        // Save the statistics
        try {
            statsManager.saveStatistics(fileName);
        } catch (IOException e) {
            fail(e);
        }
        // Create a new statistics manager with a new statistics object (as if the program was restarted)
        final var newStats = new BasicStatisticsImpl();
        final var newStatsManager = new StatisticsManagerImpl<BasicStatisticsImpl>(newStats);
        // Load the old statistics from file to the new statistics manager
        try {
            newStatsManager.loadStatistics(fileName);
        } catch (IOException | ClassNotFoundException e) {
            fail(e);
        }
        // After loading the statistics
        assertEquals(stats, newStatsManager.getTotalStatistics());
    }

}
