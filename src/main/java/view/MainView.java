package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MainController;
import controller.MainControllerImpl;
import view.commons.Scene;
import view.tempMainMenu.MainMenuScene;
import view.tempMainMenu.MainMenuSceneControllerImpl;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainView extends JFrame implements View {

    private static final double SCREEN_SIZE_FACTOR = 0.75;

    private final int screenWidth;
    private final int screenHeight;
    private final JPanel mainPanel;
    private final MainController mainController;
    private final CardLayout cardLayout;

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
        
        this.mainController = new MainControllerImpl(this);

        // Always start with the main menu scene
        MainMenuScene menuScene = new MainMenuScene(new MainMenuSceneControllerImpl(this, this.mainController));
        this.mainPanel.add(menuScene.getPanel(), menuScene.getName());
        this.cardLayout.show(this.mainPanel, menuScene.getName());

        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    @Override
    public void changeScene(Scene scene) {
        this.mainPanel.add(scene.getPanel(), scene.getName());
        this.cardLayout.show(this.mainPanel, scene.getName());
    }

}
