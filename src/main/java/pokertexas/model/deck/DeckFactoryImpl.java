package pokertexas.model.deck;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import pokertexas.model.deck.api.Card;
import pokertexas.model.deck.api.Deck;
import pokertexas.model.deck.api.DeckFactory;
import pokertexas.model.deck.api.SeedCard;
import pokertexas.model.deck.api.SimpleCard;

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
        return new DeckImpl<>(() -> {
            final List<Card> deckCard = Arrays.stream(SimpleCard.values())
                    .flatMap(t -> Arrays.stream(SeedCard.values())
                            .map(l -> new Card(t, l)))
                    .collect(Collectors.toList());
            Collections.shuffle(deckCard);
            return deckCard;
        });
    }
}
