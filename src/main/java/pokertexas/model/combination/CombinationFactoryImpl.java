package pokertexas.model.combination;

import java.util.Collections;
import java.util.Set;

import pokertexas.model.combination.api.CombinationCardGetter;
import pokertexas.model.combination.api.CombinationFactory;
import pokertexas.model.combination.api.CombinationType;
import pokertexas.model.deck.api.Card;

/**
 * Class that implemente {@link CombinationFactory} to generate all
 * {@link CombinationType} of combination.
 */
public final class CombinationFactoryImpl implements CombinationFactory {

    private final CombinationCardGetter<Card> combGetter;
    private final Set<Card> totalCard;

    /**
     * Costructor of class.
     * 
     * @param totalCard All card that form combination, hand and table cards.
     */
    public CombinationFactoryImpl(final Set<Card> totalCard) {
        this.totalCard = Collections.unmodifiableSet(totalCard);
        this.combGetter = new CombinationCardGetterImpl(totalCard,
                new CombinationUtilitiesImpl());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getPair() {
        return new Combination<>(combGetter.getPairCard(), CombinationType.PAIR, totalCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getTwoPairs() {
        return new Combination<>(combGetter.getTwoPairsCard(), CombinationType.TWO_PAIRS, totalCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getTris() {
        return new Combination<>(combGetter.getTrisCard(), CombinationType.TRIS, totalCard);
    }

    @Override
    public Combination<Card> getStraight() {
        return new Combination<>(combGetter.getStraightCard(), CombinationType.STRAIGHT, totalCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getFullHouse() {
        return new Combination<>(combGetter.getFullHouseCard(), CombinationType.FULL_HOUSE, totalCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getFlush() {
        return new Combination<>(combGetter.getFlushCard(), CombinationType.FLUSH, totalCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getPoker() {
        return new Combination<>(combGetter.getPokerCard(), CombinationType.POKER, totalCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getStraightFlush() {
        return new Combination<>(combGetter.getStraightCard(), CombinationType.STRAIGHT_FLUSH, totalCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getRoyalFlush() {
        return new Combination<>(combGetter.getStraightCard(), CombinationType.ROYAL_FLUSH, totalCard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getHightCard() {
        return new Combination<>(combGetter.getHightCardCard(), CombinationType.HIGH_CARD, totalCard);
    }

}
