package model.combination.api;

import java.util.Set;

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
     *                      Set of cards.
     * @return the best combination of the Set of cards.
     */
    Combination<X> getCombination(Set<X> totalCardList);

}
