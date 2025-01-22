package model.player.abs;

import java.util.Optional;

import model.player.api.Action;
import model.player.api.Role;
import model.temp.State;

public abstract class AbstractAIPlayer extends AbstractPlayer {

    private double audacity; // Propensione a restare in gioco
    private double bluffing; // Propensione a bluffare
    private final double upScaler;    // Fattore di crescita di audacity
    private final double downScaler;  // fattore di decrescita di audacity

    public AbstractAIPlayer(
        int initialChips, 
        Role initialRole, 
        double audacity, 
        double bluffing,
        double upScaler,
        double downScaler
    ) {
        super(initialChips, initialRole);
        this.audacity = audacity;
        this.bluffing = bluffing;
        this.upScaler = upScaler;
        this.downScaler = downScaler;
    }

    @Override
    public Optional<Integer> getBet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBet'");
    }

    @Override
    public Action getAction(State currentState) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAction'");
    }

    @Override
    public boolean isAI() {
        return true;
    }

    @Override
    public void handWon(int winnings) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handWon'");
    }

    @Override
    public void handLost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handLost'");
    }

}
