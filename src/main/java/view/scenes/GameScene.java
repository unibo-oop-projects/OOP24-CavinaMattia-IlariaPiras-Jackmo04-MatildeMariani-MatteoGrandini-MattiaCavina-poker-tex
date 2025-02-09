package view.scenes;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;

import controller.game.api.GameController;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import view.gamepanels.AIPlayerPanel;
import view.gamepanels.PauseDialog;
import view.gamepanels.PlayerPanelImpl;
import view.gamepanels.TablePanel;
import view.player.user.MyButton;
import view.player.user.UserPanel;
import view.scenes.api.Scene;

/**
 * The {@link Scene} of the game.
 */
public class GameScene implements Scene {

    private static final String SCENE_NAME = "game";

    private final GameController controller;
    private final JPanel mainPanel;
    private final TablePanel table;
    private final PlayerPanelImpl westPlayerPanel;
    private final PlayerPanelImpl northPlayerPanel;
    private final PlayerPanelImpl eastPlayerPanel;
    private final PlayerPanelImpl southPlayerPanel;

    /**
     * Creates a new {@link GameScene}.
     * @param controller the controller for the game.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Storing GameController mutable object is intented")
    public GameScene(final GameController controller) {
        this.controller = controller;
        this.mainPanel = new JPanel(new BorderLayout());
        this.westPlayerPanel = new AIPlayerPanel();
        this.northPlayerPanel = new AIPlayerPanel();
        this.eastPlayerPanel = new AIPlayerPanel();
        this.southPlayerPanel = new UserPanel(this.controller.getUserPlayerController());
        this.table = new TablePanel();
        this.initialize();
    }

    /**
     * Initializes the mainPanel.
     */
    private void initialize() {
        final var southJPanel = new JPanel(new BorderLayout());
        final var buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(southPlayerPanel.getPanel().getBackground());
        final var pause = new MyButton("Pause");
        pause.initializeButton("PAUSE", pauseActionListener, buttonPanel);

        southJPanel.add(southPlayerPanel.getPanel(), BorderLayout.CENTER);
        southJPanel.add(buttonPanel, BorderLayout.EAST);

        westPlayerPanel.getPanel().setBackground(Color.DARK_GRAY);
        northPlayerPanel.getPanel().setBackground(Color.DARK_GRAY);
        eastPlayerPanel.getPanel().setBackground(Color.DARK_GRAY);
        southJPanel.setBackground(southPlayerPanel.getPanel().getBackground());

        this.mainPanel.add(northPlayerPanel.getPanel(), BorderLayout.NORTH);
        this.mainPanel.add(westPlayerPanel.getPanel(), BorderLayout.WEST);
        this.mainPanel.add(eastPlayerPanel.getPanel(), BorderLayout.EAST);
        this.mainPanel.add(southJPanel, BorderLayout.SOUTH);
        this.mainPanel.add(table.getPanel(), BorderLayout.CENTER);

        this.controller.setGameScene(this);
        this.controller.startGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        final var wrapper = new JPanel(new BorderLayout());
        wrapper.add(mainPanel, BorderLayout.CENTER);
        return wrapper;
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
        return switch (id) {
            case 0 -> this.westPlayerPanel;
            case 1 -> this.northPlayerPanel;
            case 2 -> this.eastPlayerPanel;
            case 3 -> this.southPlayerPanel;
            default -> throw new IllegalArgumentException("Invalid id.");
        };
    }

    /**
     * Calls the updateState method in the PlayerPanel corresponding to the id.
     * @param id the player's id.
     * @param isTurn boolean indicating whether is the player's turn.
     */
    public void updatePlayerPanelState(final int id, final boolean isTurn) {
        this.getPlayerPanel(id).updateState(isTurn);
    }

    /**
     * Calls the setCards method in its table.
     * @param cardImage the list of ImageIcons.
     */
    public void setCommunityCards(final List<ImageIcon> cardImage) {
        this.table.setCards(cardImage);
    }

    /**
     * Calls the setPot and resetPlayersBet methods in its table.
     * @param pot the pot.
     */
    public void setPot(final String pot) {
        this.table.setPot(pot);
        this.table.resetPlayersBet();
    }

    /**
     * Calls the setPlayerBet method in its table.
     * @param id the player's id.
     * @param bet the player's bet.
     */
    public void setPlayerBet(final int id, final String bet) {
        this.table.setPlayerBet(id, bet);
    }

    /**
     * Implements the pause button ActionListener.
     */
    private final ActionListener pauseActionListener = new ActionListener() {

        private static final int TRASPARENCY = 170;

        @Override
        public void actionPerformed(final ActionEvent e) {
            final JPanel glassPane = new JPanel() {

                @Override
                protected void paintComponent(final Graphics g) {
                    g.setColor(getBackground());
                    g.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }
            };
            glassPane.setOpaque(false);
            glassPane.setBackground(new Color(0, 0, 0, TRASPARENCY));

            final RootPaneContainer frame = (RootPaneContainer) SwingUtilities.getWindowAncestor(GameScene.this.mainPanel);
            frame.setGlassPane(glassPane);
            glassPane.setVisible(true);

            controller.pauseGame();
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    final var pauseDialog = new PauseDialog((Window) frame, controller);
                    pauseDialog.showDialog((Window) frame);

                    glassPane.setVisible(false);
                }

            });
        }
    };
}
