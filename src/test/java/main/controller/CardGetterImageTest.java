package main.controller;

import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import controller.card.CardGetterImage;

/**
 * CardGetterImmageTest is a class that is used to test the CardGetterImmage class.
 */
public class CardGetterImageTest { //NOPMD suppressed as it is a false positive 
    private static final int NUMBER_CARD = 2;
    private static final int TABLE_NUMBER_CARD = 5;

    /**
     * This method is used to test the getCardImage method.
     */
    @Test
    public void testGetCardImage() {
        final CardGetterImage cardGetterImmage = new CardGetterImage();
        final Set<Card> card = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND));

                            assertEquals(NUMBER_CARD, cardGetterImmage.getCardImage(card).size());
                            assertEquals(NUMBER_CARD, cardGetterImmage.getBackCardImage(NUMBER_CARD).size());
                            assertEquals(TABLE_NUMBER_CARD, cardGetterImmage.getTableCardImage(card).size());


    }

}
