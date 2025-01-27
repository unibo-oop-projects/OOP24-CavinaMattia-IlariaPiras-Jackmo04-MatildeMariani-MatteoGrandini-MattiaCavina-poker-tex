package model.combination;

import java.util.List;
import model.combination.api.CombinationDimension;
import model.combination.api.CombinationsRules;
import model.deck.api.Card;

/**
 * Class that implements the rules of the combinations.
 * All the methods are used to check how type of combination is it.
 */
public class CombinationsRulesImpl implements CombinationsRules<Card> {

        private final List<Card> totalCardList;

        /**
         * Constructor for CombinationsRulesImpl.
         * 
         * @param totalCardList
         *                      list of cards.
         */
        public CombinationsRulesImpl(final List<Card> totalCardList) {
                this.totalCardList = totalCardList;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isPair() {
                return totalCardList.size() >= CombinationDimension.PAIR.getDimension()
                                && CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                                                .filter(t -> t == CombinationDimension.PAIR.getDimension()).count() == 1;

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isTwoPairs() {
                return totalCardList.size() >= CombinationDimension.TWO_PAIRS.getDimension()
                                && CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                                                .filter(t -> t == CombinationDimension.PAIR.getDimension()).count() == 2;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isTris() {
                return totalCardList.size() >= CombinationDimension.TRIS.getDimension()
                                && CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                                                .filter(t -> t == CombinationDimension.TRIS.getDimension()).count() == 1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isStraight() {
                return CombinationRulesUtilities.getRoyalFlush(totalCardList).size() == CombinationDimension.STRAIGHT
                                .getDimension();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isFullHouse() {
                return totalCardList.size() >= (CombinationDimension.STRAIGHT.getDimension())
                                && isPair() && isTris();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isFlush() {
                return totalCardList.size() >= CombinationDimension.STRAIGHT.getDimension()
                                && CombinationRulesUtilities.getSumOfSameSeedCard(totalCardList).values().stream()
                                                .filter(t -> t == CombinationDimension.STRAIGHT.getDimension())
                                                .count() == 1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isPoker() {
                return totalCardList.size() >= CombinationDimension.POKER.getDimension()
                                && CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                                                .filter(t -> t == CombinationDimension.POKER.getDimension())
                                                .count() == 1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isRoyalFlush() {
                return isStraight()
                                && CombinationRulesUtilities.getRoyalFlush(totalCardList).stream()
                                                .map(t -> t.seedName())
                                                .distinct()
                                                .count() == 1;
        }

}
