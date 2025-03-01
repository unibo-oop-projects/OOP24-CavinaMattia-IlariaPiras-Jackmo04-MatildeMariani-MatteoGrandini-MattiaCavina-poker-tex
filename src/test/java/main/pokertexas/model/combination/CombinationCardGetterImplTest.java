package main.pokertexas.model.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import pokertexas.model.combination.CombinationCardGetterImpl;
import pokertexas.model.combination.CombinationUtilitiesImpl;
import pokertexas.model.combination.api.CombinationUtilities;
import pokertexas.model.deck.api.Card;
import pokertexas.model.deck.api.SeedCard;
import pokertexas.model.deck.api.SimpleCard;

/**
 * Test of {@link CombinationCardGetterImpl}.
 */
class CombinationCardGetterImplTest {
    private final CombinationUtilities utilies = new CombinationUtilitiesImpl();

    /**
     * Empty test.
     * 
     */
    @Test
    void testEmpty() {
        final Set<Card> totalCardList = Sets.newHashSet();
        assertThrows(IllegalArgumentException.class,
                () -> new CombinationCardGetterImpl(totalCardList, utilies).getPairCard());
    }

    /**
     * Test to get a pair.
     */
    @Test
    void testPair() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        assertEquals(
                Set.of(
                        new Card(SimpleCard.ACE, SeedCard.CLUBS),
                        new Card(SimpleCard.ACE, SeedCard.DIAMOND)),
                new CombinationCardGetterImpl(totalCardList, utilies).getPairCard());
    }

    /**
     * Test to get two pairs.
     */
    @Test
    void testTwoPairs() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        assertEquals(
                Set.of(
                        new Card(SimpleCard.ACE, SeedCard.CLUBS),
                        new Card(SimpleCard.TWO, SeedCard.CLUBS),
                        new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                        new Card(SimpleCard.TWO, SeedCard.DIAMOND)),
                new CombinationCardGetterImpl(totalCardList, utilies).getTwoPairsCard());
    }

    /**
     * Test to get a tris.
     */
    @Test
    void testTris() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        assertEquals(
                Set.of(
                        new Card(SimpleCard.ACE, SeedCard.CLUBS),
                        new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                        new Card(SimpleCard.ACE, SeedCard.HEARTH)),
                new CombinationCardGetterImpl(totalCardList, utilies).getTrisCard());
    }

    /**
     * Test to get a poker.
     */
    @Test
    void testPoker() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.ACE, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        assertEquals(
                Set.of(
                        new Card(SimpleCard.ACE, SeedCard.CLUBS),
                        new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                        new Card(SimpleCard.ACE, SeedCard.SPADES),
                        new Card(SimpleCard.ACE, SeedCard.HEARTH)),
                new CombinationCardGetterImpl(totalCardList, utilies).getPokerCard());
    }

    /**
     * Test to get a flush.
     */
    @Test
    void testFlush() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.SIX, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.CLUBS),
                new Card(SimpleCard.KING, SeedCard.HEARTH),
                new Card(SimpleCard.QUEEN, SeedCard.HEARTH),
                new Card(SimpleCard.THREE, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.CLUBS));
        assertEquals(
                Set.of(
                        new Card(SimpleCard.ACE, SeedCard.CLUBS),
                        new Card(SimpleCard.SIX, SeedCard.CLUBS),
                        new Card(SimpleCard.FIVE, SeedCard.CLUBS),
                        new Card(SimpleCard.THREE, SeedCard.CLUBS),
                        new Card(SimpleCard.TWO, SeedCard.CLUBS)),
                new CombinationCardGetterImpl(totalCardList, utilies).getFlushCard());
    }

    /**
     * Test to get a full house.
     */
    @Test
    void testFullHouse() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.HEARTH),
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND));
        assertEquals(
                Set.of(
                        new Card(SimpleCard.ACE, SeedCard.CLUBS),
                        new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                        new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                        new Card(SimpleCard.ACE, SeedCard.HEARTH),
                        new Card(SimpleCard.QUEEN, SeedCard.DIAMOND)),
                new CombinationCardGetterImpl(totalCardList, utilies).getFullHouseCard());
    }

    /**
     * Test to get a straight.
     */
    @Test
    void testStraight() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.KING, SeedCard.SPADES),
                new Card(SimpleCard.THREE, SeedCard.HEARTH),
                new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.SIX, SeedCard.DIAMOND));
        assertEquals(
                Set.of(
                        new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                        new Card(SimpleCard.THREE, SeedCard.HEARTH),
                        new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                        new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                        new Card(SimpleCard.SIX, SeedCard.DIAMOND)),
                new CombinationCardGetterImpl(totalCardList, utilies).getStraightCard());

    }

    /**
     * Test to get a royal flush.
     */
    @Test
    void testRoyalFlush() {
        final Set<Card> totalCardList = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.HEARTH),
                new Card(SimpleCard.KING, SeedCard.DIAMOND),
                new Card(SimpleCard.JACK, SeedCard.DIAMOND),
                new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                new Card(SimpleCard.TEN, SeedCard.DIAMOND),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND));
        assertEquals(
                Set.of(
                        new Card(SimpleCard.QUEEN, SeedCard.DIAMOND),
                        new Card(SimpleCard.KING, SeedCard.DIAMOND),
                        new Card(SimpleCard.JACK, SeedCard.DIAMOND),
                        new Card(SimpleCard.TEN, SeedCard.DIAMOND),
                        new Card(SimpleCard.ACE, SeedCard.DIAMOND)),
                new CombinationCardGetterImpl(totalCardList, utilies).getStraightCard());
    }

}
