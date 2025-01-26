package model.deck;

import java.util.LinkedList;
import java.util.List;

import model.deck.api.Card;
import model.deck.api.DeckBuild;
import model.deck.api.SeedCard;
import model.deck.api.SimpleCard;

/**
 * Class to generate Standard Deck of Poker.
 */
public class DeckBuildImpl implements DeckBuild<Card> {

    /**
     * Method to generate Poker deck.
     * 
     * @return new Poker's deck.
     */
    @Override
    public List<Card> buildDeck() {
        final List<Card> deckNew = new LinkedList<>();
        for (final var elemSimple : SimpleCard.values()) {
            for (final var elemSeed : SeedCard.values()) {
                deckNew.add(new Card(elemSimple, elemSimple.getValueOfCard(), elemSeed));
            }
        }
        return deckNew;
    }

}
