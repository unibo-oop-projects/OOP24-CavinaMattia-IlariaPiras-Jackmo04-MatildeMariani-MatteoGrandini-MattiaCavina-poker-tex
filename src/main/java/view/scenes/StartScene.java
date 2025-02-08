package view.scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.start.StartController;
import view.scenes.api.Scene;

/**
 * The StartScene class represents the initial scene of the application.
 * It displays the title of the game and allows the user to proceed to the main menu by pressing the button.
 */
public class StartScene implements Scene {
    private static final int COLOR_BUTTONS_PANEL = 0xECCD99;
    private static final int R_BORDER = 0;
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int FONT_SIZE_TITLE = 50; 
    private static final int FONT_SIZE = 30; 
    private static final int THICKNESS = 4;
    private static final int COLOR_BACKGROUND = 0xDCBA85;
    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 60;
    private static final String SCENE_NAME = "start";

    private final StartController controller;
    private final JPanel startPanel;

    /**
     * Constructs a new StartScene.
     * @param controller the controller that handles the start scene logic.
     */
    public StartScene(final StartController controller) {

        this.controller = controller;
        this.startPanel = new JPanel(new BorderLayout());
        initialize();
    }

    private void initialize() {
        this.startPanel.setLayout(new GridBagLayout());
        this.startPanel.setBackground(new Color(COLOR_BACKGROUND));

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(new Color(COLOR_BACKGROUND));

        final JLabel title = new JLabel("POKER TEXAS HOLD'EM", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE_TITLE));
        titlePanel.add(title);

        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        final JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        menuPanel.setBackground(new Color(COLOR_BACKGROUND));

        final JButton button = new JButton("Press to start");
        button.setBackground(new Color(COLOR_BUTTONS_PANEL));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE));
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.addActionListener(e -> this.controller.goToMainMenuScene());

        menuPanel.add(button);

        centerPanel.add(titlePanel);
        centerPanel.add(menuPanel);

        mainPanel.add(centerPanel);

        this.startPanel.add(mainPanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        final var wrapper = new JPanel(new BorderLayout());
        wrapper.add(this.startPanel, BorderLayout.CENTER);
        return wrapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSceneName() {
        return SCENE_NAME;
    }
}
