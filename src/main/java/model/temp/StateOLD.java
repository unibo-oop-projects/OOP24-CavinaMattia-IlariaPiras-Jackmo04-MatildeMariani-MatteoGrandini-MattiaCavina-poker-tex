package model.temp;

import java.util.Set;

import model.deck.api.Card;

/**
 * Temporary class representing the state of a poker game.
 * @deprecated Use {@link model.game.api.State} instead.
 */
@Deprecated
public record StateOLD(
    int pot,
    int currentBet,
    int remainingPlayers,
    Set<Card> communityCards,
    HandPhase handFase
) {

}
