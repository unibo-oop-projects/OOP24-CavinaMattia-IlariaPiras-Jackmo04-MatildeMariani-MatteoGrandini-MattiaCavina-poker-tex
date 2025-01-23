package model.game.api;

public enum Phase {

    PREFLOP(0),
    FLOP(3),
    TURN(1),
    RIVER(1);

    private final int numCards;
    Phase(final int numCards) {
        this.numCards = numCards;
    }

    public int getNumCards() {
        return numCards;
    }

}
