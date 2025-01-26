package main.model.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import model.combination.CombinationsRulesImpl;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

public class CombinationsRulesImplTest {

    @Test
    void testPair() {
        List<Card> tableCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS));

        List<Card> playersCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).pair());
                assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).tris());

    }

    @Test
    void testTwoPairs() {
        List<Card> tableCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
        new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
        new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS));

List<Card> playersCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
        new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).twoPairs());
        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).tris());
        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).pair());

    }

    @Test
    void testTris() {
        List<Card> tableCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
        new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
        new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS));

    List<Card> playersCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).twoPairs());
        assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).tris());
        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).pair());
        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).poker());

    }

    @Test
    void testPoker() {
        List<Card> tableCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
        new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.SPADES),
        new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS));

    List<Card> playersCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).poker());
        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).tris());
        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).pair());

    }

    @Test
    void testFlush() {
        List<Card> tableCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.HEARTH));

    List<Card> playersCards = List.of(new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).poker());
        assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).flush());
        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).pair());

    }

    @Test
    void testFullHouse() {
        List<Card> tableCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
        new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
        new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS));

    List<Card> playersCards = List.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).twoPairs());
        assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).tris());
        assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).pair());
        assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).fullHouse());

    }

    @Test
    void testStraight() {
        List<Card> tableCards = List.of(new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.CLUBS),
        new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
        new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.SPADES),
        new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS));

    List<Card> playersCards = List.of(new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
        new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.DIAMOND));

        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).twoPairs());
        assertEquals(false, new CombinationsRulesImpl(tableCards, playersCards).fullHouse());
        assertEquals(true, new CombinationsRulesImpl(tableCards, playersCards).straight());

    }

    @Test
    void testRoyalFlush() {

    }
}
