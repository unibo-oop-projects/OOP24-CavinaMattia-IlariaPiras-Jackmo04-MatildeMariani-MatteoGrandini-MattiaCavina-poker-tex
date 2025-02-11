package pokertexas.model.player.api;

/**
 * Enum representing the possible actions a player can take in a game of poker.
 */
public enum Action {

    /**
     * If the player decides to fold.
     */
    FOLD,
    /**
     * If the player decides to check.
     */
    CHECK,
    /**
     * If the player decides to call.
     */
    CALL,
    /**
     * If the player decides to raise.
     */
    RAISE,
    /**
     * If the player decides to go all in.
     */
    ALL_IN;
}
