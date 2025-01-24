package model.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.statistics.api.Statistics;
import model.statistics.api.StatisticsContributor;
import model.statistics.api.StatisticsManager;

public class StatisticsManagerImpl<S extends Statistics> implements StatisticsManager<S> {

    private S globalStatistics;
    private List<StatisticsContributor<S>> contributors;

    public StatisticsManagerImpl(S statistics) {
        this.globalStatistics = Objects.requireNonNull(statistics);
        this.contributors = new ArrayList<>();
    }

    @Override
    public void addContributor(StatisticsContributor<S> contributor) {
        contributors.add(Objects.requireNonNull(contributor));
    }

    @Override
    public void updateTotalStatistics() {
        contributors.forEach(contributor -> contributor.updateStatistics(globalStatistics));
    }

}
