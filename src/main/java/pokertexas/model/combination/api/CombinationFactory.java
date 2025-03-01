package pokertexas.model.combination.api;

import pokertexas.model.combination.Combination;
import pokertexas.model.deck.api.Card;

/**
 * Interface to get poker's combination.
 * To know how that combination are formed can be read
 * {@link CombinationRules}.
 * 
 */
public interface CombinationFactory {

    /**
     * Get the pairs combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#PAIR} combination.
     */
    Combination<Card> getPair();

    /**
     * Get the two pairs combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#TWO_PAIRS} combination.
     */
    Combination<Card> getTwoPairs();

    /**
     * Get the tris combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#TRIS} combination.
     */
    Combination<Card> getTris();

    /**
     * Get the straight combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#STRAIGHT} combination.
     */
    Combination<Card> getStraight();

    /**
     * Get the full house combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#FULL_HOUSE} combination.
     */
    Combination<Card> getFullHouse();

    /**
     * Get the flush combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#FLUSH} combination.
     */
    Combination<Card> getFlush();

    /**
     * Get the poker combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#POKER} combination.
     */
    Combination<Card> getPoker();

    /**
     * Get the straight combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#STRAIGHT_FLUSH} combination.
     */
    Combination<Card> getStraightFlush();

    /**
     * Get the straight combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         {@link CombinationType#ROYAL_FLUSH} combination.
     */
    Combination<Card> getRoyalFlush();

    /**
     * Get the hight card combination from card Combination.
     * 
     * @return {@link Combination} of {@link Card} that form the
     *         Hight-Card combination.
     */
    Combination<Card> getHightCard();

}
