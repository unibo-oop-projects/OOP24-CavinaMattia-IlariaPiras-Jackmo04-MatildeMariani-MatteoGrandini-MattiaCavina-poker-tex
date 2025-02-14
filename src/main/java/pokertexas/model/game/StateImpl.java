package pokertexas.model.game;

import java.util.ArrayList;
import java.util.List;

import pokertexas.model.deck.api.Card;
import pokertexas.model.game.api.Phase;
import pokertexas.model.game.api.State;

/**
 * This class provides an implementation of the State interface.
 */
public final class StateImpl implements State {

    private static final int INITIAL_POT = 0;
    private static final Phase FIRST_PHASE = Phase.PREFLOP;

    private int pot;
    private int currentBet;
    private Phase handPhase;
    private int handNumber;
    private final List<Card> communityCards = new ArrayList<>();

    /**
     * Basic constructor for the StateImpl class.
     * @param initialBet the initial bet required to play.
     */
    public StateImpl(final int initialBet) {
        this.pot = INITIAL_POT;
        this.handPhase = FIRST_PHASE;
        this.currentBet = initialBet;
        this.handNumber = 0;
    }

    /**
     * Constructor for the StateImpl. Used for testing purposes.
     * @param pot the pot.
     * @param currentBet the current bet.
     * @param handPhase the current phase of the hand.
     * @param handNumber the current hand number.
     * @param communityCards the list of community cards.
     */
    public StateImpl(final int pot, final int currentBet, final int handNumber,
                    final Phase handPhase, final List<Card> communityCards) {
        this.pot = pot;
        this.currentBet = currentBet;
        this.handPhase = handPhase;
        this.handNumber = handNumber;
        this.communityCards.addAll(communityCards);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToPot(final int playerBet) {
        this.pot += playerBet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCommunityCards(final List<Card> cards) {
        this.communityCards.addAll(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextHandPhase() {
        this.handPhase = this.handPhase.next();
        this.currentBet = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newHand(final int initialBet) {
        this.pot = INITIAL_POT;
        this.handPhase = FIRST_PHASE;
        this.currentBet = initialBet;
        this.handNumber++;
        this.communityCards.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPot() {
        return this.pot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentBet() {
        return this.currentBet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phase getHandPhase() {
        return this.handPhase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHandNumber() {
        return this.handNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getCommunityCards() {
        return List.copyOf(this.communityCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentBet(final int currentBet) {
        this.currentBet = currentBet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandPhase(final Phase handPhase) {
        this.handPhase = handPhase;
    }

}
