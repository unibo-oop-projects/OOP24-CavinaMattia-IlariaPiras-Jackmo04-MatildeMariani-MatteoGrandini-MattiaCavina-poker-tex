package it.unibo.deck;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract methd to manage Deck.
 * 
 * @param <X>
 */
public abstract class DeckImpl<X> implements Deck<X> {

    private List<X> deck;
    private final PokerCard<X> standardDeck;

    /**
     * create and shuffle new deck.
     * 
     * @param standardDeck
     */
    public DeckImpl(final PokerCard<X> standardDeck) {
        this.standardDeck = standardDeck;
        deck = standardDeck.buildDeck();
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
    public void shuffled() {
        this.deck = standardDeck.buildDeck();
    }

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
