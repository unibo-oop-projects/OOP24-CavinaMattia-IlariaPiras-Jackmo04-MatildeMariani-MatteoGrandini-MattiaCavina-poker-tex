package view.gameScenePanels.api;

import java.util.List;

import javax.swing.ImageIcon;

import model.deck.api.Card;
import model.player.api.Player;
import model.player.api.Action;
import model.player.api.Role;
import view.gameScenePanels.CardsPanel;

/**
 * Interface that models a PlayerPanel. 
 * It has a {@link CardsPanel} with two {@link Card}s, and labels for the {@link Player}'s 
 * {@link Action}, amount of chips left and {@link Role}.
 */
public interface PlayerPanel {

    /**
     * Sets the player's {@link Action} label.
     * @param action the player's action.
     */
    void setAction(String action);

    /**
     * Sets the player's remaining chips label.
     * @param chips the player's remaining chips.
     */
    void setChips(String chips);

    /**
     * Sets the player's {@link Role} label.
     * @param role the player's role.
     */
    void setRole(String role);

    /**
     * Resets the player's card image and action.
     * @param cardsback the list of card back image icons.
     */
    void resetForNewHand(final List<ImageIcon> cardsback);

    /**
     * Sets the player's {@link Action} label to an empty string if he can perform
     * other actions in the next phase.
     */
    void resetActionForNewPhase();

    /**
     * Informs the PlayerPanel that the player has lost and is no longer in the game.
     * Disables the Panel.
     */
    void lost();

    /**
     * Returns the {@link CardsPanel}.
     * @return the CardsPanel.
     */
    CardsPanel getCardsPanel();

    /**
     * Returns the player's {@link Action} label.
     * @return the player's {@link Action} label.
     */
    MyLabel getPlayerAction();

    /**
     * Returns the player's remaining chips label.
     * @return the player's remaining chips label.
     */
    MyLabel getPlayerChips();

    /**
     * Returns the player's {@link Role} label.
     * @return the player's {@link Role} label.
     */
    MyLabel getPlayerRole();

}
