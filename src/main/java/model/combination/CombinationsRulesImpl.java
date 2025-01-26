package model.combination;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.combination.api.CombinationsRules;
import model.deck.api.Card;
import model.deck.api.SeedCard;
import org.javatuples.Pair;

public class CombinationsRulesImpl implements CombinationsRules<Card> {

    private final List<Card> check;

    private static final int PAIR_DIMENSION = 2;
    private static final int TRIS_DIMENSION = 3;
    private static final int POKER_DIMENSION = 4;
    private static final int STRAIGHT_DIMENSION = 5;
    private static final List<Integer> STRAIGHT_CONTROLL = List.of(0, -1, -2, -3, -4);

    /**
     * Constructor for CombinationsRulesImpl.
     * @param tableCards
     * @param playerCards
     */
    public CombinationsRulesImpl(List<Card> tableCards, List<Card> playerCards) {
        this.check = Stream.concat(tableCards.stream(), playerCards.stream()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean pair() {
        return check.size() >= PAIR_DIMENSION && getSumOfSameNameCard()
                .filter(t -> t == PAIR_DIMENSION).count() == 1 ? true : false;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean twoPairs() {
        return check.size() >= PAIR_DIMENSION * 2 && getSumOfSameNameCard()
                .filter(t -> t == PAIR_DIMENSION).count() == 2 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean tris() {
        return check.size() >= TRIS_DIMENSION && getSumOfSameNameCard()
                .filter(t -> t == TRIS_DIMENSION).count() == 1 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean straight() {
        return getRoyalFlush().size() == STRAIGHT_DIMENSION ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean fullHouse() {
        return check.size() >= (STRAIGHT_DIMENSION) && pair() && tris() ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean flush() {
        return check.size() >= STRAIGHT_DIMENSION && getSumOfSameSeedCard()
                .filter(t -> t == POKER_DIMENSION).count() == 1 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean poker() {
        return check.size() >= POKER_DIMENSION && getSumOfSameNameCard()
                .filter(t -> t == POKER_DIMENSION).count() == 1 ? true : false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean royalFlush() {
        return getRoyalFlush().size() == STRAIGHT_DIMENSION &&
                getRoyalFlush().stream().map(t -> t.getValue1()).distinct().count() == 1
                        ? true
                        : false;
    }

    private List<Pair<Integer, SeedCard>> getRoyalFlush() {
        var straightList = this.check.stream().map(t -> new Pair<>(t.valueOfCard(), t.seedName())).distinct().sorted()
                .collect(Collectors.toList());
        List<Integer> controList = new LinkedList<>();
        Boolean checkStraight = false;

        while (straightList.size() > STRAIGHT_DIMENSION && checkStraight != true) {
            for (int i = 0; i < STRAIGHT_DIMENSION; i++) {
                controList.add(straightList.get(i).getValue0() + STRAIGHT_CONTROLL.get(i));
            }
            if (controList.stream().distinct().count() == 1) {
                checkStraight = true;
            } else {
                straightList.removeFirst();
            }
        }
        while (checkStraight == true && straightList.size() > STRAIGHT_DIMENSION) {
            straightList.removeLast();
        }
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
