package model.combination.api;

import java.util.List;
import java.util.function.BiFunction;

import model.deck.api.Card;

public interface CheckFactoryOfCombination<X> {

    /*
     * Check if the combination is a pair.
     * @return true if the combination is a pair.
     */
    Boolean pair();

    /**
     * Check if the combination is two pairs.
     * @return  true if the combination is two pairs.
     */
    Boolean twoPairs();

    /**
     * Check if the combination is a tris.
     * @return  true if the combination is a tris.
     */
    Boolean tris();

    /**
     * Check if the combination is a straight.
     * @return   true if the combination is a straight.
     */
    Boolean straight();

    /**
     * Check if the combination is a full house.
     * @return  true if the combination is a full house.
     */
    Boolean fullHouse();
    /**
     * Check if the combination is a flush.
     * @return  true if the combination is a flush.
     */
    Boolean flush();

    /**
     * Check if the combination is a poker.
     * @return  true if the combination is a poker.
     */
    Boolean poker();

    /**
     * Check if the combination is a royal flush.
     * @return  true if the combination is a royal flush.
     */
    Boolean royalFlush();
}