package controller.statistics;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.scene.SceneControllerImpl;
import model.combination.api.CombinationType;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import model.statistics.api.BasicStatistics;
import model.statistics.api.StatisticsManager;
import view.View;

/**
 * Implementation of the StatsController interface.
 * Manages the retrieval of the statistics form the statistics manager and the
 * return to the main menu scene.
 */
public class BasicStatisticsControllerImpl extends SceneControllerImpl implements StatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicStatisticsControllerImpl.class);
    private static final String STATS_FILE_NAME = "stats.bin";
    private final StatisticsManager<BasicStatistics> statsManager;

    /**
     * Constructor for the StatsControllerImpl class.
     * @param mainView The main view of the application.
     */
    public BasicStatisticsControllerImpl(final View mainView) {
        super(mainView);
        this.statsManager = new StatisticsManagerImpl<>(STATS_FILE_NAME, new BasicStatisticsImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImmutablePair<String, String>> getStatistics() {
        return this.getAsList(this.statsManager.getTotalStatistics());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetStatistics() {
        this.statsManager.getTotalStatistics().reset();
        try {
            this.statsManager.saveStatistics(STATS_FILE_NAME);
        } catch (IOException e) {
            LOGGER.error("Error while saving the statistics", e);
        }
    }

    private List<ImmutablePair<String, String>> getAsList(final BasicStatistics stats) {
        return List.of(
            new ImmutablePair<>("Hands played", String.valueOf(stats.getNumOfHandsPlayed())),
            new ImmutablePair<>("Hands won", String.valueOf(stats.getNumOfHandsWon())),
            new ImmutablePair<>("Games played", String.valueOf(stats.getNumOfGamesPlayed())),
            new ImmutablePair<>("Games won", String.valueOf(stats.getNumOfGamesWon())),
            new ImmutablePair<>("Best Combination", stats.getBestCombination().map(CombinationType::getName).orElse("None")),
            new ImmutablePair<>("Biggest win", stats.getBiggestWin() + " chips"),
            new ImmutablePair<>("Hands win rate", String.format("%.2f%%", stats.getHandWinRate())),
            new ImmutablePair<>("Games win rate", String.format("%.2f%%", stats.getGameWinRate()))
        );
    }
}
