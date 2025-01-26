package main.model.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.statistics.BasicStatisticsImpl;
import model.statistics.api.BasicStatistics;
import model.temp.CombinationType;

public class TestBasicStatistics {

    private BasicStatistics stats;

    @BeforeEach
    public void setUp() {
        this.stats = new BasicStatisticsImpl();
    }

    @Test
    public void testInitialization() {
        // Test that the statistics are initialized to 0 and the best combination is empty
        assertEquals(0, stats.getNumOfHandsPlayed());
        assertEquals(0, stats.getNumOfHandsWon());
        assertEquals(0, stats.getNumOfGamesPlayed());
        assertEquals(0, stats.getNumOfGamesWon());
        assertEquals(0, stats.getBiggestWin());
        assertEquals(Optional.empty(), stats.getBestCombination());
    }

    @Test
    public void testUpdating() {
        // Test that the counting statistics are updated correctly
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

        // Test that the biggest win is updated correctly
        stats.setBiggestWinIfSo(1000);
        assertEquals(1000, stats.getBiggestWin());
        // smaller win -> should not change
        stats.setBiggestWinIfSo(500);
        assertEquals(1000, stats.getBiggestWin());
        // bigger win -> should change
        stats.setBiggestWinIfSo(1500);
        assertEquals(1500, stats.getBiggestWin());
        
        // Test that the best combination is updated correctly
        stats.setBestCombinationIfSo(CombinationType.TWO_PAIRS);
        assertEquals(Optional.of(CombinationType.TWO_PAIRS), stats.getBestCombination());
        // worse combination -> should not change
        stats.setBestCombinationIfSo(CombinationType.HIGH_CARD);
        assertEquals(Optional.of(CombinationType.TWO_PAIRS), stats.getBestCombination());
        // better combination -> should change
        stats.setBestCombinationIfSo(CombinationType.FULL_HOUSE);
        assertEquals(Optional.of(CombinationType.FULL_HOUSE), stats.getBestCombination());

        // Test that the statistics are reset correctly
        stats.reset();
        assertEquals(0, stats.getNumOfHandsPlayed());
        assertEquals(0, stats.getNumOfHandsWon());
        assertEquals(0, stats.getNumOfGamesPlayed());
        assertEquals(0, stats.getNumOfGamesWon());
        assertEquals(0, stats.getBiggestWin());
        assertEquals(Optional.empty(), stats.getBestCombination());
    }

    @Test
    public void testRatioes() {
        // Test that the ratioes are calculated correctly
        stats.setHandsPlayed(10);
        stats.setHandsWon(5);
        stats.setGamesPlayed(10);
        stats.setGamesWon(8);
        assertEquals(0.5, stats.getHandWinRate());
        assertEquals(0.8, stats.getGameWinRate());
        stats.reset();
        assertEquals(0, stats.getHandWinRate());
        assertEquals(0, stats.getGameWinRate());
    }

}
