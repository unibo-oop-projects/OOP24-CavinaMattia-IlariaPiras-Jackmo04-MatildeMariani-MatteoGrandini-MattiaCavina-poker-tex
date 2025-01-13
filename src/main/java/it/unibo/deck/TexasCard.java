package it.unibo.deck;

/**
 * Card tipology of Texas hold'm.
 */
public class TexasCard {
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
}
