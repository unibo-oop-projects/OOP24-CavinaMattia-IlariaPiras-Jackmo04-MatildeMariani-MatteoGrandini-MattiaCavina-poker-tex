package model.player.api;

import java.util.List;

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

    public static List<Role> getNewRolesFromList(List<Role> currentRoles) {
        switch (currentRoles.size()) {
            case 4:
                return currentRoles.stream().map(Role::next).toList();
            
            case 3:
                // TODO

            case 2:
                // TODO
        
            default:
                throw new IllegalStateException("Must provide a list with size between 2 and 4");
        }
    }
}
