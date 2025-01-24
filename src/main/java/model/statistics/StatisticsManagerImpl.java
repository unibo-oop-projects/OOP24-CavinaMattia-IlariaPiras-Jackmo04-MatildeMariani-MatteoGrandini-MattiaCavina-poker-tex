package model.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.statistics.api.Statistics;
import model.statistics.api.StatisticsContributor;
import model.statistics.api.StatisticsManager;

/**
 * Implementation of the {@link StatisticsManager} interface.
 * @param <S> the type of statistics to manage
 */
public class StatisticsManagerImpl<S extends Statistics> implements StatisticsManager<S> {

    private S globalStatistics;
    private List<StatisticsContributor<S>> contributors;

    /**
     * Constructs a new instance of this class.
     * @param statistics The object representing the statistics to manage.
     */
    public StatisticsManagerImpl(S statistics) {
        this.globalStatistics = Objects.requireNonNull(statistics);
        this.contributors = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addContributor(StatisticsContributor<S> contributor) {
        contributors.add(Objects.requireNonNull(contributor));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTotalStatistics() {
        contributors.forEach(contributor -> contributor.updateStatistics(globalStatistics));
    }

}
