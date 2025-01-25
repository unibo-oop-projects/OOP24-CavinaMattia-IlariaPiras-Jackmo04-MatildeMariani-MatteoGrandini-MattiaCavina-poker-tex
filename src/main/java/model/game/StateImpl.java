package model.game;

import java.util.HashSet;
import java.util.Set;

import model.deck.api.Card;
import model.game.api.Phase;
import model.game.api.State;

public class StateImpl implements State{

    private static final int START_HAND_NUMBER = 1;
    private static final int INITIAL_POT = 0;
    private static final Phase FIRST_PHASE = Phase.PREFLOP;

    private int pot;
    private int currentBet;
    private int remainingPlayers;
    private int handNumber;
    private Phase handPhase;
    private final Set<Card> communityCards = new HashSet<>();

    public StateImpl(final int initialBet, final int numPlayers) {
        this.pot = INITIAL_POT;
        this.handNumber = START_HAND_NUMBER;
        this.handPhase = FIRST_PHASE;
        this.currentBet = initialBet;
        this.remainingPlayers = numPlayers;
    }

    @Override
    public void addToPot(final int playerBet) {
        this.pot += playerBet;
    }

    @Override
    public void addCommunityCards(final Set<Card> cards) {
        this.communityCards.addAll(communityCards);
    }

    @Override
    public void nextHandPhase() {
        this.handPhase.next();
    }

    @Override
    public void newHand(final int initialBet, final int remainingPlayers) {
        this.pot = INITIAL_POT;
        this.handPhase = FIRST_PHASE;
        this.handNumber++;
        this.currentBet = initialBet;
        this.remainingPlayers = remainingPlayers;
        this.communityCards.clear();
    }

    @Override
    public int getPot() {
        return this.pot;
    }

    @Override
    public int getCurrentBet() {
        return this.currentBet;
    }

    @Override
    public int getRemainingPlayers() {
        return this.remainingPlayers;
    }

    @Override
    public int getHandNumber() {
        return this.handNumber;
    }

    @Override
    public Phase getHandPhase() {
        return this.handPhase;
    }

    @Override
    public Set<Card> getCommunityCards() {
        return Set.copyOf(this.communityCards);
    }

    @Override
    public void setCurrentBet(final int currentBet) {
        this.currentBet = currentBet;
    }

    @Override
    public void setRemainingPlayers(final int remainingPlayers) {
        this.remainingPlayers = remainingPlayers;
    }

}
