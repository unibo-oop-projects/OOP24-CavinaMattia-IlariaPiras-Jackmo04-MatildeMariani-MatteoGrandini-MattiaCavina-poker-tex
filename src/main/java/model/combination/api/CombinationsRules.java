package model.combination.api;

/**
 * Interface for combination rules.
 * 
 * @param <X>
 */
public interface CombinationsRules<X> {

    /**
     * Check if the combination is a pair.
     * Two card whith same seed.
     * 
     * @return true if the combination is a pair.
     */
    Boolean isPair();

    /**
     * Check if the combination is two pairs.
     * Two couples of cards with the same seed.
     * 
     * @return true if the combination is two pairs.
     */
    Boolean isTwoPairs();

    /**
     * Check if the combination is a tris.
     * Tree cards with the same seed.
     * 
     * @return true if the combination is a tris.
     */
    Boolean isTris();

    /**
     * Check if the combination is a straight.
     * Five cards with consecutive values.
     * 
     * @return true if the combination is a straight.
     */
    Boolean isStraight();

    /**
     * Check if the combination is a full house.
     * A pair and a tris.
     * 
     * @return true if the combination is a full house.
     */
    Boolean isFullHouse();

    /**
     * Check if the combination is a flush.
     * Five cards with the same seed.
     * 
     * @return true if the combination is a flush.
     */
    Boolean isFlush();

    /**
     * Check if the combination is a poker.
     * Four cards with the same seed.
     * 
     * @return true if the combination is a poker.
     */
    Boolean isPoker();

    /**
     * Check if the combination is a royal flush.
     * Five cards with the same seed and consecutive values.
     * 
     * @return true if the combination is a royal flush.
     */
    Boolean isRoyalFlush();
}
