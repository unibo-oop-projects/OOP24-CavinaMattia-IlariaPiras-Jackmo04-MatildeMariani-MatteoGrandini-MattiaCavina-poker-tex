package model.temp;

import java.util.Set;

import model.deck.api.Card;

public record State(
    int handNumber,
    int pot,
    int currentBet,
    int remainingPlayers,
    Set<Card> communityCards,
    HandFase handFase
) {

}
