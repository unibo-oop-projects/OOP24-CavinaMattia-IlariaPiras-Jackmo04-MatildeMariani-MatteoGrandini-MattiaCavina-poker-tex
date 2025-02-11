package pokertexas.model.combination;

import java.util.Collections;
import java.util.Set;

import pokertexas.model.combination.api.CombinationType;

/**
 * Combination of List of {@link Card} and its type and value.
 * This class generate a combination whith propreties util for the game.
 * 
 * @param <X> type of card.
 */
public class Combination<X> {
    private final Set<X> combinationCard;
    private final CombinationType type;
    private final Set<X> totalCard;

    /**
     * Pubblic costructors of Combination to unmodifiable value of my Combination.
     * 
     * @param combinationCard Set of card that form combination.
     * @param type            Type of combination {@link CombinationType}.
     * @param totalCard       Set of total card sum the hand'card and table.
     */
    public Combination(final Set<X> combinationCard, final CombinationType type, final Set<X> totalCard) {
        this.combinationCard = Collections.unmodifiableSet(combinationCard);
        this.type = type;
        this.totalCard = Collections.unmodifiableSet(totalCard);

    }

    /**
     * Public getters for Set card combination.
     * 
     * @return {@link Set} of my combination cards.
     */
    public Set<X> getCombinationCard() {
        return combinationCard;
    }

    /**
     * Getters to a type of combination and its value.
     * 
     * @return {@link CombinationType} of combination cards.
     */
    public CombinationType getType() {
        return type;
    }

    /**
     * Public getters for Set card combination.
     * 
     * @return {@link Set} of my combination cards.
     */
    public Set<X> getTotalCard() {
        return totalCard;
    }

}
