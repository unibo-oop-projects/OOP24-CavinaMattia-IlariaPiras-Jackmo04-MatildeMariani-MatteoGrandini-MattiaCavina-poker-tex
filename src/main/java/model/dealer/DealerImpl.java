package model.dealer;

import java.util.Set;
import java.util.stream.Collectors;

import model.dealer.api.Dealer;
import model.deck.DeckFactoryImpl;
import model.deck.api.Card;
import model.deck.api.Deck;

public class DealerImpl implements Dealer{

    private static final int NUM_CARD_PLAYER = 2;
    private final Deck<Card> deck;

    public DealerImpl() {
        this.deck = new DeckFactoryImpl().simplePokerDeck();
    }

    @Override
    public Set<Card> giveCardsToPlayer() {
        return this.deck.getSomeCards(NUM_CARD_PLAYER)
                   .stream()
                   .collect(Collectors.toSet());
    }

    @Override
    public Set<Card> giveCardsToTheGame(final int numCardPhase) {
        return this.deck.getSomeCards(numCardPhase)
                   .stream()
                   .collect(Collectors.toSet());
    }

    @Override
    public void shuffle() {
        this.deck.shuffled();
    }

}
