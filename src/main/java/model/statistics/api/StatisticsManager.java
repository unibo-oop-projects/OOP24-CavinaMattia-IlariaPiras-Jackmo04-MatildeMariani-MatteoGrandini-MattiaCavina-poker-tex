package model.statistics.api;

public interface StatisticsManager<S extends Statistics> {

    void addContributor(StatisticsContributor<S> contributor);

    void updateTotalStatistics();

}
