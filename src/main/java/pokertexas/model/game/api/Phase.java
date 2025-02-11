package pokertexas.model.game.api;

/**
 * Enum representing the possible phases of a hand.
 * Each phase has a "numCard" field which indicates the number of community cards that 
 * must be dealt in that phase.
 */
public enum Phase {

    /**
     * PreFlop phase.
     */
    PREFLOP(0),
    /**
     * Flop phase.
     */
    FLOP(3),
    /**
     * Turn phase.
     */
    TURN(1),
    /**
     * River phase.
     */
    RIVER(1);

    private final int numCards;

    /**
     * Constructor for a phase.
     * @param numCards the number of community cards that must be dealt in that phase.
     */
    Phase(final int numCards) {
        this.numCards = numCards;
    }

    /**
     * Returns the number of cards that must be dealt in this phase.
     * @return the number of cards that must be dealt in this phase.
     */
    public int getNumCards() {
        return numCards;
    }

    /**
     * Returns the next Phase.
     * @return the next Phase.
     */
    public Phase next() {
        return values()[(this.ordinal() + 1) % values().length];
    }

}
