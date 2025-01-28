package controller;

import java.util.Map;

import model.game.api.Game;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import view.View;

public class MainControllerImpl implements MainController {

    private final View mainView; // will need for updating the game scene
    private Game game; // will need for managing the game model

    public MainControllerImpl(View mainView) {
        this.mainView = mainView;
    }

    @Override
    public Map<String, String> getStatistics() {
        var statsManager = new StatisticsManagerImpl<>(new BasicStatisticsImpl());
        try {
            statsManager.loadStatistics("stats.bin");
        } catch (Exception e) {
            System.err.println("Failed to load statistics from file");
        }
        return statsManager.getTotalStatistics().getAsMap();
    }
}
