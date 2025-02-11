package pokertexas.controller.card;

import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;

import pokertexas.model.deck.api.Card;

/**
 * Inteface to transfom {@link Set} of {@link Card} in its
 * respective Immage to be showed.
 */
public interface CardGetterImage {
    /**
     * This method is used to get the image of the card from its class
     * {@link Card}.
     * 
     * @param card Set of {@link Card} to be showed.
     * @return List of {@link ImageIcon} of the card.
     */
    List<ImageIcon> getCardImage(Set<Card> card);

    /**
     * This method is used to get the {@link ImageIcon} of the card back.
     * Is used to show hiden card.
     * 
     * @param numerOfBack Number of back card to be showed.
     * @return List of {@link ImageIcon} of the back card.
     */
    List<ImageIcon> getBackCardImage(int numerOfBack);

    /**
     * This method is used to get the image of the showed card and the hiden
     * card on the table .
     * 
     * @param card Set of card to be showed , its size must be under 5 card.
     * @return List of {@link ImageIcon} of the card and the back card.
     * @throws IllegalArgumentException If the number of {@link Card}
     *                                  is over the limit.
     */
    List<ImageIcon> getTableCardImage(Set<Card> card);

}
