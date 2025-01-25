package model.statistics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import model.statistics.api.Statistics;
import model.statistics.api.StatisticsContributor;
import model.statistics.api.StatisticsManager;

/**
 * Implementation of the {@link StatisticsManager} interface.
 * 
 * @param <S> the type of statistics to manage
 */
public class StatisticsManagerImpl<S extends Statistics> implements StatisticsManager<S> {

    private S globalStatistics;
    private List<StatisticsContributor<S>> contributors;

    /**
     * Constructs a new instance of this class.
     * 
     * @param statistics The object representing the statistics to manage.
     */
    public StatisticsManagerImpl(S statistics) {
        this.globalStatistics = Objects.requireNonNull(statistics);
        this.contributors = new ArrayList<>();
    }

    /**
     * Constructs a new instance of this class.
     * Will load the statistics from the specified file if they were saved
     * previously
     * using the {@link #saveStatistics(String)} method.
     * 
     * @param fileName   The name of the file to load the statistics from.
     * @param statistics The object representing the statistics to manage.
     * @throws IOException If an I/O error occurs while loading the statistics.
     */
    public StatisticsManagerImpl(String fileName, S statistics) throws Exception {
        this(statistics);
        this.loadStatistics(fileName);
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
    public void addAllContributors(Set<StatisticsContributor<S>> contributors) {
        contributors.forEach(this::addContributor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTotalStatistics() {
        contributors.forEach(contributor -> contributor.updateStatistics(globalStatistics));
    }

    @Override
    public void saveStatistics(String fileName) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(globalStatistics);
        oos.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadStatistics(String fileName) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        this.globalStatistics = (S) ois.readObject();
        ois.close();
    }

}
