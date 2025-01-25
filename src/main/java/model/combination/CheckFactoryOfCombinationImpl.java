package model.combination;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.combination.api.CheckFactoryOfCombination;
import model.combination.api.CheckTypeOfCombination;
import model.deck.api.Card;

public class CheckFactoryOfCombinationImpl implements CheckFactoryOfCombination<Card> {

    private final List<Card> tableCards;
    private final List<Card> playerCards;
    private final HashMap<Card, Integer> check;

    public CheckFactoryOfCombinationImpl(List<Card> tableCards, List<Card> playerCards) {
        this.tableCards = tableCards;
        this.playerCards = playerCards;
        this.check = Stream.concat(tableCards.stream(), playerCards.stream())
                .collect(Collectors.toMap(card -> card, card -> 1, Integer::sum, HashMap::new));
    }

    @Override
    public Boolean pair() {
        return check.entrySet().stream().filter(t -> t.getValue() == 2).count() == 1 ? true : false;
    }

    @Override
    public Boolean twoPairs() {
        return check.entrySet().stream().filter(t -> t.getValue() == 2).count() == 1 ? true : false;
    }

    @Override
    public Boolean tris() {
        return check.entrySet().stream().filter(t -> t.getValue() == 3).count() == 1 ? true : false;
    }

    @Override
    public Boolean straight() {
        
        return check.entrySet().stream().filter(t -> t.getValue() == 1).count() == 7 &&
        check.entrySet().stream().sorted( (t,l) -> t.getKey().valueOfCard() < t.getKey().valueOfCard() ).map( t ->  ) ?true:false;
    }

    @Override
    public Boolean fullHouse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fullHouse'");
    }

    @Override
    public Boolean flush() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flush'");
    }

    @Override
    public Boolean poker() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'poker'");
    }

    @Override
    public Boolean royalFlush() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'royalFlush'");
    }

    private boolean isStraight() {
        ListIterator<Card> it = this.check.keySet().stream()
        .sorted( (t,l) -> Integer.compare(t.valueOfCard(), l.valueOfCard())  )
        .toList()
        .listIterator();

        return false;
    }

}
