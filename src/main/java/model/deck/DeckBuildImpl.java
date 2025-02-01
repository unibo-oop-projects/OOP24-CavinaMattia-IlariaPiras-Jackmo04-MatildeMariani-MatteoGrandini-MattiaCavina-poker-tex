package model.deck;

import java.util.List;

import com.google.common.collect.Lists;

import model.deck.api.Card;
import model.deck.api.DeckBuild;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Class to generate Standard Deck of Poker.
 */
public class DeckBuildImpl implements DeckBuild<Card> {


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> buildDeck() {
        final List<Card> deckNew = Lists.newLinkedList();
        for (final var elemSimple : SimpleCard.values()) {
            for (final var elemSeed : SeedCard.values()) {
                deckNew.add(new Card(elemSimple, elemSimple.getValueOfCard(), elemSeed));
            }
        }
        return deckNew;
    }

}
