package model.combination.api;

/**
 * Interface for for create different combination rules.
 * Is used to ask if some card are ones of {@link CombinationType}.
 * 
 * @param <X>
 * Generic type to reuse in differt type of card.
 */
public interface CombinationRules<X> {

    /**
     * Check if the combination is a pair.
     * Two card whith same seed.
     * 
     * @return true if the combination is a {@link CombinationType#PAIR}.
     */
    Boolean isPair();

    /**
     * Check if the combination is two pairs.
     * Two couples of cards with the same seed.
     * 
     * @return true if the combination is {@link CombinationType#TWO_PAIRS}.
     */
    Boolean isTwoPairs();

    /**
     * Check if the combination is a tris.
     * Tree cards with the same seed.
     * 
     * @return true if the combination is a {@link CombinationType#TRIS}.
     */
    Boolean isTris();

    /**
     * Check if the combination is a straight.
     * Five cards with consecutive values.
     * 
     * @return true if the combination is a {@link CombinationType#STRAIGHT}.
     */
    Boolean isStraight();

    /**
     * Check if the combination is a full house.
     * A pair and a tris.
     * 
     * @return true if the combination is a {@link CombinationType#FULL_HOUSE}.
     */
    Boolean isFullHouse();

    /**
     * Check if the combination is a flush.
     * Five cards with the same seed.
     * 
     * @return true if the combination is a {@link CombinationType#FLUSH}.
     */
    Boolean isFlush();

    /**
     * Check if the combination is a poker.
     * Four cards with the same seed.
     * 
     * @return true if the combination is a {@link CombinationType#POKER}.
     */
    Boolean isPoker();

    /**
     * Check if the combination is a royal flush.
     * Five cards with the same seed and consecutive values.
     * 
     * @return true if the combination is a {@link CombinationType#STRAIGHT_FLUSH}.
     */
    Boolean isStraightFlush();

    /**
     * Check if the combination is a royal flush.
     * Five cards with the same seed and consecutive values and the less card is ten.
     * 
     * @return true if the combination is a {@link CombinationType#ROYAL_FLUSH}.
     */
    Boolean isRoyalFlush();
}
