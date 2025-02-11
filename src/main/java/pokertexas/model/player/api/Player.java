package pokertexas.model.player.api;

import java.util.Optional;
import java.util.Set;

import pokertexas.model.combination.Combination;
import pokertexas.model.deck.api.Card;
import pokertexas.model.game.api.State;
import pokertexas.model.statistics.api.BasicStatistics;
import pokertexas.model.statistics.api.StatisticsContributor;

/**
 * Interface that models a generic player in the game.
 * A player can be either a human or an AI.
 * A player has two {@link Card}s, a {@link Role} and a certain amount of money at any given time.
 * The player can make a decision based on the current state of the game and return an {@link Action}.
 */
public interface Player extends StatisticsContributor<BasicStatistics> {

    /**
     * Sets the player's cards for the current hand.
     * @param cards the cards to be given to the player.
     */
    void setCards(Set<Card> cards);

    /** 
     * Sets the player's role for the current hand.
     * The role is reset at the end of each hand.
     * @param role the role to be given to the player.
     */
    void setRole(Role role);

    /**
     * Returns the player's id.
     * @return the player's id.
     */
    int getId();

    /**
     * Returns the amount of chips the player has betted since the beginning of the phase.
     * For example, let's assume that the {@link HandPhase} is {@link HandPhase#PRE_FLOP}.
     * If this player has betted 10 chips and another player raises to 25 chips,
     * if this player calls the raise, the returned value will be 25.
     * But when the {@link HandPhase} changes to {@link HandPhase#FLOP}, the returned value will be reset to 0.
     * @return the amount of chips the player has betted since the beginning of the phase.
     */
    int getTotalPhaseBet();

    /**
     * Informs the player that it's their turn.
     * The player will make a decision based on the current state and return an {@link Action}.
     * @return the player's action for the current hand.
     */
    Action getAction();

    /**
     * Returns the player's cards for the current hand.
     * @return the cards of the player.
     */
    Set<Card> getCards();

    /**
     * Returns the best {@link Combination} the player has in the current hand.
     * @return the best {@link Combination} the player has in the current hand.
     */
    Combination<Card> getCombination();

    /**
     * Returns an {@link Optional} containing the {@link Role} of the player in the current hand.
     * If the player is neither the Small Blind nor the Big Blind, the Optional will be empty.
     * Otherwise, it will contain the role of the player.
     * @return an {@link Optional} containing the {@link Role} of the player in the current hand.
     */
    Optional<Role> getRole();

    /**
     * Returns the amount of chips the player has left.
     * @return the amount of chips the player has left.
     */
    int getChips();

    /**
     * Checks weather the player has any chips left.
     * @return true if the player has chips left, false otherwise.
     */
    boolean hasChipsLeft();

    /**
     * Returns true if the player is an AI, false otherwise.
     * @return true if the player is an AI, false otherwise.
     */
    boolean isAI();

    /**
     * Informs the player that they have won the current hand.
     * @param winnings The amount of chips the player has won.
     */
    void handWon(int winnings);

    /**
     * Informs the player that they have lost the current hand.
     */
    void handLost();

    /**
     * Informs the player that the current phase has ended.
     */
    void nextPhase();

    /**
     * Informs the player of the new state of the game.
     * @param gameState the current state of the game.
     */
    void setGameState(State gameState);
}
