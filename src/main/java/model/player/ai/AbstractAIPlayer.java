package model.player.ai;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.player.AbstractPlayer;
import model.player.ai.api.AIPlayer;
import model.player.api.Action;
import model.player.api.Role;
import model.temp.Blind;
import model.combination.CombinationHandlerImpl;
import model.game.api.Phase;
import model.game.api.State;

/**
 * This class provides a basic implementation of the {@link AIPlayer} interface.
 * It provides basic methods to handle the player's actions during a hand.
 */
public abstract class AbstractAIPlayer extends AbstractPlayer implements AIPlayer {

    private static final int BASIC_BET = 1000;
    private final double raisingFactor;
    private boolean paidBlind;

    /**
     * Creates a new AI player with the given initial amount of chips, role and raising factor.
     * @param initialChips the initial amount of chips of the player.
     * @param initialRole the initial role of the player.
     * @param raisingFactor a double determining by how much the player will raise.
     */
    AbstractAIPlayer(final int initialChips, final Role initialRole, final double raisingFactor) {
        super(initialChips, initialRole);
        this.raisingFactor = raisingFactor;
        this.paidBlind = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(final State currentState) {
        //this.paidBlind = this.getRole() == Role.REGULAR || this.getRole() == Role.DEALER;
        if (this.getCards().size() != 2) {
            throw new IllegalStateException("Player must have 2 cards to play");
        }
        this.updateCombination(currentState);
        if (shouldRaise(currentState)) {
            this.makeBet((int) (currentState.getCurrentBet() + BASIC_BET * raisingFactor));
            return Action.RAISE;
        }
        if (canCheck(currentState)) {
            return Action.CHECK;
        }
        if (shouldCall(currentState)) {
            this.makeBet(currentState.getCurrentBet());
            return Action.CALL;
        }
        if (!this.paidBlind) {
            this.makeBet(requiredBet(currentState));
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
     * @param currentState
     * @return the amount of chips required to call or raise in the current state.
     */
    protected int requiredBet(final State currentState) {
        if (currentState.getHandPhase() == Phase.PREFLOP) {
            return (int) (currentState.getCurrentBet() * switch (getRole()) {
                case SMALL_BLIND -> Blind.SMALL.getMultiplier();
                case BIG_BLIND -> Blind.BIG.getMultiplier();
                default -> 1;
            });
        } else {
            return currentState.getCurrentBet();
        }
    }

    private int maxBetToReach(final int amount) {
        return (int) Math.min(getChips(), amount);
    }

    private boolean canCheck(final State currentState) {
        return requiredBet(currentState) == getTotalPhaseBet();
    }

    private void makeBet(final int amount) {
        var actualBet = maxBetToReach(amount) - this.getTotalPhaseBet();
        this.setTotalPhaseBet(this.getTotalPhaseBet() + actualBet);
        this.setChips(getChips() - actualBet);
        this.paidBlind = true;
    }

    private void endhand() {
        this.setCards(Set.of());
        this.paidBlind = false;
    }

    private void updateCombination(final State currentState) {
        var usableCards = Stream.concat(currentState.getCommunityCards().stream(), this.getCards().stream())
            .collect(Collectors.toSet());
        this.setCombination(new CombinationHandlerImpl().getCombination(usableCards));
    }

}
