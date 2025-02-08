package model.combination.api;

/**
 * Enum for the dimension number card to compose Poker's combination .
 * Can be used for find correct {@link CombinationType}.
 */
public enum CombinationDimension {

    /**
     * Number of Card of Pair.
     */
    PAIR(2),
    /**
     * Number of Card of Two-Pair.
     */
    TWO_PAIRS(4),
    /**
     * Number of Card of Tris.
     */
    TRIS(3),
    /**
     * Number of Card of Poker.
     */
    POKER(4),
    /**
     * Number of Card of Full-House, Straight, Straight Flush, Royal-Flush.
     */
    STRAIGHT(5);

    private final int dimension;

    /**
     * Constructor for CombinationDimension.
     * 
     * @param dimension Number of card to form combination.
     */
    CombinationDimension(final int dimension) {
        this.dimension = dimension;
    }

    /**
     * Method to keep from enum its relative number of card to form its combination.
     * 
     * @return minumum number of cards to have a combination.
     */
    public int getDimension() {
        return this.dimension;
    }

}
