package model.game;

import java.util.HashSet;
import java.util.Set;

import model.deck.api.Card;
import model.game.api.Phase;
import model.game.api.State;

/**
 * This class provides an implementation of the State interface.
 */
public final class StateImpl implements State {

    private static final int START_HAND_NUMBER = 0;
    private static final int INITIAL_POT = 0;
    private static final Phase FIRST_PHASE = Phase.PREFLOP;

    private int pot;
    private int currentBet;
    private int remainingPlayers;
    private int handNumber;
    private Phase handPhase;
    private final int startingBet;
    private final Set<Card> communityCards = new HashSet<>();

    /**
     * Basic constructor for the StateImpl class.
     * @param initialBet the initial bet required to play.
     * @param numPlayers the initial number of players.
     */
    public StateImpl(final int initialBet, final int numPlayers) {
        this.pot = INITIAL_POT;
        this.handNumber = START_HAND_NUMBER;
        this.handPhase = FIRST_PHASE;
        this.startingBet = initialBet;
        this.currentBet = startingBet;
        this.remainingPlayers = numPlayers;
    }

    /**
     * Constructor for the StateImpl. Used for testing purposes.
     * @param pot the pot.
     * @param currentBet the current bet.
     * @param remainingPlayers the number of players still in the game.
     * @param handNumber the number of the hand.
     * @param handPhase the current phase of the hand.
     * @param communityCards the set of community cards.
     */
    public StateImpl(final int pot, final int currentBet, final int remainingPlayers, 
                     final int handNumber, final Phase handPhase, final Set<Card> communityCards) {
        this.pot = pot;
        this.currentBet = currentBet;
        this.startingBet = currentBet;
        this.remainingPlayers = remainingPlayers;
        this.handNumber = handNumber;
        this.handPhase = handPhase;
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
    public void addCommunityCards(final Set<Card> cards) {
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
    public void newHand(final int initialBet, final int remainingPlayers) {
        this.pot = INITIAL_POT;
        this.handPhase = FIRST_PHASE;
        this.handNumber++;
        this.currentBet = initialBet;
        this.remainingPlayers = remainingPlayers;
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
    public int getRemainingPlayers() {
        return this.remainingPlayers;
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
    public Phase getHandPhase() {
        return this.handPhase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Card> getCommunityCards() {
        return Set.copyOf(this.communityCards);
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
    public void setRemainingPlayers(final int remainingPlayers) {
        this.remainingPlayers = remainingPlayers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHandPhase(final Phase handPhase) {
        this.handPhase = handPhase;
    }

}
