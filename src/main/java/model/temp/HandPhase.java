package model.temp;

/**
 * Temporary enum representing the possible phases of a hand in a poker game.
 * @deprecated Use {@link model.game.api.Phase} instead.
 */
@Deprecated
public enum HandPhase {
    // CHECKSTYLE: JavadocVariable OFF
    PREFLOP,
    FLOP,
    TURN,
    RIVER;
    // CHECKSTYLE: JavadocVariable ON
}
