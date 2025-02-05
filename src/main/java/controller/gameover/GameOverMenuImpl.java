package controller.gameover;

import controller.menu.MainMenuControllerImpl;
import view.View;
import view.scenes.MainMenuScene;

/**
 * Class to implement {@link controller.gameover.GameOverMenu} controller.
 */
public class GameOverMenuImpl extends MainMenuControllerImpl implements GameOverMenu {

    private boolean endGameStatus;

    /**
     * Costructor of Game over pannel.
     * 
     * @param mainView
     *                      the main view of the application.
     * @param endGameStatus
     *                      Status of intial pannel , used to show correct pannel
     *                      and to change from victory pannel to loseer pannel.
     */
    public GameOverMenuImpl(final View mainView, final boolean endGameStatus) {
        super(mainView);
        this.endGameStatus = endGameStatus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMainScene() {
        getView().changeScene(new MainMenuScene(new MainMenuControllerImpl(getView())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeResultPannel() {
        setEndGameStatus(!isEndGameStatus());
    }

    /**
     * Method to get endGameStatus.
     * 
     * @return
     *         end game status , true in winner , false is looser.
     */
    @Override
    public boolean isEndGameStatus() {
        return endGameStatus;
    }

    private void setEndGameStatus(final boolean endGameStatus) {
        this.endGameStatus = endGameStatus;
    }

}
