package pokertexas.model.player.ai;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.ImmutablePair;

import pokertexas.model.game.api.Phase;
import pokertexas.model.game.api.State;
import pokertexas.model.player.AbstractPlayer;
import pokertexas.model.player.ai.api.AIPlayer;
import pokertexas.model.player.api.Action;
import pokertexas.model.player.api.Role;
import pokertexas.model.statistics.api.BasicStatistics;

/**
 * This class provides a basic implementation of the {@link AIPlayer} interface.
 * It provides basic methods to handle the player's actions during a hand.
 */
abstract class AbstractAIPlayer extends AbstractPlayer implements AIPlayer {

    private static final int RAISE_REDUCING_FACTOR = 10;

    private final double raisingFactor;
    private final int standardRaise;

    /**
     * Creates a new AI player with the given initial amount of chips, role and raising factor.
     * @param id the id of the player
     * @param initialChips the initial amount of chips of the player
     * @param raisingFactor a double determining by how much the player will raise
     */
    AbstractAIPlayer(final int id, final int initialChips, final double raisingFactor) {
        super(id, initialChips);
        this.raisingFactor = raisingFactor;
        this.standardRaise = initialChips / RAISE_REDUCING_FACTOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction() {
        this.assertValidState();
        this.updateCombination();
        final List<ImmutablePair<Predicate<State>, Function<State, Action>>> actions = List.of(
            ImmutablePair.of(state -> !this.hasChipsLeft(), state -> this.check()),
            ImmutablePair.of(this::hasToPayBlind, this::call),
            ImmutablePair.of(state -> this.shouldRaise(), this::raise),
            ImmutablePair.of(this::canCheck, state -> this.check()),
            ImmutablePair.of(state -> this.shouldCall(), this::call),
            ImmutablePair.of(state -> true, state -> this.fold())
        );
        final var currentState = this.getGameState();
        return actions.stream()
            .filter(pair -> pair.getLeft().test(currentState))
            .findFirst()
            .map(pair -> pair.getRight().apply(currentState))
            .orElseThrow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAI() {
        return true;
    }

    /**
     * {@inheritDoc}
     * This implementation does nothing, 
     * because the AI player does not need to update statistics.
     */
    @Override
    public void updateStatistics(final BasicStatistics stats) {
    }

    /**
     * Returns the amount of chips the player is required to call in the current state.
     * This amount is adjusted considering blinds.
     * @param state the current state of the game
     * @return the amount of chips that the player is required to call
     */
    protected int requiredBet(final State state) {
        return (int) (state.getCurrentBet() * this.getRole()
            .filter(r -> this.hasToPayBlind(state))
            .map(Role::getMultiplier)
            .orElse(1.0)
        );
    }

    /**
     * Returns whether the player should call in the current state.
     * @return whether the player should call in the current state
     */
    protected abstract boolean shouldCall();

    /**
     * Returns whether the player should raise in the current state.
     * @return whether the player should raise in the current state
     */
    protected abstract boolean shouldRaise();

    private void assertValidState() {
        if (this.getGameState() == null) {
            throw new IllegalStateException("Player must have a game state to play");
        }
        if (!this.hasEnoughCards()) {
            throw new IllegalStateException("Player must have 2 cards to play");
        }
    }

    @SuppressWarnings("PMD.UnusedPrivateMethod") // False positive, method is referenced in the getAction method
    private Action call(final State state) {
        this.makeBet(requiredBet(state));
        return this.actionOrAllIn(Action.CALL);
    }

    @SuppressWarnings("PMD.UnusedPrivateMethod") // False positive, method is referenced in the getAction method
    private Action raise(final State state) {
        this.makeBet((int) (requiredBet(state) + this.standardRaise * raisingFactor));
        return this.actionOrAllIn(Action.RAISE);
    }

    private Action check() {
        return Action.CHECK;
    }

    private Action fold() {
        return Action.FOLD;
    }

    private boolean hasEnoughCards() {
        return this.getCards().size() == 2;
    }

    private boolean hasToPayBlind(final State state) {
        return this.getRole()
            .filter(r -> this.getTotalPhaseBet() == 0 && state.getHandPhase() == Phase.PREFLOP)
            .isPresent();
    }

    @SuppressWarnings("PMD.UnusedPrivateMethod") // False positive, method is referenced in the getAction method
    private boolean canCheck(final State state) {
        return state.getCurrentBet() == this.getTotalPhaseBet();
    }

    private int maxBetToReach(final int amount) {
        return Math.min(getChips(), amount);
    }

    private Action actionOrAllIn(final Action action) {
        return this.getChips() == 0 ? Action.ALL_IN : action;
    }

    private void makeBet(final int amount) {
        final var diff = amount - this.getTotalPhaseBet();
        final var actualBet = maxBetToReach(diff);
        this.setTotalPhaseBet(this.getTotalPhaseBet() + actualBet);
        this.setChips(getChips() - actualBet);
    }

}
