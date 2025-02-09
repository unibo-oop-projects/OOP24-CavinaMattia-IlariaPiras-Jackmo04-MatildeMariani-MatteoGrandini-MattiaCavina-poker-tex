package view.gamepanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.game.api.GameController;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.game.api.Game;
import view.gamepanels.api.CustomLabel;
import view.player.user.MyButton;
import view.scenes.MainMenuScene;

/**
 * Class that implements a {@link PauseDialog}. It contains buttons to resume the paused {@link Game}, 
 * to start a new one and to go back to the {@link MainMenuScene}.
 * When visible, the underlying panel and its components are no longer accessible.
 */
public class PauseDialog {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;
    private static final int R_BACKGROUND = 220;
    private static final int G_BACKGROUND = 186;
    private static final int B_BACKGROUND = 133;
    private static final int VERTICAL_GAP = 10;
    private static final int TITLE_FONT_SIZE = 40;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final String FONT_FAMILY = "Roboto";
    private static final Logger LOGGER = LoggerFactory.getLogger(PauseDialog.class);

    private final GameController controller;
    private final JDialog dialog;

    /**
     * Creates a new {@link PauseDialog}.
     * @param owner the owner of the PauseDialog.
     * @param controller the game controller.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Storing GameController mutable object is intented")
    public PauseDialog(final Window owner, final GameController controller) {
        this.controller = controller;
        this.dialog = new JDialog(owner);
        this.initialize();
    }

    /**
     * Initializes the dialog.
     */
    private void initialize() {
        this.dialog.setModal(true);
        this.dialog.setLayout(new GridBagLayout());
        this.dialog.getContentPane().add(this.getPausePanel());
        this.dialog.setUndecorated(true);
        this.dialog.setSize(new Dimension(WIDTH, HEIGHT));
        this.dialog.getContentPane().setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
    }

    /**
     * Returns a new pause panel.
     * @return a new pause panel.
     */
    private JPanel getPausePanel() {
        final var pausePanel = new JPanel();
        final JLabel title = new CustomLabel().getCustomLabel("PAUSED");
        title.setFont(new Font(FONT_FAMILY, Font.BOLD, TITLE_FONT_SIZE));

        pausePanel.setLayout(new GridLayout(4, 1, 0, VERTICAL_GAP));
        pausePanel.add(title);

        final MyButton resumeButton = new MyButton("RESUME");
        resumeButton.initializeButton("RESUME", actionListener, pausePanel);
        final MyButton restartButton = new MyButton("NEW GAME");
        restartButton.initializeButton("NEW GAME", actionListener, pausePanel);
        final MyButton menuButton = new MyButton("MENU");
        menuButton.initializeButton("MENU", actionListener, pausePanel);

        resumeButton.setFont(new Font(FONT_FAMILY, Font.BOLD, BUTTON_FONT_SIZE));
        restartButton.setFont(new Font(FONT_FAMILY, Font.BOLD, BUTTON_FONT_SIZE));
        menuButton.setFont(new Font(FONT_FAMILY, Font.BOLD, BUTTON_FONT_SIZE));

        pausePanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        return pausePanel;
    }

    /**
     * Implements the buttons actionListener.
     */
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
            switch (((MyButton) e.getSource()).getActionCommand()) {
                case "NEW GAME":
                    controller.goToDifficultySelectionScene();
                    break;
                case "MENU":
                    controller.goToMainScene();
                    break;
                case "RESUME":
                    controller.resumeGame();
                    break;
                default:
                    LOGGER.error("Unexpeted button command");
                    break;
            }
            dialog.dispose();
        }
    };

    /**
     * Makes the dialog visible.
     * @param owner the owner of the PauseDialog.
     */
    public void showDialog(final Window owner) {
        this.dialog.setLocationRelativeTo(owner);
        this.dialog.setVisible(true);
    }
}
