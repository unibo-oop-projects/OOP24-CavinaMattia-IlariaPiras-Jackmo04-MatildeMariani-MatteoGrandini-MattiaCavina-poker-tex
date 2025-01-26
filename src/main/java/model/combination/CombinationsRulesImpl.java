package model.combination;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.combination.api.CombinationsRules;
import model.deck.api.Card;

public class CombinationsRulesImpl implements CombinationsRules<Card> {

    private final List<Card> check;
    // private final HashMap<Card, Integer> check;

    private final static int PAIR_DIMENSION = 2;
    private final static int TRIS_DIMENSION = 3;
    private final static int POKER_DIMENSION = 4;
    private final static int STRAIGHT_DIMENSION = 5;

    public CombinationsRulesImpl(List<Card> tableCards, List<Card> playerCards) {
        this.check = Stream.concat(tableCards.stream(), playerCards.stream()).collect(Collectors.toList());
        // .collect(Collectors.toMap(t -> t.valueOfCard(), t -> 1, Integer::sum,
        // HashMap::new));
    }

    @Override
    public Boolean pair() {
        return getSumOfSameNameCard()
                .filter(t -> t == PAIR_DIMENSION).count() == 1 ? true : false;

    }

    @Override
    public Boolean twoPairs() {
        return getSumOfSameNameCard()
                .filter(t -> t == PAIR_DIMENSION).count() == 2 ? true : false;
    }

    @Override
    public Boolean tris() {
        return getSumOfSameNameCard()
                .filter(t -> t == TRIS_DIMENSION).count() == 1 ? true : false;
    }

    @Override
    public Boolean straight() {
        return getStraight().size() >= STRAIGHT_DIMENSION ? true : false;
    }

    @Override
    public Boolean fullHouse() {
        return pair() && tris() ? true : false;
    }

    @Override
    public Boolean flush() {
        return getSumOfSameSeedCard()
                .filter(t -> t == POKER_DIMENSION).count() == 1 ? true : false;
    }

    @Override
    public Boolean poker() {
        return getSumOfSameNameCard()
                .filter(t -> t == POKER_DIMENSION).count() == 1 ? true : false;
    }

    @Override
    public Boolean royalFlush() {
        return straight() && flush() ? true : false;
    }

    private List<Integer> getStraight() {
        List<Integer> straightList = this.check.stream().map(t -> t.valueOfCard()).sorted()
                .collect(Collectors.toList());
        ListIterator<Integer> it = straightList.listIterator();

        while (it.hasNext()) {
            if (it.next() != it.previous() + 1) {
                it.remove();
               
            }else{
                it.next();
            }
        }
        System.out.println(straightList);

        return straightList;
    }

    private Stream<Integer> getSumOfSameNameCard() {
        return check.stream().map(t -> t.cardName())
                .collect(Collectors.toMap(t -> t, t -> 1, Integer::sum, HashMap::new))
                .values().stream();
    }

    private Stream<Integer> getSumOfSameSeedCard() {
        return check.stream().map(t -> t.seedName())
                .collect(Collectors.toMap(t -> t, t -> 1, Integer::sum, HashMap::new))
                .values().stream();
    }

}
