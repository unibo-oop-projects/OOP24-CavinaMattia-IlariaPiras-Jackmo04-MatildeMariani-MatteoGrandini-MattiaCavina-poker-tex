package pokertexas.controller.gameover;

import pokertexas.controller.scene.SceneControllerImpl;
import pokertexas.view.View;

/**
 * Class to implement {@link GameOverMenu} controller.
 */
public class GameOverMenuImpl extends SceneControllerImpl implements GameOverMenu {

    private boolean endGameStatus;

    /**
     * Costructor of GameOverPannel.
     * 
     * @param mainView the main view of the application.
     * @param endGameStatus Status of intial pannel , used to show correct pannel
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
    public void changeResultPannel() {
        setEndGameStatus(!isEndGameStatus());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEndGameStatus() {
        return endGameStatus;
    }

    private void setEndGameStatus(final boolean endGameStatus) {
        this.endGameStatus = endGameStatus;
    }

}
