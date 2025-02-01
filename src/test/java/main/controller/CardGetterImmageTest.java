package main.controller;

import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import controller.card.CardGetterImmage;

public class CardGetterImmageTest {

    @Test
    public void testGetCardImage() {
        CardGetterImmage cardGetterImmage = new CardGetterImmage();
        Set<Card> card = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND));

                            assertEquals(2, cardGetterImmage.getCardImage(card).size());


    }

}
