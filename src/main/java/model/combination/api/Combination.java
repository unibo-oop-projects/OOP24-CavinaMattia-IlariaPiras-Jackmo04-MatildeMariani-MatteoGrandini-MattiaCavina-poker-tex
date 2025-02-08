package model.combination.api;

import java.util.Set;

/**
 * Combination of List of {@link Card} and its type and value.
 * This class generate a combination whith propreties util for the game.
 * 
 * @param <X>             type of card.
 * @param combinationCard Set of card's combination.
 * @param type            Type of combination keeped from enum
 *                        {@link Combination}.
 * 
 */
public record Combination<X>(Set<X> combinationCard, CombinationType type) {

}
