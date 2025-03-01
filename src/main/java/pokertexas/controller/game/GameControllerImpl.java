package pokertexas.controller.game;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import pokertexas.controller.card.CardGetterImage;
import pokertexas.controller.card.CardGetterImageImpl;
import pokertexas.controller.game.api.Difficulty;
import pokertexas.controller.game.api.GameController;
import pokertexas.controller.player.user.UserPlayerController;
import pokertexas.controller.scene.SceneControllerImpl;
import pokertexas.model.deck.api.Card;
import pokertexas.model.game.GameFactoryImpl;
import pokertexas.model.game.api.Game;
import pokertexas.view.View;
import pokertexas.view.scenes.GameScene;

/**
 * Class that implements the {@link GameController} interface.
 */
public class GameControllerImpl extends SceneControllerImpl implements GameController {

    private static final int MAX_PLAYERS = 4;
    private static final int NUM_PLAYER_CARD = 2;
    private static final Logger LOGGER = LoggerFactory.getLogger(GameControllerImpl.class);

    private final CardGetterImage cardGetterImage;
    private final View mainView;
    private final Game game;
    private GameScene gameScene;

    private volatile boolean isPaused;
    private volatile boolean isTerminated;
    private final Object pauseLock = new Object();
    private final Object endLock = new Object();

    /**
     * Creates a new {@link GameController}.
     * @param mainView the mainView.
     * @param difficulty the game difficulty.
     * @param initialChips the player's initial chips.
     */
    public GameControllerImpl(final View mainView, final Difficulty difficulty, final int initialChips) {
        super(mainView);
        this.mainView = mainView;
        final var gameFactory = new GameFactoryImpl();
        switch (difficulty) {
            case MEDIUM:
                this.game = gameFactory.mediumGame(this, initialChips);
                break;
            case HARD:
                this.game = gameFactory.hardGame(this, initialChips);
                break;
            case EASY:
            default:
                this.game = gameFactory.easyGame(this, initialChips);
                break;
        }
        this.game.setInitialPlayers(initialChips);
        this.cardGetterImage = new CardGetterImageImpl();
        this.mainView.enableConfermationOnClose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Storing GameScene mutable object is intented")
    public final void setGameScene(final GameScene gameScene) {
        this.gameScene = gameScene;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        this.game.getPlayers().forEach(p -> {
            this.setPlayerChips(p.getId(), p.getChips());
            this.gameScene.getPlayerPanel(p.getId())
                .resetForNewHand(this.cardGetterImage.getBackCardImage(NUM_PLAYER_CARD));
        });
        this.setCommunityCards(List.of());
        this.game.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateForNewHand() {
        Stream.iterate(0, i -> i < MAX_PLAYERS, i -> i + 1)
            .filter(id -> this.gameScene.getPlayerPanel(id).shouldBeUpdated())
            .forEach(id -> this.gameScene.getPlayerPanel(id)
                .resetForNewHand(this.cardGetterImage.getBackCardImage(NUM_PLAYER_CARD)));
        this.setCommunityCards(List.of());
        Stream.iterate(0, i -> i < MAX_PLAYERS, i -> i + 1)
            .filter(id -> this.game.getPlayers().stream().noneMatch(p -> p.getId() == id) 
                && this.gameScene.getPlayerPanel(id).shouldBeUpdated())
            .forEach(id -> this.gameScene.getPlayerPanel(id).lost());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateForNewPhase(final int pot) {
        this.game.getPlayers().forEach(p -> {
            this.setPlayerBet(p.getId(), 0);
            this.gameScene.getPlayerPanel(p.getId()).resetActionForNewPhase();
        });
        this.setPot(pot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerCards(final int id, final List<Card> cards) {
        this.gameScene.getPlayerPanel(id).setCards(this.cardGetterImage.getCardImage(cards));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommunityCards(final List<Card> cards) {
        this.gameScene.setCommunityCards(this.cardGetterImage.getTableCardImage(cards));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPot(final int pot) {
        this.gameScene.setPot(String.valueOf(pot));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerAction(final int id, final String action) {
        this.gameScene.getPlayerPanel(id).setAction(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerBet(final int id, final int bet) {
        this.gameScene.setPlayerBet(id, bet != 0 ? String.valueOf(bet) : "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerChips(final int id, final int chips) {
        this.gameScene.getPlayerPanel(id).setChips(String.valueOf(chips));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRoles(final int smallBlindId, final int bigBlindId) {
        Stream.iterate(0, i -> i < MAX_PLAYERS, i -> i + 1)
            .filter(id -> this.game.getPlayers().stream().anyMatch(p -> p.getId() == id))
            .forEach(id -> this.gameScene.getPlayerPanel(id).setRole(
                id == smallBlindId ? "SB"
                : id == bigBlindId ? "BB" : ""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showWinner(final int winnerId, final int winnerChips, final int pot) {
        this.setPot(0);
        this.setPlayerBet(winnerId, pot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWinnerData(final int winnerId, final int winnerChips) {
        this.setPlayerBet(winnerId, 0);
        this.setPlayerChips(winnerId, winnerChips);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainMenuScene() {
        this.endGame();
        super.goToMainMenuScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToDifficultySelectionScene() {
        this.endGame();
        super.goToDifficultySelectionScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isTurn(final int id, final boolean isTurn) {
        this.gameScene.updatePlayerPanelState(id, isTurn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserPlayerController getUserPlayerController() {
        return this.game.getUserPlayerController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseGame() {
        synchronized (pauseLock) {
            this.isPaused = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resumeGame() {
        synchronized (pauseLock) {
            this.isPaused = false;
            pauseLock.notifyAll();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        synchronized (endLock) {
            this.isTerminated = true;
        }
        this.resumeGame();
        this.mainView.disableConfermationOnClose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waitIfPaused() {
        synchronized (pauseLock) {
            while (this.isPaused) {
                try {
                    pauseLock.wait();
                } catch (InterruptedException e) {
                    LOGGER.info("Thread interrupted.");
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTerminated() {
        synchronized (endLock) {
            return this.isTerminated;
        }
    }
}
