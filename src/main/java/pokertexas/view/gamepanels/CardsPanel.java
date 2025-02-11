package pokertexas.view.gamepanels;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pokertexas.view.gamepanels.api.CustomLabel;

/**
 * Class that models a card panel with the specified number of cards. It has a CardGetterImage
 * and a method to set its cards icon.
 */
public class CardsPanel {

    private static final int CARD_HEIGHT = 70;
    private static final int CARD_WIDTH = 50;

    private final JPanel mainPanel;

    /**
     * Creates a new CardsPanel with numCards cards.
     * @param numCards the number of cards
     */
    public CardsPanel(final int numCards) {
        this.mainPanel = new JPanel(new GridLayout(1, numCards));
        for (var i = 0; i < numCards; i++) {
            final var card = new CustomLabel().getCustomLabel("");
            card.setSize(CARD_WIDTH, CARD_HEIGHT);
            this.mainPanel.add(card);
        }
    }

    /**
     * Sets its cards icon from the ImageIcon list.
     * @param cardsImages the list of ImageIcons.
     */
    public void setCards(final List<ImageIcon> cardsImages) {
        final var customLabelFactory = new CustomLabel();
        cardsImages.forEach(img -> customLabelFactory.setIconFromIcon(
            (JLabel) mainPanel.getComponent(cardsImages.indexOf(img)), img));
    }

    /**
     * Returns the CardsPanel panel.
     * @return the panel.
     */
    public JPanel getPanel() {
        final var wrapper = new JPanel(new GridBagLayout());
        wrapper.add(mainPanel);
        return wrapper;
    }
}
