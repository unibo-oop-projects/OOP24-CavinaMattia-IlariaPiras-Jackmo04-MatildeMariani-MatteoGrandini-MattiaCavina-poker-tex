package model.combination.api;

/**
 * Enumeration of all possible combinations and its value
 * from less to more powerfull combination .
 * 
 */
public enum CombinationType {
    // Five cards than not form neather combination.
    HIGH_CARD(1, "High Card"),
    // Two card whith same seed.
    PAIR(2, "Pair"),
    // Two couples of cards with the same seed.
    TWO_PAIRS(3, "Two Pair"),
    // Three card whith same seed.
    TRIS(4, "Three of a Kind"),
    // Five cards with consecutive values.
    STRAIGHT(5, "Straight"),
    // Five card with one pair and one tris.
    FULL_HOUSE(6, "Full House"),
    // Five cards with the same seed.
    FLUSH(7, "Flush"),
    // Foor card whith same seed.
    POKER(8, "Four of a Kind"),
    // Five cards with the same seed and consecutive values.
    ROYAL_FLUSH(9, "Royal Flush");

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
