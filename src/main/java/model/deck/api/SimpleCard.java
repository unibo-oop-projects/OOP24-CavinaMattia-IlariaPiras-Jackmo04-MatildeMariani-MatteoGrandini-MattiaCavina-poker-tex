package model.deck.api;

/**
 * Enum that list all type and value of Poker's Cards.
 */
public enum SimpleCard {
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

    /**
     * Method to get Value of my Card.
     * 
     * @return
     *         value of my card.
     */
    public int getValueOfCard() {
        return valueOfCard;
    }
}
