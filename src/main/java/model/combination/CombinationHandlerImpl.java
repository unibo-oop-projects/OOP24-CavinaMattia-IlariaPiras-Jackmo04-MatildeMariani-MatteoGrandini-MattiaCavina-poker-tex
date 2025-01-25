package model.combination;

import java.util.List;

import model.combination.api.CheckFactoryOfCombination;
import model.combination.api.Combination;
import model.combination.api.CombinationHandler;
import model.deck.api.Card;

public class CombinationHandlerImpl implements CombinationHandler<Card> {

    @Override
    public Combination<Card> getCombinationFromCards(List<Card> tableCards, List<Card> playerCards) {
        
        return null;//to do;
    }

    
}
