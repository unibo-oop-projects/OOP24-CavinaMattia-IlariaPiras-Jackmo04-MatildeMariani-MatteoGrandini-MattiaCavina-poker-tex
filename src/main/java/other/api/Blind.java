package other.api;

/**
 * Enum representing the two possible blinds in a poker game.
 * The multiplier is used to calculate the amount of chips that a player must
 * pay when posting a blind.
 * For example, if the minimum required bet is 10 chips, the small blind would be 5 chips
 * and the big blind would be 10 chips.
 */
public enum Blind {
    SMALL(0.5),
    BIG(1.0);

    private final double multiplier;
    private Blind(final double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
