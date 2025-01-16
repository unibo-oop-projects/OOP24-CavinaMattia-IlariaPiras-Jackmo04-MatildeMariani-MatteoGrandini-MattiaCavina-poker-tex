package it.unibo.deck;

import java.util.LinkedList;
import java.util.List;

/**
 * Card tipology of Texas hold'm.
 */
public class PokerCardImpl implements PokerCard<Card> {
    enum SeedCard {
        // CHECKSTYLE: JavadocVariable OFF
        CLUBS,
        SPADES,
        DIAMOND,
        HEARTH;
        // CHECKSTYLE: JavadocVariable ON
    }

    enum SimpleCard {
        // CHECKSTYLE: JavadocVariable OFF
        ACE(14),
        KING(13),
        QUEEN(12),
        JACK(11),
        TEN(10),
        NINE(9),
        EIGHT(8),
        SEVEN(7),
        SIX(6),
        FIVE(5),
        FOUR(4),
        THREE(3),
        TWO(2);
        // CHECKSTYLE: JavadocVariable ON

        private final int valueOfCard;

        SimpleCard(final int valueOfCard) {
            this.valueOfCard = valueOfCard;
        }

        public int getValueOfCard() {
            return valueOfCard;
        }

    }

    /**
     * Return a Poker deck.
     */
    @Override
    public List<Card> buildDeck() {
        final List<Card> deckNew = new LinkedList<>();
        for (final var elemSimple : SimpleCard.values()) {
            for (final var elemSeed : SeedCard.values()) {
                deckNew.add(new Card(elemSimple.name(), elemSimple.getValueOfCard(), elemSeed.name()));
            }
        }
        return deckNew;

    }
}
