package view.statistics;

import java.util.List;

import temp.Pair;

public interface StatsSceneController {

    void goToMainMenu();

    List<Pair<String, String>> getStatistics();
}
