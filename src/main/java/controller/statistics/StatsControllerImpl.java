package controller.statistics;

import java.util.List;

import controller.menu.MainMenuControllerImpl;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import temp.Pair;
import view.View;
import view.menu.MainMenuScene;

public class StatsControllerImpl implements StatsController {

    private static final String STATS_FILE_NAME = "stats.bin";
    private final View mainView;

    public StatsControllerImpl(View mainView) {
        this.mainView = mainView;
    }

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

    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }
}
