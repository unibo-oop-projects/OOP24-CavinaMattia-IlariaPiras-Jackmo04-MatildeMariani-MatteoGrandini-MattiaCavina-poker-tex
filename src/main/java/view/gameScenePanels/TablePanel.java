package view.gameScenePanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.gameScenePanels.api.MyLabel;

/**
 * Class that models a table panel. It has a {@link CardsPanel}, a label for the pot (or the winnings)
 * and one for each of the four player's bet.
 */
public class TablePanel extends JPanel {

    private static final int NUM_CARDS = 5;
    private static final int THICKNESS = 30;
    private static final int GAP = 10;

    private final CardsPanel cardsPanel;
    private final MyLabel pot;
    private final PlayerBetLabel westPlayerBet;
    private final PlayerBetLabel northPlayerBet;
    private final PlayerBetLabel eastPlayerBet;
    private final PlayerBetLabel southPlayerBet;

    /**
     * Constructor for the TablePanel class.
    */
    public TablePanel() {

        super();

        /*Creation of the table cardsPanel, with the cards initially covered*/
        this.cardsPanel = new CardsPanel(NUM_CARDS, GAP);
        //this.cardsPanel.setCards(cardsPanel.getCardGetterImage().getTableCardImage(Set.of()));
        cardsPanel.setBackground(Color.GREEN);

        /*Creation of the pot label*/
        this.pot = new MyLabel("Pot: 0");

        /*Creation of the tableData panel, it contains the pot and the cardsPanel*/
        JPanel tableData = new JPanel(new GridLayout(2, 1));
        tableData.add(pot);
        tableData.add(cardsPanel);
        tableData.setBackground(Color.GREEN);
        JPanel table = new JPanel(new GridBagLayout());
        table.add(tableData);
        table.setBackground(Color.GREEN);

        /*Creation of the four player's bet labels*/
        this.westPlayerBet = new PlayerBetLabel("");
        this.northPlayerBet = new PlayerBetLabel("");
        this.eastPlayerBet = new PlayerBetLabel("");
        this.southPlayerBet = new PlayerBetLabel("");
        eastPlayerBet.setHorizontalTextPosition(JLabel.LEFT);
            
        /*Adding components to the TablePanel*/
        this.setLayout(new BorderLayout(GAP, GAP));
        this.add(table, BorderLayout.CENTER);
        this.add(westPlayerBet, BorderLayout.WEST);
        this.add(northPlayerBet, BorderLayout.NORTH);
        this.add(eastPlayerBet, BorderLayout.EAST);
        this.add(southPlayerBet, BorderLayout.SOUTH);

        this.setBackground(Color.GREEN);
        this.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker(), THICKNESS, true));

    }

    /**
     * Returns the {@link CardsPanel}.
     * @return the CardsPanel.
     */
    public CardsPanel getCardsPanel() {
        return this.cardsPanel;
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
        var playerBet = switch(id) {
            case 0 -> westPlayerBet;
            case 1 -> northPlayerBet;
            case 2 -> eastPlayerBet;
            case 3 -> southPlayerBet;
            default -> null;
        };
        if (playerBet != null) {
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
     * Private class that models a player's bet label.
    */
    private class PlayerBetLabel extends MyLabel{

        private static final int HEIGHT = 60;
        private static final int WIDTH = 50;
        private static final String PATH_FISCHES = "src/main/resources/fisches.png";

        /**
         * Creates a new PlayerBetLabel.
         * @param text the text of the label.
         */
        PlayerBetLabel(final String text) {
            super(text);
            this.setSize(WIDTH, HEIGHT);
            this.setImageFromPath(PATH_FISCHES);
            this.setVerticalTextPosition(JLabel.CENTER);
            this.setHorizontalTextPosition(JLabel.RIGHT);
            this.setVisible(false);
        }
    }
}
