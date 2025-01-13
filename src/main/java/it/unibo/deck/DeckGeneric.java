package it.unibo.deck;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract methd to manage Deck.
 */
public abstract class DeckGeneric<X> implements Deck<X> {
    private List<X> deck = new LinkedList<>();

    public DeckGeneric() {
        this.deck = getDeck();
    }

    /**
     * 
     * @param deck
     */
    protected void setDeck(List<X> deck) {
        this.deck = deck;
    }

    /**
     * 
     * @return
     */
    protected List<X> getDeck() {
        return this.deck;
    }

    /**
     * Generate deck.Can be use to shuffle a deck.
     */
    public abstract void generateDeck();

    @Override
    public List<X> getCards(final int numberOfCard) {
        return Stream.iterate(0, t -> t + 1)
                .limit(numberOfCard)
                .map(t -> this.deck.removeFirst())
                .toList();
    }

}
