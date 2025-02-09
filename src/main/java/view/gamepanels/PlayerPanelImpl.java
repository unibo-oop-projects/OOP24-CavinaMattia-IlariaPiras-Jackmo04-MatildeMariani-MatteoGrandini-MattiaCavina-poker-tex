package view.gamepanels;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.player.api.Action;
import model.player.api.Player;
import model.player.api.Role;
import view.gamepanels.api.CustomLabel;
import view.gamepanels.api.PlayerPanel;

/**
 * Class that implements the common PlayerPanel methods.
 */
public abstract class PlayerPanelImpl implements PlayerPanel {

    private static final int NUM_CARDS = 2;

    private final CardsPanel cardsPanel;
    private final JLabel playerAction;
    private final JLabel playerChips;
    private final JLabel playerRole;

    private boolean lost;

    /**
     * Creates a new PlayerPanel.
     */
    public PlayerPanelImpl() {
        final var customLabelFactory = new CustomLabel();
        this.playerAction = customLabelFactory.getCustomLabel("");
        this.playerChips = customLabelFactory.getCustomLabel(" Chips: ");
        this.playerRole = customLabelFactory.getCustomLabel("");
        this.cardsPanel = new CardsPanel(NUM_CARDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAction(final String action) {
        this.playerAction.setText(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChips(final String chips) {
        this.playerChips.setText(" Chips: " + chips);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(final String role) {
        this.playerRole.setText(role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetForNewHand(final List<ImageIcon> cardsback) {
        this.cardsPanel.setCards(cardsback);
        this.playerAction.setText("");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lost() {
        this.lost = true;
        this.playerRole.setText("");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldBeUpdated() {
        return !this.lost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetActionForNewPhase() {
        switch (this.playerAction.getText()) {
            case "CALL":
            case "RAISE":
            case "CHECK":
                this.setAction("");
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCards(final List<ImageIcon> cardImage) {
        this.cardsPanel.setCards(cardImage);
    }

    /**
     * Returns the {@link CardsPanel}.
     * @return the CardsPanel.
     */
    protected final CardsPanel getCardsPanel() {
        return this.cardsPanel;
    }

    /**
     * Returns the {@link Player}'s {@link Action} label.
     * @return the player's action label.
     */
    protected final JLabel getPlayerAction() {
        return this.playerAction;
    }

    /**
     * Returns the {@link Player}'s remaining chips label.
     * @return the player's remaining chips label.
     */
    protected final JLabel getPlayerChips() {
        return this.playerChips;
    }

    /**
     * Returns the {@link Player}'s {@link Role} label.
     * @return the player's role label.
     */
    protected final JLabel getPlayerRole() {
        return this.playerRole;
    }
}
