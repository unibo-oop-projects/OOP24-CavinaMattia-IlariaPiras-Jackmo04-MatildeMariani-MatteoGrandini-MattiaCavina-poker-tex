package model.combination.api;

import java.util.Set;

import model.deck.api.Card;

public interface CombinationsCardGetter {

    /**
     * {@inheritDoc}
     */
    Set<Card> getPair();

    /**
     * {@inheritDoc}
     */
    Set<Card> getTwoPairs();

    /**
     * {@inheritDoc}
     */
    Set<Card> getTris();

    /**
     * {@inheritDoc}
     */
    Set<Card> getStraight();

    /**
     * {@inheritDoc}
     */
    Set<Card> getFullHouse();

    /**
     * {@inheritDoc}
     */
    Set<Card> getFlush();

    /**
     * {@inheritDoc}
     */
    Set<Card> getPoker();

    /**
     * {@inheritDoc}
     */
    Set<Card> getRoyalFlush();

}