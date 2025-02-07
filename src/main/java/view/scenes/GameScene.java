package view.scenes;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;

import controller.game.api.GameController;
import view.gameScenePanels.AIPlayerPanel;
import view.gameScenePanels.PauseDialog;
import view.gameScenePanels.PlayerPanelImpl;
import view.gameScenePanels.TablePanel;
import view.player.user.MyButton;
import view.player.user.UserPanel;
import view.scenes.api.Scene;

/**
 * The {@link Scene} of the game.
 */
public class GameScene extends JPanel implements Scene {

    private static final String SCENE_NAME = "game";
    
    private final GameController controller;
    private final TablePanel table;
    private final PlayerPanelImpl westPlayerPanel;
    private final PlayerPanelImpl northPlayerPanel;
    private final PlayerPanelImpl eastPlayerPanel;
    private final PlayerPanelImpl southPlayerPanel;

    /**
     * Creates a new {@link GameScene}.
     * @param controller the controller for the game.
     */
    public GameScene(final GameController controller) {

        this.controller = controller;
        this.setLayout(new BorderLayout());

        /*Sets the panels for the player and for the table*/
        this.westPlayerPanel = new AIPlayerPanel();
        this.northPlayerPanel = new AIPlayerPanel();
        this.eastPlayerPanel = new AIPlayerPanel();
        /*To change with a UserPlayerPanel */
        this.southPlayerPanel = new UserPanel(this.controller.getUserPlayerController());  
        this.table = new TablePanel();

        /*Creates the south panel with the southPlayerPanel and a buttonPanel*/
        JPanel southJPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        buttonsPanel.setBackground(southPlayerPanel.getBackground());
        /*MyButton pause = new MyButton(" Pause ", "PAUSE", pauseActionListener, buttonsPanel);
        MyButton menu = new MyButton(" Menu ", "MENU", menuActionListener, buttonsPanel);*/
        MyButton pause = new MyButton("Pause");
        pause.initializeButton("PAUSE", pauseActionListener, buttonsPanel);
        MyButton menu = new MyButton("Menu");
        menu.initializeButton("MENU", menuActionListener, buttonsPanel);
        
        southJPanel.add(southPlayerPanel, BorderLayout.CENTER);
        southJPanel.add(buttonsPanel, BorderLayout.EAST);

        /*Sets background color*/
        westPlayerPanel.setBackground(Color.DARK_GRAY);
        northPlayerPanel.setBackground(Color.DARK_GRAY);
        eastPlayerPanel.setBackground(Color.DARK_GRAY);
        southJPanel.setBackground(southPlayerPanel.getBackground());

        /*Adds the panels*/
        this.add(northPlayerPanel, BorderLayout.NORTH);
        this.add(westPlayerPanel, BorderLayout.WEST);
        this.add(eastPlayerPanel, BorderLayout.EAST);
        this.add(southJPanel, BorderLayout.SOUTH);
        this.add(table, BorderLayout.CENTER);

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

    /**
     * Returns the PlayerPanel that corresponds to the given id.
     * @param id the player's id.
     * @return the corrisponding PlayerPanel.
     */
    public PlayerPanelImpl getPlayerPanel(final int id) {
        return switch(id) {
            case 0 -> this.westPlayerPanel;
            case 1 -> this.northPlayerPanel;
            case 2 -> this.eastPlayerPanel;
            case 3 -> this.southPlayerPanel;
            default -> throw new IllegalArgumentException();
        };
    }

    /**
     * Returns the {@link TablePanel}.
     * @return the table panel.
     */
    public TablePanel getTable() {
        return this.table;
    }

    private ActionListener pauseActionListener = new ActionListener() {

        private static final int TRASPARENCY = 170;

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel glassPane = new JPanel() {

                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(getBackground());
                    g.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }
            };
            glassPane.setOpaque(false);
            glassPane.setBackground(new Color(0, 0, 0, TRASPARENCY));

            RootPaneContainer frame = (RootPaneContainer) SwingUtilities.getWindowAncestor(GameScene.this);
            frame.setGlassPane(glassPane);
            glassPane.setVisible(true);

            controller.pauseGame();
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    PauseDialog pauseDialog = new PauseDialog((Window) frame, controller);
                    pauseDialog.setLocationRelativeTo((Window) frame);
                    pauseDialog.setVisible(true);

                    glassPane.setVisible(false);
                }
                
            });
        }
    };

    private ActionListener menuActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameScene.this.controller.goToMainMenuScene();
        }
        
    };

}
