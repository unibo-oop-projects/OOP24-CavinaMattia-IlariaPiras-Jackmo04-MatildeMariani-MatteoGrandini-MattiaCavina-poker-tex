package view.scenes;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

import controller.game.GameControllerImpl;
import view.panels.AIPlayerPanel;
import view.panels.TablePanel;
import view.scenes.api.Scene;

public class GameScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "game";
    
    private final GameControllerImpl controller;
    private final TablePanel table;
    private final AIPlayerPanel west;
    private final AIPlayerPanel north;
    private final AIPlayerPanel east;
    private final AIPlayerPanel south;

    public GameScene(final GameControllerImpl controller) {

        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);
        this.setOpaque(true);

        this.west = new AIPlayerPanel();
        this.north = new AIPlayerPanel();
        this.east = new AIPlayerPanel();
        /*To change with a UserPlayerPanel */
        this.south = new AIPlayerPanel();  
        this.table = new TablePanel();

        west.setBackground(Color.DARK_GRAY);
        north.setBackground(Color.DARK_GRAY);
        east.setBackground(Color.DARK_GRAY);
        south.setBackground(Color.DARK_GRAY);

        this.add(north, BorderLayout.NORTH);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        this.add(south, BorderLayout.SOUTH);
        this.add(table, BorderLayout.CENTER);

        //TODO: add userPlayer panel and button to exit and pause the game.

        this.controller.setGameScene(this);
        this.controller.startGame();
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

    public AIPlayerPanel getPlayerPanel(final int id) {
        return switch(id) {
            case 0 -> this.west;
            case 1 -> this.north;
            case 2 -> this.east;
            case 3 -> this.south;
            default -> null;
        };
    }

    public TablePanel getTable() {
        return this.table;
    }


}
