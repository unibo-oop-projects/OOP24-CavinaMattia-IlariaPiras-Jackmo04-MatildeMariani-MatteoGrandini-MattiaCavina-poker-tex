package view.panels;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import view.panels.api.PlayerPanel;

/**
 * Class that models an AIPlayer panel. 
 * It implements the PlayerPanel interface.
 */
public class AIPlayerPanel extends JPanel implements PlayerPanel {

    private static final int NUM_CARDS = 2;
    private static final int PLAYER_IMAGE_WIDTH = 50;
    private static final int PLAYER_IMAGE_HEIGHT = 50;

    private CardsPanel cardsPanel;
    private MyLabel playerAction;
    private MyLabel playerChips;
    private MyLabel playerRole;

    /**
     * Constructor for the AIPlayerPanel class.
     */
    public AIPlayerPanel() {

        /*Creation of the player cardsPanel, with the cards initially covered*/
        this.cardsPanel = new CardsPanel(NUM_CARDS, 0);
        //this.cardsPanel.setCards(cardsPanel.getCardGetterImage().getBackCardImage(NUM_CARDS));
        this.cardsPanel.setBackground(Color.LIGHT_GRAY);

        /*Creation of the player image label*/
        MyLabel playerImage = new MyLabel("");
        playerImage.setSize(PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT);
        playerImage.setImageFromPath("src/main/resources/hat.png");

        /*Creation of imagesPanel, it contains the player image and his set of cards*/
        JPanel imagesPanel = new JPanel();
        imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.X_AXIS));
        imagesPanel.add(playerImage);
        imagesPanel.add(cardsPanel);
        imagesPanel.setBackground(Color.LIGHT_GRAY);

        /*Creation of dataPanel, it contains the basic data of a player*/
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.X_AXIS));
            
        this.playerAction = new MyLabel("ACTION ");
        this.playerChips = new MyLabel(" Chips: 2000 ");
        this.playerRole = new MyLabel(" SB");
        dataPanel.add(this.playerAction);
        dataPanel.add(this.playerChips);
        dataPanel.add(this.playerRole);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public CardsPanel getCardsPanel() {
        return this.cardsPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAction(final String action) {
        this.playerAction.setText(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChips(final String chips) {
        this.playerChips.setText(chips);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRole(final String role) {
        this.playerAction.setText(role);
    }

}
