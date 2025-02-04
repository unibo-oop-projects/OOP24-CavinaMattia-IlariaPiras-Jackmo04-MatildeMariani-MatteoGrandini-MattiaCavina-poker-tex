package controller.card;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.swing.ImageIcon;

import model.deck.api.Card;

/**
 * CardGetterImmage is a class that implements
 * {@link controller.card.CardGetterImmage}is used to get the image of the card.
 */
public class CardGetterImmageImpl implements CardGetterImmage {
    private static final String BASE_PATH = "card/";
    private static final String EXTENSION = ".jpg";
    private static final String DIVIDE_SIGN = "_";
    private static final String BACK_NAME = "BACK";
    private static final int TABLE_CARD = 5;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImageIcon> getCardImage(final Set<Card> card) {
        return card.stream()
                .map(t -> new ImageIcon(
                        ClassLoader.getSystemResource(
                                BASE_PATH + t.seedName() + DIVIDE_SIGN + t.valueOfCard() + EXTENSION)))
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImageIcon> getBackCardImage(final int numerOfBack) {
        return Stream.iterate(0, t -> t < numerOfBack, t -> t + 1)
                .map(t -> new ImageIcon(ClassLoader.getSystemResource(BASE_PATH + BACK_NAME + EXTENSION)))
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ImageIcon> getTableCardImage(final Set<Card> card) {
        if (card.size() > TABLE_CARD) {
            throw new IllegalArgumentException("The number of card is over the limit");
        }
        return Stream.concat(getCardImage(card).stream(), getBackCardImage(TABLE_CARD - card.size())
                .stream())
                .toList();
    }

}
