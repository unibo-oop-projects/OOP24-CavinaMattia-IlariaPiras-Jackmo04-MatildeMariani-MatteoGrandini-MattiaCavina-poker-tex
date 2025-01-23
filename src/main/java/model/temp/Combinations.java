package model.temp;

import java.util.Set;

import model.deck.api.Card;

// TODO Da rimpiazzare con quella di Mattia
public class Combinations {

    public static Combination getBestCombination(Set<Card> cards) {
        return new Combination(CombinationType.PAIR, cards, 0); // Per controllo
    }

}
