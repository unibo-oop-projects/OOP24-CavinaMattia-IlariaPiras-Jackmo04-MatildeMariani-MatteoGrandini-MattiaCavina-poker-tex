package model.combination;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import com.google.common.collect.Multiset.Entry;

import model.combination.api.CombinationDimension;
import model.combination.api.CombinationRulesUtilities;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Class whith method to support method of
 * {@link model.combination.CombinationsRulesImpl}
 * and {@link model.combination.CombinationsCardGetterImpl} classes.
 */
public final class CombinationRulesUtilitiesImpl implements CombinationRulesUtilities {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getRoyalFlush(final List<Card> cardList) {
        final var straightList = filteredSameValueCard(addAceOneValue(cardList)).reversed();
        Boolean checkStraight = false;

        while (straightList.size() >= CombinationDimension.STRAIGHT.getDimension() && !checkStraight) {
            final List<Integer> controList = Lists.newLinkedList();
            for (int i = 0; i < CombinationDimension.STRAIGHT.getDimension(); i++) {
                controList.add(straightList.get(i).valueOfCard() + i);
            }
            if (controList.stream().allMatch(t -> t.equals(controList.getFirst()))) {
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
     * {@inheritDoc}
     */
    @Override
    public Multiset<SimpleCard> getSumOfSameNameCard(final List<Card> cardList) {
       final Multiset<SimpleCard> nameCardMultiset = TreeMultiset.create();
        nameCardMultiset.addAll(cardList.stream().map(Card::cardName).toList());
        return nameCardMultiset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Multiset<SeedCard> getSumOfSameSeedCard(final List<Card> cardList) {
        final Multiset<SeedCard> seedCardMultiset = TreeMultiset.create();
        seedCardMultiset.addAll(cardList.stream().map(Card::seedName).toList());
        return seedCardMultiset;
    }

    /**
     * Method to filter the same value card.
     * 
     * @param cardList
     *                 List of card to be filtered.
     * @return
     *         List of card filtered and merged same value.
     */
    private List<Card> filteredSameValueCard(final List<Card> cardList) {
        final SeedCard mustUsedSeedCard;
        if (!cardList.isEmpty()) {
            mustUsedSeedCard = getSumOfSameSeedCard(cardList).entrySet().stream()
                    .max(Comparator.comparing(Entry::getCount))
                    .get().getElement();

            final var straightList = cardList.stream()
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
            return cardList;
        }
    }

    /**
     * Method to add card ace type, if it is present, with one value to consider
     * both value of that card.
     * 
     * @param cardList
     *                 List to add ace like one value if it present.
     * @return
     *         list with add ace with one value.
     */
    private List<Card> addAceOneValue(final List<Card> cardList) {
        final List<Card> aceList = Lists.newLinkedList();
        cardList.stream().forEach(t -> {
            if (t.cardName().equals(SimpleCard.ACE)) {
                aceList.add(new Card(SimpleCard.ACE, 1, t.seedName()));
            }
        });
        cardList.addAll(aceList);
        return cardList;
    }

}
