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
    protected static final int NUM_INITIAL_PLAYERS = 4;

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
        this.gameState = new StateImpl(startingBet, NUM_INITIAL_PLAYERS);
        this.setInitialPlayers(initialChips);
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

    /*
     * TODO: change the commented lines using Optional.empty 
     */
    /**
     * Sets the {@link Role}s for the hand, assigning each role to the next player in 
     * the list (keeping in mind that some players may no longer be in the game) and 
     * updating the smallBlindPlayer and bigBlindPlayer fields.
     */
    private void setRolesForNewHand() {
        var originalList = List.copyOf(players);
        this.players.removeIf(p -> !p.hasChipsLeft());
        //smallBlindPlayer.setRole(Role.REGULAR);
        //bigBlindPlayer.setRole(Role.REGULAR);
        
        var indexNextSmallBlind = originalList.indexOf(smallBlindPlayer) + 
                                  (this.players.contains(smallBlindPlayer)? 1 : 0);
        var indexNextBigBlind = originalList.indexOf(bigBlindPlayer) + 
                                (this.players.contains(bigBlindPlayer)? 1 : 0);
    
        this.players.get(indexNextSmallBlind).setRole(Role.SMALL_BLIND);
        this.players.get(indexNextBigBlind).setRole(Role.BIG_BLIND);

        smallBlindPlayer = this.players.get(indexNextSmallBlind);
        bigBlindPlayer = this.players.get(indexNextBigBlind);
    }

    /*
     * TODO: change the initial role assignment to optional empty and then use rand to get two players to assign the roles to.
     */
    /** 
     * Sets the initial list of {@link Player}s, assigning to each of them a
     * different role randomly. It's a template method.
     * @param initialChips initial amount of chips of players.
     */
    private void setInitialPlayers(final int initialChips) {
        Random rand = new Random();
        var startingRole = Role.values()[rand.nextInt(Role.values().length)];

        for (var i = 0; i < NUM_AI_PLAYERS; i++) {
            this.players.add(this.getAIPlayer(initialChips, startingRole));
        }
        //this.players.add(new UserPlayer(initialChips, startingRole.next()));
        this.smallBlindPlayer = this.players.stream()
                                    .filter(p -> p.getRole().equals(Role.SMALL_BLIND))
                                    .findAny()
                                    .get();
        this.bigBlindPlayer = this.players.stream()
                                  .filter(p -> p.getRole().equals(Role.BIG_BLIND))
                                  .findAny()
                                  .get();
    }

    /**
     * Returns a different type of AI player based on the difficulty level of the game.
     * @param initialChips initial amount of chips of players.
     * @return an AI player.
     */
    protected abstract Player getAIPlayer(int initialChips, Role startingRole);

}
