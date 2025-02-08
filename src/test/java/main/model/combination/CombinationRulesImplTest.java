package main.model.combination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import model.combination.CombinationRulesImpl;
import model.combination.CombinationUtilitiesImpl;
import model.combination.api.CombinationUtilities;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test of {@link CombinationRulesImpl}.
 */
class CombinationRulesImplTest {

            CombinationUtilities utilies = new CombinationUtilitiesImpl();

    /**
     * Test for the method isPair.
     */
    @Test
    void testPair() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isPair());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTris());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isPoker());

        totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.ACE, SeedCard.HEARTH));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isPair());

        totalCardList = Sets.newHashSet();

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isPair());

    }

    /**
     * Test for the method isTwoPairs.
     */
    @Test
    void testTwoPairs() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isTwoPairs());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTris());
        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isPair());

        totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.ACE, SeedCard.HEARTH));

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTris());

        totalCardList = Sets.newHashSet();

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTwoPairs());

    }

    /**
     * Test for the method isTris.
     */
    @Test
    void testTris() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTwoPairs());
        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isTris());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isPair());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isPoker());

        totalCardList = Sets.newHashSet();

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTris());

    }

    /**
     * Test for the method isPoker.
     */
    @Test
    void testPoker() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.ACE, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isPoker());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTris());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isPair());

        totalCardList = Sets.newHashSet();

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isPoker());

    }

    /**
     * Test for the method isFlush.
     */
    @Test
    void testFlush() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.SIX, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.CLUBS),
                new Card(SimpleCard.KING, SeedCard.HEARTH),
                new Card(SimpleCard.QUEEN, SeedCard.HEARTH),
                new Card(SimpleCard.THREE, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.CLUBS));

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isPoker());
        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isFlush());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isPair());

        totalCardList = Sets.newHashSet();

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isFlush());

    }

    /**
     * Test for the method isFullHouse.
     */
    @Test
    void testFullHouse() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.HEARTH),
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND));

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTwoPairs());
        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isTris());
        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isPair());
        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isFullHouse());

        totalCardList = Sets.newHashSet();

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isFullHouse());

    }

    /**
     * Test for the method isStraight.
     */
    @Test
    void testStraight() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.KING, SeedCard.SPADES),
                new Card(SimpleCard.THREE, SeedCard.HEARTH),
                new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.SIX, SeedCard.DIAMOND));

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isTwoPairs());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isFullHouse());
        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isStraight());

        totalCardList = Set.of(new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.THREE, SeedCard.SPADES),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.KING, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isStraight());

        totalCardList = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.EIGHT, SeedCard.SPADES),
                new Card(SimpleCard.SEVEN, SeedCard.HEARTH),
                new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.SIX, SeedCard.DIAMOND));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isStraight());
        /**
         * test with Ace like 1.
         */
        totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.THREE, SeedCard.SPADES),
                new Card(SimpleCard.SEVEN, SeedCard.HEARTH),
                new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.SEVEN, SeedCard.DIAMOND));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isStraight());

        totalCardList = Sets.newHashSet();

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isStraight());

    }

       /**
     * Test for the method isStraightFlush.
     */
    @Test
    void testStraightFlush() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.KING, SeedCard.SPADES),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.FOUR, SeedCard.DIAMOND),
                new Card(SimpleCard.FIVE, SeedCard.DIAMOND),
                new Card(SimpleCard.SIX, SeedCard.DIAMOND));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isStraightFlush());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isRoyalFlush());

    }

    /**
     * Test for the method isRoyalFlush.
     */
    @Test
    void testRoyalFlush() {
        Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.HEARTH),
                new Card(SimpleCard.KING, SeedCard.DIAMOND),
                new Card(SimpleCard.JACK, SeedCard.DIAMOND),
                new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                new Card(SimpleCard.TEN, SeedCard.DIAMOND),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isRoyalFlush());
        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isFullHouse());
        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isStraight());

        totalCardList = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND),
                new Card(SimpleCard.KING, SeedCard.HEARTH),
                new Card(SimpleCard.KING, SeedCard.DIAMOND),
                new Card(SimpleCard.JACK, SeedCard.DIAMOND),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.TEN, SeedCard.DIAMOND),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND));

        assertTrue(new CombinationRulesImpl(totalCardList,utilies).isRoyalFlush());

        totalCardList = Sets.newHashSet();

        assertFalse(new CombinationRulesImpl(totalCardList,utilies).isRoyalFlush());

    }
}
