package model.deck.api;

/**
 * This is a record to generate a single card with its all propreties.
 * 
 * @param cardName
 *                    name of card.
 * @param valueOfCard
 *                    value of my card.
 * @param seedName
 *                    name of card's seed.
 */
public record Card(SimpleCard cardName, int valueOfCard, SeedCard seedName) {

}
