package model.temp;

import java.util.Set;

import model.deck.api.Card;

/**
 * Utility class to calculate the best combination from a given set of cards.
 */
// TODO Da rimpiazzare con quella di Mattia
public class Combinations {

    private Combinations() {
    }

    /**
     * Returns the best combination from the given set of cards.
     * @param cards the set of cards to evaluate.
     * @return the best combination from the given set of cards.
     */
    public static Combination getBestCombination(final Set<Card> cards) {
        return new Combination(CombinationType.PAIR, cards, 0); // Per controllo
    }

}
