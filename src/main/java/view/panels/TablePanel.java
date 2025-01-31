package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablePanel extends JPanel {

        private static final int CARD_HEIGHT = 70;
        private static final int CARD_WIDTH = 50;

        TablePanel() {
            JPanel cardsPanel = new JPanel(new GridLayout(1, 5, 10, 0));
            MyLabel card1 = new MyLabel("");
            MyLabel card2 = new MyLabel("");
            MyLabel card3 = new MyLabel("");
            MyLabel card4 = new MyLabel("");
            MyLabel card5 = new MyLabel("");
            card1.setSize(CARD_WIDTH, CARD_HEIGHT);
            card2.setSize(CARD_WIDTH, CARD_HEIGHT);
            card3.setSize(CARD_WIDTH, CARD_HEIGHT);
            card4.setSize(CARD_WIDTH, CARD_HEIGHT);
            card5.setSize(CARD_WIDTH, CARD_HEIGHT);

            card1.setImage("src/main/resources/retroCarta.png");
            card2.setImage("src/main/resources/retroCarta.png");
            card3.setImage("src/main/resources/retroCarta.png");
            card4.setImage("src/main/resources/retroCarta.png");
            card5.setImage("src/main/resources/retroCarta.png");

            cardsPanel.add(card1);
            cardsPanel.add(card2);
            cardsPanel.add(card3);
            cardsPanel.add(card4);
            cardsPanel.add(card5);
            cardsPanel.setBackground(Color.GREEN);

            MyLabel pot = new MyLabel("Pot: 0");

            JPanel tableData = new JPanel(new GridLayout(2, 1));
            tableData.add(pot);
            tableData.add(cardsPanel);
            tableData.setBackground(Color.GREEN);
            JPanel table = new JPanel(new GridBagLayout());
            table.add(tableData);
            table.setBackground(Color.GREEN);

            MyLabel westPlayerBet = new MyLabel("20");
            MyLabel northPlayerBet = new MyLabel("20");
            MyLabel eastPlayerBet = new MyLabel("20");
            MyLabel southPlayerBet = new MyLabel("20");

            westPlayerBet.setSize(50, 60);
            westPlayerBet.setImage("src/main/resources/fisches.png");
            westPlayerBet.setVerticalTextPosition(JLabel.CENTER);
            westPlayerBet.setHorizontalTextPosition(JLabel.RIGHT);

            northPlayerBet.setSize(50, 60);
            northPlayerBet.setImage("src/main/resources/fisches.png");
            northPlayerBet.setVerticalTextPosition(JLabel.CENTER);
            northPlayerBet.setHorizontalTextPosition(JLabel.RIGHT);

            eastPlayerBet.setSize(50, 60);
            eastPlayerBet.setImage("src/main/resources/fisches.png");
            eastPlayerBet.setVerticalTextPosition(JLabel.CENTER);
            eastPlayerBet.setHorizontalTextPosition(JLabel.LEFT);

            southPlayerBet.setSize(50, 60);
            southPlayerBet.setImage("src/main/resources/fisches.png");
            southPlayerBet.setVerticalTextPosition(JLabel.CENTER);
            southPlayerBet.setHorizontalTextPosition(JLabel.RIGHT);
            
            this.setLayout(new BorderLayout(10, 10));
            this.add(table, BorderLayout.CENTER);
            this.add(westPlayerBet, BorderLayout.WEST);
            this.add(northPlayerBet, BorderLayout.NORTH);
            this.add(eastPlayerBet, BorderLayout.EAST);
            this.add(southPlayerBet, BorderLayout.SOUTH);

            this.setBackground(Color.GREEN);
            this.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker(), 30, true));

        }
    }
