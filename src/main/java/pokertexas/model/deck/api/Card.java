package pokertexas.model.deck.api;

/**
 * This is a record to single card with its all propreties.
 * 
 * @param cardName    name of card keep from {@link SimpleCard}.
 * @param valueOfCard value of my card.
 * @param seedName    name of card's seed keep from {@link SeedCard}.
 */
public record Card(SimpleCard cardName, Integer valueOfCard, SeedCard seedName) {
    /**
     * Costructor with less argument.
     * 
     * @param cardName name of card keep from {@link SimpleCard}.
     * @param seedName name of card's seed keep from {@link SeedCard}.
     */
    public Card(final SimpleCard cardName, final SeedCard seedName) {
        this(cardName, cardName.getValueOfCard(), seedName);
    }

}
