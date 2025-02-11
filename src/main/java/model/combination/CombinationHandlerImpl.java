package model.combination;

import java.util.Set;

import model.combination.api.CombinationHandler;
import model.combination.api.CombinationType;
import model.combination.api.CombinationCardGetter;
import model.combination.api.CombinationRules;
import model.deck.api.Card;

/**
 * Class that find type of combination.
 * That class implemets {@link model.combination.api.CombinationHandler} with
 * card type {@link model.deck.api.Card}.
 */
public class CombinationHandlerImpl implements CombinationHandler<Card> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getBestCombination(final Set<Card> totalCardList) {
        final CombinationRules<Card> combRules = new CombinationRulesImpl(totalCardList,
                new CombinationUtilitiesImpl());
        final CombinationCardGetter<Card> combGetter = new CombinationCardGetterImpl(totalCardList,
                new CombinationUtilitiesImpl());

        if (combRules.isRoyalFlush()) {
            return new Combination<>(combGetter.getStraightCard(), CombinationType.ROYAL_FLUSH, totalCardList);
        } else if (combRules.isStraightFlush()) {
            return new Combination<>(combGetter.getStraightCard(), CombinationType.STRAIGHT_FLUSH, totalCardList);
        } else if (combRules.isPoker()) {
            return new Combination<>(combGetter.getPokerCard(), CombinationType.POKER, totalCardList);
        } else if (combRules.isFlush()) {
            return new Combination<>(combGetter.getFlushCard(), CombinationType.FLUSH, totalCardList);
        } else if (combRules.isFullHouse()) {
            return new Combination<>(combGetter.getFullHouseCard(), CombinationType.FULL_HOUSE, totalCardList);
        } else if (combRules.isStraight()) {
            return new Combination<>(combGetter.getStraightCard(), CombinationType.STRAIGHT, totalCardList);
        } else if (combRules.isTris()) {
            return new Combination<>(combGetter.getTrisCard(), CombinationType.TRIS, totalCardList);
        } else if (combRules.isTwoPairs()) {
            return new Combination<>(combGetter.getTwoPairsCard(), CombinationType.TWO_PAIRS, totalCardList);
        } else if (combRules.isPair()) {
            return new Combination<>(combGetter.getPairCard(), CombinationType.PAIR, totalCardList);
        } else {
            return new Combination<>(combGetter.getHightCardCard(), CombinationType.HIGH_CARD, totalCardList);
        }

    }

}
