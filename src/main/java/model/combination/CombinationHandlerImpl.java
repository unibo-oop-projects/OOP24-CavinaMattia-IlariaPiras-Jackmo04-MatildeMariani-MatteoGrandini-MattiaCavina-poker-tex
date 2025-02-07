package model.combination;

import java.util.Set;

import model.combination.api.Combination;
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
        final CombinationRules<Card> combRules = new CombinationRulesImpl(totalCardList);
        final CombinationCardGetter<Card> combGetter = new CombinationCardGetterImpl(totalCardList);

        if (combRules.isRoyalFlush()) {
            return new Combination<>(combGetter.getRoyalFlush(), CombinationType.ROYAL_FLUSH);
        } else if (combRules.isPoker()) {
            return new Combination<>(combGetter.getPoker(), CombinationType.POKER);
        } else if (combRules.isFlush()) {
            return new Combination<>(combGetter.getFlush(), CombinationType.FLUSH);
        } else if (combRules.isFullHouse()) {
            return new Combination<>(combGetter.getFullHouse(), CombinationType.FULL_HOUSE);
        } else if (combRules.isStraight()) {
            return new Combination<>(combGetter.getStraight(), CombinationType.STRAIGHT);
        } else if (combRules.isTris()) {
            return new Combination<>(combGetter.getTris(), CombinationType.TRIS);
        } else if (combRules.isTwoPairs()) {
            return new Combination<>(combGetter.getTwoPairs(), CombinationType.TWO_PAIRS);
        } else if (combRules.isPair()) {
            return new Combination<>(combGetter.getPair(), CombinationType.PAIR);
        } else {
            return new Combination<>(combGetter.getHightCard(), CombinationType.HIGH_CARD);
        }

    }

}
