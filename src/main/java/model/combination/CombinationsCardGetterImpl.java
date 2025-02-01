package model.combination;

import java.util.Comparator;
import java.util.LinkedList;
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

        private final List<Card> totalCardList = new LinkedList<>();
        private final static int COMBINATION_NUMBER = 5; ;

        /**
         * Constructor for CombinationsRulesImpl.
         * 
         * @param totalCardList
         *                      list of cards.
         * @throws IllegalAccessError
         *                              thrown when the list is empty.
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
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(safetyList).entrySet()
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
                var safetyList = Lists.newLinkedList(totalCardList);

                List<SimpleCard> twoPairSeedList = CombinationRulesUtilities.getSumOfSameNameCard(safetyList)
                                .entrySet()
                                .stream()
                                .filter(l -> l.getCount() == CombinationDimension.PAIR.getDimension())
                                .map(Entry::getElement)
                                .toList();

                return safetyList.stream().filter(t -> t.cardName() == twoPairSeedList.getFirst()
                                || t.cardName() == twoPairSeedList.getLast())
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getTris() {
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(safetyList).entrySet()
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
                var safetyList = Lists.newLinkedList(totalCardList);

                return CombinationRulesUtilities.getRoyalFlush(safetyList)
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
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.stream().filter(t -> t.seedName()
                                .equals(CombinationRulesUtilities.getSumOfSameSeedCard(safetyList).entrySet()
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
                var safetyList = Lists.newLinkedList(totalCardList);

                return safetyList.stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(safetyList).entrySet()
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
        

}
