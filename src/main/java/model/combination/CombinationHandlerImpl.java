package model.combination;

import java.util.Set;

import model.combination.api.CombinationHandler;
import model.combination.api.CombinationFactory;
import model.combination.api.CombinationRules;
import model.deck.api.Card;

/**
 * Class that find type of combination.
 * That class implemets {@link CombinationHandler} with
 * card type {@link Card}.
 */
public class CombinationHandlerImpl implements CombinationHandler<Card> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getBestCombination(final Set<Card> totalCard) {
        final CombinationRules<Card> combRules = new CombinationRulesImpl(totalCard,
                new CombinationUtilitiesImpl());
        final CombinationFactory combGetter = new CombinationFactoryImpl(totalCard);

        if (combRules.isRoyalFlush()) {
            return combGetter.getRoyalStraight(totalCard);
        } else if (combRules.isStraightFlush()) {
            return combGetter.getStraightFlush(totalCard);
        } else if (combRules.isPoker()) {
            return combGetter.getPoker(totalCard);
        } else if (combRules.isFlush()) {
            return combGetter.getFlush(totalCard);
        } else if (combRules.isFullHouse()) {
            return combGetter.getFullHouse(totalCard);
        } else if (combRules.isStraight()) {
            return combGetter.getStraight(totalCard);
        } else if (combRules.isTris()) {
            return combGetter.getTris(totalCard);
        } else if (combRules.isTwoPairs()) {
            return combGetter.getTwoPairs(totalCard);
        } else if (combRules.isPair()) {
            return combGetter.getPair(totalCard);
        } else {
            return combGetter.getHightCard(totalCard);
        }

    }

}
