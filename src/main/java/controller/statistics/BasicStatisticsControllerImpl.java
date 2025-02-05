package controller.statistics;

import java.util.List;

import commons.Pair;
import controller.menu.MainMenuControllerImpl;
import model.combination.api.CombinationType;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import model.statistics.api.BasicStatistics;
import view.View;
import view.scenes.MainMenuScene;

/**
 * Implementation of the StatsController interface.
 * Manages the retrieval of the statistics form the statistics manager and the
 * return to the main menu scene.
 */
public class BasicStatisticsControllerImpl implements StatsController {

    private static final String STATS_FILE_NAME = "stats.bin";
    private final View mainView;

    /**
     * Constructor for the StatsControllerImpl class.
     * @param mainView The main view of the application.
     */
    public BasicStatisticsControllerImpl(final View mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, String>> getStatistics() {
        final var statsManager = new StatisticsManagerImpl<>(new BasicStatisticsImpl());
        try {
            statsManager.loadStatistics(STATS_FILE_NAME);
        } catch (Exception e) {
            System.err.println("Failed to load statistics from file");
        }
        return this.getAsList(statsManager.getTotalStatistics());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }

    private List<Pair<String, String>> getAsList(final BasicStatistics stats) {
        return List.of(
            new Pair<>("Hands played", String.valueOf(stats.getNumOfHandsPlayed())),
            new Pair<>("Hands won", String.valueOf(stats.getNumOfHandsWon())),
            new Pair<>("Games played", String.valueOf(stats.getNumOfGamesPlayed())),
            new Pair<>("Games won", String.valueOf(stats.getNumOfGamesWon())),
            new Pair<>("Best Combination", stats.getBestCombination().map(CombinationType::getName).orElse("None")),
            new Pair<>("Biggest win", stats.getBiggestWin() + " chips"),
            new Pair<>("Hands win rate", String.format("%.2f%%", stats.getHandWinRate())),
            new Pair<>("Games win rate", String.format("%.2f%%", stats.getGameWinRate()))
        );
    }
}
