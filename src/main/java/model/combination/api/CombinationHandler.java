package model.combination.api;

import java.util.List;

/**
 * Interface for combination handler.
 * 
 * @param <X>
 */
public interface CombinationHandler<X> {

    /**
     * Method to get the best combination of the list of cards.
     * 
     * @param totalCardList
     *                      list of cards.
     * @return the best combination of the list of cards.
     */
    Combination<X> getCombination(List<X> totalCardList);

}
