package model.game;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.game.api.GameController;
import model.dealer.DealerImpl;
import model.dealer.api.Dealer;
import model.game.api.Game;
import model.game.api.State;
import model.player.api.Player;
import model.player.api.Role;
import model.player.user.UserPlayer;
import model.statistics.BasicStatisticsImpl;
import model.statistics.StatisticsManagerImpl;
import model.statistics.api.BasicStatistics;
import model.statistics.api.StatisticsContributor;
import model.statistics.api.StatisticsManager;

/**
 * This class provides an implementation of the Game interface, abstracting the choice of players.
 */
public abstract class AbstractGame implements Game, StatisticsContributor<BasicStatistics>{

    private static final int INITIAL_BET_DIVISION_FACT = 10;
    protected static final int NUM_AI_PLAYERS = 3;
    private static final int USER_PLAYER_ID = NUM_AI_PLAYERS;
    private static final String STATISTICS_FILE_NAME = "stats.bin";
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGame.class);

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
     * @param initialChips initial amount of chips of players.
     */
    public AbstractGame(final GameController controller, final int initialChips) {
        this.controller = controller;
        this.statistics = new BasicStatisticsImpl();
        this.statsManager = new StatisticsManagerImpl<>(STATISTICS_FILE_NAME, new BasicStatisticsImpl());
        this.statsManager.addContributor(this);
        this.startingBet = (int) initialChips / INITIAL_BET_DIVISION_FACT;
        this.dealer = new DealerImpl();
        final var user = new UserPlayer(USER_PLAYER_ID, initialChips);
        this.userPlayer = user;
        this.statsManager.addContributor(user); // TODO potrebbe non funzionare
        this.setInitialPlayers(initialChips);
        this.gameState = new StateImpl(startingBet, this.players.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.players.stream().allMatch(p -> p.isAI()) || isWon();

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
        return this.players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getGameState() {
        return this.gameState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserPlayer getUserPlayer() {
        return (UserPlayer) this.userPlayer;
    }

    /**
     * Sets the {@link Role}s for the hand, randomly if it's the first hand or to the next player in 
     * the list (keeping in mind that some players may no longer be in the game) otherwise.
     * Updates the smallBlindPlayer and bigBlindPlayer fields.
     */
    private void setRolesForNewHand() {
        int indexNextSmallBlind;
        int indexNextBigBlind;

        if (this.gameState.getHandNumber() == 0) {
            Random rand = new Random();
            indexNextSmallBlind = rand.nextInt(players.size());
        } else {
            var originalList = List.copyOf(players);
            this.players.removeIf(p -> !p.hasChipsLeft());

            indexNextSmallBlind = (this.players.contains(smallBlindPlayer)?
                this.players.indexOf(smallBlindPlayer) + 1 :
                (this.players.contains(bigBlindPlayer)? this.players.indexOf(bigBlindPlayer) :
                this.players.indexOf(originalList.get(
                    (originalList.indexOf(bigBlindPlayer) + 1 % originalList.size()))))) % players.size();
        }
        indexNextBigBlind = (indexNextSmallBlind + 1) % players.size();
    
        smallBlindPlayer = this.players.get(indexNextSmallBlind);
        bigBlindPlayer = this.players.get(indexNextBigBlind);
        
        this.smallBlindPlayer.setRole(Role.SMALL_BLIND);
        this.bigBlindPlayer.setRole(Role.BIG_BLIND);

        this.controller.setRoles(smallBlindPlayer.getId(), bigBlindPlayer.getId());
    }

    /** 
     * Sets the initial list of {@link Player}s. It's a template method.
     * @param initialChips initial amount of chips of players.
     */
    private void setInitialPlayers(final int initialChips) {
        for (var i = 0; i < NUM_AI_PLAYERS; i++) {
            this.players.add(this.getAIPlayer(i, initialChips));
        }
        this.players.add(this.userPlayer);
    }

    /**
     * Returns a different type of AI player based on the difficulty level of the game.
     * @param initialChips initial amount of chips of players.
     * @return an AI player.
     */
    protected abstract Player getAIPlayer(int id, int initialChips);

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatistics(final BasicStatistics totalStats) {
        totalStats.append(this.statistics);
        this.statistics.reset();
    }

    /**
     * 
     */
    private void updateStatisticsAfterGameEnd() {
        if (this.isWon()) {
            this.statistics.incrementGamesWon(1);            
        }
        this.statsManager.updateTotalStatistics();
        try {
            this.statsManager.saveStatistics(STATISTICS_FILE_NAME);
        } catch (IOException e) {
            LOGGER.error("Error while saving statistics: ", e);
        }
    }

    /**
     * Private inner class that implements a GameLoop that handles the logistical aspects of a game.
     */
    private class GameLoop extends Thread {

        @Override
        public void run() {
            statistics.incrementGamesPlayed(1);
            while (!isOver() && !controller.isTerminated()) {
                dealer.shuffle();
                statistics.incrementHandsPlayed(1);
                setRolesForNewHand();
                players.stream().forEachOrdered(p -> p.setCards(dealer.giveCardsToPlayer()));
                gameState.newHand(startingBet, players.size());
                var hand = new HandImpl(controller, players, gameState);

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
                    controller.updateForNewPhase(gameState.getPot());
                    gameState.nextHandPhase();   
                } while (!hand.isHandOver() && !controller.isTerminated());

                if (!controller.isTerminated()) {
                    hand.determinesWinnerOfTheHand(); 
                }            
            }
            updateStatisticsAfterGameEnd();
            if (!controller.isTerminated()) {
                controller.goToGameOverScene(isWon());
            }
        }
    }

}
