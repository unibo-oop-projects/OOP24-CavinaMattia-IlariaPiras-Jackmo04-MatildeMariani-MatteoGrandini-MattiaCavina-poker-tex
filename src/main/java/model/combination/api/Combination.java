package model.combination.api;

import java.util.List;

/**
 * Combination of List of cards and its type and value.
 * 
 * @param <X>
 *                   type of card.
 * @param tableCards
 *                   list of cards on the table.
 * @param playerCard
 *                   list of cards of the player.
 * @param type
 *                   type of combination.
 * @param tieBreaker
 *                   is used to compare two combinations of the same type ,is
 *                   the sum value of players's cards.
 * 
 */
public record Combination<X>(List<X> tableCards, List<X> playerCard, CombinationType type, int tieBreaker) {

};
