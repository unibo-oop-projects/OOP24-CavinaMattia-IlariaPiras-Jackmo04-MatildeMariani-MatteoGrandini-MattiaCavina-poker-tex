package model.deck;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.deck.api.Deck;
import model.deck.api.DeckBuild;

/**
 * Generic class to manage deck , like create a deck ora shuffled or more to
 * keep card.
 * 
 * @param <X>
 */
public class DeckImpl<X> implements Deck<X> {

    private final DeckBuild<X> deckBuilder;
    private List<X> deck;

    /**
     * Create and shuffle new deck.
     * 
     * @param deckBuilder
     */
    public DeckImpl(final DeckBuild<X> deckBuilder) {
        this.deckBuilder = deckBuilder;
        this.deck = deckBuilder.buildDeck();
    }

 /**
  * Method to shuffle the deck.
  */
    @Override
    public void shuffled() {
        this.deck = deckBuilder.buildDeck();
    }

    /**
     * Method to keep some card from deck.
     */
    @Override
    public List<X> getSomeCards(final int numberOfCard) {
        if (this.deck.isEmpty()) {
            throw new IllegalAccessError("Keep more Cards than remaing in Deck");
        } else {
            return Stream.iterate(0, t -> t + 1)
                    .limit(numberOfCard)
                    .map(t -> this.deck.remove(0))
                    .collect(Collectors.toList());
        }

    }

}
