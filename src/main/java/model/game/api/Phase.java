package model.game.api;

/**
 * Enum representing the possible phases of a hand.
 * Each phase has a "numCard" field which indicates the number of community cards that 
 * must be dealt in that phase.
 */
public enum Phase {

    PREFLOP(0),
    FLOP(3),
    TURN(1),
    RIVER(1);

    private final int numCards;
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
        return Phase.values()[(this.ordinal() + 1) % Phase.values().length];
    }

}
