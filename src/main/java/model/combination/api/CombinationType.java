package model.combination.api;

/**
 * Enumeration of all possible combinations and its value for important.
 * 
 */
public enum CombinationType {
    // CHECKSTYLE: JavadocVariable OFF
    HIGH_CARD(1, "High Card"),
    PAIR(2, "Pair"),
    TWO_PAIRS(3, "Two Pair"),
    TRIS(4, "Three of a Kind"),
    STRAIGHT(5, "Straight"),
    FULL_HOUSE(6, "Full House"),
    FLUSH(7, "Flush"),
    POKER(8, "Four of a Kind"),
    ROYAL_FLUSH(9, "Royal Flush");
    // CHECKSTYLE: JavadocVariable ON

    private final int value;
    private final String name;

    CombinationType(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * @return the value of the combination.
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the name of the combination.
     */
    public String getName() {
        return name;
    }

}
