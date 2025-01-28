package model.combination;

import java.util.Set;

import model.combination.api.Combination;
import model.combination.api.CombinationHandler;
import model.combination.api.CombinationType;
import model.combination.api.CombinationsRules;
import model.deck.api.Card;

/**
 * Class that find type of combination.
 */
public class CombinationHandlerImpl implements CombinationHandler<Card> {


    /**
     * {@inheritDoc}
     */
    @Override
    public Combination<Card> getCombination(final Set<Card> totalCardList) {
        CombinationsRules<Card> combRules = new CombinationsRulesImpl(totalCardList);
        Integer tieBreakValue = totalCardList.stream()
                .mapToInt(Card::valueOfCard).sum();

        if (combRules.isRoyalFlush()) {
            return new Combination<>(totalCardList, CombinationType.ROYAL_FLUSH, tieBreakValue);
        } else if (combRules.isPoker()) {
            return new Combination<>(totalCardList, CombinationType.POKER, tieBreakValue);
        } else if (combRules.isFlush()) {
            return new Combination<>(totalCardList, CombinationType.FLUSH, tieBreakValue);
        } else if (combRules.isFullHouse()) {
            return new Combination<>(totalCardList, CombinationType.FULL_HOUSE, tieBreakValue);
        } else if (combRules.isStraight()) {
            return new Combination<>(totalCardList, CombinationType.STRAIGHT, tieBreakValue);
        } else if (combRules.isTris()) {
            return new Combination<>(totalCardList, CombinationType.TRIS, tieBreakValue);
        } else if (combRules.isTwoPairs()) {
            return new Combination<>(totalCardList, CombinationType.TWO_PAIRS, tieBreakValue);
        } else if (combRules.isPair()) {
            return new Combination<>(totalCardList, CombinationType.PAIR, tieBreakValue);
        } else {
            return new Combination<>(totalCardList, CombinationType.HIGH_CARD, tieBreakValue);
        }

    }

}
