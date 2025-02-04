package controller.card;

import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;

import model.deck.api.Card;

/**
 * Inteface to trafrom set of card in its respective Immage to be showed.
 */
public interface CardGetterImmage {

    /**
     * This method is used to get the image of the card.
     * 
     * @param card
     *             Set of {@link model.deck.api.Card} to be showed.
     * @return
     *         List of {@link javax.swing.ImageIcon} of the card.
     */
    List<ImageIcon> getCardImage(Set<Card> card);

    /**
     * This method is used to get the image of the back card.
     * 
     * @param numerOfBack
     *                    Number of back card to be showed.
     * @return
     *         List of {@link javax.swing.ImageIcon} of the back card.
     */
    List<ImageIcon> getBackCardImage(int numerOfBack);

    /**
     * This method is used to get the image of the showed card and the hiden
     * card on the table .
     * 
     * @param card
     *             Set of card to be showed , its size must be under 5 card.
     * @return
     *         List of {@link javax.swing.ImageIcon} of the card and the back card.
     * @throws IllegalArgumentException
     *                                  If the number of {@link model.deck.api.Card} is over the limit.
     */
    List<ImageIcon> getTableCardImage(Set<Card> card);

}
