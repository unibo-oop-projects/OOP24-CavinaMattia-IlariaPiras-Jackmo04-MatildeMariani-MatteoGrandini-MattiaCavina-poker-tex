package model.player.api;

import java.util.List;

/**
 * Enum representing the possible roles that a player can have in a poker game.
 * The roles are: big blind, small blind, dealer and regular player.
 * The next() method is used to cycle through the roles in the correct order.
 */
public enum Role {
    BIG_BLIND, SMALL_BLIND, DEALER, REGULAR;

    /**
     * Only use this method if there are exactly 4 players in the game.
     * @return the next role.
     */
    public Role next() {
        return Role.values()[(this.ordinal() + 1) % Role.values().length];
    }

    /**
     * Returns a list of new roles based on the current roles of the remaining players.
     * @param currentRoles the current roles of the remaining players.
     * @return a list of the same size as the input list containing the new roles.
     */
    public static List<Role> getNewRolesFromList(List<Role> currentRoles) {
        switch (currentRoles.size()) {
            case 4:
                return currentRoles.stream().map(Role::next).toList();
            
            case 3:
                // TODO
                throw new UnsupportedOperationException("Not implemented yet");

            case 2:
                // TODO
                throw new UnsupportedOperationException("Not implemented yet");
        
            default:
                throw new IllegalStateException("Must provide a list with size between 2 and 4");
        }
    }
}
