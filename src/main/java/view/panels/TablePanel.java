package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that models a table panel. It has a {@link CardsPanel}, a label for the pot (or the winnings)
 * and one for each of the four player's bet.
 */
public class TablePanel extends JPanel {

        private static final int NUM_CARDS = 5;
        private static final int THICKNESS = 30;
        private static final int GAP = 10;

        private CardsPanel cardsPanel;
        private MyLabel pot;
        private PlayerBetLabel westPlayerBet;
        private PlayerBetLabel northPlayerBet;
        private PlayerBetLabel eastPlayerBet;
        private PlayerBetLabel southPlayerBet;

        /**
         * Constructor for the TablePanel class.
        */
        public TablePanel() {

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
            this.westPlayerBet = new PlayerBetLabel("20");
            this.northPlayerBet = new PlayerBetLabel("20");
            this.eastPlayerBet = new PlayerBetLabel("20");
            this.southPlayerBet = new PlayerBetLabel("20");
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

        public CardsPanel getCardsPanel() {
            return this.cardsPanel;
        }

        public void setPot(final String pot) {
            this.pot.setText("Pot: " + pot);
        }

        public void setPlayerBet(final int id, final String bet) {
            var playerBet = switch(id) {
                case 1 -> westPlayerBet;
                case 2 -> northPlayerBet;
                case 3 -> eastPlayerBet;
                case 4 -> southPlayerBet;
                default -> null;
            };
            playerBet.setText(bet);
            playerBet.showIcon();
        }

        /**
         * Private class that models a player's bet label.
         */
        private class PlayerBetLabel extends MyLabel{

            private static final int HEIGHT = 60;
            private static final int WIDTH = 50;
            private static final String PATH_FISCHES = "src/main/resources/fisches.png";

            PlayerBetLabel(final String text) {
                super(text);
                this.setSize(WIDTH, HEIGHT);
                this.setVerticalTextPosition(JLabel.CENTER);
                this.setHorizontalTextPosition(JLabel.RIGHT);
            }

            public void showIcon() {
                this.setImageFromPath(PATH_FISCHES);
            }
        }
    }
