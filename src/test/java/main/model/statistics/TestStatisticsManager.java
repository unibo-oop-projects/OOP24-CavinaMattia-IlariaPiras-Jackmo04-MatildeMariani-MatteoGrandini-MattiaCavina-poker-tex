package main.model.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;

import org.junit.jupiter.api.Test;

import model.statistics.GeneralStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import model.statistics.api.StatisticsContributor;

public class TestStatisticsManager {

    private static final int GAMES_PLAYED = 1;
    private static final int HANDS_PLAYED = 20;

    /**
     * Class to simulate a StatisticsContributor that manages hands
     */
    private class HandManager implements StatisticsContributor<GeneralStatisticsImpl> {

        private int handsPlayed = 0;

        public void playHand() {
            // Logic to play a hand
            handsPlayed++;
        }

        @Override
        public void updateStatistics(GeneralStatisticsImpl stats) {
            stats.setHandsPlayed(handsPlayed);
        }
    
    }

    /**
     * Class to simulate a StatisticsContributor that manages games
     */
    private class GameManager implements StatisticsContributor<GeneralStatisticsImpl> {

        private int gamesPlayed = 0;

        public void playGame() {
            // Logic to play a game
            gamesPlayed++;
        }

        @Override
        public void updateStatistics(GeneralStatisticsImpl stats) {
            stats.setGamesPlayed(gamesPlayed);
        }
    
    }

    @Test
    public void testSingleContributor() {
        final var stats = new GeneralStatisticsImpl();
        final var statsManager = new StatisticsManagerImpl<GeneralStatisticsImpl>(stats);
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

    @Test
    public void testMultipleContributors() {
        final var stats = new GeneralStatisticsImpl();
        final var statsManager = new StatisticsManagerImpl<GeneralStatisticsImpl>(stats);
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

    @Test
    public void testSaveAndLoad() {
        final var stats = new GeneralStatisticsImpl();
        final var statsManager = new StatisticsManagerImpl<GeneralStatisticsImpl>(stats);
        statsManager.addContributor(s -> s.setHandsPlayed(HANDS_PLAYED));
        statsManager.addContributor(s -> s.setGamesPlayed(GAMES_PLAYED));
        statsManager.updateTotalStatistics();
        final var FILE_NAME = "stats.bin";
        // Save the statistics
        try {
            statsManager.saveStatistics(FILE_NAME);
        } catch (Exception e) {
            fail(e);
        }
        // Reset the statistics
        statsManager.getTotalStatistics().reset();
        // Load the statistics
        try {
            statsManager.loadStatistics(FILE_NAME);
        } catch (Exception e) {
            fail(e);
        }
        // After loading the statistics
        assertEquals(HANDS_PLAYED, statsManager.getTotalStatistics().getNumOfHandsPlayed());
        assertEquals(GAMES_PLAYED, statsManager.getTotalStatistics().getNumOfGamesPlayed());
    }

}
