package model.player.ai.api;

import model.player.api.Role;

public interface AIPlayerFactory {

    AIPlayer easy(int initialChips, Role initialRole);

    AIPlayer medium(int initialChips, Role initialRole);

    AIPlayer hard(int initialChips, Role initialRole);

}
