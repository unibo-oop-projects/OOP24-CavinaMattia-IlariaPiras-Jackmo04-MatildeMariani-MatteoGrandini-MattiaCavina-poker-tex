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
 * It is a JFrame that contains a CardLayout to switch between different scenes.
 */
public class ViewImpl extends JFrame implements View {

    private static final double INIT_FRAME_SIZE_FACTOR = 0.75;
    private static final double MIN_FRAME_SIZE_FACTOR = 0.5; // TODO: change this value if needed

    private final Dimension screenSize;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    /**
     * Constructor for the MainView.
     * It initializes the frame and size of the window.
     * It also sets the CardLayout for switching between scenes.
     * It starts with the main menu scene.
     */
    public ViewImpl() {
        super("Poker Texas Hold'em");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var initWidth = (int) (screenSize.width * INIT_FRAME_SIZE_FACTOR);
        var initHeight = (int) (screenSize.height * INIT_FRAME_SIZE_FACTOR);
        var minWidth = (int) (screenSize.width * MIN_FRAME_SIZE_FACTOR);
        var minHeight = (int) (screenSize.height * MIN_FRAME_SIZE_FACTOR);
        this.setSize(new Dimension(initWidth, initHeight));
        this.setMinimumSize(new Dimension(minWidth, minHeight));

        // CardLayout for switching between scenes
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.setContentPane(this.mainPanel);

        // Always start with the start scene
        this.changeScene(new StartScene(new StartControllerImpl(this)));

        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeScene(final Scene scene) {
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
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                    ViewImpl.this, 
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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (this.getWindowListeners().length > 0) {
            this.removeWindowListener(this.getWindowListeners()[0]);            
        }
    }

}
