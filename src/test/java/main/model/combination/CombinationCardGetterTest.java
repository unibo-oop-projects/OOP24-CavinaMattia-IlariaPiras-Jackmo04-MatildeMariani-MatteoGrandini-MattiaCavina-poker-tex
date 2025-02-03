package main.model.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import model.combination.CombinationsCardGetterImpl;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test of CombinationCardGetter.
 */
public class CombinationCardGetterTest { //NOPMD suppressed as it is a false positive

        /**
         * Empty test.
         * 
         */
        @Test
        void testEmpty() {
               final Set<Card> totalCardList = Sets.newHashSet();
                assertThrows(IllegalArgumentException.class, () -> new CombinationsCardGetterImpl(totalCardList).getPair());
        }

        /**
         * Test to get a pair.
         */
        @Test
        void testPair() {
                final Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND)),
                                new CombinationsCardGetterImpl(totalCardList).getPair());

        }

        /**
         * Test to get two pairs.
         */
        @Test
        void testTwoPairs() {
               final Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND)),
                                new CombinationsCardGetterImpl(totalCardList).getTwoPairs());

        }

        /**
         * Test to get a tris.
         */
        @Test
        void testTris() {
                final Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH)),
                                new CombinationsCardGetterImpl(totalCardList).getTris());

        }

        /**
         * Test to get a poker.
         */
        @Test
        void testPoker() {
                final Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH)),
                                new CombinationsCardGetterImpl(totalCardList).getPoker());

        }

        /**
         * Test to get a flush.
         */
        @Test
        void testFlush() {
                final Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS));

                assertEquals(Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS)),
                                new CombinationsCardGetterImpl(totalCardList).getFlush());

        }

        /**
         * Test to get a full house.
         */
        @Test
        void testFullHouse() {
                final Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND)),
                                new CombinationsCardGetterImpl(totalCardList).getFullHouse());

        }

        /**
         * Test to get a straight.
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

                assertEquals(Set.of(
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.DIAMOND)),
                                new CombinationsCardGetterImpl(totalCardList).getStraight());

        }

        /**
         * Test to get a royal flush.
         */
        @Test
        void testRoyalFlush() {
                final Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND)),
                                new CombinationsCardGetterImpl(totalCardList).getRoyalFlush());

        }

}
