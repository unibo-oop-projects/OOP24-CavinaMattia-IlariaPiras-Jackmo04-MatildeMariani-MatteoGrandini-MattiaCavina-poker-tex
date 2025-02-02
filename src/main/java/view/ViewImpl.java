package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.menu.MainMenuControllerImpl;
import view.scenes.MainMenuScene;
import view.scenes.api.Scene;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

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

        // Always start with the main menu scene
        this.changeScene(new MainMenuScene(new MainMenuControllerImpl(this)));

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

    @Override
    public int getScreenWidth() {
        return this.screenSize.width;
    }

    @Override
    public int getScreenHeight() {
        return this.screenSize.height;
    }

}
