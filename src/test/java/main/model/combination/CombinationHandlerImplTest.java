package main.model.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.combination.CombinationHandlerImpl;
import model.combination.api.CombinationType;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test of CombinationHandlerImpl.
 */
public class CombinationHandlerImplTest { // NOPMD suppressed as it is a false positive

    /**
     * Test of Pair.
     */
    @Test
    void testPair() {
        final Set<Card> totalCardList = Set.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.SPADES),
                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(CombinationType.PAIR, new CombinationHandlerImpl().getCombination(totalCardList).type());
        assertNotEquals(CombinationType.POKER, new CombinationHandlerImpl().getCombination(totalCardList).type());

    }

    /**
     * Test of straight.
     */
    @Test
    void testStraight() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.SPADES),
                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.HEARTH),
                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(CombinationType.STRAIGHT, new CombinationHandlerImpl().getCombination(totalCardList).type());
        assertNotEquals(CombinationType.ROYAL_FLUSH, new CombinationHandlerImpl().getCombination(totalCardList).type());
    }

        /**
     * Test of Pair.
     */
    @Test
    void testHightCard() {
        final Set<Card> totalCardList = Set.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.SPADES),
                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.SPADES),
                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(CombinationType.HIGH_CARD, new CombinationHandlerImpl().getCombination(totalCardList).type());
        assertNotEquals(CombinationType.POKER, new CombinationHandlerImpl().getCombination(totalCardList).type());

    }



}
