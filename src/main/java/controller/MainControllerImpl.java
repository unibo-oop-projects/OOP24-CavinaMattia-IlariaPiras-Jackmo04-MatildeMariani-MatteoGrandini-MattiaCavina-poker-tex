package controller;

import java.util.Map;

import model.game.api.Game;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import view.View;

public class MainControllerImpl implements MainController {

    private static final String STATS_FILE_NAME = "stats.bin";

    private final View mainView; // TODO will need for updating the game scene
    private Game game; // TODO will need for managing the game model

    public MainControllerImpl(View mainView) {
        this.mainView = mainView;
    }

    @Override
    public Map<String, String> getStatistics() {
        var statsManager = new StatisticsManagerImpl<>(new BasicStatisticsImpl());
        try {
            statsManager.loadStatistics(STATS_FILE_NAME);
        } catch (Exception e) {
            System.err.println("Failed to load statistics from file");
        }
        return statsManager.getTotalStatistics().getAsMap();
    }
}
