package main.model.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.combination.CombinationComparator;
import model.combination.CombinationHandlerImpl;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test of CombinationComparator.
 */
public class CombinationComparatorTest { // NOPMD suppressed as it is a false positive

        private static final int FIRST_WIN = 1;
        private static final int SECOND_WIN = -1;
        private static final int EQUAL = 0;

        private Set<Card> firstPlayerCard;
        private Set<Card> secondPlayerCard;
        private CombinationComparator combinationComparator = new CombinationComparator();

        /**
         * Pair vs Poker; Poker wins.
         */
        @Test
        void pairVsPoker() {

                firstPlayerCard = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                secondPlayerCard = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(SECOND_WIN,
                                combinationComparator.compare(
                                                new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                                                new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

        }

        /**
         * Hight Card vs Hight Card.
         */
        @Test
        void hightVsHight() {

                firstPlayerCard = Set.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                secondPlayerCard = Set.of(new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.KING, SimpleCard.KING.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(EQUAL,
                                combinationComparator.compare(
                                                new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                                                new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

        }

        /**
         * TwoPair vs TwoPair.
         */
        @Test
        void twoPairvsTwoPair() {

                firstPlayerCard = Set.of(
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                secondPlayerCard = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.THREE, SimpleCard.THREE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(SECOND_WIN,
                                combinationComparator.compare(
                                                new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                                                new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

        }

        /**
         * TwoPair vs TwoPair.
         */
        @Test
        void fullHousVsFullHous() {

                firstPlayerCard = Set.of(
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.ACE, SimpleCard.ACE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND));

                secondPlayerCard = Set.of(
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(FIRST_WIN,
                                combinationComparator.compare(
                                                new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                                                new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

        }

        /**
         * Equals Test.
         */
        @Test
        void equalsTest() {

                firstPlayerCard = Set.of(
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND));

                secondPlayerCard = Set.of(
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.JACK, SimpleCard.JACK.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.TEN, SimpleCard.TEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(EQUAL,
                                combinationComparator.compare(
                                                new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                                                new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

        }

        /**
         * Straight vs Straigh with differt card , first player wins.
         */
        @Test
        void straighVsStraigh() {

                firstPlayerCard = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.NINE, SimpleCard.NINE.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.EIGHT, SimpleCard.EIGHT.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.SEVEN, SimpleCard.SEVEN.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.DIAMOND));

                secondPlayerCard = Set.of(
                                new Card(SimpleCard.QUEEN, SimpleCard.QUEEN.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.TWO, SimpleCard.TWO.getValueOfCard(), SeedCard.DIAMOND),
                                new Card(SimpleCard.EIGHT, SimpleCard.EIGHT.getValueOfCard(), SeedCard.SPADES),
                                new Card(SimpleCard.SEVEN, SimpleCard.SEVEN.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.FOUR, SimpleCard.FOUR.getValueOfCard(), SeedCard.CLUBS),
                                new Card(SimpleCard.FIVE, SimpleCard.FIVE.getValueOfCard(), SeedCard.HEARTH),
                                new Card(SimpleCard.SIX, SimpleCard.SIX.getValueOfCard(), SeedCard.DIAMOND));

                assertEquals(FIRST_WIN,
                                combinationComparator.compare(
                                                new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                                                new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

        }

}
