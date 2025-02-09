package view.gamepanels.api;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.deck.api.Card;
import model.game.api.Phase;
import model.player.api.Player;
import model.player.api.Action;
import model.player.api.Role;
import view.gamepanels.CardsPanel;

/**
 * Interface that models a PlayerPanel. 
 * It has a {@link CardsPanel} with two {@link Card}s, and labels for the {@link Player}'s 
 * {@link Action}, amount of chips left and {@link Role}.
 */
public interface PlayerPanel {

    /**
     * Sets the {@link Player}'s {@link Action} label.
     * @param action the player's action.
     */
    void setAction(String action);

    /**
     * Sets the {@link Player}'s remaining chips label.
     * @param chips the player's remaining chips.
     */
    void setChips(String chips);

    /**
     * Sets the {@link Player}'s {@link Role} label.
     * @param role the player's role.
     */
    void setRole(String role);

    /**
     * Resets the {@link Player}'s card icon, role and action.
     * @param cardsback the list of card back image icons.
     */
    void resetForNewHand(List<ImageIcon> cardsback);

    /**
     * Sets the {@link Player}'s {@link Action} label to an empty string if he can perform
     * other actions in the next {@link Phase}.
     */
    void resetActionForNewPhase();

    /**
     * Informs the PlayerPanel that the {@link Player} has lost and is no longer in the game.
     * Sets the lost boolean value to true.
     */
    void lost();

    /**
     * Returns whether the PlayerPanel should be still updated (the player is still in the game).
     * @return whether the PlayerPanel should be still updated.
     */
    boolean shouldBeUpdated();

    /**
     * Returns the PlayerPanel.
     * @return the panel.
     */
    JPanel getPanel();

    /**
     * Updates PlayerPanel state based on isTurn.
     * @param isTurn the boolean value that says whether it is the player's turn.
     */
    void updateState(boolean isTurn);

    /**
     * Calls the setCards method in its {@link CardsPanel}.
     * @param cardImage the list of ImageIcons. 
     */
    void setCards(List<ImageIcon> cardImage);
}
