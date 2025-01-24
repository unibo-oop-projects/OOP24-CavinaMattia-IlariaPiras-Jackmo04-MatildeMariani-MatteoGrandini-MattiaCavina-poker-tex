package model.temp;

import java.util.Set;

import model.deck.api.Card;

/**
 * Class representing a combination of cards in a poker game.
 */
// TODO Dummy class to be replaced by actual implementation by Mattia
public record Combination(CombinationType type, Set<Card> cards, int value) {
} 
