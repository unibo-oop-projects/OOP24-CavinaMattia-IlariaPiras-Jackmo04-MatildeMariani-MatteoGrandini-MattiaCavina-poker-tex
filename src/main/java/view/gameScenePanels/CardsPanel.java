package view.gameScenePanels;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.gameScenePanels.api.MyLabel;

/**
 * Class that models a card panel with the specified number of cards. It has a CardGetterImage
 * and a method to set its cards image.
 */
public class CardsPanel extends JPanel {
    
    private static final int CARD_HEIGHT = 70;
    private static final int CARD_WIDTH = 50;
    
    /**
     * Creates a new CardsPanel with numCards cards.
     * @param numCards the number of cards
     * @param hGap the horizontal gap between cards
     */
    public CardsPanel(final int numCards, final int hGap) {

        this.setLayout(new GridLayout(1, numCards, hGap, 0));
        for (var i = 0; i < numCards; i++) {
            MyLabel card = new MyLabel("");
            card.setSize(CARD_WIDTH, CARD_HEIGHT);
            this.add(card);
        }

    }

    /**
     * Sets its cards image from the ImageIcon list
     * @param cardsImages the list of ImageIcons
     */
    public void setCards(final List<ImageIcon> cardsImages) {
        cardsImages.forEach(img -> ((MyLabel) this.getComponent(cardsImages.indexOf(img)))
                                    .setImageFromIcon(img));
    }

}
