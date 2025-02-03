package model.combination;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset.Entry;

import model.combination.api.CombinationDimension;
import model.combination.api.CombinationsCardGetter;
import model.deck.api.Card;
import model.deck.api.SimpleCard;

/**
 * Class that implements the rules of the combinations.
 * All the methods are used to check how type of combination is it.
 */
public class CombinationsCardGetterImpl implements CombinationsCardGetter<Card> {

        private final List<Card> totalCardList = Lists.newLinkedList();
        private static final int COMBINATION_NUMBER = 5;

        /**
         * Constructor for CombinationsRulesImpl.
         * 
         * @param totalCardList
         *                      list of cards.
         * @throws IllegalAccessError
         *                            thrown when the list is empty.
         * 
         */
        public CombinationsCardGetterImpl(final Set<Card> totalCardList) {
                if (!totalCardList.isEmpty()) {
                        totalCardList.forEach(this.totalCardList::add);
                } else {
                        throw new IllegalArgumentException("Empty Set passed like Argument");
                }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getPair() {
                return getSafetyList().stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(getSafetyList()).entrySet()
                                                .stream()
                                                .filter(l -> l.getCount() == CombinationDimension.PAIR
                                                                .getDimension())
                                                .toList().getFirst().getElement()))
                                .collect(Collectors.toSet());

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getTwoPairs() {
                List<SimpleCard> twoPairSeedList = CombinationRulesUtilities.getSumOfSameNameCard(getSafetyList())
                                .entrySet()
                                .stream()
                                .filter(l -> l.getCount() == CombinationDimension.PAIR.getDimension())
                                .map(Entry::getElement)
                                .toList();

                return getSafetyList().stream().filter(t -> t.cardName() == twoPairSeedList.getFirst()
                                || t.cardName() == twoPairSeedList.getLast())
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getTris() {

                return getSafetyList().stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(getSafetyList()).entrySet()
                                                .stream()
                                                .filter(l -> l.getCount() == CombinationDimension.TRIS.getDimension())
                                                .toList().getFirst().getElement()))
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getStraight() {

                return CombinationRulesUtilities.getRoyalFlush(getSafetyList())
                                .stream()
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getFullHouse() {
                return Stream.concat(getPair().stream(), getTris().stream())
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getFlush() {
                return getSafetyList().stream().filter(t -> t.seedName()
                                .equals(CombinationRulesUtilities.getSumOfSameSeedCard(getSafetyList()).entrySet()
                                                .stream()
                                                .filter(l -> l.getCount() == CombinationDimension.STRAIGHT
                                                                .getDimension())
                                                .toList().getFirst().getElement()))
                                .collect(Collectors.toSet());

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getPoker() {
                return getSafetyList().stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(getSafetyList()).entrySet()
                                                .stream()
                                                .filter(l -> l.getCount() == CombinationDimension.POKER.getDimension())
                                                .toList().getFirst().getElement()))
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getRoyalFlush() {
                return getStraight();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getHightCard() {
                return totalCardList.stream()
                                .sorted(Comparator.comparing(Card::valueOfCard)).toList()
                                .reversed()
                                .stream()
                                .limit(COMBINATION_NUMBER)
                                .collect(Collectors.toSet());
        }

        private List<Card> getSafetyList() {
                return Lists.newLinkedList(this.totalCardList);
        }
}
