package main.model.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.statistics.BasicStatisticsImpl;
import model.statistics.api.BasicStatistics;
import model.combination.api.CombinationType;

/**
 * Tests for the BasicStatistics implementation.
 */
public class TestBasicStatistics {

    private static final int GAMES_WON_8 = 8;
    private static final int GAMES_PLAYED_10 = 10;
    private static final int HANDS_WON_5 = 5;
    private static final int HANDS_PLAYED_10 = 10;
    private static final int WIN_1500 = 1500;
    private static final int WIN_500 = 500;
    private static final int WIN_1000 = 1000;
    private BasicStatistics stats;

    /**
     * Create a new BasicStatistics for each test.
     */
    @BeforeEach
    public void setUp() {
        this.stats = new BasicStatisticsImpl();
    }

    /**
     * Test that the numeric statistics are initialized to 0 and the best combination is empty.
     */
    @Test
    public void testInitialization() {
        assertEquals(0, stats.getNumOfHandsPlayed());
        assertEquals(0, stats.getNumOfHandsWon());
        assertEquals(0, stats.getNumOfGamesPlayed());
        assertEquals(0, stats.getNumOfGamesWon());
        assertEquals(0, stats.getBiggestWin());
        assertEquals(Optional.empty(), stats.getBestCombination());
        assertEquals(0, stats.getHandWinRate());
        assertEquals(0, stats.getGameWinRate());
    }

    /**
     * Test that incrementing statistics are updated correctly.
     */
    @Test
    public void testIncrementingStats() {
        stats.setHandsPlayed(1);
        stats.setHandsWon(2);
        stats.setGamesPlayed(3);
        stats.setGamesWon(4);
        assertEquals(1, stats.getNumOfHandsPlayed());
        assertEquals(2, stats.getNumOfHandsWon());
        assertEquals(3, stats.getNumOfGamesPlayed());
        assertEquals(4, stats.getNumOfGamesWon());
        stats.incrementHandsPlayed(1);
        stats.incrementHandsWon(2);
        stats.incrementGamesPlayed(3);
        stats.incrementGamesWon(4);
        assertEquals(1 + 1, stats.getNumOfHandsPlayed());
        assertEquals(2 + 2, stats.getNumOfHandsWon());
        assertEquals(3 + 3, stats.getNumOfGamesPlayed());
        assertEquals(4 + 4, stats.getNumOfGamesWon());
    }

    /**
     * Test that the best combination is updated correctly.
     */
    @Test
    public void testBestCombination() {
        stats.setBestCombinationIfSo(CombinationType.TWO_PAIRS);
        assertEquals(Optional.of(CombinationType.TWO_PAIRS), stats.getBestCombination());
        // worse combination -> should not change
        stats.setBestCombinationIfSo(CombinationType.HIGH_CARD);
        assertEquals(Optional.of(CombinationType.TWO_PAIRS), stats.getBestCombination());
        // better combination -> should change
        stats.setBestCombinationIfSo(CombinationType.FULL_HOUSE);
        assertEquals(Optional.of(CombinationType.FULL_HOUSE), stats.getBestCombination());
    }

    /**
     * Test that the biggest win is updated correctly.
     */
    @Test
    public void testBiggestWin() {
        stats.setBiggestWinIfSo(WIN_1000);
        assertEquals(WIN_1000, stats.getBiggestWin());
        // smaller win -> should not change
        stats.setBiggestWinIfSo(WIN_500);
        assertEquals(WIN_1000, stats.getBiggestWin());
        // bigger win -> should change
        stats.setBiggestWinIfSo(WIN_1500);
        assertEquals(WIN_1500, stats.getBiggestWin());
    }

    /**
     * Test that the ratioes are calculated correctly.
     */
    @Test
    public void testRatioes() {
        stats.setHandsPlayed(HANDS_PLAYED_10);
        stats.setHandsWon(HANDS_WON_5);
        stats.setGamesPlayed(GAMES_PLAYED_10);
        stats.setGamesWon(GAMES_WON_8);
        assertEquals((double) HANDS_WON_5 / HANDS_PLAYED_10, stats.getHandWinRate());
        assertEquals((double) GAMES_WON_8 / GAMES_PLAYED_10, stats.getGameWinRate());
    }

    /**
     * Test that the statistics are reset correctly.
     */
    @Test
    public void testReset() {
        stats.setHandsPlayed(HANDS_PLAYED_10);
        stats.setHandsWon(HANDS_WON_5);
        stats.setGamesPlayed(GAMES_PLAYED_10);
        stats.setGamesWon(GAMES_WON_8);
        stats.setBiggestWinIfSo(WIN_1000);
        stats.setBestCombinationIfSo(CombinationType.FULL_HOUSE);
        stats.reset();
        assertEquals(0, stats.getNumOfHandsPlayed());
        assertEquals(0, stats.getNumOfHandsWon());
        assertEquals(0, stats.getNumOfGamesPlayed());
        assertEquals(0, stats.getNumOfGamesWon());
        assertEquals(0, stats.getBiggestWin());
        assertEquals(Optional.empty(), stats.getBestCombination());
        assertEquals(0, stats.getHandWinRate());
        assertEquals(0, stats.getGameWinRate());
    }

}
