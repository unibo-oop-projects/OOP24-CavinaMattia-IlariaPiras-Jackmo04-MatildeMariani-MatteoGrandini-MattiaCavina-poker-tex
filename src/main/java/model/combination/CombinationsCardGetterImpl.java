package model.combination;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Multiset.Entry;

import model.combination.api.CombinationDimension;
import model.combination.api.CombinationsCardGetter;
import model.deck.api.Card;
import model.deck.api.SimpleCard;

/**
 * Class that implements the rules of the combinations.
 * All the methods are used to check how type of combination is it.
 */
public class CombinationsCardGetterImpl implements CombinationsCardGetter {

        private final List<Card> totalCardList = new LinkedList<>();

        /**
         * Constructor for CombinationsRulesImpl.
         * 
         * @param totalCardList
         *                      list of cards.
         */
        public CombinationsCardGetterImpl(final Set<Card> totalCardList) {
                totalCardList.forEach(this.totalCardList::add);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getPair() {
                return totalCardList.stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(totalCardList).entrySet()
                                                .stream()
                                                .filter(l -> l.getCount() == CombinationDimension.PAIR.getDimension())
                                                .toList().getFirst().getElement()))
                                .collect(Collectors.toSet());

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getTwoPairs() {
                List<SimpleCard> twoPairSeedList = CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                                .entrySet()
                                .stream()
                                .filter(l -> l.getCount() == CombinationDimension.PAIR.getDimension())
                                .map(Entry::getElement)
                                .toList();
                return totalCardList.stream().filter(t -> t.cardName() == twoPairSeedList.getFirst()
                                || t.cardName() == twoPairSeedList.getLast())
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getTris() {
                return totalCardList.stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(totalCardList).entrySet()
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
                return CombinationRulesUtilities.getRoyalFlush(totalCardList)
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
                return totalCardList.stream().filter(t -> t.seedName()
                                .equals(CombinationRulesUtilities.getSumOfSameSeedCard(totalCardList).entrySet()
                                .stream()
                                .filter(l -> l.getCount() == CombinationDimension.STRAIGHT.getDimension())
                                .toList().getFirst().getElement()))
                                .collect(Collectors.toSet());

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getPoker() {
                return totalCardList.stream().filter(t -> t.cardName()
                                .equals(CombinationRulesUtilities.getSumOfSameNameCard(totalCardList).entrySet()
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

}
