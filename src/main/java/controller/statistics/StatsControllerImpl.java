package controller.statistics;

import java.util.List;

import commons.Pair;
import controller.menu.MainMenuControllerImpl;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import view.View;
import view.scenes.MainMenuScene;

/**
 * Implementation of the StatsController interface.
 * Manages the retrieval of the statistics form the statistics manager and the
 * return to the main menu scene.
 */
public class StatsControllerImpl implements StatsController {

    private static final String STATS_FILE_NAME = "stats.bin";
    private final View mainView;

    /**
     * Constructor for the StatsControllerImpl class.
     * @param mainView The main view of the application.
     */
    public StatsControllerImpl(final View mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, String>> getStatistics() {
        var statsManager = new StatisticsManagerImpl<>(new BasicStatisticsImpl());
        try {
            statsManager.loadStatistics(STATS_FILE_NAME);
        } catch (Exception e) {
            System.err.println("Failed to load statistics from file");
        }
        return statsManager.getTotalStatistics().getAsList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }
}
