package model.combination;

import java.util.Comparator;

import model.deck.api.Card;
import model.combination.api.Combination;

public class CombinationComparator implements Comparator<Combination<Card>> {

    @Override
    public int compare(Combination<Card> o1, Combination<Card> o2) {

        int returnValue = Integer.compare(o1.type().getValue(),o2.type().getValue());
        
        if (returnValue == 0) {
            switch (o1.type()) {
                case HIGH_CARD:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                case PAIR:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                case TWO_PAIRS:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                case TRIS:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                case STRAIGHT:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                case FLUSH:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                case FULL_HOUSE:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                case POKER:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                case ROYAL_FLUSH:
                    return Integer.compare(o1.tieBreaker(), o2.tieBreaker());
                    default:
                        return 0;
            }   
        }
        return returnValue;

    }
}
