package view.panels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class AIPlayerPanel extends JPanel {

    /**
     * Constructor for the AIPlayerPanel class.
     */
    public AIPlayerPanel() {
        /*Temporary Images*/

        /*Creation cards Panel, initially covered*/
        JPanel cardsPanel = new JPanel(new GridLayout(1, 2));
        MyLabel dxCard = new MyLabel("");
        MyLabel sxCard = new MyLabel("");
        dxCard.setSize(50, 70);
        sxCard.setSize(50, 70);
        dxCard.setImage("retroCarta.png");
        sxCard.setImage("retroCarta.png");
        cardsPanel.add(sxCard);
        cardsPanel.add(dxCard);
        cardsPanel.setBackground(Color.CYAN);
        cardsPanel.setOpaque(true);

        /*Creation player image label*/
        MyLabel playerImage = new MyLabel("");
        playerImage.setSize(50, 50);
        playerImage.setImage("hat.png");

        /*Creation images panel, it contains the player image and his set of cards*/
        JPanel imagesPanel = new JPanel();
        imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.X_AXIS));
        imagesPanel.add(playerImage);
        imagesPanel.add(cardsPanel);
        imagesPanel.setBackground(Color.CYAN);
        imagesPanel.setOpaque(true);

        /*Creation data Panel, it contains the basic data of a player*/
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
        
        MyLabel playerAction = new MyLabel("ACTION ");
        MyLabel playerChips = new MyLabel(" Chips: 2000 ");
        MyLabel playerRole = new MyLabel(" SB");
        dataPanel.add(playerAction);
        dataPanel.add(playerChips);
        dataPanel.add(playerRole);
        dataPanel.setBackground(Color.CYAN);
        dataPanel.setOpaque(true);

        /*Adding panels to the main one*/
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(imagesPanel);
        this.add(dataPanel);
        this.setBackground(Color.CYAN);
        this.setOpaque(true);

    }

}
