package model.temp;

import java.util.Set;

import model.deck.api.Card;

/**
 * Class representing the state of a poker game.
 */
public record State(
    int pot,
    int currentBet,
    int remainingPlayers,
    Set<Card> communityCards,
    HandFase handFase
) {

}
