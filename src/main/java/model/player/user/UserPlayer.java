package model.player.user;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.player.user.UserPlayerController;
import model.combination.CombinationHandlerImpl;
import model.game.api.Phase;
import model.game.api.State;
import model.player.AbstractPlayer;
import model.player.api.Action;

/**
 * Class representing a human player in the game.
 */
public class UserPlayer extends AbstractPlayer {

    private static final int INITIAL_TOTAL_PHASE_BET = 0;
    private final UserPlayerController controller;

    /**
     * Constructor for the UserPlayer class.
     * @param initialChips the initial amount of chips that the player has.
     * @param initialRole the initial role of the player.
     */
    public UserPlayer(final int initialChips) {
        super(initialChips);
        this.controller = new UserPlayerController(this);
        this.setTotalPhaseBet(INITIAL_TOTAL_PHASE_BET);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(final State currentState) {
        this.controller.setCurrentState(currentState);
        if(this.getCards().size() != 2) {
            throw new IllegalStateException("Player must have 2 cards to play");
        }
        this.updateCombination(currentState);
        if(currentState.getHandPhase() == Phase.PREFLOP && this.getTotalPhaseBet() == 0 && this.getRole().isPresent()) {
            this.setTotalPhaseBet((int) (currentState.getCurrentBet() * this.getRole().get().getMultiplier()));
            this.setChips(this.getChips() - this.getTotalPhaseBet());
            return Action.CALL;
        } else { 
            //var action = this.controller.getUserAction();
            //int chips = this.calculateChipsToBet(currentState.getCurrentBet(), action);
            //this.setChips(this.getChips() - chips);
            //this.setTotalPhaseBet(this.getTotalPhaseBet() + chips);
            switch (controller.getUserAction()) {
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
                        this.setChips(getChips() - (currentState.getCurrentBet() - this.getTotalPhaseBet()));
                        this.setTotalPhaseBet(currentState.getCurrentBet());
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

    /*private int calculateChipsToBet(final int currentBet, final Action action) {
        switch (action) {
            case Action.RAISE -> {
                return this.controller.getRaiseAmount();
            }
            case Action.CALL -> {
                return this.getChips() < (currentBet - this.getTotalPhaseBet()) ? this.getChips() : (currentBet - this.getTotalPhaseBet());
            }
            case Action.ALL_IN -> {
                return this.getChips();
            }
        }
        return 0;

    }*/

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
     * This method resets the player's cards.
     */
    private void endHand() {
        this.setCards(Set.of());
    }

    /**
     * Updates the player's best combination based on the current state of the game.
     * This method combines the player's cards with the community cards and calculates the best combination.
     * @param currentState the current state of the game, which includes the community cards.
     */
    private void updateCombination(final State currentState) {
        var allCards = Stream.concat(currentState.getCommunityCards().stream(), this.getCards().stream()).collect(Collectors.toSet());
        this.setCombination((new CombinationHandlerImpl()).getCombination(allCards)); 
    }

    /**
     * Gets the controller associated with this user player.
     * @return the UserPlayerController associated with this user player.
     */
    public UserPlayerController getController() {
        return this.controller;
    }
}