package view.panels.api;

import model.deck.api.Card;
import model.player.api.Player;
import model.player.api.Action;
import model.player.api.Role;

import view.panels.CardsPanel;

/**
 * Interface that models a PlayerPanel. 
 * It has a {@link CardsPanel} with two {@link Card}s, and labels for the {@link Player}'s 
 * {@link Action}, amount of chips left and {@link Role}.
 */
public interface PlayerPanel {

    /**
     * Returns the {@link CardsPanel}.
     * @return the CardsPanel.
     */
    CardsPanel getCardsPanel();

    /**
     * Sets the label of the player's {@link Action}.
     * @param action the player's action.
     */
    void setAction(String action);

    /**
     * Sets the label of the player's remaining chips.
     * @param chips the player's remaining chips.
     */
    void setChips(String chips);

    /**
     * Sets the label of the player's {@link Role}.
     * @param role the player's role.
     */
    void setRole(String role);

}
