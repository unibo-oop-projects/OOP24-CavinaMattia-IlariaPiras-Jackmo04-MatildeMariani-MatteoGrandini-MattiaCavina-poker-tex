package view.scenes;

import javax.swing.JFrame;

import controller.gameover.GameOverMenu;
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

        pannel.changeScene(new EndGameScene(new GameOverMenuImpl(pannel)));


    }
}