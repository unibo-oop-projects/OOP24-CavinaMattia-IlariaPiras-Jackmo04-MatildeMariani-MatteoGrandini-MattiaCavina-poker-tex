package pokertexas.model.player.ai;

import java.util.Random;
import java.util.function.Function;

import pokertexas.model.combination.Combination;
import pokertexas.model.combination.api.CombinationType;
import pokertexas.model.game.api.State;
import pokertexas.model.player.ai.api.AIPlayer;
import pokertexas.model.player.ai.api.AIPlayerFactory;

/**
 * Implementation of the {@link AIPlayerFactory} interface.
 * The decision-making process of the AI players is based on the
 * {@link Combination} they have and the current {@link State} of the game.
 * The better the combination, the more likely the AI player is to call or
 * raise.
 * They're also much more likely to raise if no one has betted yet.
 */
public class AIPlayerFactoryImpl implements AIPlayerFactory {

    // Changes how much the AI player will raise.
    private static final double EASY_RAISING_FACTOR = 0.50;
    private static final double MEDIUM_RAISING_FACTOR = 1.00;
    private static final double HARD_RAISING_FACTOR = 2.00;

    // Changes how likely the AI player is to call or raise.
    private static final double EASY_DIFFICULTY_MODIFIER = 0.75;
    private static final double MEDIUM_DIFFICULTY_MODIFIER = 1.00;
    private static final double HARD_DIFFICULTY_MODIFIER = 1.25;

    // Decides what is considered to be a high raise to call.
    private static final double HIGH_RAISE_THRESHOLD = 1.5;

    // Modifier to decrease the chance of calling a high raise.
    private static final double HIGH_RAISE_MODIFIER = 0.75;

    // Modifier to increase the chance of raising if the AI player is the first to bet.
    private static final double FIRST_TO_BET_INCREMENT = 0.80;

    // Modifiers to change the chance of calling or raising based on the current hand phase.
    private static final double PREFLOP_MODIFIER = 1.00;
    private static final double FLOP_MODIFIER = 0.75;
    private static final double TURN_MODIFIER = 0.60;
    private static final double RIVER_MODIFIER = 0.45;

    // Standard values for raising based on the combination type.
    private static final double STD_RAISE_ROYAL_FLUSH = 0.80;
    private static final double STD_RAISE_STRAIGHT_FLUSH = 0.70;
    private static final double STD_RAISE_POKER = 0.60;
    private static final double STD_RAISE_FULL_HOUSE = 0.40;
    private static final double STD_RAISE_FLUSH = 0.30;
    private static final double STD_RAISE_STRAIGHT = 0.25;
    private static final double STD_RAISE_TRIS = 0.20;
    private static final double STD_RAISE_TWO_PAIR = 0.10;
    private static final double STD_RAISE_PAIR = 0.05;
    private static final double STD_RAISE_HIGH_CARD = 0.01;

    // Standard values for calling based on the combination type.
    private static final double STD_CALL_ROYAL_FLUSH = 2.00;
    private static final double STD_CALL_STRAIGHT_FLUSH = 1.97;
    private static final double STD_CALL_POKER = 1.95;
    private static final double STD_CALL_FULL_HOUSE = 1.90;
    private static final double STD_CALL_FLUSH = 1.85;
    private static final double STD_CALL_STRAIGHT = 1.80;
    private static final double STD_CALL_TRIS = 1.60;
    private static final double STD_CALL_TWO_PAIR = 1.10;
    private static final double STD_CALL_PAIR = 0.90;
    private static final double STD_CALL_HIGH_CARD = 0.80;

    /**
     * {@inheritDoc}
     */
    @Override
    public AIPlayer createEasy(final int id, final int initialChips) {
        return standard(id, initialChips, EASY_RAISING_FACTOR, EASY_DIFFICULTY_MODIFIER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AIPlayer createMedium(final int id, final int initialChips) {
        return standard(id, initialChips, MEDIUM_RAISING_FACTOR, MEDIUM_DIFFICULTY_MODIFIER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AIPlayer createHard(final int id, final int initialChips) {
        return standard(id, initialChips, HARD_RAISING_FACTOR, HARD_DIFFICULTY_MODIFIER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AIPlayer createCustom(
        final int id, 
        final int initialChips, 
        final double raisingFactor, 
        final double difficultyModifier,
        final Function<CombinationType, Double> callChance, 
        final Function<CombinationType, Double> raiseChance
    ) {
        return new AbstractAIPlayer(id, initialChips, raisingFactor) {

            private final Random random = new Random();

            @Override
            protected boolean shouldCall() {
                final var currentState = this.getGameState();
                final var currentHandPhase = currentState.getHandPhase();
                var chance = difficultyModifier * callChance.apply(this.getCombination().getType());
                chance = chance * switch (currentHandPhase) {
                    case PREFLOP -> PREFLOP_MODIFIER;
                    case FLOP -> FLOP_MODIFIER;
                    case TURN -> TURN_MODIFIER;
                    case RIVER -> RIVER_MODIFIER;
                };
                if (this.getTotalPhaseBet() != 0
                        && requiredBet(currentState) > this.getTotalPhaseBet() * HIGH_RAISE_THRESHOLD) {
                    chance = chance * HIGH_RAISE_MODIFIER;
                }
                return random.nextDouble() < chance;
            }

            @Override
            protected boolean shouldRaise() {
                final var currentState = this.getGameState();
                var chance = difficultyModifier * raiseChance.apply(this.getCombination().getType());
                if (requiredBet(currentState) == 0) {
                    chance = chance + FIRST_TO_BET_INCREMENT;
                }
                return random.nextDouble() < chance;
            }

        };
    }

    private AIPlayer standard(
        final int id, 
        final int initialChips,
        final double raisingFactor, 
        final double difficultyModifier
    ) {
        return createCustom(id, initialChips, raisingFactor, difficultyModifier,
            type -> switch (type) {
                case HIGH_CARD -> STD_CALL_HIGH_CARD;
                case PAIR -> STD_CALL_PAIR;
                case TWO_PAIRS -> STD_CALL_TWO_PAIR;
                case TRIS -> STD_CALL_TRIS;
                case STRAIGHT -> STD_CALL_STRAIGHT;
                case FLUSH -> STD_CALL_FLUSH;
                case FULL_HOUSE -> STD_CALL_FULL_HOUSE;
                case POKER -> STD_CALL_POKER;
                case STRAIGHT_FLUSH -> STD_CALL_STRAIGHT_FLUSH;
                case ROYAL_FLUSH -> STD_CALL_ROYAL_FLUSH;
            },
            type -> switch (type) {
                case HIGH_CARD -> STD_RAISE_HIGH_CARD;
                case PAIR -> STD_RAISE_PAIR;
                case TWO_PAIRS -> STD_RAISE_TWO_PAIR;
                case TRIS -> STD_RAISE_TRIS;
                case STRAIGHT -> STD_RAISE_STRAIGHT;
                case FLUSH -> STD_RAISE_FLUSH;
                case FULL_HOUSE -> STD_RAISE_FULL_HOUSE;
                case POKER -> STD_RAISE_POKER;
                case STRAIGHT_FLUSH -> STD_RAISE_STRAIGHT_FLUSH;
                case ROYAL_FLUSH -> STD_RAISE_ROYAL_FLUSH;
            }
        );
    }

}
