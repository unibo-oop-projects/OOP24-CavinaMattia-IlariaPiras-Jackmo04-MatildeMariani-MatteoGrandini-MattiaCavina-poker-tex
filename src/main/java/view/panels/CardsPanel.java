package view.panels;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.card.CardGetterImmage;

public class CardsPanel extends JPanel {
    
    private static final int CARD_HEIGHT = 70;
    private static final int CARD_WIDTH = 50;
    
    private CardGetterImmage cardGetterImage;

    public CardsPanel(final int numCards, final int hGap) {

        this.cardGetterImage = new CardGetterImmage();

        this.setLayout(new GridLayout(1, numCards, hGap, 0));
        for (var i = 0; i < numCards; i++) {
            MyLabel card = new MyLabel("");
            card.setSize(CARD_WIDTH, CARD_HEIGHT);
            this.add(card);
        }

    }

    public void setCards(final List<ImageIcon> cardsImages) {
        cardsImages.forEach(img -> ((MyLabel) this.getComponent(cardsImages.indexOf(img)))
                                    .setImageFromIcon(img));
    }

    public CardGetterImmage getCardGetterImage() {
        return cardGetterImage;
    }

}
