package model.combination;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import com.google.common.collect.Multiset.Entry;

import model.combination.api.CombinationDimension;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

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
    public static List<Card> getRoyalFlush(final List<Card> totalCardList) {
        final var straightList = filteredSameValueCard(addAceOneValue(totalCardList)).reversed();
        Boolean checkStraight = false;

        while (straightList.size() >= CombinationDimension.STRAIGHT.getDimension() && !checkStraight) {
            final List<Integer> controList = Lists.newLinkedList();
            for (int i = 0; i < CombinationDimension.STRAIGHT.getDimension(); i++) {
                controList.add(straightList.get(i).valueOfCard() + i);
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
    private static List<Card> filteredSameValueCard(final List<Card> totalCardList) {
        SeedCard mustUsedSeedCard;
        if (!totalCardList.isEmpty()) {
            mustUsedSeedCard = getSumOfSameSeedCard(totalCardList).entrySet().stream()
                    .max(Comparator.comparing(Entry::getCount))
                    .get().getElement();

          final  var straightList = totalCardList.stream()
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
        } else {
            return totalCardList;
        }
    }

    /**
     * Method to get the sum of the same name card.
     * 
     * @param totalCardList
     * @return
     *         Stream of Integer that represent the sum of the same name card.
     */
    public static Multiset<SimpleCard> getSumOfSameNameCard(final List<Card> totalCardList) {
        Multiset<SimpleCard> nameCardMultiset = TreeMultiset.create();
        nameCardMultiset.addAll(totalCardList.stream().map(Card::cardName).toList());
        return nameCardMultiset;
    }

    /**
     * Method to get the sum of the same seed card.
     * 
     * @param totalCardList
     * @return
     *         Map of SeedCard and Integer that represent the sum of the same seed
     *         card.
     */
    public static Multiset<SeedCard> getSumOfSameSeedCard(final List<Card> totalCardList) {
       final Multiset<SeedCard> seedCardMultiset = TreeMultiset.create();
        seedCardMultiset.addAll(totalCardList.stream().map(Card::seedName).toList());
        return seedCardMultiset;
    }

    /**
     * Method to add card ace type, if it is present, with one value to consider
     * both value of that card.
     * 
     * @param totalCardList
     * @return
     *         list with add ace with one value.
     */
    private static List<Card> addAceOneValue(final List<Card> totalCardList) {
        final List<Card> aceList = Lists.newLinkedList();
        totalCardList.stream().forEach(t -> {
            if (t.cardName().equals(SimpleCard.ACE)) {
                aceList.add(new Card(SimpleCard.ACE, 1, t.seedName()));
            }
        });
        totalCardList.addAll(aceList);
        return totalCardList;
    }

}
