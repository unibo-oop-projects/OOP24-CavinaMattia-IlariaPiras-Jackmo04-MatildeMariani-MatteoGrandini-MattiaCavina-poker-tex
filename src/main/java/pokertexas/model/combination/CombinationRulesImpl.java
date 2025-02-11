package pokertexas.model.combination;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset.Entry;

import pokertexas.model.combination.api.CombinationDimension;
import pokertexas.model.combination.api.CombinationRules;
import pokertexas.model.combination.api.CombinationUtilities;
import pokertexas.model.deck.api.Card;
import pokertexas.model.deck.api.SimpleCard;

/**
 * Class that implements the Rules of the combinations.
 * All the methods are used to check how type of
 * {@link CombinationType} is it.
 */
public class CombinationRulesImpl implements CombinationRules<Card> {

    private final List<Card> totalCardList = Lists.newLinkedList();
    private final CombinationUtilities utilies;

    /**
     * Constructor for CombinationsRulesImpl.
     * 
     * @param totalCardList list of cards.
     * @param utilies utilities used to find correct Combination.
     */
    public CombinationRulesImpl(final Set<Card> totalCardList, final CombinationUtilities utilies) {
        totalCardList.forEach(this.totalCardList::add);
        this.utilies = utilies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isPair() {
        return isLongEnough(CombinationDimension.PAIR.getDimension())
                && utilies.getSumOfSameNameCard(getSafetyList()).entrySet()
                        .stream()
                        .map(Entry::getCount)
                        .toList()
                        .contains(CombinationDimension.PAIR.getDimension());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isTwoPairs() {
        return isLongEnough(CombinationDimension.TWO_PAIRS.getDimension())
                && utilies.getSumOfSameNameCard(getSafetyList()).entrySet()
                        .stream()
                        .map(Entry::getCount)
                        .filter(t -> t == CombinationDimension.PAIR.getDimension())
                        .count() == 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isTris() {
        return isLongEnough(CombinationDimension.TRIS.getDimension())
                && utilies.getSumOfSameNameCard(getSafetyList()).entrySet()
                        .stream()
                        .map(Entry::getCount)
                        .toList()
                        .contains(CombinationDimension.TRIS.getDimension());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isStraight() {
        return utilies.getHighterStraight(getSafetyList()).size() == CombinationDimension.STRAIGHT
                .getDimension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isFullHouse() {
        return isLongEnough(CombinationDimension.STRAIGHT.getDimension())
                && isPair()
                && isTris();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isFlush() {
        return isLongEnough(CombinationDimension.STRAIGHT.getDimension())
                && utilies.getSumOfSameSeedCard(getSafetyList()).entrySet()
                        .stream()
                        .map(Entry::getCount)
                        .toList()
                        .contains(CombinationDimension.STRAIGHT.getDimension());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isPoker() {
        return isLongEnough(CombinationDimension.POKER.getDimension())
                && utilies.getSumOfSameNameCard(getSafetyList()).entrySet()
                        .stream()
                        .map(Entry::getCount)
                        .toList()
                        .contains(CombinationDimension.POKER.getDimension());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isStraightFlush() {
        return isStraight()
                && utilies.getHighterStraight(getSafetyList()).stream()
                        .map(Card::seedName)
                        .distinct()
                        .count() == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isRoyalFlush() {
        return isStraightFlush()
                && utilies.getHighterStraight(getSafetyList()).stream()
                        .mapToInt(Card::valueOfCard)
                        .min().getAsInt() == SimpleCard.TEN.getValueOfCard();
    }

    private List<Card> getSafetyList() {
        return Lists.newLinkedList(this.totalCardList);
    }

    private boolean isLongEnough(final Integer combinationDimension) {
        return getSafetyList().size() >= combinationDimension;
    }
}
