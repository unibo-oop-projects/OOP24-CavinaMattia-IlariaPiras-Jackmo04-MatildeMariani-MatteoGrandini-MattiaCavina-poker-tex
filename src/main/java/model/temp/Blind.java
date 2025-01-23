package model.temp;

/**
 * Enum representing the two possible blinds in a poker game.
 * The multiplier is used to calculate the amount of chips that a player must
 * pay when posting a blind.
 * For example, if the minimum required bet is 10 chips, the small blind would be 5 chips
 * and the big blind would be 10 chips.
 */
public enum Blind {
    // CHECKSTYLE: JavadocVariable OFF
    SMALL(0.5),
    BIG(1.0);
    // CHECKSTYLE: JavadocVariable ON

    private final double multiplier;
    Blind(final double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Returns the multiplier of the blind.
     * @return the multiplier of the blind.
     */
    public double getMultiplier() {
        return multiplier;
    }
}
