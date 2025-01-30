package model.combination;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset.Entry;
import model.combination.api.CombinationDimension;
import model.combination.api.CombinationsRules;
import model.deck.api.Card;

/**
 * Class that implements the rules of the combinations.
 * All the methods are used to check how type of combination is it.
 */
public class CombinationsRulesImpl implements CombinationsRules<Card> {

        private final List<Card> totalCardList = new LinkedList<>();

        /**
         * Constructor for CombinationsRulesImpl.
         * 
         * @param totalCardList
         *                      list of cards.
         */
        public CombinationsRulesImpl(final Set<Card> totalCardList) {
                totalCardList.forEach(this.totalCardList::add);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isPair() {
               var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.size() >= CombinationDimension.PAIR.getDimension()
                                && CombinationRulesUtilities.getSumOfSameNameCard(safetyList).entrySet()
                                                .stream()
                                                .map(Entry::getCount)
                                                .toList()
                                                .contains(CombinationDimension.PAIR.getDimension());

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isTwoPairs() {
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.size() >= CombinationDimension.TWO_PAIRS.getDimension()
                                && CombinationRulesUtilities.getSumOfSameNameCard(safetyList).entrySet()
                                                .stream()
                                                .map(Entry::getCount)
                                                .filter(t -> t == CombinationDimension.PAIR.getDimension())
                                                .count() == 2;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isTris() {
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.size() >= CombinationDimension.TRIS.getDimension()
                                && CombinationRulesUtilities.getSumOfSameNameCard(safetyList).entrySet()
                                                .stream()
                                                .map(Entry::getCount)
                                                .toList()
                                                .contains(CombinationDimension.TRIS.getDimension());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isStraight() {
                var safetyList = Lists.newLinkedList(totalCardList);

                return CombinationRulesUtilities.getRoyalFlush(safetyList).size() == CombinationDimension.STRAIGHT
                                .getDimension();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isFullHouse() {
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.size() >= (CombinationDimension.STRAIGHT.getDimension())
                                && isPair() && isTris();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isFlush() {
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.size() >= CombinationDimension.STRAIGHT.getDimension()
                                && CombinationRulesUtilities.getSumOfSameSeedCard(safetyList).entrySet()
                                .stream()
                                .map(Entry::getCount)
                                .toList()
                                .contains(CombinationDimension.STRAIGHT.getDimension());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isPoker() {
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.size() >= CombinationDimension.POKER.getDimension()
                                && CombinationRulesUtilities.getSumOfSameNameCard(safetyList).entrySet()
                                                .stream()
                                                .map(Entry::getCount)
                                                .toList()
                                                .contains(CombinationDimension.POKER.getDimension());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isRoyalFlush() {
                var safetyList = Lists.newLinkedList(totalCardList);
                
                return isStraight()
                                && CombinationRulesUtilities.getRoyalFlush(safetyList).stream()
                                                .map(t -> t.seedName())
                                                .distinct()
                                                .count() == 1;
        }
       
}
