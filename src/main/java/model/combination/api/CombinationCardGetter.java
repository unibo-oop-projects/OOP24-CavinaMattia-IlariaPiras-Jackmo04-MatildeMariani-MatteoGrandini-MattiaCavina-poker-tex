package model.combination.api;

import java.util.Set;

/**
 * Interface to get various poker combination card.
 * To know how that combination are formed can be read
 * {@link model.combination.api.CombinationRules}.
 * 
 * @param <X>
 *            Generic type to reuse in differt type of card.
 */
public interface CombinationCardGetter<X> {

    /**
     * Get the pairs combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         pair combination.
     */
    Set<X> getPair();

    /**
     * Get the two pairs combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         two-pair combination.
     */
    Set<X> getTwoPairs();

    /**
     * Get the tris combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         tris combination.
     */
    Set<X> getTris();

    /**
     * Get the straight combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         straight combination.
     */
    Set<X> getStraight();

    /**
     * Get the full house combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         Full-House combination.
     */
    Set<X> getFullHouse();

    /**
     * Get the flush combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         Flush combination.
     */
    Set<X> getFlush();

    /**
     * Get the poker combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         Poker combination.
     */
    Set<X> getPoker();

    /**
     * Get the royal flush combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         Royal-Flush combination.
     */
    Set<X> getStraightFlush();


    /**
     * Get the royal flush combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         Royal-Flush combination.
     */
    Set<X> getRoyalFlush();

    /**
     * Get the hight card combination from card Set.
     * 
     * @return
     *         {@link java.util.Set} of {@link model.deck.api.Card} that form the
     *         Hight-Card combination.
     */
    Set<X> getHightCard();

}
