package model.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.dealer.DealerImpl;
import model.dealer.api.Dealer;
import model.game.api.Game;
import model.game.api.State;
import model.player.api.Player;
import model.player.api.Role;

/**
 * This class provides an implementation of the Game interface, abstracting the choice of players.
 */
public abstract class AbstractGame implements Game{

    private static final int INITIAL_BET_DIVISION_FACT = 10;
    protected static final int NUM_AI_PLAYERS = 3;

    private final Dealer dealer;
    private final State gameState;
    private final int startingBet;
    private final List<Player> players = new LinkedList<>();
    private Player smallBlindPlayer;
    private Player bigBlindPlayer;
    
    /**
     * Constructor for the AbstractGame. 
     * @param initialChips initial amount of chips of players.
     */
    public AbstractGame(final int initialChips) {
        this.startingBet = (int) initialChips / INITIAL_BET_DIVISION_FACT;
        this.dealer = new DealerImpl();
        this.setInitialPlayers(initialChips);
        this.gameState = new StateImpl(startingBet, this.players.size());
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
        while (!isOver()) {
            this.setRolesForNewHand();
            this.players.stream().forEachOrdered(p -> p.setCards(this.dealer.giveCardsToPlayer()));
            this.gameState.newHand(startingBet, this.players.size());
            var hand = new HandImpl(this.players, this.gameState);

            do {
                this.gameState.addCommunityCards(this.dealer.giveCardsToTheGame(
                     gameState.getHandPhase().getNumCards()));

                hand.startPhase();
                this.players.forEach(p -> this.gameState.addToPot(p.getTotalPhaseBet()));
                this.gameState.nextHandPhase();
                
            } while (!hand.isHandOver());

            hand.determinateWinnerOfTheHand();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getGameState() {
        return gameState;
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

    }

    /** 
     * Sets the initial list of {@link Player}s. It's a template method.
     * @param initialChips initial amount of chips of players.
     */
    private void setInitialPlayers(final int initialChips) {
        for (var i = 0; i < NUM_AI_PLAYERS; i++) {
            this.players.add(this.getAIPlayer(initialChips));
        }
        //this.players.add(new UserPlayer(initialChips));
    
    }

    /**
     * Returns a different type of AI player based on the difficulty level of the game.
     * @param initialChips initial amount of chips of players.
     * @return an AI player.
     */
    protected abstract Player getAIPlayer(int initialChips);

}
