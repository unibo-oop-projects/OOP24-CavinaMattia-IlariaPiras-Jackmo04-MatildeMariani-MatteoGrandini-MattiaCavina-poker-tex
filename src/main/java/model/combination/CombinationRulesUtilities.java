package model.combination;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.combination.api.CombinationDimension;
import model.deck.api.Card;
import model.deck.api.SeedCard;

/**
 * Class whith method to support CombinationRulesImpl class.
 */
public final class CombinationRulesUtilities {

    private CombinationRulesUtilities() {
    }

    /**
     * Method to get a possible combination of RoyalFlush , if it is present. Can
     * used for Straight too.
     * 
     * @param totalCardList
     * @return
     *         List of card that represent the possible RoyalFlush combination.
     */
    protected static List<Card> getRoyalFlush(final List<Card> totalCardList) {
        var straightList = filteredSameValueCard(totalCardList);
        Boolean checkStraight = false;

        while (straightList.size() >= CombinationDimension.STRAIGHT.getDimension() && !checkStraight) {
            List<Integer> controList = new LinkedList<>();
            for (int i = 0; i < CombinationDimension.STRAIGHT.getDimension(); i++) {
                controList.add(straightList.get(i).valueOfCard() - i);
            }
            if (controList.stream().distinct().count() == 1) {
                checkStraight = true;
            } else {
                straightList.removeFirst();
            }
        }
        while (checkStraight && straightList.size() > CombinationDimension.STRAIGHT.getDimension()) {
            straightList.removeLast();
        }
        return straightList;
    }

    /**
     * Method to filter the same value card.
     * 
     * @param totalCardList
     * @return
     *         List of card filtered and merged same value.
     */
    protected static List<Card> filteredSameValueCard(final List<Card> totalCardList) {
        SeedCard mustUsedSeedCard = getSumOfSameSeedCard(totalCardList).entrySet().stream()
                .max(Comparator.comparing(Entry::getValue))
                .get()
                .getKey();

        var straightList = totalCardList.stream()
                .sorted(Comparator.comparing(Card::valueOfCard))
                .collect(Collectors.toList());

        for (int i = 0; i < straightList.size() - 1; i++) {
            if (straightList.get(i).valueOfCard().equals(straightList.get(i + 1).valueOfCard())) {
                if (!straightList.get(i).seedName().equals(mustUsedSeedCard)) {
                    straightList.remove(i);
                } else {
                    straightList.remove(i + 1);
                }
            }
        }
        return straightList;
    }

    /**
     * Method to get the sum of the same name card.
     * 
     * @param totalCardList
     * @return
     *         Stream of Integer that represent the sum of the same name card.
     */
    protected static Stream<Integer> getSumOfSameNameCard(final List<Card> totalCardList) {
        return totalCardList.stream().map(t -> t.cardName())
                .collect(Collectors.toMap(t -> t, t -> 1, Integer::sum, HashMap::new))
                .values().stream();
    }

    /**
     * Method to get the sum of the same seed card.
     * 
     * @param totalCardList
     * @return
     *         Map of SeedCard and Integer that represent the sum of the same seed
     *         card.
     */
    protected static Map<SeedCard, Integer> getSumOfSameSeedCard(final List<Card> totalCardList) {
        return totalCardList.stream()
                .map(t -> t.seedName())
                .collect(Collectors.toMap(t -> t, t -> 1, Integer::sum, HashMap::new));
    }

}
