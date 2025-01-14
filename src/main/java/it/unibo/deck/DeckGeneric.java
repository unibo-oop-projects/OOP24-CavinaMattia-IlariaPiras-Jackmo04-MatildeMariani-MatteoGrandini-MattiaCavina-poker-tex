package it.unibo.deck;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract methd to manage Deck.
 * 
 * @param <X>
 */
public abstract class DeckGeneric<X> implements Deck<X> {

    private List<X> deck;

    /**
     * create and shuffle new deck.
     */
    public DeckGeneric() {
        deck = new LinkedList<>();
    }

    /**
     * 
     * @param deck
     */
    protected void setDeck(final List<X> deck) {
        this.deck = deck;
    }

    /**
     * 
     * @return my list of deck.
     */
    protected List<X> getDeck() {
        return this.deck;
    }

    /**
     * Generate deck.Can be use to shuffle a deck.
     */
    @Override
    public abstract void shuffled();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<X> getSomeCards(final int numberOfCard) {
        if (getDeck().isEmpty()) {
            throw new IllegalAccessError();
        } else {
            return Stream.iterate(0, t -> t + 1)
                    .limit(numberOfCard)
                    .map(t -> getDeck().remove(0))
                    .collect(Collectors.toList());
        }

    }
}
