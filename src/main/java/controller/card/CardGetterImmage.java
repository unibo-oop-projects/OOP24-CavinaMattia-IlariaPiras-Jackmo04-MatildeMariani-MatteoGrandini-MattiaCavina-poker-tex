package controller.card;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.swing.ImageIcon;

import model.deck.api.Card;

/**
 * CardGetterImmage is a class that is used to get the image of the card.
 */
public class CardGetterImmage {
    private static final String BASE_PATH = "card/";
    private static final String EXTENSION = ".jpg";
    private static final String DIVIDE_SIGN = "_";
    private static final String BACK_NAME = "BACK";
    private static final int TABLE_CARD = 5;

    public CardGetterImmage() {
    };

    /**
     * This method is used to get the image of the card.
     * 
     * @param card
     *             Set of card to be showed.
     * @return
     *         List of ImageIcon of the card.
     */
    public List<ImageIcon> getCardImage(final Set<Card> card) {
        return card.stream()
                .map(t -> new ImageIcon(ClassLoader
                        .getSystemResource(BASE_PATH + t.cardName() + DIVIDE_SIGN + t.valueOfCard() + EXTENSION)))
                .toList();
    }

    /**
     * This method is used to get the image of the back card.
     * 
     * @param numerOfBack
     *                    Number of back card to be showed.
     * @return
     *         List of ImageIcon of the back card.
     */
    public List<ImageIcon> getBackCardImage(final int numerOfBack) {
        return Stream.iterate(0, t -> t < numerOfBack, t -> t++)
                .map(t -> new ImageIcon(ClassLoader.getSystemResource(BASE_PATH + BACK_NAME + EXTENSION)))
                .toList();
    }

    /**
     * This method is used to get the image of the showed card and the hiden
     * card on the table .
     * 
     * @param card
     *             Set of card to be showed , its size must be under 5 card.
     * @return
     *         List of ImageIcon of the card and the back card.
     * @throws IllegalArgumentException
     *                                  If the number of card is over the limit.
     */
    public List<ImageIcon> tableImmageIcons(final Set<Card> card) {
        if (card.size() > TABLE_CARD) {
            throw new IllegalArgumentException("The number of card is over the limit");
        }
        return Stream.concat(getCardImage(card).stream(), getBackCardImage(TABLE_CARD - card.size())
                .stream())
                .toList();
    }

}
