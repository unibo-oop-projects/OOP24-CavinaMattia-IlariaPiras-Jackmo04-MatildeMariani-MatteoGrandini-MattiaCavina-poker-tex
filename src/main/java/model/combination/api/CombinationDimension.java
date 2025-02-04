package model.combination.api;

/**
 * Enum for the dimension of the Poker's combination.
 * Can be used for find correct {@link model.combination.api.CombinationType}.
 */
public enum CombinationDimension {

    // CHECKSTYLE: JavadocVariable OFF
    PAIR(2),
    TWO_PAIRS(4),
    TRIS(3),
    POKER(4),
    STRAIGHT(5);
    // CHECKSTYLE: JavadocVariable ON

    private final int dimension;

    /**
     * Constructor for CombinationDimension.
     * 
     * @param dimension
     *                  Number of card to form combination.
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
