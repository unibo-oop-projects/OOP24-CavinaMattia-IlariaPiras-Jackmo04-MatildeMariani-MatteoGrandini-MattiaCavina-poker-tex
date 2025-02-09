package model.deck;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import model.deck.api.Card;
import model.deck.api.Deck;
import model.deck.api.DeckFactory;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Factory to generate different {@link Deck} whit diffent
 * propreties.
 * 
 */
public class DeckFactoryImpl implements DeckFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Card> simplePokerDeck() {
        return new DeckImpl<Card>(() -> {
            List<Card> deckCard = Arrays.stream(SimpleCard.values())
                    .flatMap(t -> Arrays.stream(SeedCard.values())
                            .map(l -> new Card(t, l)))
                    .collect(Collectors.toList());
            Collections.shuffle(deckCard);
            return deckCard;
        });
    }
}
