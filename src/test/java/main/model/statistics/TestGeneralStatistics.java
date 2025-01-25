package main.model.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.statistics.GeneralStatisticsImpl;
import model.statistics.api.GeneralStatistics;
import model.temp.CombinationType;

public class TestGeneralStatistics {

    private GeneralStatistics stats;

    @BeforeEach
    public void setUp() {
        this.stats = new GeneralStatisticsImpl();
    }

    @Test
    public void testInitialization() {
        // Test that the statistics are initialized to 0 and the best combination is empty
        assertEquals(0, stats.getNumOfHandsPlayed());
        assertEquals(0, stats.getNumOfHandsWon());
        assertEquals(0, stats.getNumOfGamesPlayed());
        assertEquals(0, stats.getNumOfGamesWon());
        assertEquals(Optional.empty(), stats.getBestCombination());
    }

    @Test
    public void testUpdating() {
        // Test that the statistics are updated correctly
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
        
        // first combination -> should be set as the best
        stats.setBestCombinationIfSo(CombinationType.TWO_PAIRS);
        assertEquals(Optional.of(CombinationType.TWO_PAIRS), stats.getBestCombination());
        // worse combination -> should not change
        stats.setBestCombinationIfSo(CombinationType.HIGH_CARD);
        assertEquals(Optional.of(CombinationType.TWO_PAIRS), stats.getBestCombination());
        // better combination -> should change
        stats.setBestCombinationIfSo(CombinationType.FULL_HOUSE);
        assertEquals(Optional.of(CombinationType.FULL_HOUSE), stats.getBestCombination());
    }

}
