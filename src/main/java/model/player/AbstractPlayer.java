package model.player;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.combination.Combination;
import model.combination.CombinationHandlerImpl;
import model.deck.api.Card;
import model.game.api.State;
import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;

/**
 * Abstract class that implements the common methods of a generic player.
 * It also provides some abstract methods that must be implemented by the subclasses.
 * This class may be extended by both human and AI players.
 * @see Player
 */
public abstract class AbstractPlayer implements Player {

    private final int id;
    private Set<Card> cards;
    private Optional<Role> role;
    private Combination<Card> bestCombination;
    private int chips;
    private int totalPhaseBet;
    private State gameState;

    /**
     * Constructor for the AbstractPlayer class.
     * @param id the id of the player.
     * @param initialChips the initial amount of chips that the player has.
     */
    public AbstractPlayer(final int id, final int initialChips) {
        this.id = id;
        this.cards = Set.of();
        this.role = Optional.empty();
        this.chips = initialChips;
        this.totalPhaseBet = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return this.id;
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
        this.bestCombination = cards.isEmpty() ? null : new CombinationHandlerImpl().getBestCombination(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Role> getRole() {
        return this.role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(final Role role) {
        this.role = Optional.ofNullable(role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getCombination() {
        return this.bestCombination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChipsLeft() {
        return this.chips > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalPhaseBet() {
        return this.totalPhaseBet;
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
    public void nextPhase() {
        this.totalPhaseBet = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Action getAction();

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
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Storing State mutable object is intented")
    public void setGameState(final State gameState) {
        this.gameState = Objects.requireNonNull(gameState);
    }

    /**
     * Used to get the current state of the game.
     * @return the current state of the game.
     */
    protected State getGameState() {
        return this.gameState;
    }

    /**
     * Used to set the current combination of the player.
     * @param combination the combination to set as the current combination.
     */
    protected void setCombination(final Combination<Card> combination) {
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
     * Used to set the total bet of the player in the current {@link HandPhase}.
     * @param totalPhaseBet the amount of chips betted in the current phase.
     */
    protected void setTotalPhaseBet(final int totalPhaseBet) {
        this.totalPhaseBet = totalPhaseBet;
    }

    /**
     * Used to update the combination of the player.
     * @param currentState the current state of the game.
     * @return the best combination of cards that the player can form 
     * with the available cards.
     */
    protected Combination<Card> updateCombination(final State currentState) {
        final var usableCards = Stream.concat(currentState.getCommunityCards().stream(), this.getCards().stream())
            .collect(Collectors.toSet());
        final var combination = new CombinationHandlerImpl().getBestCombination(usableCards);
        this.setCombination(combination);
        return combination;
    }

    /**
     * Resets the player's cards, role and total phase bet.
     */
    protected void endHand() {
        this.setCards(Set.of());
        this.setRole(null);
        this.setTotalPhaseBet(0);
    }

}
