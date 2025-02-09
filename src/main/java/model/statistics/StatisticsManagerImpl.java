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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsManagerImpl.class);

    private S globalStatistics;
    private Set<StatisticsContributor<S>> contributors;

    /**
     * Constructs a new instance of this class.
     * @param statistics The object representing the statistics to manage.
     */
    public StatisticsManagerImpl(final S statistics) {
        this.globalStatistics = Objects.requireNonNull(statistics);
        this.contributors = new HashSet<>();
    }

    /**
     * Constructs a new instance of this class.
     * Will load the statistics from the specified file if they were saved
     * previously using the {@link #saveStatistics(String)} method, 
     * otherwise the statistics will be initialized with the
     * provided object and saved to the specified file.
     * @param fileName The name of the file to load the statistics from or save them to.
     * @param statistics The object representing the statistics to manage.
     */
    public StatisticsManagerImpl(final String fileName, final S statistics) {
        this(statistics);
        try {
            this.loadStatistics(fileName);
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.warn("Could not load statistics from file '{}'.", fileName);
            this.globalStatistics = statistics;
            try {
                this.saveStatistics(fileName);
                LOGGER.info("Statistics saved to file '{}'.", fileName);
            } catch (IOException e1) {
                LOGGER.error("Could not save statistics to file '{}'.", fileName);
            }
        }
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
    public void addContributor(final StatisticsContributor<S> contributor) {
        this.contributors.add(Objects.requireNonNull(contributor));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAllContributors(final Collection<StatisticsContributor<S>> contributorsCollection) {
        contributorsCollection.forEach(this::addContributor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTotalStatistics() {
        this.contributors.forEach(contributor -> contributor.updateStatistics(globalStatistics));
    }

    /**
     * {@inheritDoc}
     * <p>The file will be saved in the user's home directory under the
     * <i>poker_tex</i> directory.
     */
    @Override
    public final void saveStatistics(final String fileName) throws IOException {
        final File file = getFileInProjectDir(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(globalStatistics);
        }
        LOGGER.info("Statistics saved to file '{}'.", fileName);
    }

    /**
     * {@inheritDoc}
     * <p>The file will be loaded from the user's home directory under the
     * <i>poker_tex</i> directory.
     */
    @SuppressWarnings("unchecked")
    @SuppressFBWarnings(value = "RV_RETURN_VALUE_IGNORED_BAD_PRACTICE", justification = "The returned value is irrelevant")
    @Override
    public final void loadStatistics(final String fileName) throws IOException, ClassNotFoundException {
        final File file = getFileInProjectDir(fileName);
        file.createNewFile(); 
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            this.globalStatistics = (S) ois.readObject();
        }
    }

    @SuppressFBWarnings(value = "RV_RETURN_VALUE_IGNORED_BAD_PRACTICE", justification = "The returned value is unimportant")
    private File getFileInProjectDir(final String fileName) {
        final String userHome = System.getProperty("user.home");
        final File pokerDir = new File(userHome, PROJECT_DIR_NAME);
        if (!pokerDir.exists()) {
            pokerDir.mkdirs();
        }
        return new File(pokerDir, fileName);
    }

}
