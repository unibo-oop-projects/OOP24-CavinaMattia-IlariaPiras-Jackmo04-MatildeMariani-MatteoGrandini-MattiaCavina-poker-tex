package view.gameScenePanels;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.gameScenePanels.api.MyLabel;
import view.gameScenePanels.api.PlayerPanel;

/**
 * Class that implements the common PlayerPanel methods.
 */
public abstract class PlayerPanelImpl extends JPanel implements PlayerPanel{

    private static final int NUM_CARDS = 2;

    private CardsPanel cardsPanel;
    private MyLabel playerAction;
    private MyLabel playerChips;
    private MyLabel playerRole;

    /**
     * Creates a new PlayerPanel
     */
    public PlayerPanelImpl() {
        this.cardsPanel = new CardsPanel(NUM_CARDS, 0);
        this.playerAction = new MyLabel("");
        this.playerChips = new MyLabel(" Chips: ");
        this.playerRole = new MyLabel("");
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
        this.setEnabled(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsPanel getCardsPanel() {
        return this.cardsPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MyLabel getPlayerAction() {
        return this.playerAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MyLabel getPlayerChips() {
        return this.playerChips;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MyLabel getPlayerRole() {
        return this.playerRole;
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

    public abstract void updateState(boolean isTurn);
}
