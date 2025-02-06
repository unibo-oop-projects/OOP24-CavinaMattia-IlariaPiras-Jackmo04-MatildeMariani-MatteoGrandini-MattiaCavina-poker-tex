package view.gameScenePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.game.api.PauseController;
import model.game.api.Game;
import view.gameScenePanels.api.MyLabel;
import view.player.user.MyButton;
import view.scenes.MainMenuScene;

/**
 * Class that implements a {@link PauseDialog}. It contains buttons to resume the paused {@link Game}, 
 * to start a new or and to go back to the {@link MainMenuScene}.
 * When visible, the underlying panel and its components are no longer accessible.
 */
public class PauseDialog extends JDialog {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;

    private final PauseController controller;

    /**
     * Creates a new {@link PauseDialog}.
     * @param owner the owner of the PauseDialog
     */
    public PauseDialog(Window owner, PauseController controller) {
        super(owner);
        this.setModal(true);
        this.setLayout(new GridBagLayout());
        this.getContentPane().add(new PausePanel());
        this.setUndecorated(true);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.controller = controller;
    }

    /**
     * Private class that implements the {@link PauseDialog} panel.
     */
    private class PausePanel extends JPanel {

        private static final int VERTICAL_GAP = 10;
        private static final int TITLE_FONT_SIZE = 40;
        private static final int BUTTON_FONT_SIZE = 20;
        private static final int R_BACKGROUND = 220;
        private static final int G_BACKGROUND = 186;
        private static final int B_BACKGROUND = 133;

        /**
         * Creates a new {@link PausePanel}.
         */
        public PausePanel() {
            MyLabel title = new MyLabel("PAUSED");
            title.setFont(new Font("Roboto", Font.BOLD, TITLE_FONT_SIZE));

            this.setLayout(new GridLayout(4, 1, 0, VERTICAL_GAP));
            this.add(title);

            /*MyButton resumeButton = new MyButton("RESUME", "RESUME", actionListener, this);
              MyButton restartButton = new MyButton("NEW GAME", "NEW GAME", actionListener, this);
              MyButton menuButton = new MyButton("MENU", "MENU", actionListener, this);*/ 
              
            MyButton resumeButton = new MyButton("RESUME");
            resumeButton.initializeButton("RESUME", actionListener, this);
            MyButton restartButton = new MyButton("NEW GAME");
            restartButton.initializeButton("NEW GAME", actionListener, this);
            MyButton menuButton = new MyButton("MENU");
            menuButton.initializeButton(TOOL_TIP_TEXT_KEY, actionListener, this);

            resumeButton.setFont(new Font("Roboto", Font.BOLD, BUTTON_FONT_SIZE));
            restartButton.setFont(new Font("Roboto", Font.BOLD, BUTTON_FONT_SIZE));
            menuButton.setFont(new Font("Roboto", Font.BOLD, BUTTON_FONT_SIZE));

            this.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        }

        /**
         * Initialize the buttons actionListener.
         */
        private ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (((MyButton) e.getSource()).getActionCommand()) {
                    case "RESUME": 
                        PauseDialog.this.setVisible(false);
                        break;
                    case "NEW GAME": 
                        PauseDialog.this.controller.goToDifficultySelectionScene();
                        break;
                    case "MENU":
                        PauseDialog.this.controller.goToMainMenuScene();
                        break;
                    default:
                        break;
                }
            }
        };
    }

}
