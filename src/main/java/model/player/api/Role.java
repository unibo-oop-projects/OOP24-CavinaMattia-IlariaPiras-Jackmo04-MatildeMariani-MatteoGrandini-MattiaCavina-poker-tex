package model.player.api;

/**
 * Enum representing the possible roles a player can have in a poker game.
 * The roles are: big blind, small blind.
 * The multiplier is used to calculate the amount of chips that players taking on one 
 * of the two roles must pay at the start of a hand.
 * For example, if the minimum required bet is 10 chips, the small blind must pay 5 chips
 * and the big blind 10 chips.
 */
public enum Role {

    /**
     * The big blind role.
     */
    BIG_BLIND(1.0), 
    /**
     * The small blind role.
     */
    SMALL_BLIND(0.5);

    private final double multiplier;
    Role(final double multiplier) {
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
