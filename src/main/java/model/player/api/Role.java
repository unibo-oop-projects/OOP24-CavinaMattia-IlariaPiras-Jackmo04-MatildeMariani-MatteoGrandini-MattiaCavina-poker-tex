package model.player.api;

/**
 * Enum representing the possible roles that a player can have in a poker game.
 * The roles are: big blind, small blind, dealer and regular player.
 * The next() method is used to cycle through the roles in the correct order.
 */
public enum Role {
    BIG_BLIND, SMALL_BLIND, DEALER, REGULAR;

    public Role next() {
        return Role.values()[(this.ordinal() + 1) % Role.values().length];
    }
}
