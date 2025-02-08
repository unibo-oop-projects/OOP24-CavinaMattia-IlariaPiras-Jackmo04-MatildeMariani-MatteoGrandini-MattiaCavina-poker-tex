package model.player.user;

import controller.player.user.UserPlayerController;
import model.combination.api.Combination;
import model.deck.api.Card;
import model.game.api.Phase;
import model.game.api.State;
import model.player.AbstractPlayer;
import model.player.api.Action;
import model.statistics.BasicStatisticsImpl;
import model.statistics.api.BasicStatistics;

/**
 * Class representing a human player in the game.
 */
public class UserPlayer extends AbstractPlayer {

    private final UserPlayerController controller;
    private final BasicStatistics statistics;

    /**
     * Constructor for the UserPlayer class.
     * @param id the identifier for the player.
     * @param initialChips the initial amount of chips that the player has.
     */
    public UserPlayer(final int id, final int initialChips) {
        super(id, initialChips);
        this.controller = new UserPlayerController(this);
        this.statistics = new BasicStatisticsImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction() {
        if (this.getCards().size() != 2) {
            throw new IllegalStateException("Player must have 2 cards to play");
        }
        this.updateCombination(this.getGameState());
        final Action action;
        if (this.getGameState().getHandPhase() == Phase.PREFLOP && this.getTotalPhaseBet() == 0 && this.getRole().isPresent()) {
            this.setTotalPhaseBet((int) (this.getGameState().getCurrentBet() * this.getRole().get().getMultiplier()));
            this.setChips(this.getChips() - this.getTotalPhaseBet());
            action = Action.CALL;
        } else { 
            action = this.controller.getUserAction();
            final int bet = this.calculateChipsToBet(this.getGameState().getCurrentBet(), action);
            this.setChips(this.getChips() - bet);
            this.setTotalPhaseBet(this.getTotalPhaseBet() + bet);
        }
        return action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Combination<Card> updateCombination(final State currentState) {
        final var combination = super.updateCombination(currentState);
        this.statistics.setBestCombinationIfSo(combination.type());
        return combination;
    }

    /**
     * Calculates the chips to bet based on the current bet and the action taken by the player.
     * @param currentBet the current bet in the game.
     * @param action the action taken by the player (RAISE, CALL, ALL_IN, FOLD, CHECK).
     * @return the number of chips to bet.
     */
    private int calculateChipsToBet(final int currentBet, final Action action) {
        switch (action) {
            case Action.RAISE -> {
                return this.controller.getRaiseAmount();
            }
            case Action.CALL -> {
                return this.getChips() < (currentBet - this.getTotalPhaseBet())
                ? this.getChips() : (currentBet - this.getTotalPhaseBet());
            }
            case Action.ALL_IN -> {
                return this.getChips();
            }
            case Action.FOLD, Action.CHECK -> {
                return 0;
            }
            default -> {
                throw new IllegalArgumentException("Action is not valid: " + action);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAI() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handWon(final int winnings) {
        this.setChips(this.getChips() + winnings);
        this.statistics.incrementHandsWon(1);
        this.statistics.setBiggestWinIfSo(winnings);
        this.endHand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handLost() {
        this.endHand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatistics(final BasicStatistics stats) {
        stats.append(this.statistics);
        this.statistics.reset();
    }

    /**
     * Gets the controller associated with this user player.
     * @return the controller associated with this user player.
     */
    public UserPlayerController getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getGameState() {
        return super.getGameState();
    }
}
