package model.combination;

import java.util.Comparator;
import java.util.Set;

import model.deck.api.Card;
import model.combination.api.Combination;

public class CombinationComparator implements Comparator<Combination<Card>> {

    @Override
    public int compare(Combination<Card> o1, Combination<Card> o2) {

        int returnValue = Integer.compare(o1.type().getValue(), o2.type().getValue());

        if (returnValue == 0) {
            switch (o1.type()) {
                case HIGH_CARD:
                    return Integer.compare(sumValueCard(o1.totalCard()), sumValueCard(o2.totalCard()));
                case PAIR:
                    return Integer.compare(sumValueCard(o1.totalCard()), sumValueCard(o2.totalCard()));
                case TWO_PAIRS:
                    return Integer.compare(sumValueCard(o1.totalCard()), sumValueCard(o2.totalCard()));
                case TRIS:
                    return Integer.compare(sumValueCard(o1.totalCard()), sumValueCard(o2.totalCard()));
                case STRAIGHT:
                    return Integer.compare(sumValueCard(o1.totalCard()), sumValueCard(o2.totalCard()));
                case FLUSH:
                    return Integer.compare(sumValueCard(o1.totalCard()), sumValueCard(o2.totalCard()));
                case FULL_HOUSE:
                    return Integer.compare(sumValueCard(new CombinationsCardGetterImpl(o1.totalCard()).getTris()),
                            sumValueCard(new CombinationsCardGetterImpl(o2.totalCard()).getTris()));
                case POKER:
                    return Integer.compare(sumValueCard(o1.totalCard()), sumValueCard(o2.totalCard()));
                default:
                    return 0;
            }
        }
        return returnValue;

    }

    private static Integer sumValueCard(Set<Card> totalCardList) {
        return totalCardList.stream().mapToInt(Card::valueOfCard).sum();
    }
}
