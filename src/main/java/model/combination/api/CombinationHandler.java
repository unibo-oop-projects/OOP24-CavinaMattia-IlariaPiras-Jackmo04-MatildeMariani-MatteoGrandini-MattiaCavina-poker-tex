package model.combination.api;

import java.util.Set;

/**
 * Interface for combination handler.
 * 
 * @param <X>
 *            Generic parameter to reuse for diffent type of Card.
 */
public interface CombinationHandler<X> {

    /**
     * Method to get the best combination of the list of cards.
     * 
     * @param cardList
     *                 Set of cards to be analized.
     * @return
     *         the best combination of the Set of cards passed like argument.
     */
    Combination<X> getBestCombination(Set<X> cardList);

}
