package model.combination.api;

import java.util.List;

import com.google.common.collect.Multiset;

import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Inteface to implement method useful to find correct {@link Combination}.
 */
public interface CombinationUtilities {

    /**
     * Method to get a possible combination of RoyalFlush , if it is present. Can
     * used for Straight too.
     * 
     * @param cardList List of card to get if present subList that form only
     *                 Straight , Straight Flush or RoyalFlush.
     * @return List of {@link Card} that represent the possible
     *         RoyalFlush combination.
     */
    List<Card> getHighterStraight(List<Card> cardList);

    /**
     * Method to count the sum of the same name card.
     * 
     * @param cardList List of {@link Card} to be analized.
     * @return Map of {@link SimpleCard} and Integer that represent
     *         the sum of the same name card.
     */
    Multiset<SimpleCard> getSumOfSameNameCard(List<Card> cardList);

    /**
     * Method to get the sum of the same seed card.
     * 
     * @param cardList List of {@link Card} to be analized.
     * @return Map of {@link SeedCard} and Integer that represent the
     *         sum of the same seed card.
     */
    Multiset<SeedCard> getSumOfSameSeedCard(List<Card> cardList);

}
