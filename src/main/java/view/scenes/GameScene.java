package view.scenes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import controller.game.GameController;
import view.scenes.api.Scene;

public class GameScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "game";
    
    private final GameController controller;

    public GameScene(final GameController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());


    }


    private class AIPlayerPanel extends JPanel{

        AIPlayerPanel() {

        }
    }

    private class TablePanel extends JPanel{

        TablePanel() {

        }
    }


    @Override
    public JPanel getPanel() {
        return this;
    }


    @Override
    public String getSceneName() {
        return SCENE_NAME;
    }
}
