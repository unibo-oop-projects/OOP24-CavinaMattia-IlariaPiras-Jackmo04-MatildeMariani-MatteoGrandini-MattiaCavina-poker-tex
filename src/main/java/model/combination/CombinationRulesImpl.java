package model.combination;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset.Entry;
import model.combination.api.CombinationDimension;
import model.combination.api.CombinationUtilities;
import model.combination.api.CombinationRules;
import model.deck.api.Card;
import model.deck.api.SimpleCard;

/**
 * Class that implements the rules of the combinations.
 * All the methods are used to check how
 * {@link model.combination.api.CombinationType} is it.
 */
public class CombinationRulesImpl implements CombinationRules<Card> {

        private final List<Card> totalCardList = Lists.newLinkedList();
        private final CombinationUtilities rulesUtilities = new CombinationUtilitiesImpl();

        /**
         * Constructor for CombinationsRulesImpl.
         * 
         * @param totalCardList
         *                      list of cards.
         */
        public CombinationRulesImpl(final Set<Card> totalCardList) {
                totalCardList.forEach(this.totalCardList::add);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isPair() {
                return isLongEnough(CombinationDimension.PAIR.getDimension())
                                && rulesUtilities.getSumOfSameNameCard(getSafetyList()).entrySet()
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
                return isLongEnough(CombinationDimension.TWO_PAIRS.getDimension())
                                && rulesUtilities.getSumOfSameNameCard(getSafetyList()).entrySet()
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
                return isLongEnough(CombinationDimension.TRIS.getDimension())
                                && rulesUtilities.getSumOfSameNameCard(getSafetyList()).entrySet()
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
                return rulesUtilities.getRoyalFlush(getSafetyList()).size() == CombinationDimension.STRAIGHT
                                .getDimension();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isFullHouse() {
                return isLongEnough(CombinationDimension.STRAIGHT.getDimension())
                                && isPair()
                                && isTris();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isFlush() {
                return isLongEnough(CombinationDimension.STRAIGHT.getDimension())
                                && rulesUtilities.getSumOfSameSeedCard(getSafetyList()).entrySet()
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
                return isLongEnough(CombinationDimension.POKER.getDimension())
                                && rulesUtilities.getSumOfSameNameCard(getSafetyList()).entrySet()
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
                return isStraight()
                                && rulesUtilities.getRoyalFlush(getSafetyList()).stream()
                                                .map(Card::seedName)
                                                .distinct()
                                                .count() == 1
                                && rulesUtilities.getRoyalFlush(getSafetyList()).stream()
                                                .mapToInt(Card::valueOfCard)
                                                .min().getAsInt() == SimpleCard.TEN.getValueOfCard();
        }

        private List<Card> getSafetyList() {
                return Lists.newLinkedList(this.totalCardList);
        }

        private boolean isLongEnough(final Integer combinationDimension) {
                return getSafetyList().size() >= combinationDimension;
        }
}
