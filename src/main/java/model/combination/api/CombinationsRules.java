package model.combination.api;

public interface CombinationsRules<X> {

    /*
     * Check if the combination is a pair.
     * Two card whith same seed.
     * @return true if the combination is a pair.
     */
    Boolean pair();

    /**
     * Check if the combination is two pairs.
     * Two couples of cards with the same seed.
     * @return  true if the combination is two pairs.
     */
    Boolean twoPairs();

    /**
     * Check if the combination is a tris.
     * Tree cards with the same seed.
     * @return  true if the combination is a tris.
     */
    Boolean tris();

    /**
     * Check if the combination is a straight.
     * Five cards with consecutive values.
     * @return   true if the combination is a straight.
     */
    Boolean straight();

    /**
     * Check if the combination is a full house.
     * A pair and a tris.
     * @return  true if the combination is a full house.
     */
    Boolean fullHouse();
    /**
     * Check if the combination is a flush.
     * Five cards with the same seed.
     * @return  true if the combination is a flush.
     */
    Boolean flush();

    /**
     * Check if the combination is a poker.
     * Four cards with the same seed.
     * @return  true if the combination is a poker.
     */
    Boolean poker();

    /**
     * Check if the combination is a royal flush.
     * Five cards with the same seed and consecutive values.
     * @return  true if the combination is a royal flush.
     */
    Boolean royalFlush();
}