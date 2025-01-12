package it.unibo.deck;

import java.util.LinkedList;
import java.util.List;

import it.unibo.deck.TexasCard.SeedCard;
import it.unibo.deck.TexasCard.SimpleCard;

public class CardGenerateImpl implements CardGenerate<Card>{

    @Override
    public List<Card> getDeck() {
                final List<Card> deck = new LinkedList<>();
        for (final var elemSimple : SimpleCard.values()) {
            for (final var elemSeed : SeedCard.values()) {
                deck.add(new Card(elemSimple.name(), elemSimple.getValueOfCard(), elemSeed.name()));
            }
        }
        return deck.stream().sorted().toList();

    }
    
}
