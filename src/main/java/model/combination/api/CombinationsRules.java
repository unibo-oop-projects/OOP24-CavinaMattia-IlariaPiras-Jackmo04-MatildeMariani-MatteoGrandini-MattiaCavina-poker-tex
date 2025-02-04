package model.combination.api;

/**
 * Interface for for create different combination rules.
 * Is used to ask if some card are ones of {@link model.combination.api.CombinationType}.
 * 
 * @param <X>
 * Generic type to reuse in differt type of card.
 */
public interface CombinationsRules<X> {

    /**
     * Check if the combination is a pair.
     * Two card whith same seed.
     * 
     * @return true if the combination is a {@link model.combination.api.CombinationType#PAIR}.
     */
    Boolean isPair();

    /**
     * Check if the combination is two pairs.
     * Two couples of cards with the same seed.
     * 
     * @return true if the combination is {@link model.combination.api.CombinationType#TWO_PAIRS}.
     */
    Boolean isTwoPairs();

    /**
     * Check if the combination is a tris.
     * Tree cards with the same seed.
     * 
     * @return true if the combination is a {@link model.combination.api.CombinationType#TRIS}.
     */
    Boolean isTris();

    /**
     * Check if the combination is a straight.
     * Five cards with consecutive values.
     * 
     * @return true if the combination is a {@link model.combination.api.CombinationType#STRAIGHT}.
     */
    Boolean isStraight();

    /**
     * Check if the combination is a full house.
     * A pair and a tris.
     * 
     * @return true if the combination is a {@link model.combination.api.CombinationType#FULL_HOUSE}.
     */
    Boolean isFullHouse();

    /**
     * Check if the combination is a flush.
     * Five cards with the same seed.
     * 
     * @return true if the combination is a {@link model.combination.api.CombinationType#FLUSH}.
     */
    Boolean isFlush();

    /**
     * Check if the combination is a poker.
     * Four cards with the same seed.
     * 
     * @return true if the combination is a {@link model.combination.api.CombinationType#POKER}.
     */
    Boolean isPoker();

    /**
     * Check if the combination is a royal flush.
     * Five cards with the same seed and consecutive values.
     * 
     * @return true if the combination is a {@link model.combination.api.CombinationType#ROYAL_FLUSH}.
     */
    Boolean isRoyalFlush();
}
