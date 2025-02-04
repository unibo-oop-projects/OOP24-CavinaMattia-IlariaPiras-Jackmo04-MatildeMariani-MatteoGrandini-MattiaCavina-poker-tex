package view.scenes;

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

public class StartScene extends JPanel implements Scene {

    private static final int R_BUTTONS_PANEL = 236;
    private static final int G_BUTTONS_PANEL = 205;
    private static final int B_BUTTONS_PANEL = 153;
    private static final int R_BORDER = 0;  
    private static final int G_BORDER = 0;
    private static final int B_BORDER = 0;
    private static final int A_BORDER = 50;
    private static final int FONT_SIZE = 30; 
    private static final int THICKNESS = 4;
    private static final int R_BACKGROUND = 220;
    private static final int G_BACKGROUND = 186;
    private static final int B_BACKGROUND = 133;
    private static final String SCENE_NAME = "start";

    private final StartController controller;

    public StartScene(final StartController controller) {

        this.controller = controller;
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));
        
        JLabel title = new JLabel("POKER TEXAS HOLD'EM", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 50));
        titlePanel.add(title);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        menuPanel.setBackground(new Color(R_BACKGROUND, G_BACKGROUND, B_BACKGROUND));

        JButton button = new JButton("Press to start");
        button.setBackground(new Color(R_BUTTONS_PANEL, G_BUTTONS_PANEL, B_BUTTONS_PANEL));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Roboto", Font.BOLD, FONT_SIZE));
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), 
            BorderFactory.createLineBorder(new Color(R_BORDER, G_BORDER, B_BORDER, A_BORDER), THICKNESS, true)));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setPreferredSize(new Dimension(250, 60));
        button.addActionListener(e -> this.controller.goToMainMenuScene());
        menuPanel.add(button);
        centerPanel.add(titlePanel);
        centerPanel.add(menuPanel);
        mainPanel.add(centerPanel);
        this.add(mainPanel);
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
}