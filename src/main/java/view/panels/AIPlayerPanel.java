package view.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class AIPlayerPanel extends JPanel {

    private static final int PLAYER_IMAGE_WIDTH = 50;
    private static final int PLAYER_IMAGE_HEIGHT = 50;
    private static final int CARD_HEIGHT = 70;
    private static final int CARD_WIDTH = 50;

    /**
     * Constructor for the AIPlayerPanel class.
     */
    public AIPlayerPanel() {
        /*Temporary Images*/

        /*Creation cards Panel, initially covered*/
        JPanel cardsPanel = new JPanel(new GridLayout(1, 2));
        MyLabel dxCard = new MyLabel("");
        MyLabel sxCard = new MyLabel("");
        dxCard.setSize(CARD_WIDTH, CARD_HEIGHT);
        sxCard.setSize(CARD_WIDTH, CARD_HEIGHT);
        dxCard.setImage("src/main/resources/retroCarta.png");
        sxCard.setImage("src/main/resources/retroCarta.png");
        cardsPanel.add(sxCard);
        cardsPanel.add(dxCard);
        cardsPanel.setBackground(Color.LIGHT_GRAY);

        /*Creation player image label*/
        MyLabel playerImage = new MyLabel("");
        playerImage.setSize(PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT);
        playerImage.setImage("src/main/resources/hat.png");

        /*Creation images panel, it contains the player image and his set of cards*/
        JPanel imagesPanel = new JPanel();
        imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.X_AXIS));
        imagesPanel.add(playerImage);
        imagesPanel.add(cardsPanel);
        imagesPanel.setBackground(Color.LIGHT_GRAY);

        /*Creation data Panel, it contains the basic data of a player*/
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
            
        MyLabel playerAction = new MyLabel("ACTION ");
        MyLabel playerChips = new MyLabel(" Chips: 2000 ");
        MyLabel playerRole = new MyLabel(" SB");
        dataPanel.add(playerAction);
        dataPanel.add(playerChips);
        dataPanel.add(playerRole);
        dataPanel.setBackground(Color.LIGHT_GRAY);

        /*Adding panels to the main one*/
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(imagesPanel);
        mainPanel.add(dataPanel);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setOpaque(true);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5, true));

        this.setLayout(new GridBagLayout());
        this.add(mainPanel);

    }

}
