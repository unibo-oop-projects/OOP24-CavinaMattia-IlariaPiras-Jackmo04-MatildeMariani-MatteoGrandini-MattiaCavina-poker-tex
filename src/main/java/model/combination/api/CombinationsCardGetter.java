package model.combination.api;

import java.util.Set;

/**
 * Interface to get various poker combination card.
 * @param <X>
 */
public interface CombinationsCardGetter<X> {

    /**
     * Get the pair combination from card Set.
     * 
     * @return
     *         the pair combination from card Set.
     */
    Set<X> getPair();

    /**
     * Get the two pairs combination from card Set.
     * 
     * @return
     *         the two pairs combination from card Set.
     */
    Set<X> getTwoPairs();

    /**
     * Get the tris combination from card Set.
     * 
     * @return
     *         the tris combination from card Set.
     */
    Set<X> getTris();

    /**
     * Get the straight combination from card Set.
     * 
     * @return
     *         the straight combination from card Set.
     */
    Set<X> getStraight();

    /**
     * Get the full house combination from card Set.
     * 
     * @return
     *         the full house combination from card Set.
     */
    Set<X> getFullHouse();

    /**
     * Get the flush combination from card Set.
     * 
     * @return
     *         the flush combination from card Set.
     */
    Set<X> getFlush();

    /**
     * Get the poker combination from card Set.
     * 
     * @return
     *         the poker combination from card Set.
     */
    Set<X> getPoker();

    /**
     * Get the royal flush combination from card Set.
     * 
     * @return
     *         the royal flush combination from card Set.
     */
    Set<X> getRoyalFlush();

    /**
     * Get the hight card combination from card Set.
     * @return
     *        the hight card combination from card Set.
     */
    Set<X> getHightCard();

}
