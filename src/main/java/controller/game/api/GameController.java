package controller.game.api;

import java.util.Set;

import controller.card.CardGetterImage;
import controller.player.user.UserPlayerController;
import controller.scene.SceneController;
import model.deck.api.Card;
import model.game.api.Game;
import model.game.api.Hand;
import model.game.api.Phase;
import model.player.api.Player;
import view.View;
import view.gamepanels.api.PlayerPanel;
import view.scenes.GameScene;

/**
 * Interface that models a GameController. 
 * It has a {@link Game}, a {@link GameScene}, a main{@link View} and a {@link CardGetterImage}.
 * It acts as a mediator between the model (Game) and the view (GameScene).
 */
public interface GameController extends SceneController {

    /**
     * Sets the {@link GameScene}.
     * @param gameScene the gameScene.
     */
    void setGameScene(GameScene gameScene);

    /**
     * Calls {@link GameScene} methods to set it and starts the game.
     */
    void startGame();

    /**
     * Calls {@link GameScene} methods to update it at the start of a new {@link Hand}.
     */
    void updateForNewHand();

    /**
     * Calls {@link GameScene} methods to update it at the start of a new {@link Phase}.
     * @param pot the pot.
     */
    void updateForNewPhase(int pot);

    /**
     * Converts the {@link Player}'s card set to a list of ImageIcons via its {@link CardGetterImage}
     * and calls the setPlayerCards method in its {@link GameScene}.
     * @param id the player's id.
     * @param cards the set of cards of the player.
     */
    void setPlayerCards(int id, Set<Card> cards);

    /**
     * Converts the set of community cards to a list of ImageIcons via its {@link CardGetterImage}
     * and calls the setCommunityCards method in its {@link GameScene}.
     * @param cards the set of community cards.
     */
    void setCommunityCards(Set<Card> cards);

    /**
     * Calls the setPot method in its {@link GameScene}.
     * @param pot the pot.
     */
    void setPot(int pot);

    /**
     * Calls the setPlayerAction method in a different {@link PlayerPanel} in its {@link GameScene}
     * depending on the id.
     * @param id the player's id.
     * @param action the player's action.
     */
    void setPlayerAction(int id, String action);

    /**
     * Calls the setPlayerBet method in its {@link GameScene}.
     * @param id the player's id.
     * @param bet the player's bet.
     */
    void setPlayerBet(int id, int bet);

    /**
     * Calls the setPlayerChips method in a different {@link PlayerPanel} in its {@link GameScene}
     * depending on the id.
     * @param id the player's id.
     * @param chips the player's chips.
     */
    void setPlayerChips(int id, int chips);

    /**
     * Calls the setRole method in each {@link PlayerPanel} of its {@link GameScene} depending on 
     * the ids of the smallBlind and bigBlind.
     * @param smallBlindId the small blind player's id.
     * @param bigBlindId the big blind player's id.
     */
    void setRoles(int smallBlindId, int bigBlindId);

    /**
     * Calls the setPot and the setPlayerBet methods in its {@link GameScene} so that it shows the fact
     * that the pot is been assigned to the winner. 
     * @param winnerId the winner's id.
     * @param winnerChips the winner's chips.
     * @param pot the pot.
     */
    void showWinner(int winnerId, int winnerChips, int pot);

    /**
     * Calls the setPlayerChips and the setPlayerBet methods in its {@link GameScene} to update the winner data.
     * @param winnerId the winner's id.
     * @param winnerChips the winner's chips.
     */
    void setWinnerData(int winnerId, int winnerChips);

    /**
     * Calls the updatePlayerPanelState method in its {@link GameScene}.
     * @param id the id of the player whose turn it is.
     * @param isTurn the boolean indicating the state to which the player panel should be updated.
     */
    void isTurn(int id, boolean isTurn);

    /**
     * Returns the {@link UserPlayerController}.
     * @return the user player controller.
     */
    UserPlayerController getUserPlayerController();

    /**
     * Synchronized method that sets the isPaused boolean value to true.
     */
    void pauseGame();

    /**
     * Synchronized method that sets the isPaused boolean value to false and notifies the thread 
     * waiting for pauseLock.
     */
    void resumeGame();

    /**
     * Synchronized method that sets the isTerminated boolean value to true and calls the
     * resumeGame method.
     */
    void endGame();

    /**
     * Synchronized method that calls the wait() method if isPaused is true.
     */
    void waitIfPaused();

    /**
     * Returns whether the game is terminated.
     * @return whether the game is terminated.
     */
    boolean isTerminated();
}
