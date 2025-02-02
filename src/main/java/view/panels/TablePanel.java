package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TablePanel extends JPanel {

        private static final int NUM_CARDS = 5;
        private static final int THICKNESS = 30;
        private static final int GAP = 10;

        private CardsPanel cardsPanel;
        private MyLabel pot;
        private MyLabel westPlayerBet;
        private MyLabel northPlayerBet;
        private MyLabel eastPlayerBet;
        private MyLabel southPlayerBet;

        public TablePanel() {
            this.cardsPanel = new CardsPanel(NUM_CARDS, GAP);
            this.cardsPanel.setCards(cardsPanel.getCardGetterImage().getTableCardImage(Set.of()));
            cardsPanel.setBackground(Color.GREEN);

            this.pot = new MyLabel("Pot: 0");

            JPanel tableData = new JPanel(new GridLayout(2, 1));
            tableData.add(pot);
            tableData.add(cardsPanel);
            tableData.setBackground(Color.GREEN);
            JPanel table = new JPanel(new GridBagLayout());
            table.add(tableData);
            table.setBackground(Color.GREEN);

            this.westPlayerBet = new PlayerBetLabel("20");
            this.northPlayerBet = new PlayerBetLabel("20");
            this.eastPlayerBet = new PlayerBetLabel("20");
            this.southPlayerBet = new PlayerBetLabel("20");
            eastPlayerBet.setHorizontalTextPosition(JLabel.LEFT);
            
            this.setLayout(new BorderLayout(GAP, GAP));
            this.add(table, BorderLayout.CENTER);
            this.add(westPlayerBet, BorderLayout.WEST);
            this.add(northPlayerBet, BorderLayout.NORTH);
            this.add(eastPlayerBet, BorderLayout.EAST);
            this.add(southPlayerBet, BorderLayout.SOUTH);

            this.setBackground(Color.GREEN);
            this.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker(), THICKNESS, true));

        }

        private class PlayerBetLabel extends MyLabel{

            private static final int HEIGHT = 60;
            private static final int WIDTH = 50;
            private static final String PATH_FISCHES = "src/main/resources/fisches.png";

            PlayerBetLabel(final String text) {
                super(text);
                this.setSize(WIDTH, HEIGHT);
                this.setImageFromPath(PATH_FISCHES);
                this.setVerticalTextPosition(JLabel.CENTER);
                this.setHorizontalTextPosition(JLabel.RIGHT);
            }
        }
    }
