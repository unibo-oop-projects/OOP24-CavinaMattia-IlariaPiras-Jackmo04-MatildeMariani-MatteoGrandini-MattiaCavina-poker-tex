package model.player.ai;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.player.AbstractPlayer;
import model.player.ai.api.AIPlayer;
import model.player.api.Action;
import model.player.api.Role;
import model.combination.CombinationHandlerImpl;
import model.game.api.Phase;
import model.game.api.State;

/**
 * This class provides a basic implementation of the {@link AIPlayer} interface.
 * It provides basic methods to handle the player's actions during a hand.
 */
public abstract class AbstractAIPlayer extends AbstractPlayer implements AIPlayer {

    private final double raisingFactor;
    private final int standardRaise;

    /**
     * Creates a new AI player with the given initial amount of chips, role and raising factor.
     * @param id the id of the player.
     * @param initialChips the initial amount of chips of the player.
     * @param raisingFactor a double determining by how much the player will raise.
     */
    AbstractAIPlayer(final int id, final int initialChips, final double raisingFactor) {
        super(id, initialChips);
        this.raisingFactor = raisingFactor;
        this.standardRaise = initialChips / 10;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(final State currentState) {
        if (this.getCards().size() != 2) {
            throw new IllegalStateException("Player must have 2 cards to play");
        }
        this.updateCombination(currentState);
        if (!this.hasChipsLeft()) {
            return Action.CHECK;
        }
        final var hasToPayBlind = this.getRole().isPresent() && this.getTotalPhaseBet() == 0;
        if (hasToPayBlind) {
            this.makeBet(requiredBet(currentState));
            return this.actionOrAllIn(Action.CALL);
        }
        if (shouldRaise(currentState)) {
            this.makeBet((int) (currentState.getCurrentBet() + this.standardRaise * raisingFactor));
            return this.actionOrAllIn(Action.RAISE);
        }
        if (canCheck(currentState)) {
            return Action.CHECK;
        }
        if (shouldCall(currentState)) {
            this.makeBet(currentState.getCurrentBet());
            return this.actionOrAllIn(Action.CALL);
        }
        return Action.FOLD;
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
     */
    @Override
    public void handWon(final int winnings) {
        this.setChips(this.getChips() + winnings);
        this.endhand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handLost() {
        this.endhand();
    }

    /**
     * Returns whether the player should call in the current state.
     * @param currentState the current state of the game.
     * @return whether the player should call in the current state.
     */
    protected abstract boolean shouldCall(State currentState);

    /**
     * Returns whether the player should raise in the current state.
     * @param currentState the current state of the game.
     * @return whether the player should raise in the current state.
     */
    protected abstract boolean shouldRaise(State currentState);

    /**
     * Returns the amount of chips required to call or raise in the current state.
     * This amount is multiplied by the role's multiplier if the hand phase is preflop.
     * @param currentState the current state of the game.
     * @return the amount of chips required to call or raise in the current state.
     */
    protected int requiredBet(final State currentState) {
        return (int) (currentState.getCurrentBet() * this.getRole()
            .filter(r -> currentState.getHandPhase() == Phase.PREFLOP)
            .map(Role::getMultiplier)
            .orElse(1.0)
        );
    }

    private int maxBetToReach(final int amount) {
        return Math.min(getChips(), amount);
    }

    private boolean canCheck(final State currentState) {
        return currentState.getCurrentBet() == this.getTotalPhaseBet();
    }

    private void makeBet(final int amount) {
        final var diff = amount - this.getTotalPhaseBet();
        final var actualBet = maxBetToReach(diff);
        this.setTotalPhaseBet(this.getTotalPhaseBet() + actualBet);
        this.setChips(getChips() - actualBet);
    }

    private void endhand() {
        this.setCards(Set.of());
        this.setRole(null);
    }

    private void updateCombination(final State currentState) {
        final var usableCards = Stream.concat(currentState.getCommunityCards().stream(), this.getCards().stream())
            .collect(Collectors.toSet());
        this.setCombination(new CombinationHandlerImpl().getBestCombination(usableCards));
    }

    private Action actionOrAllIn(final Action action) {
        return this.getChips() == 0 ? Action.ALL_IN : action;
    }

}
