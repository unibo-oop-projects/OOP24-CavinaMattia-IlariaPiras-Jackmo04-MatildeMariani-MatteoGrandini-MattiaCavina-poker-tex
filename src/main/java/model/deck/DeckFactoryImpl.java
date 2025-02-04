package model.deck;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import model.deck.api.Card;
import model.deck.api.Deck;
import model.deck.api.DeckFactory;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Factory to generate different {@link Deck} whit diffent propreties.
 * 
 */
public class DeckFactoryImpl implements DeckFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck<Card> simplePokerDeck() {
        return new DeckImpl<>(() -> {

            final List<Card> deckNew = Lists.newLinkedList();
            for (final var elemSimple : SimpleCard.values()) {
                for (final var elemSeed : SeedCard.values()) {
                    deckNew.add(new Card(elemSimple, elemSimple.getValueOfCard(), elemSeed));
                }
            }
            Collections.shuffle(deckNew);
            return deckNew;
        });
    }

}
