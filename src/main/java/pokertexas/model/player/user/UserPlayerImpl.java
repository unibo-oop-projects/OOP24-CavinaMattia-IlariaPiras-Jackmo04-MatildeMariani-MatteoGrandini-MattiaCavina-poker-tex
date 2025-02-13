package pokertexas.model.player.user;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import pokertexas.controller.player.user.UserPlayerController;
import pokertexas.model.combination.Combination;
import pokertexas.model.deck.api.Card;
import pokertexas.model.game.api.Phase;
import pokertexas.model.game.api.State;
import pokertexas.model.player.AbstractPlayer;
import pokertexas.model.player.api.Action;
import pokertexas.model.player.user.api.UserPlayer;
import pokertexas.model.statistics.BasicStatisticsImpl;
import pokertexas.model.statistics.api.BasicStatistics;

/**
 * Class representing a human player in the game.
 */
public class UserPlayerImpl extends AbstractPlayer implements UserPlayer {

    private final UserPlayerController controller;
    private final BasicStatistics statistics;

    /**
     * Constructor for the UserPlayer class.
     * @param id the identifier for the player.
     * @param initialChips the initial amount of chips that the player has.
     */
    public UserPlayerImpl(final int id, final int initialChips) {
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
        this.updateCombination();
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
    protected Combination<Card> updateCombination() {
        final var combination = super.updateCombination();
        this.statistics.setBestCombinationIfSo(combination.getType());
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
        this.statistics.incrementHandsWon(1);
        this.statistics.setBiggestWinIfSo(winnings);
        super.handWon(winnings);
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
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The controller is intended to be exposed")
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
