package controller.statistics;

import java.util.List;

import temp.Pair;

public interface StatsController {

    List<Pair<String, String>> getStatistics();

    void goToMainMenu();
}
