package model.combination;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import model.combination.api.Combination;
import model.deck.api.Card;

/**
 * Class that compare two combination to decretate the winner.
 */
public class CombinationComparator implements Comparator<Combination<Card>> {

    /**
     * Method to compare two combination.
     * 
     * @return
     *         0 if they are equals, 1 if first is bigger, -1 if second is bigger.
     */
    @Override
    public int compare(Combination<Card> o1, Combination<Card> o2) {

        int returnValue = Integer.compare(o1.type().getValue(), o2.type().getValue());

        if (returnValue == 0) {
            switch (o1.type()) {
                case TWO_PAIRS:
                    return twoPairCompair(o1.totalCard(), o2.totalCard());
                case FULL_HOUSE:
                    return Integer.compare(sumValueCard(getTrisFromCombination(o1)),
                            sumValueCard(getTrisFromCombination(o2)));
                default:
                    return Integer.compare(sumValueCard(o1.totalCard()), sumValueCard(o2.totalCard()));
            }
        }
        return returnValue;

    }

    /**
     * Method to sum value of Card set.
     * 
     * @param totalCardList
     * @return
     *         sum of card's value
     */
    private static Integer sumValueCard(Set<Card> totalCardList) {
        return totalCardList.stream().mapToInt(Card::valueOfCard).sum();
    }

    /**
     * Method to get the tris from a combination.
     * 
     * @param combination
     * @return
     *         Set of card that represent the tris.
     */
    private static Set<Card> getTrisFromCombination(Combination<Card> combination) {
        return new CombinationsCardGetterImpl(combination.totalCard()).getTris();
    }

    /**
     * Method to compare two two-pair.
     * 
     * @param firstList
     * @param secondList
     * @return
     *         0 if they are equals, 1 if first is bigger, -1 if second is bigger.
     */
    private static Integer twoPairCompair(Set<Card> firstList, Set<Card> secondList) {

        var valueFirstList = firstList.stream().map(Card::valueOfCard)
                .distinct()
                .collect(Collectors.toList());
        var valueSecondList = secondList.stream().map(Card::valueOfCard)
                .distinct()
                .collect(Collectors.toList());

        if (valueFirstList.getLast().equals(valueSecondList.getLast())) {
            if (valueFirstList.getFirst().equals(valueSecondList.getFirst())) {
                return 0;
            }
            return Integer.compare(valueFirstList.getFirst(), valueSecondList.getFirst());
        }
        return Integer.compare(valueFirstList.getLast(), valueSecondList.getLast());
    }
}
