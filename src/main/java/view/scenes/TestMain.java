package view.scenes;

import controller.gameover.GameOverMenuImpl;
import view.ViewImpl;

/**
 * Main class of the application.
 */
public final class TestMain {

    private TestMain() {
    }

    /**
     * Main method of the application.
     * @param args unused
     */
    public static void main(final String[] args) {
        ViewImpl pannel = new ViewImpl();

        pannel.changeScene(new GameOverScene(new GameOverMenuImpl(pannel,true)));

    }
}