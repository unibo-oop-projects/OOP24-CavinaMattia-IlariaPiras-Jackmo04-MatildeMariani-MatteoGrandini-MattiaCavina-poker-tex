package model.player;

import java.util.Objects;
import java.util.Set;

import model.deck.api.Card;
import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;
import model.temp.Combination;
import model.temp.Combinations;
import model.temp.State;

/**
 * Abstract class that implements the common methods of a generic player.
 * It also provides some abstract methods that must be implemented by the subclasses.
 * This class may be extended by both human and AI players.
 * @see Player
 */
public abstract class AbstractPlayer implements Player {

    private Set<Card> cards;
    private Role role;
    private Combination bestCombination;
    private int chips;
    private int totalFaseBet;

    /**
     * Constructor for the AbstractPlayer class.
     * @param initialChips the initial amount of chips that the player has.
     * @param initialRole the initial role of the player.
     */
    public AbstractPlayer(final int initialChips, final Role initialRole) {
        this.cards = Set.of();
        this.role = initialRole;
        this.chips = initialChips;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Card> getCards() {
        return Set.copyOf(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCards(final Set<Card> cards) {
        this.cards = Objects.requireNonNull(Set.copyOf(cards));
        this.bestCombination = Combinations.getBestCombination(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role getRole() {
        return this.role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(final Role role) {
        this.role = role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination getCombination() {
        return this.bestCombination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChipsLeft() {
        return chips > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalFaseBet() {
        return this.totalFaseBet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getChips() {
        return this.chips;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Action getAction(State currentState);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isAI();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void handWon(int winnings);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void handLost();

    /**
     * Used to set the current combination of the player.
     * @param combination the combination to set as the current combination.
     */
    protected void setCombination(final Combination combination) {
        this.bestCombination = combination;
    }

    /**
     * Used to set the chips of the player.
     * @param chips the amount of chips to set.
     */
    protected void setChips(final int chips) {
        this.chips = chips;
    }

    /**
     * Used to set the total bet of the player in the current {@link HandFase}.
     * @param totalFaseBet the amount of chips betted in the current fase.
     */
    protected void setTotalFaseBet(final int totalFaseBet) {
        this.totalFaseBet = totalFaseBet;
    }

}
