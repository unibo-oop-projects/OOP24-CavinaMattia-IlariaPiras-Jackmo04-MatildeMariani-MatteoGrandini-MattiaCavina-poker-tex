package player.api;

// TODO Implement enum Role
public enum Role {
    BIG_BLIND, SMALL_BLIND, DEALER, REGULAR;

    public Role next() {
        return Role.values()[(this.ordinal() + 1) % Role.values().length];
    }
}
