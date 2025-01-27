package model.combination;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.combination.api.CombinationDimension;
import model.combination.api.CombinationsRules;
import model.deck.api.Card;

public class CombinationsRulesImpl implements CombinationsRules<Card> {

    private final List<Card> totalCardList;

    /**
     * Constructor for CombinationsRulesImpl.
     * 
     * @param tableCards
     * @param playerCards
     */
    public CombinationsRulesImpl(List<Card> tableCards, List<Card> playerCards) {
        this.totalCardList = Stream.concat(tableCards.stream(), playerCards.stream()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean pair() {
        return totalCardList.size() >= CombinationDimension.PAIR.getDimension() &&
                CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                        .filter(t -> t == CombinationDimension.PAIR.getDimension()).count() == 1 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean twoPairs() {
        return totalCardList.size() >= CombinationDimension.PAIR.getDimension() * 2 &&
                CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                        .filter(t -> t == CombinationDimension.PAIR.getDimension()).count() == 2 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean tris() {
        return totalCardList.size() >= CombinationDimension.TRIS.getDimension() &&
                CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                        .filter(t -> t == CombinationDimension.TRIS.getDimension()).count() == 1 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean straight() {
        return CombinationRulesUtilities.getRoyalFlush(totalCardList).size() == CombinationDimension.STRAIGHT
                .getDimension() ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean fullHouse() {
        return totalCardList.size() >= (CombinationDimension.STRAIGHT.getDimension()) &&
                pair() && tris() ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean flush() {
        return totalCardList.size() >= CombinationDimension.STRAIGHT.getDimension() &&
                CombinationRulesUtilities.getSumOfSameSeedCard(totalCardList).values().stream()
                        .filter(t -> t == CombinationDimension.STRAIGHT.getDimension()).count() == 1 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean poker() {
        return totalCardList.size() >= CombinationDimension.POKER.getDimension() &&
                CombinationRulesUtilities.getSumOfSameNameCard(totalCardList)
                        .filter(t -> t == CombinationDimension.POKER.getDimension()).count() == 1 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean royalFlush() {
        return CombinationRulesUtilities.getRoyalFlush(totalCardList).size() == CombinationDimension.STRAIGHT
                .getDimension() &&
                CombinationRulesUtilities.getRoyalFlush(totalCardList).stream().map(t -> t.seedName()).distinct()
                        .count() == 1 ? true : false;
    }

}
