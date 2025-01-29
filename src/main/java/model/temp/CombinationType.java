package model.temp;

/**
 * Enum representing the possible types of combinations in a poker game.
 */
public enum CombinationType {
    // CHECKSTYLE: JavadocVariable OFF
    HIGH_CARD("High card"),
    PAIR("Pair"),
    TWO_PAIRS("Two pair"),
    THREE_OF_A_KIND("Three of a kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULL_HOUSE("Full house"),
    FOUR_OF_A_KIND("Four of a kind"),
    STRAIGHT_FLUSH("Straight flush");
    // CHECKSTYLE: JavadocVariable ON
    
    String fullName;
    CombinationType(String fullName) {
        this.fullName = fullName;
    }

    public String fullName() {
        return this.fullName;
    }

}
