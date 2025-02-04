package model.combination;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset.Entry;
import model.combination.api.CombinationDimension;
import model.combination.api.CombinationRulesUtilities;
import model.combination.api.CombinationsRules;
import model.deck.api.Card;
import model.deck.api.SimpleCard;

/**
 * Class that implements the rules of the combinations.
 * All the methods are used to check how {@link model.combination.api.CombinationType} is it.
 */
public class CombinationsRulesImpl implements CombinationsRules<Card> {

        private final List<Card> totalCardList = Lists.newLinkedList();
        private final CombinationRulesUtilities rulesUtilities = new CombinationRulesUtilitiesImpl();

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
                return getSafetyList().size() >= CombinationDimension.PAIR.getDimension()
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
                return getSafetyList().size() >= CombinationDimension.TWO_PAIRS.getDimension()
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
                return getSafetyList().size() >= CombinationDimension.TRIS.getDimension()
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
                return getSafetyList().size() >= CombinationDimension.STRAIGHT.getDimension()
                                && isPair() && isTris();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean isFlush() {
                return getSafetyList().size() >= CombinationDimension.STRAIGHT.getDimension()
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
                return getSafetyList().size() >= CombinationDimension.POKER.getDimension()
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
}
