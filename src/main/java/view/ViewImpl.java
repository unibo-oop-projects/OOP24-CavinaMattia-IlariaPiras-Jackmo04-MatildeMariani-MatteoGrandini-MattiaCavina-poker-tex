package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.start.StartControllerImpl;
import view.scenes.StartScene;
import view.scenes.api.Scene;

/**
 * The main view of the application.
 * It uses a JFrame with a CardLayout to switch between different scenes.
 */
public class ViewImpl implements View {

    private static final double INIT_FRAME_SIZE_FACTOR = 0.75;
    private static final double MIN_FRAME_SIZE_FACTOR = 0.5; // TODO: change this value if needed

    private final JFrame frame;
    private final Dimension screenSize;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    /**
     * Constructor for the MainView.
     * It initializes the frame and size of the window.
     * It also sets the CardLayout for switching between scenes.
     */
    public ViewImpl() {
        this.frame = new JFrame("Poker Texas Hold'em");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final var initWidth = (int) (screenSize.width * INIT_FRAME_SIZE_FACTOR);
        final var initHeight = (int) (screenSize.height * INIT_FRAME_SIZE_FACTOR);
        final var minWidth = (int) (screenSize.width * MIN_FRAME_SIZE_FACTOR);
        final var minHeight = (int) (screenSize.height * MIN_FRAME_SIZE_FACTOR);
        this.frame.setSize(new Dimension(initWidth, initHeight));
        this.frame.setMinimumSize(new Dimension(minWidth, minHeight));

        // CardLayout for switching between scenes
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.frame.setContentPane(this.mainPanel);

        // Always start with the start scene
        this.changeScene(new StartScene(new StartControllerImpl(this)));

        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void changeScene(final Scene scene) {
        this.mainPanel.add(scene.getPanel(), scene.getSceneName());
        this.cardLayout.show(this.mainPanel, scene.getSceneName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScreenWidth() {
        return this.screenSize.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScreenHeight() {
        return this.screenSize.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableConfermationOnClose() {
        if (this.isConfirmationOnCloseEnabled()) {
            return;
        }
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                final int confirm = JOptionPane.showOptionDialog(
                    frame, 
                    """
                        Are you sure you want to exit the game?
                        Progress will be lost.
                    """,
                    "Exit game?",
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    null, 
                    null
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableConfermationOnClose() {
        if (!this.isConfirmationOnCloseEnabled()) {
            return;
        }
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.removeWindowListener(this.frame.getWindowListeners()[0]);
    }

    private boolean isConfirmationOnCloseEnabled() {
        return this.frame.getWindowListeners().length > 0;
    }

}
