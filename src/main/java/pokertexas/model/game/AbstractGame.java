package pokertexas.model.game;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import pokertexas.controller.game.api.GameController;
import pokertexas.controller.player.user.UserPlayerController;
import pokertexas.model.dealer.DealerImpl;
import pokertexas.model.dealer.api.Dealer;
import pokertexas.model.game.api.Game;
import pokertexas.model.game.api.Phase;
import pokertexas.model.game.api.State;
import pokertexas.model.player.api.Player;
import pokertexas.model.player.api.Role;
import pokertexas.model.player.user.UserPlayerImpl;
import pokertexas.model.statistics.BasicStatisticsImpl;
import pokertexas.model.statistics.StatisticsManagerImpl;
import pokertexas.model.statistics.api.BasicStatistics;
import pokertexas.model.statistics.api.StatisticsContributor;
import pokertexas.model.statistics.api.StatisticsManager;

/**
 * This class provides an implementation of the Game interface, abstracting the initialization
 * of AI players.
 */
public abstract class AbstractGame implements Game, StatisticsContributor<BasicStatistics> {

    private static final int INITIAL_BET_DIVISION_FACT = 10;
    private static final int NUM_AI_PLAYERS = 3;
    private static final int USER_PLAYER_ID = NUM_AI_PLAYERS;
    private static final String STATISTICS_FILE_NAME = "stats.ser";
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGame.class);
    private static final Random RAND = new Random();

    private final GameController controller;
    private final Dealer dealer;
    private final State gameState;
    private final int startingBet;
    private final List<Player> players = new LinkedList<>();
    private final Player userPlayer;
    private Player smallBlindPlayer;
    private Player bigBlindPlayer;
    private final BasicStatistics statistics;
    private final StatisticsManager<BasicStatistics> statsManager;

    private final GameLoop loop = new GameLoop();

    /**
     * Constructor for the AbstractGame.
     * @param controller the game controller. 
     * @param initialChips initial amount of chips of players.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Storing GameController mutable object is intented")
    public AbstractGame(final GameController controller, final int initialChips) {
        this.controller = controller;
        this.statistics = new BasicStatisticsImpl();
        this.statsManager = new StatisticsManagerImpl<>(STATISTICS_FILE_NAME, new BasicStatisticsImpl());
        this.startingBet = initialChips / INITIAL_BET_DIVISION_FACT;
        this.dealer = new DealerImpl();
        this.userPlayer = new UserPlayerImpl(USER_PLAYER_ID, initialChips);
        this.gameState = new StateImpl(startingBet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.players.stream().allMatch(Player::isAI) || isWon();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWon() {
        return this.players.stream().allMatch(p -> !p.isAI());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        new Thread(this.loop).start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return List.copyOf(this.players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserPlayerController getUserPlayerController() {
        return ((UserPlayerImpl) this.userPlayer).getController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInitialPlayers(final int initialChips) {
        for (var i = 0; i < NUM_AI_PLAYERS; i++) {
            this.players.add(this.getAIPlayer(i, initialChips));
        }
        this.players.add(this.userPlayer);
        initStatsManager();
    }

    /**
     * Returns a different type of AI player based on the difficulty level of the game.
     * @param id the player's id.
     * @param initialChips initial amount of chips of players.
     * @return an AI player.
     */
    protected abstract Player getAIPlayer(int id, int initialChips);

    /**
     * Sets the {@link Role}s for the hand, randomly if it's the first hand or to the next player in 
     * the list (keeping in mind that some players may no longer be in the game) otherwise.
     * Updates the smallBlindPlayer and bigBlindPlayer fields.
     */
    private void setRolesForNewHand() {
        final int indexNextSmallBlind;
        final int indexNextBigBlind;

        if (this.gameState.getHandNumber() == 0) {
            indexNextSmallBlind = RAND.nextInt(players.size());
        } else {
            final var originalList = List.copyOf(players);
            this.players.removeIf(p -> !p.hasChipsLeft());

            indexNextSmallBlind = (this.players.contains(smallBlindPlayer)
                ? this.players.indexOf(smallBlindPlayer) + 1
                : this.players.contains(bigBlindPlayer) ? this.players.indexOf(bigBlindPlayer)
                : this.players.indexOf(originalList.get(
                    (originalList.indexOf(bigBlindPlayer) + 1) % originalList.size()))) % players.size();
        }
        indexNextBigBlind = (indexNextSmallBlind + 1) % players.size();

        smallBlindPlayer = this.players.get(indexNextSmallBlind);
        bigBlindPlayer = this.players.get(indexNextBigBlind);

        this.smallBlindPlayer.setRole(Role.SMALL_BLIND);
        this.bigBlindPlayer.setRole(Role.BIG_BLIND);

        this.controller.setRoles(smallBlindPlayer.getId(), bigBlindPlayer.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatistics(final BasicStatistics totalStats) {
        totalStats.append(this.statistics);
        this.statistics.reset();
    }

    private void initStatsManager() {
        this.statsManager.addContributor(this);
        this.statsManager.addContributor(this.userPlayer);
    }

    private void updateStatisticsAfterGameEnd() {
        if (this.isWon()) {
            this.statistics.incrementGamesWon(1);
        }
        saveStatistics();
    }

    private void saveStatistics() {
        this.statsManager.updateTotalStatistics();
        try {
            this.statsManager.saveStatistics(STATISTICS_FILE_NAME);
        } catch (IOException e) {
            LOGGER.error("Error while saving statistics: ", e);
        }
    }

    /**
     * Private inner class that implements a GameLoop that handles the logistical aspects of a game.
     * A new hand begins until isOver returns true or the game has been terminated, updating the list 
     * of {@link Player}s and their {@link Role}s, and asking the {@link Dealer} to deal cards to each of them. 
     * It then goes through each {@link Phase} of the hand until it ends. 
     * The winner of the hand is then declared.
     */
    private final class GameLoop extends Thread {

        @Override
        public void run() {
            statistics.incrementGamesPlayed(1);
            setRolesForNewHand();
            while (!isOver() && !controller.isTerminated()) {
                dealer.shuffle();
                statistics.incrementHandsPlayed(1);

                players.stream().forEachOrdered(p -> p.setCards(dealer.giveCardsToPlayer()));
                gameState.newHand(startingBet);
                final var hand = new HandImpl(controller, players, gameState);

                controller.waitIfPaused();
                controller.updateForNewHand();
                controller.setPlayerCards(userPlayer.getId(), userPlayer.getCards());

                do {
                    controller.waitIfPaused();
                    gameState.addCommunityCards(dealer.giveCardsToTheGame(
                        gameState.getHandPhase().getNumCards()));
                    controller.setCommunityCards(gameState.getCommunityCards());
                    hand.startPhase();
                    players.forEach(p -> {
                        gameState.addToPot(p.getTotalPhaseBet());
                        p.nextPhase();
                    });
                    controller.waitIfPaused();
                    controller.updateForNewPhase(gameState.getPot());
                    gameState.nextHandPhase();
                } while (!hand.isHandOver() && !controller.isTerminated());

                if (!controller.isTerminated()) {
                    controller.waitIfPaused();
                    hand.determinesWinnerOfTheHand(); 
                }
                setRolesForNewHand();

                saveStatistics();
            }
            updateStatisticsAfterGameEnd();
            if (!controller.isTerminated()) {
                controller.goToGameOverScene(isWon());
            }
        }
    }

}
