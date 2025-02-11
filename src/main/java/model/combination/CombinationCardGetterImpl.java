package model.combination;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset.Entry;

import model.combination.api.CombinationDimension;
import model.combination.api.CombinationRules;
import model.combination.api.CombinationUtilities;
import model.combination.api.CombinationCardGetter;
import model.deck.api.Card;
import model.deck.api.SimpleCard;

/**
 * Class that implements {@link CombinationRules}.
 * All the methods are used to check how type of combination is it.
 */
public class CombinationCardGetterImpl implements CombinationCardGetter<Card> {

        private final List<Card> totalCardList = Lists.newLinkedList();
        private static final int COMBINATION_NUMBER = 5;
        private final CombinationUtilities utilies;

        /**
         * Constructor for CombinationsRulesImpl.
         * 
         * @param totalCardList list of cards.
         * @param utilies       utilities used to find correct Combination.
         * @throws IllegalAccessError thrown when the list is empty.
         * 
         */
        public CombinationCardGetterImpl(final Set<Card> totalCardList, final CombinationUtilities utilies) {
                if (!totalCardList.isEmpty()) {
                        totalCardList.forEach(this.totalCardList::add);
                } else {
                        throw new IllegalArgumentException("Empty Set passed like Argument");
                }
                this.utilies = utilies;

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getPairCard() {
                return getSafetyList().stream().filter(t -> t.cardName()
                                .equals(utilies.getSumOfSameNameCard(getSafetyList()).entrySet()
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
        public Set<Card> getTwoPairsCard() {
                final List<SimpleCard> twoPairSeedList = utilies.getSumOfSameNameCard(getSafetyList())
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
        public Set<Card> getTrisCard() {

                return getSafetyList().stream().filter(t -> t.cardName()
                                .equals(utilies.getSumOfSameNameCard(getSafetyList()).entrySet()
                                                .stream()
                                                .filter(l -> l.getCount() == CombinationDimension.TRIS.getDimension())
                                                .toList().getFirst().getElement()))
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getStraightCard() {

                return utilies.getHighterStraight(getSafetyList())
                                .stream()
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getFullHouseCard() {
                return Stream.concat(getPairCard().stream(), getTrisCard().stream())
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getFlushCard() {
                return getSafetyList().stream().filter(t -> t.seedName()
                                .equals(utilies.getSumOfSameSeedCard(getSafetyList()).entrySet()
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
        public Set<Card> getPokerCard() {
                return getSafetyList().stream().filter(t -> t.cardName()
                                .equals(utilies.getSumOfSameNameCard(getSafetyList()).entrySet()
                                                .stream()
                                                .filter(l -> l.getCount() == CombinationDimension.POKER.getDimension())
                                                .toList().getFirst().getElement()))
                                .collect(Collectors.toSet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Card> getHightCardCard() {
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
