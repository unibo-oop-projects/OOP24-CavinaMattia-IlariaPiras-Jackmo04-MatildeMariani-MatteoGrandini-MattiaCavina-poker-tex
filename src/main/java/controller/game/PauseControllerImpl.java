package controller.game;

import controller.difficulty.DifficultySelectionControllerImpl;
import controller.game.api.PauseController;
import controller.menu.MainMenuControllerImpl;

import view.View;
import view.scenes.DifficultySelectionScene;
import view.scenes.MainMenuScene;

public class PauseControllerImpl implements PauseController {

    private final View mainView;

    public PauseControllerImpl(View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void goToDifficultySelectionScene() {
        this.mainView.changeScene(new DifficultySelectionScene(new DifficultySelectionControllerImpl(this.mainView)));

    }

    @Override
    public void goToMainMenuScene() {
        this.mainView.changeScene(new MainMenuScene(new MainMenuControllerImpl(this.mainView)));
    }

}
