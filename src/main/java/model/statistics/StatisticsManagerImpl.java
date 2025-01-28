package model.statistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashSet;
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

    private static final String PROJECT_DIR_NAME = "poker_tex";

    private S globalStatistics;
    private Set<StatisticsContributor<S>> contributors;

    /**
     * Constructs a new instance of this class.
     * 
     * @param statistics The object representing the statistics to manage.
     */
    public StatisticsManagerImpl(S statistics) {
        this.globalStatistics = Objects.requireNonNull(statistics);
        this.contributors = new HashSet<>();
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
    public S getTotalStatistics() {
        return this.globalStatistics;
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
    public void addAllContributors(Collection<StatisticsContributor<S>> contributors) {
        contributors.forEach(this::addContributor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTotalStatistics() {
        contributors.forEach(contributor -> contributor.updateStatistics(globalStatistics));
    }

    /**
     * {@inheritDoc}
     * <p>The file will be saved in the user's home directory under the
     * <i>poker_tex</i> directory.
     */
    @Override
    public void saveStatistics(String fileName) throws Exception {
        File file = getFileInProjectDir(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(globalStatistics);
        oos.close();
    }

    /**
     * {@inheritDoc}
     * <p>The file will be loaded from the user's home directory under the
     * <i>poker_tex</i> directory.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void loadStatistics(String fileName) throws Exception {
        File file = getFileInProjectDir(fileName);
        file.createNewFile();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        this.globalStatistics = (S) ois.readObject();
        ois.close();
    }

    private File getFileInProjectDir(String fileName) {
        String userHome = System.getProperty("user.home");
        File pokerDir = new File(userHome, PROJECT_DIR_NAME);
        if (!pokerDir.exists()) {
            pokerDir.mkdirs();
        }
        return new File(pokerDir, fileName);
    }

}
