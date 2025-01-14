package player.api;

import java.util.Optional;
import java.util.Set;

import other.api.Card;
import other.api.Combination;
import other.api.State;

/**
 * Interface that models a generic player in the game.
 * A player can be either a human or an AI.
 * A player has two {@link Card}s, a {@link Role} and a certain amount of money at any given time.
 * The player can make a decision based on the current state of the game and return an {@link Action}.
 */
public interface Player {

    /**
     * Sets the player's cards for the current hand.
     * @param cards the cards to be given to the player.
     */
    void giveCards(Set<Card> cards);

    /** 
     * @deprecated Use constructor instead to set an initial role.
     */
    void setRole(Role role);

    /**
     * Returns an optional integer representing the bet made by the player.
     * <ul>
     * <li>If the player's {@link Action} is {@link Action#BET} or {@link Action#RAISE}, 
     * the optional will contain the bet made by the player.</li>
     * <li>If the player's {@link Action} is {@link Action#FOLD} or {@link Action#CHECK},
     * the optional will be empty.</li>
     * </ul>
     * @return an optional integer representing the bet made by the player.
     */
    Optional<Integer> getBet();

    /**
     * Informs the player that it's their turn.
     * The player will make a decision based on the current state and return an {@link Action}.
     * @param currentState the current state of the game.
     * @return the player's action for the current hand.
     */
    Action getAction(State currentState);

    /**
     * Returns the player's cards for the current hand.
     * @return the cards of the player.
     */
    Set<Card> getCards();

    /**
     * Returns the best {@link Combination} the player has in the current hand.
     * @return the best {@link Combination} the player has in the current hand.
     */
    Combination getCombination();

    /**
     * Returns the {@link Role} of the player in the current hand.
     * @return the {@link Role} of the player in the current hand.
     */
    Role getRole();

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
     * Informs the player that they have lost the current hand;
     */
    void handLost();
}
