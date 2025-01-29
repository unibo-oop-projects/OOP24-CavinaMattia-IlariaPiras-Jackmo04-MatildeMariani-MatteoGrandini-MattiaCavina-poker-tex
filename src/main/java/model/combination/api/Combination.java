package model.combination.api;

import java.util.Set;

/**
 * Combination of List of cards and its type and value.
 * This class generate a combination whith propreties util for the game.
 * @param <X>
 *                   type of card.
 * @param totalCard
 *                   list of cards .
 * @param type
 *                   Set of combination.
 * @param tieBreaker
 *                   is used to compare two combinations of the same type ,is
 *                   the sum value of players's cards.
 * 
 */
public record Combination<X>(Set<X> totalCard, CombinationType type, int tieBreaker) {

}

