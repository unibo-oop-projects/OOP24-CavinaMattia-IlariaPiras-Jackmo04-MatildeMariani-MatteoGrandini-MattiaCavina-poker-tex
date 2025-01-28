package main.model.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import model.combination.CombinationsRulesImpl;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test class for is used to test all combination rules with differet cards.
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

                assertEquals(true, new CombinationsRulesImpl(totalCardList).isPair());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isTris());

                totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH));

                assertEquals(true, new CombinationsRulesImpl(totalCardList).isPair());

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

                assertEquals(true, new CombinationsRulesImpl(totalCardList).isTwoPairs());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isTris());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isPair());

                totalCardList = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH));

                assertEquals(false, new CombinationsRulesImpl(totalCardList).isTris());

        }

        /**
         * Test for the method isTris.
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

                assertEquals(false, new CombinationsRulesImpl(totalCardList).isTwoPairs());
                assertEquals(true, new CombinationsRulesImpl(totalCardList).isTris());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isPair());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isPoker());

        }

        /**
         * Test for the method isPoker.
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

                assertEquals(true, new CombinationsRulesImpl(totalCardList).isPoker());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isTris());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isPair());

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

                assertEquals(false, new CombinationsRulesImpl(totalCardList).isPoker());
                assertEquals(true, new CombinationsRulesImpl(totalCardList).isFlush());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isPair());

        }

        /**
         * Test for the method isFullHouse.
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

                assertEquals(false, new CombinationsRulesImpl(totalCardList).isTwoPairs());
                assertEquals(true, new CombinationsRulesImpl(totalCardList).isTris());
                assertEquals(true, new CombinationsRulesImpl(totalCardList).isPair());
                assertEquals(true, new CombinationsRulesImpl(totalCardList).isFullHouse());

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

                assertEquals(false, new CombinationsRulesImpl(totalCardList).isTwoPairs());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isFullHouse());
                assertEquals(true, new CombinationsRulesImpl(totalCardList).isStraight());

                totalCardList = Set.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(false, new CombinationsRulesImpl(totalCardList).isStraight());
                totalCardList = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.EIGHT, SimpleCard.EIGHT.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.SEVEN, SimpleCard.SEVEN.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(true, new CombinationsRulesImpl(totalCardList).isStraight());

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

                assertEquals(true, new CombinationsRulesImpl(totalCardList).isRoyalFlush());
                assertEquals(false, new CombinationsRulesImpl(totalCardList).isFullHouse());
                assertEquals(true, new CombinationsRulesImpl(totalCardList).isStraight());

                totalCardList = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(true, new CombinationsRulesImpl(totalCardList).isRoyalFlush());

        }
}
