package model.combination.api;

/**
 * Enumeration of all possible combinations.
 * 
 */
public enum CombinationType {
    // CHECKSTYLE: JavadocVariable OFF
    HIGH_CARD(1),
    PAIR(2),
    TWO_PAIRS(3),
    TRIS(4),
    STRAIGHT(5),
    FULL_HOUSE(6),
    FLUSH(7),
    POKER(8),
    ROYAL_FLUSH(9);
    // CHECKSTYLE: JavadocVariable ON

    private final int value;

    CombinationType(int value) {
        this.value = value;
    }

    /**
     * @return the value of the combination.
     */
    public int getValue() {
        return value;
    }

}
