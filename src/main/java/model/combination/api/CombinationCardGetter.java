package model.combination.api;

import java.util.Set;

/**
 * Interface to get various poker combination card.
 * To know how that combination are formed can be read
 * {@link CombinationRules}.
 * 
 * @param <X> Generic type to reuse in differt type of card.
 */
public interface CombinationCardGetter<X> {

    /**
     * Get the pairs combination from card Set.
     * 
     * @return {@link Set} of {@link Card} that form the
     *         {@link CombinationType#PAIR} combination.
     */
    Set<X> getPair();

    /**
     * Get the two pairs combination from card Set.
     * 
     * @return {@link Set} of {@link Card} that form the
     *         {@link CombinationType#TWO_PAIRS} combination.
     */
    Set<X> getTwoPairs();

    /**
     * Get the tris combination from card Set.
     * 
     * @return {@link Set} of {@link Card} that form the
     *         {@link CombinationType#TRIS} combination.
     */
    Set<X> getTris();

    /**
     * Get the straight combination from card Set.
     * 
     * @return {@link Set} of {@link Card} that form the
     *         {@link CombinationType#STRAIGHT} combination.
     *         Can be use to get {@link CombinationType#STRAIGHT_FLUSH}
     *         {@link CombinationType#ROYAL_FLUSH} too.
     */
    Set<X> getStraight();

    /**
     * Get the full house combination from card Set.
     * 
     * @return {@link Set} of {@link Card} that form the
     *         {@link CombinationType#FULL_HOUSE} combination.
     */
    Set<X> getFullHouse();

    /**
     * Get the flush combination from card Set.
     * 
     * @return {@link Set} of {@link Card} that form the
     *         {@link CombinationType#FLUSH} combination.
     */
    Set<X> getFlush();

    /**
     * Get the poker combination from card Set.
     * 
     * @return {@link Set} of {@link Card} that form the
     *         {@link CombinationType#POKER} combination.
     */
    Set<X> getPoker();

    /**
     * Get the hight card combination from card Set.
     * 
     * @return {@link Set} of {@link Card} that form the
     *         Hight-Card combination.
     */
    Set<X> getHightCard();

}
