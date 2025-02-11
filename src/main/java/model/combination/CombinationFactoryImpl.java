package model.combination;

import java.util.Set;

import model.combination.api.CombinationCardGetter;
import model.combination.api.CombinationFactory;
import model.combination.api.CombinationType;
import model.deck.api.Card;

public class CombinationFactoryImpl implements CombinationFactory {

    final CombinationCardGetter<Card> combGetter;
    final Set<Card> totalCard;

    public CombinationFactoryImpl(Set<Card> totalCard) {
        this.totalCard = totalCard;
        this.combGetter = new CombinationCardGetterImpl(totalCard,
                new CombinationUtilitiesImpl());
    }

    @Override
    public Combination<Card> getPair(Set<Card> totalCard) {
        return new Combination<>(combGetter.getPairCard(), CombinationType.PAIR, totalCard);
    }

    @Override
    public Combination<Card> getTwoPairs(Set<Card> totalCard) {
        return new Combination<>(combGetter.getTwoPairsCard(), CombinationType.TWO_PAIRS, totalCard);
    }

    @Override
    public Combination<Card> getTris(Set<Card> totalCard) {
        return new Combination<>(combGetter.getTrisCard(), CombinationType.TRIS, totalCard);
    }

    @Override
    public Combination<Card> getStraight(Set<Card> totalCard) {
        return new Combination<>(combGetter.getStraightCard(), CombinationType.STRAIGHT, totalCard);
    }

    @Override
    public Combination<Card> getFullHouse(Set<Card> totalCard) {
        return new Combination<>(combGetter.getFullHouseCard(), CombinationType.FULL_HOUSE, totalCard);
    }

    @Override
    public Combination<Card> getFlush(Set<Card> totalCard) {
        return new Combination<>(combGetter.getFlushCard(), CombinationType.FLUSH, totalCard);
    }

    @Override
    public Combination<Card> getPoker(Set<Card> totalCard) {
        return new Combination<>(combGetter.getPokerCard(), CombinationType.POKER, totalCard);
    }

    @Override
    public Combination<Card> getStraightFlush(Set<Card> totalCard) {
        return new Combination<>(combGetter.getStraightCard(), CombinationType.STRAIGHT_FLUSH, totalCard);
    }

    @Override
    public Combination<Card> getRoyalStraight(Set<Card> totalCard) {
        return new Combination<>(combGetter.getStraightCard(), CombinationType.ROYAL_FLUSH, totalCard);
    }

    @Override
    public Combination<Card> getHightCard(Set<Card> totalCard) {
        return new Combination<>(combGetter.getHightCardCard(), CombinationType.HIGH_CARD, totalCard);
    }

}
