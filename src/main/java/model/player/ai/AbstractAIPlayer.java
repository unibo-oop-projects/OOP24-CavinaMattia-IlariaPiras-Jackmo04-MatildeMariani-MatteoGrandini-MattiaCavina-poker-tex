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
    private Phase currentPhase;

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
        this.currentPhase = Phase.PREFLOP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(final State currentState) {
        if (!this.hasEnoughCards()) {
            throw new IllegalStateException("Player must have 2 cards to play");
        }
        this.updateCombination(currentState);
        final var currentHandPhase = currentState.getHandPhase();
        final var currentBet = currentState.getCurrentBet();
        if (this.isNewPhase(currentHandPhase)) {
            this.endPhase(currentHandPhase);
        }
        if (!this.hasChipsLeft()) {
            return this.check(); // TODO maybe change this to all in
        }
        if (this.hasToPayBlind(currentHandPhase)) {
            return this.call(currentBet, currentHandPhase);
        }
        if (this.shouldRaise(currentState)) {
            return this.raise(currentBet, currentHandPhase);
        }
        if (this.canCheck(currentBet)) {
            return this.check();
        }
        if (this.shouldCall(currentState)) {
            return this.call(currentBet, currentHandPhase);
        }
        return this.fold();
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
     * Returns the amount of chips the player is required to call in the current state.
     * This amount is adjusted considering blinds.
     * @param currentBet the current bet.
     * @param currentHandPhase the current phase of the hand.
     * @return the amount of chips that the player is required to call.
     */
    protected int requiredBet(final int currentBet, final Phase currentHandPhase) {
        return (int) (currentBet * this.getRole()
            .filter(r -> this.hasToPayBlind(currentHandPhase))
            .map(Role::getMultiplier)
            .orElse(1.0)
        );
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

    private Action call(final int currentBet, final Phase currentHandPhase) {
        this.makeBet(requiredBet(currentBet, currentHandPhase));
        return this.actionOrAllIn(Action.CALL);
    }

    private Action raise(final int currentBet, final Phase currentHandPhase) {
        this.makeBet((int) (requiredBet(currentBet, currentHandPhase) + this.standardRaise * raisingFactor));
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

    private boolean isNewPhase(final Phase currentHandPhase) {
        return currentHandPhase != this.currentPhase;
    }

    private boolean hasToPayBlind(final Phase currentHandPhase) {
        return this.getRole()
            .filter(r -> this.getTotalPhaseBet() == 0 && currentHandPhase == Phase.PREFLOP)
            .isPresent();
    }

    private boolean canCheck(final int currentBet) {
        return currentBet == this.getTotalPhaseBet();
    }

    private int maxBetToReach(final int amount) {
        return Math.min(getChips(), amount);
    }

    private Action actionOrAllIn(final Action action) {
        return this.getChips() == 0 ? Action.ALL_IN : action;
    }

    private void updateCombination(final State currentState) {
        final var usableCards = Stream.concat(currentState.getCommunityCards().stream(), this.getCards().stream())
            .collect(Collectors.toSet());
        this.setCombination(new CombinationHandlerImpl().getBestCombination(usableCards));
    }

    private void makeBet(final int amount) {
        final var diff = amount - this.getTotalPhaseBet();
        final var actualBet = maxBetToReach(diff);
        this.setTotalPhaseBet(this.getTotalPhaseBet() + actualBet);
        this.setChips(getChips() - actualBet);
    }

    private void endPhase(final Phase currentHandPhase) {
        this.currentPhase = currentHandPhase;
        this.setTotalPhaseBet(0);
    }

    private void endhand() {
        this.setCards(Set.of());
        this.setRole(null);
        this.setTotalPhaseBet(0);
    }

}
