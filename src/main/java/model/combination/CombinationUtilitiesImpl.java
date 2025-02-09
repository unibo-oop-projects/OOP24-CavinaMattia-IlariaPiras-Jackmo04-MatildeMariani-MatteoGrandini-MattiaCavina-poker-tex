package model.combination;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import com.google.common.collect.Multiset.Entry;

import model.combination.api.CombinationDimension;
import model.combination.api.CombinationUtilities;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Class with method to support method of
 * {@link CombinationRulesImpl} and {@link CombinationCardGetterImpl} classes.
 * This class it composed to some pubblic methd that allow
 * to count or filter list of card by its feuture.
 */
public final class CombinationUtilitiesImpl implements CombinationUtilities {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getHighterStraight(final List<Card> cardList) {
        final var straightList = filteredSameValueCard(addAceOneValue(cardList)).reversed();

        while (straightList.size() >= CombinationDimension.STRAIGHT.getDimension()
                && !Stream.iterate(0, t -> t + 1)
                        .limit(CombinationDimension.STRAIGHT.getDimension())
                        .map(t -> t + straightList.get(t).valueOfCard())
                        .allMatch(t -> t.equals(straightList.getFirst().valueOfCard()))) {
            straightList.removeFirst();
        }
        return straightList.stream()
                .limit(CombinationDimension.STRAIGHT.getDimension())
                .collect(Collectors.toList());
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
     * @param cardList List of card to be filtered.
     * @return List of card filtered and merged same value.
     */
    private List<Card> filteredSameValueCard(final List<Card> cardList) {
        final SeedCard mustUsedSeedCard;
        if (!cardList.isEmpty()) {
            mustUsedSeedCard = getSumOfSameSeedCard(cardList)
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(Entry::getCount))
                    .map(Entry::getElement)
                    .get();

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
     * @param cardList List to add ace like one value if it present.
     * @return {@link List} with add ace with one value.
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
