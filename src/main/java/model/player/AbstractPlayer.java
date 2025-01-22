package model.player;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import model.deck.api.Card;
import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;
import model.temp.Combination;
import model.temp.State;

public abstract class AbstractPlayer implements Player {

    private Set<Card> cards;
    private Role role;
    private Combination bestCombination;
    private int chips;

    public AbstractPlayer(int initialChips, Role initialRole) {
        this.cards = Set.of();
        this.role = initialRole;
        this.chips = initialChips;
    }

    @Override
    public Set<Card> getCards() {
        return Set.copyOf(cards);
    }

    @Override
    public void giveCards(Set<Card> cards) {
        this.cards = Objects.requireNonNull(Set.copyOf(cards));
    }

    @Override
    public Role getRole() {
        return this.role;
    }

    @Override
    @Deprecated
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Combination getCombination() {
        return this.bestCombination;
    }

    @Override
    public boolean hasChipsLeft() {
        return chips > 0;
    }

    @Override
    public abstract Optional<Integer> getBet();

    @Override
    public abstract Action getAction(State currentState);

    @Override
    public abstract boolean isAI();

    @Override
    public abstract void handWon(int winnings);

    @Override
    public abstract void handLost();

    protected void setCombination(Combination combination) {
        this.bestCombination = combination;
    }

    protected void setChips(int chips) {
        this.chips = chips;
    }

}
