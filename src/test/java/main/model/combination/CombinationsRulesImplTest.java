package main.model.combination;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import model.combination.CombinationsRulesImpl;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test of {@link model.combination.CombinationsRulesImpl}.
 */
public class CombinationsRulesImplTest { // NOPMD suppressed as it is a false positive

        /**
         * Test for the method isPair.
         */
        @Test
        void testPair() {
                Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertTrue(new CombinationsRulesImpl(totalCardList).isPair());
                assertFalse(new CombinationsRulesImpl(totalCardList).isTris());
                assertFalse(new CombinationsRulesImpl(totalCardList).isPoker());

                totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH));

                assertTrue(new CombinationsRulesImpl(totalCardList).isPair());

                totalCardList = Sets.newHashSet();

                assertFalse(new CombinationsRulesImpl(totalCardList).isPair());

        }

        /**
         * Test for the method isTwoPairs.
         */
        @Test
        void testTwoPairs() {
                Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertTrue(new CombinationsRulesImpl(totalCardList).isTwoPairs());
                assertFalse(new CombinationsRulesImpl(totalCardList).isTris());
                assertTrue(new CombinationsRulesImpl(totalCardList).isPair());

                totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH));

                assertFalse(new CombinationsRulesImpl(totalCardList).isTris());

                totalCardList = Sets.newHashSet();

                assertFalse(new CombinationsRulesImpl(totalCardList).isTwoPairs());

        }

        /**
         * Test for the method isTris.
         */
        @Test
        void testTris() {
                Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertFalse(new CombinationsRulesImpl(totalCardList).isTwoPairs());
                assertTrue(new CombinationsRulesImpl(totalCardList).isTris());
                assertFalse(new CombinationsRulesImpl(totalCardList).isPair());
                assertFalse(new CombinationsRulesImpl(totalCardList).isPoker());

                totalCardList = Sets.newHashSet();

                assertFalse(new CombinationsRulesImpl(totalCardList).isTris());

        }

        /**
         * Test for the method isPoker.
         */
        @Test
        void testPoker() {
                Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertTrue(new CombinationsRulesImpl(totalCardList).isPoker());
                assertFalse(new CombinationsRulesImpl(totalCardList).isTris());
                assertFalse(new CombinationsRulesImpl(totalCardList).isPair());

                totalCardList = Sets.newHashSet();

                assertFalse(new CombinationsRulesImpl(totalCardList).isPoker());

        }

        /**
         * Test for the method isFlush.
         */
        @Test
        void testFlush() {
                Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS));

                assertFalse(new CombinationsRulesImpl(totalCardList).isPoker());
                assertTrue(new CombinationsRulesImpl(totalCardList).isFlush());
                assertFalse(new CombinationsRulesImpl(totalCardList).isPair());

                totalCardList = Sets.newHashSet();

                assertFalse(new CombinationsRulesImpl(totalCardList).isFlush());

        }

        /**
         * Test for the method isFullHouse.
         */
        @Test
        void testFullHouse() {
                Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND));

                assertFalse(new CombinationsRulesImpl(totalCardList).isTwoPairs());
                assertTrue(new CombinationsRulesImpl(totalCardList).isTris());
                assertTrue(new CombinationsRulesImpl(totalCardList).isPair());
                assertTrue(new CombinationsRulesImpl(totalCardList).isFullHouse());

                totalCardList = Sets.newHashSet();

                assertFalse(new CombinationsRulesImpl(totalCardList).isFullHouse());

        }

        /**
         * Test for the method isStraight.
         */
        @Test
        void testStraight() {
                Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.DIAMOND));

                assertFalse(new CombinationsRulesImpl(totalCardList).isTwoPairs());
                assertFalse(new CombinationsRulesImpl(totalCardList).isFullHouse());
                assertTrue(new CombinationsRulesImpl(totalCardList).isStraight());

                totalCardList = Set.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertFalse(new CombinationsRulesImpl(totalCardList).isStraight());

                totalCardList = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.EIGHT, SimpleCard.EIGHT.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.SEVEN, SimpleCard.SEVEN.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.DIAMOND));

                assertTrue(new CombinationsRulesImpl(totalCardList).isStraight());
/**
 * test with Ace like 1.
 */
                totalCardList = Set.of(
                        new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                        new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                        new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.SPADES),
                        new Card(SimpleCard.SEVEN, SimpleCard.SEVEN.getValueOfCard(), SeedCard.HEARTH),
                        new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                        new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                        new Card(SimpleCard.SEVEN, SimpleCard.SEVEN.getValueOfCard(), SeedCard.DIAMOND));

                        assertTrue(new CombinationsRulesImpl(totalCardList).isStraight());

                totalCardList = Sets.newHashSet();

                assertFalse(new CombinationsRulesImpl(totalCardList).isStraight());

        }

        /**
         * Test for the method isRoyalFlush.
         */
        @Test
        void testRoyalFlush() {
                Set<Card> totalCardList = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND));

                assertTrue(new CombinationsRulesImpl(totalCardList).isRoyalFlush());
                assertFalse(new CombinationsRulesImpl(totalCardList).isFullHouse());
                assertTrue(new CombinationsRulesImpl(totalCardList).isStraight());

                totalCardList = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND));

                assertTrue(new CombinationsRulesImpl(totalCardList).isRoyalFlush());

                totalCardList = Sets.newHashSet();

                assertFalse(new CombinationsRulesImpl(totalCardList).isRoyalFlush());

        }
}
