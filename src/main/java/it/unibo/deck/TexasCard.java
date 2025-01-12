package it.unibo.deck;

public class TexasCard {
    static enum SeedCard{
            // CHECKSTYLE: JavadocVariable OFF
    CLUBS,
    SPADES,
    DIAMOND,
    HEARTH;
    // CHECKSTYLE: JavadocVariable ON
    } 

    static enum SimpleCard {
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

    private SimpleCard(int valueOfCard) {
        this.valueOfCard = valueOfCard;
    }

    public int getValueOfCard() {
        return valueOfCard;
    }

    }

    
}
