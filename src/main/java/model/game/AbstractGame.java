package model.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    private static final int INITIAL_BET_DIVISION_FACT = 100;
    protected static final int NUM_AI_PLAYERS = 3;
    private static final int USER_PLAYER_ID = NUM_AI_PLAYERS;
    private static final String STATISTICS_FILE_NAME = "stats.bin";

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
    
    /**
     * Constructor for the AbstractGame. 
     * @param initialChips initial amount of chips of players.
     */
    public AbstractGame(final GameController controller, final int initialChips) {
        this.controller = controller;
        this.startingBet = (int) initialChips / INITIAL_BET_DIVISION_FACT;
        this.dealer = new DealerImpl();
        this.userPlayer = new UserPlayer(USER_PLAYER_ID, initialChips);
        this.setInitialPlayers(initialChips);
        this.gameState = new StateImpl(startingBet, this.players.size());
        this.statistics = new BasicStatisticsImpl();
        this.statsManager = new StatisticsManagerImpl<>(STATISTICS_FILE_NAME, new BasicStatisticsImpl());
        this.statsManager.addContributor(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.players.stream().allMatch(p -> p.isAI()) || 
               isWon();
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
        this.statistics.incrementGamesPlayed(1);
        while (!isOver()) {
            this.statistics.incrementHandsPlayed(1);
            this.setRolesForNewHand();
            this.players.stream().forEachOrdered(p -> p.setCards(this.dealer.giveCardsToPlayer()));
            this.gameState.newHand(startingBet, this.players.size());
            var hand = new HandImpl(this.controller, this.players, this.gameState);

            this.controller.updateForNewHand();
            this.controller.setPlayerCards(USER_PLAYER_ID, userPlayer.getCards());

            do {
                this.gameState.addCommunityCards(this.dealer.giveCardsToTheGame(
                     gameState.getHandPhase().getNumCards()));
                this.controller.setCommunityCards(this.gameState.getCommunityCards());
                
                hand.startPhase();
                this.players.forEach(p -> {
                    this.gameState.addToPot(p.getTotalPhaseBet());
                    this.controller.setPlayerBet(p.getId(), 0);
                });
                this.controller.setPot(this.gameState.getPot());
                this.gameState.nextHandPhase();
                
            } while (!hand.isHandOver());

            hand.determinesWinnerOfTheHand();
            
        }
        this.updateStatisticsAfterGameEnd();
        this.controller.goToGameOverScene(this.isWon());
        
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
            indexNextBigBlind = (indexNextSmallBlind + 1) % players.size();
        } else {
            var originalList = List.copyOf(players);
            this.players.removeIf(p -> !p.hasChipsLeft());

            indexNextSmallBlind = originalList.indexOf(smallBlindPlayer) + 
                                  (this.players.contains(smallBlindPlayer)? 1 : 0);
            indexNextBigBlind = originalList.indexOf(bigBlindPlayer) + 
                                (this.players.contains(bigBlindPlayer)? 1 : 0);
        }
    
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

    private void updateStatisticsAfterGameEnd() {
        if (this.isWon()) {
            this.statistics.incrementGamesWon(1);            
        }
        this.statsManager.updateTotalStatistics();
        try {
            this.statsManager.saveStatistics(STATISTICS_FILE_NAME);
        } catch (Exception e) {
            System.err.println("Failed to save statistics");
        }
    }
}
