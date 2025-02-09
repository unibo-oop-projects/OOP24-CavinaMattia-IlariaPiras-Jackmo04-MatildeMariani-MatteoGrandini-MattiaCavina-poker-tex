package view.gamepanels;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.gamepanels.api.CustomLabel;

/**
 * Class that models an AIPlayer panel. 
 * It extends PlayerPanelImpl.
 */
public class AIPlayerPanel extends PlayerPanelImpl {

    private static final int PLAYER_IMAGE_WIDTH = 50;
    private static final int PLAYER_IMAGE_HEIGHT = 50;
    private static final int BORDER_THICKNESS = 5;
    private static final String HAT_PATH = "table/hat.png";

    private final CustomLabel customLabelFactory;
    private final JLabel playerImage;
    private final JPanel imagesPanel;
    private final JPanel dataPanel;
    private final JPanel mainPanel;

    /**
     * Constructor for the AIPlayerPanel class.
     */
    public AIPlayerPanel() {
        super();
        this.customLabelFactory = new CustomLabel();
        this.playerImage = this.customLabelFactory.getCustomLabel("");
        this.imagesPanel = new JPanel();
        this.dataPanel = new JPanel();
        this.mainPanel = new JPanel();
        this.initialize();
    }

    private void initialize() {
        this.getCardsPanel().getPanel().setBackground(Color.LIGHT_GRAY);

        playerImage.setSize(PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT);
        this.customLabelFactory.setIconFromPath(playerImage, HAT_PATH);
        this.playerImage.setEnabled(false);

        imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.X_AXIS));
        imagesPanel.add(playerImage);
        imagesPanel.add(this.getCardsPanel().getPanel());
        imagesPanel.setBackground(Color.LIGHT_GRAY);

        dataPanel.add(this.getPlayerAction());
        dataPanel.add(this.getPlayerChips());
        dataPanel.add(this.getPlayerRole());
        dataPanel.setBackground(Color.LIGHT_GRAY);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(imagesPanel);
        mainPanel.add(dataPanel);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setOpaque(true);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, BORDER_THICKNESS, true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        final var wrapper = new JPanel(new GridBagLayout());
        wrapper.add(mainPanel);
        wrapper.setBackground(Color.DARK_GRAY);
        return wrapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final boolean isTurn) {
        this.playerImage.setEnabled(isTurn);
    }
}
