package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.menu.MainMenuControllerImpl;
import view.commons.Scene;
import view.menu.MainMenuScene;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * The main view of the application.
 * It is a JFrame that contains a CardLayout to switch between different scenes.
 */
public class MainView extends JFrame implements View {

    private static final double SCREEN_SIZE_FACTOR = 0.75;

    private final int screenWidth;
    private final int screenHeight;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    /**
     * Constructor for the MainView.
     * It initializes the frame and size of the window.
     * It also sets the CardLayout for switching between scenes.
     * It starts with the main menu scene.
     */
    public MainView() {
        super("Poker Texas Hold'em");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = (int) (screenSize.width * SCREEN_SIZE_FACTOR);
        this.screenHeight = (int) (screenSize.height * SCREEN_SIZE_FACTOR);
        this.setSize(new Dimension(screenWidth, screenHeight));

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
    public void changeScene(Scene scene) {
        this.mainPanel.add(scene.getPanel(), scene.getSceneName());
        this.cardLayout.show(this.mainPanel, scene.getSceneName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScreenHeight() {
        return this.screenHeight;
    }

}
