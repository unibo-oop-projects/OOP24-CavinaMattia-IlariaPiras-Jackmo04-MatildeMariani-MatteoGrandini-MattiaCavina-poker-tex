package model.combination.api;

import java.util.List;

/**
 * Interface for combination handler.
 * @param <X>
 */
public interface CombinationHandler<X> {

    /**
     * 
     * @param tableCards
     *              List of table's cards.
     * @param playerCards
     *             List of player's cards.
     * @return the best combination of the list of cards.
     */
    Combination<X> getCombinationFromCards(List<X> tableCards ,List<X> playerCards);

}
