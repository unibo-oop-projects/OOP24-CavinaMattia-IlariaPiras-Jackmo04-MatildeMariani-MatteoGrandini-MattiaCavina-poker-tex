package model.combination.api;

import java.util.List;

/**
 * Interface for combination handler.
 * 
 */
public interface CombinationHandler<X> {

    /**
     * 
     * @param cards
     *              List of cards to get the best combination.
     * @return the best combination of the list of cards.
     */
    Combination<X> getCombinationFromCards(List<X> tableCards ,List<X> playerCards);

}
