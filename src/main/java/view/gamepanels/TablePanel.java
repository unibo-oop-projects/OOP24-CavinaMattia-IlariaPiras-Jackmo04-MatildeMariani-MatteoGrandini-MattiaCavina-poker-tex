package view.gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.gamepanels.api.CustomLabel;

/**
 * Class that models a table panel. It has a {@link CardsPanel}, a label for the pot (or the winnings)
 * and one for each of the four player's bet.
 */
public class TablePanel {

    private static final int HEIGHT = 60;
    private static final int WIDTH = 50;
    private static final int NUM_CARDS = 5;
    private static final int THICKNESS = 30;
    private static final String PATH_FISCHES = "table/fisches.png";

    private final CustomLabel customLabelFactory;
    private final JPanel mainPanel;
    private final CardsPanel cardsPanel;
    private final JLabel pot;
    private final JLabel westPlayerBet;
    private final JLabel northPlayerBet;
    private final JLabel eastPlayerBet;
    private final JLabel southPlayerBet;

    /**
     * Constructor for the TablePanel class.
    */
    public TablePanel() {
        this.customLabelFactory = new CustomLabel();
        this.mainPanel = new JPanel(new BorderLayout());
        this.cardsPanel = new CardsPanel(NUM_CARDS);
        this.pot = this.customLabelFactory.getCustomLabel("Pot: 0");
        this.westPlayerBet = this.getPlayerBetLabel("");
        this.northPlayerBet = this.getPlayerBetLabel("");
        this.eastPlayerBet = this.getPlayerBetLabel("");
        this.southPlayerBet = this.getPlayerBetLabel("");
        this.initialize();
    }

    /**
     * Initializes the panel.
     */
    private void initialize() {
        final JPanel tableData = new JPanel(new GridLayout(2, 1));
        tableData.add(pot);
        tableData.add(cardsPanel.getPanel());
        tableData.setBackground(Color.GREEN);
        final JPanel table = new JPanel(new GridBagLayout());
        table.add(tableData);
        table.setBackground(Color.GREEN);

        eastPlayerBet.setHorizontalTextPosition(JLabel.LEFT);

        this.mainPanel.add(table, BorderLayout.CENTER);
        this.mainPanel.add(westPlayerBet, BorderLayout.WEST);
        this.mainPanel.add(northPlayerBet, BorderLayout.NORTH);
        this.mainPanel.add(eastPlayerBet, BorderLayout.EAST);
        this.mainPanel.add(southPlayerBet, BorderLayout.SOUTH);

        this.mainPanel.setBackground(Color.GREEN);
        this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker(), THICKNESS, true));
    }

    /**
     * Calls the setCards method in its {@link CardsPanel}.
     * @param cardImage the list of ImageIcons. 
     */
    public void setCards(final List<ImageIcon> cardImage) {
        this.cardsPanel.setCards(cardImage);
    }

    /**
     * Sets the pot label.
     * @param pot the pot.
     */
    public void setPot(final String pot) {
        this.pot.setText("Pot: " + pot);
    }

    /**
     * Sets the PlayerBetLabel corresponding to id.
     * @param id the player's id.
     * @param bet the player's bet.
     */
    public void setPlayerBet(final int id, final String bet) {
        final var playerBet = switch (id) {
            case 0 -> westPlayerBet;
            case 1 -> northPlayerBet;
            case 2 -> eastPlayerBet;
            case 3 -> southPlayerBet;
            default -> throw new IllegalArgumentException("Invalid id.");
        };
        if (bet.isBlank()) {
            playerBet.setVisible(false);
        } else {
            playerBet.setText(bet);
            playerBet.setVisible(true);
        }
    }

    /**
     * Disables all the PlayerBetLabels.
     */
    public void resetPlayersBet() {
        this.westPlayerBet.setVisible(false);
        this.northPlayerBet.setVisible(false);
        this.eastPlayerBet.setVisible(false);
        this.southPlayerBet.setVisible(false);
    }

    /**
     * Returns the TablePanel panel.
     * @return the panel.
     */
    public JPanel getPanel() {
        final var wrapper = new JPanel(new BorderLayout());
        wrapper.add(mainPanel, BorderLayout.CENTER);
        return wrapper;
    }

    /**
     * Creates a new PlayerBetLabel.
     * @param text the text of the label.
     * @return the player bet label.
     */
    private JLabel getPlayerBetLabel(final String text) {
        final var playerBetLabel = this.customLabelFactory.getCustomLabel(text);
        playerBetLabel.setSize(WIDTH, HEIGHT);
        playerBetLabel.setVerticalTextPosition(JLabel.CENTER);
        playerBetLabel.setHorizontalTextPosition(JLabel.RIGHT);
        this.customLabelFactory.setIconFromPath(playerBetLabel, PATH_FISCHES);
        playerBetLabel.setVisible(false);
        return playerBetLabel;
    }
}
