package view.scenes;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import controller.game.GameController;
import view.panels.AIPlayerPanel;
import view.panels.TablePanel;
import view.scenes.api.Scene;

public class GameScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "game";
    
    private final GameController controller;

    public GameScene(final GameController controller) {

        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);
        this.setOpaque(true);

        AIPlayerPanel west = new AIPlayerPanel();
        AIPlayerPanel north = new AIPlayerPanel();
        AIPlayerPanel east = new AIPlayerPanel();
        /*To change with a UserPlayerPanel */
        AIPlayerPanel south = new AIPlayerPanel();  
        TablePanel table = new TablePanel();

        west.setBackground(Color.DARK_GRAY);
        north.setBackground(Color.DARK_GRAY);
        east.setBackground(Color.DARK_GRAY);
        south.setBackground(Color.DARK_GRAY);

        this.add(north, BorderLayout.NORTH);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        this.add(south, BorderLayout.SOUTH);
        this.add(table, BorderLayout.CENTER);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSceneName() {
        return SCENE_NAME;
    }

}
