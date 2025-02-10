package main.model.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.combination.CombinationComparator;
import model.combination.CombinationHandlerImpl;
import model.combination.api.CombinationType;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Test of {@link CombinationComparator}.
 */
class CombinationComparatorTest {

    private static final int FIRST_WIN = 1;
    private static final int SECOND_WIN = -1;
    private static final int EQUAL = 0;

    private final CombinationComparator combinationComparator = new CombinationComparator();

    /**
     * Pair vs Poker; Poker wins.
     */
    @Test
    void pairVsPoker() {

        final Set<Card> firstPlayerCard = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        final Set<Card> secondPlayerCard = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        assertEquals(
                SECOND_WIN,
                combinationComparator.compare(
                        new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                        new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));
    }

    /**
     * Hight Card vs Hight Card.
     */
    @Test
    void hightVsHight() {
        final Set<Card> firstPlayerCard = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.JACK, SeedCard.SPADES),
                new Card(SimpleCard.FOUR, SeedCard.SPADES),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.KING, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        final Set<Card> secondPlayerCard = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.JACK, SeedCard.SPADES),
                new Card(SimpleCard.THREE, SeedCard.SPADES),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.KING, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        assertEquals(
                EQUAL,
                combinationComparator.compare(
                        new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                        new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));
    }

    /**
     * TwoPair vs TwoPair.
     */
    @Test
    void twoPairvsTwoPair() {
        final Set<Card> firstPlayerCard = Set.of(
                new Card(SimpleCard.THREE, SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        final Set<Card> secondPlayerCard = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.THREE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND));
        assertEquals(
                SECOND_WIN,
                combinationComparator.compare(
                        new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                        new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));
    }

    /**
     * TwoPair vs TwoPair.
     */
    @Test
    void fullHouseVsFullHouse() {
        final Set<Card> firstPlayerCard = Set.of(
                new Card(SimpleCard.ACE, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.ACE, SeedCard.HEARTH),
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND));
        final Set<Card> secondPlayerCard = Set.of(
                new Card(SimpleCard.TWO, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.HEARTH),
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND));
        assertEquals(
                FIRST_WIN,
                combinationComparator.compare(
                        new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                        new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

    }

    /**
     * Equals Test.
     */
    @Test
    void equalsTest() {
        final Set<Card> firstPlayerCard = Set.of(
                new Card(SimpleCard.TWO, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.HEARTH),
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND));
        final Set<Card> secondPlayerCard = Set.of(
                new Card(SimpleCard.TWO, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.QUEEN, SeedCard.SPADES),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TEN, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.HEARTH),
                new Card(SimpleCard.QUEEN, SeedCard.DIAMOND));
        assertEquals(
                EQUAL,
                combinationComparator.compare(
                        new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                        new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));
    }

    /**
     * Straight vs Straigh with differt card , first player wins.
     */
    @Test
    void straighVsStraigh() {
        final Set<Card> firstPlayerCard = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.CLUBS),
                new Card(SimpleCard.NINE, SeedCard.DIAMOND),
                new Card(SimpleCard.EIGHT, SeedCard.SPADES),
                new Card(SimpleCard.SEVEN, SeedCard.HEARTH),
                new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.SIX, SeedCard.DIAMOND));
        final Set<Card> secondPlayerCard = Set.of(
                new Card(SimpleCard.QUEEN, SeedCard.CLUBS),
                new Card(SimpleCard.TWO, SeedCard.DIAMOND),
                new Card(SimpleCard.EIGHT, SeedCard.SPADES),
                new Card(SimpleCard.SEVEN, SeedCard.HEARTH),
                new Card(SimpleCard.FOUR, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.HEARTH),
                new Card(SimpleCard.SIX, SeedCard.DIAMOND));
        assertEquals(
                FIRST_WIN,
                combinationComparator.compare(
                        new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                        new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

    }

    /**
     * Test Pair vs Two Pair.
     */
    @Test
    void pairVsTwoPair() {
        final Set<Card> firstPlayerCard = Set.of(
                new Card(SimpleCard.EIGHT, SeedCard.CLUBS),
                new Card(SimpleCard.NINE, SeedCard.CLUBS),
                new Card(SimpleCard.JACK, SeedCard.SPADES),
                new Card(SimpleCard.NINE, SeedCard.DIAMOND),
                new Card(SimpleCard.SIX, SeedCard.CLUBS),
                new Card(SimpleCard.QUEEN, SeedCard.CLUBS),
                new Card(SimpleCard.FIVE, SeedCard.SPADES));
        final Set<Card> secondPlayerCard = Set.of(
                new Card(SimpleCard.EIGHT, SeedCard.CLUBS),
                new Card(SimpleCard.NINE, SeedCard.CLUBS),
                new Card(SimpleCard.JACK, SeedCard.SPADES),
                new Card(SimpleCard.NINE, SeedCard.DIAMOND),
                new Card(SimpleCard.SIX, SeedCard.CLUBS),
                new Card(SimpleCard.JACK, SeedCard.HEARTH),
                new Card(SimpleCard.TWO, SeedCard.SPADES));

        final var first = new CombinationHandlerImpl().getBestCombination(firstPlayerCard);
        final var second = new CombinationHandlerImpl().getBestCombination(secondPlayerCard);
        assertEquals(CombinationType.PAIR, first.getType());
        assertEquals(CombinationType.TWO_PAIRS, second.getType());

        assertEquals(
                SECOND_WIN,
                combinationComparator.compare(
                        new CombinationHandlerImpl().getBestCombination(firstPlayerCard),
                        new CombinationHandlerImpl().getBestCombination(secondPlayerCard)));

    }

}
