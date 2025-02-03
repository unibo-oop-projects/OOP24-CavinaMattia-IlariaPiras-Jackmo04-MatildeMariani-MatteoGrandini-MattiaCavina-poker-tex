package model.combination.api;

import java.util.List;

import com.google.common.collect.Multiset;

import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

public interface CombinationRulesUtilities {

    /**
     * Method to get a possible combination of RoyalFlush , if it is present. Can
     * used for Straight too.
     * 
     * @param totalCardList
     * @return
     *         List of card that represent the possible RoyalFlush combination.
     */
    List<Card> getRoyalFlush(List<Card> totalCardList);

    /**
     * Method to get the sum of the same name card.
     * 
     * @param totalCardList
     * @return
     *         Stream of Integer that represent the sum of the same name card.
     */
    Multiset<SimpleCard> getSumOfSameNameCard(List<Card> totalCardList);

    /**
     * Method to get the sum of the same seed card.
     * 
     * @param totalCardList
     * @return
     *         Map of SeedCard and Integer that represent the sum of the same seed
     *         card.
     */
    Multiset<SeedCard> getSumOfSameSeedCard(List<Card> totalCardList);

}