package controller.game;

import java.util.Set;
import java.util.stream.Stream;

import controller.card.CardGetterImage;
import controller.card.CardGetterImageImpl;
import controller.game.api.Difficulty;
import controller.game.api.GameController;
import controller.gameover.GameOverMenuImpl;
import controller.menu.MainMenuControllerImpl;
import model.deck.api.Card;
import model.game.GameFactoryImpl;
import model.game.api.Game;
import model.player.api.Action;
import view.View;
import view.scenes.GameOverScene;
import view.scenes.GameScene;
import view.scenes.MainMenuScene;

/**
 * Class that implements the {@link GameController} interface.
 */
public class GameControllerImpl implements GameController{

    private static final int NUM_PLAYER_CARD = 2;
    private final CardGetterImage cardGetterImage;
    private final View mainView;
    private final Game game;
    private GameScene gameScene;

    /**
     * Creates a new {@link GameController}.
     * @param mainView the mainView.
     * @param difficulty the game difficulty.
     * @param initialChips the player's initial chips.
     */
    public GameControllerImpl(final View mainView, final Difficulty difficulty, final int initialChips) {
        this.mainView = mainView;
        var gameFactory = new GameFactoryImpl();
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
        this.cardGetterImage = new CardGetterImageImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameScene(final GameScene gameScene) {
        this.gameScene = gameScene;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        this.game.getPlayers().forEach(p -> {
            this.setPlayerChips(p.getId(), p.getChips());
            this.gameScene.getPlayerPanel(p.getId()).reset(this.cardGetterImage.getBackCardImage(NUM_PLAYER_CARD));
        });
        this.setCommunityCards(Set.of());
        this.game.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateForNewHand() {
        Stream.iterate(0, i -> i < 4, i -> i + 1)
              .filter(id -> !this.gameScene.getPlayerPanel(id).isEnabled())
              .forEach(id -> this.gameScene.getPlayerPanel(id).reset(this.cardGetterImage.getBackCardImage(NUM_PLAYER_CARD)));
        
        this.setCommunityCards(Set.of());
        
        Stream.iterate(0, i -> i < 4, i -> i + 1)
              .filter(id -> this.game.getPlayers().stream().noneMatch(p -> p.getId() == id))
              .forEach(id -> this.gameScene.getPlayerPanel(id).lost());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerCards(final int id, final Set<Card> cards) {
        this.gameScene.getPlayerPanel(id).getCardsPanel().setCards(this.cardGetterImage.getCardImage(cards));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommunityCards(final Set<Card> cards) {
        this.gameScene.getTable().getCardsPanel().setCards(this.cardGetterImage.getTableCardImage(cards));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPot(final int pot) {
        this.gameScene.getTable().setPot(String.valueOf(pot));
        this.gameScene.getTable().resetPlayersBet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerAction(final int id, final Action action) {
        this.gameScene.getPlayerPanel(id).setAction(String.valueOf(action));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerBet(final int id, final int bet) {
        this.gameScene.getTable().setPlayerBet(id, String.valueOf(bet));
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
        Stream.iterate(0, i -> i < 4, i -> i + 1)
              .filter(id -> this.game.getPlayers().stream().anyMatch(p -> p.getId() == id))
              .forEach(id -> this.gameScene.getPlayerPanel(id).setRole(
                    id == smallBlindId ? "SB" : 
                    id == bigBlindId ? "BB" : ""));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToGameOverScene(boolean won) {
        this.mainView.changeScene(new GameOverScene(new GameOverMenuImpl(this.mainView, won)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getMainView() {
        return mainView;
    }
    
}
