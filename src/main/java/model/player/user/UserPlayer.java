package model.player.user;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.player.user.UserPlayerController;
import model.game.api.Phase;
import model.game.api.State;
import model.player.AbstractPlayer;
import model.player.api.Action;
import model.player.api.Role;
import model.temp.Blind;
import model.temp.Combinations;

/**
 * Class representing a human player in the game.
 */
public class UserPlayer extends AbstractPlayer {
    
    private final UserPlayerController controller;

    /**
     * Constructor for the UserPlayer class.
     * @param initialChips the initial amount of chips that the player has.
     * @param initialRole the initial role of the player.
     */
    public UserPlayer(final int initialChips, final Role initialRole) {
        super(initialChips, initialRole);
        this.controller = new UserPlayerController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(final State currentState) {
        if (this.getCards().size() != 2) {
            throw new IllegalStateException("Player must have 2 cards to play");
        }
        //if(currentState.getHandPhase() == Phase.PREFLOP && this.getTotalPhaseBet() == 0 && (this.getRole() == Role.BIG_BLIND || this.getRole() == Role.SMALL_BLIND)) {
        if(currentState.getHandPhase() == Phase.PREFLOP && this.getTotalPhaseBet() == 0 ){
            if(this.getRole() == Role.SMALL_BLIND) {
                this.setTotalPhaseBet((int) (currentState.getCurrentBet() * Blind.SMALL.getMultiplier()));
            } else if(this.getRole() == Role.BIG_BLIND) {
                this.setTotalPhaseBet((int) (currentState.getCurrentBet() * Blind.BIG.getMultiplier()));
            }
            //this.setTotalPhaseBet(currentState.getCurrentBet() * this.getRole().getMultiplier());
            this.setChips(this.getChips() - this.getTotalPhaseBet());
            return Action.CALL;
        } else { 
            this.updateCombination(currentState);
            var action = controller.getUserAction(currentState.getCurrentBet());
            switch (action) {
                case Action.RAISE -> {
                    this.setTotalPhaseBet(this.getTotalPhaseBet() + controller.getRaiseAmount());
                    this.setChips(this.getChips() - controller.getRaiseAmount());
                    return Action.RAISE;
                }
                case Action.CALL -> {
                    if(getChips() < (currentState.getCurrentBet() - this.getTotalPhaseBet())) {
                        this.setTotalPhaseBet(this.getTotalPhaseBet() + this.getChips());
                        this.setChips(0);
                    } else {  
                        this.setChips(this.getChips() - (currentState.getCurrentBet() -  this.getTotalPhaseBet()));
                        this.setTotalPhaseBet(this.getTotalPhaseBet() + currentState.getCurrentBet());
                    }
                    return Action.CALL;
                }
                case Action.CHECK -> {
                    return Action.CHECK;
                }
                case Action.FOLD -> {
                    return Action.FOLD;
                }
                case Action.ALL_IN -> {
                    this.setTotalPhaseBet(this.getTotalPhaseBet() + this.getChips());
                    this.setChips(0);
                    return Action.ALL_IN;
                }
                default -> throw new IllegalArgumentException();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAI() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handWon(final int winnings) {
        this.setChips(this.getChips() + winnings);
        this.endHand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handLost() {
        this.endHand();
    }

    /**
     * Ends the current hand for the player.
     * This method resets the player's cards and updates the player's role to the next role.
     */
    private void endHand() {
        this.setCards(Set.of());
        this.setRole(getRole().next());
    }

    private void updateCombination(final State currentState) {
        var allCards = Stream.concat(currentState.getCommunityCards().stream(), this.getCards().stream()).collect(Collectors.toSet()); 
        this.setCombination(Combinations.getBestCombination(allCards)); // Imposta la combinazione migliore trovata
    }
}