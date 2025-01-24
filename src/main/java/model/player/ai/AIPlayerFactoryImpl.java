package model.player.ai;

import java.util.Random;

import model.player.ai.api.AIPlayer;
import model.player.ai.api.AIPlayerFactory;
import model.player.api.Role;
import model.temp.State;

// TODO Le percentuali saranno sicuramente da rivedere
/**
 * Implementation of the {@link AIPlayerFactory} interface.
 * This class provides methods to create AI players with different difficulty
 * levels.
 * The difficulty levels are: easy, medium and hard.
 * The decision-making process of the AI players is based on the
 * {@link Combination} they have.
 * The better the combination, the more likely the AI player is to call or
 * raise.
 * They're also much more likely to raise if no one has betted yet.
 */
public class AIPlayerFactoryImpl implements AIPlayerFactory {

    private static final double EASY_DIFFICULTY_MODIFIER = 0.70;
    private static final double MEDIUM_DIFFICULTY_MODIFIER = 1.00;
    private static final double HARD_DIFFICULTY_MODIFIER = 1.25;
    private static final double EASY_RAISING_FACTOR = 0.50;
    private static final double MEDIUM_RAISING_FACTOR = 1.00;
    private static final double HARD_RAISING_FACTOR = 2.00;

    /**
     * {@inheritDoc}
     */
    @Override
    public AIPlayer easy(final int initialChips, final Role initialRole) {
        return standard(initialChips, initialRole, EASY_RAISING_FACTOR, EASY_DIFFICULTY_MODIFIER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AIPlayer medium(final int initialChips, final Role initialRole) {
        return standard(initialChips, initialRole, MEDIUM_RAISING_FACTOR, MEDIUM_DIFFICULTY_MODIFIER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AIPlayer hard(final int initialChips, final Role initialRole) {
        return standard(initialChips, initialRole, HARD_RAISING_FACTOR, HARD_DIFFICULTY_MODIFIER);
    }

    private AIPlayer standard(final int initialChips, final Role initialRole,
            final double raisingFactor, final double difficultyModifier) {
        return new AbstractAIPlayer(initialChips, initialRole, raisingFactor) {

            private final Random random = new Random();

            @Override
            protected boolean shouldCall(final State currentState) {
                var callChance = difficultyModifier * switch (this.getCombination().type()) {
                    case HIGH_CARD -> 0.80;
                    case PAIR -> 0.90;
                    case TWO_PAIRS -> 1.10;
                    case THREE_OF_A_KIND -> 1.60;
                    case STRAIGHT -> 1.80;
                    case FLUSH -> 1.85;
                    case FULL_HOUSE -> 1.90;
                    case FOUR_OF_A_KIND -> 1.95;
                    case STRAIGHT_FLUSH -> 2.00;
                };
                callChance = callChance * switch (currentState.handFase()) {
                    case PREFLOP -> 1.00;
                    case FLOP -> 0.75;
                    case TURN -> 0.60;
                    case RIVER -> 0.45;
                };
                if (this.getTotalFaseBet() != 0 && requiredBet(currentState) > this.getTotalFaseBet() * 1.5) {
                    callChance = callChance * 0.75;
                }
                return random.nextDouble() < callChance;
            }

            @Override
            protected boolean shouldRaise(final State currentState) {
                var raiseChance = difficultyModifier * switch (this.getCombination().type()) {
                    case HIGH_CARD -> 0.01;
                    case PAIR -> 0.05;
                    case TWO_PAIRS -> 0.10;
                    case THREE_OF_A_KIND -> 0.20;
                    case STRAIGHT -> 0.25;
                    case FLUSH -> 0.30;
                    case FULL_HOUSE -> 0.40;
                    case FOUR_OF_A_KIND -> 0.60;
                    case STRAIGHT_FLUSH -> 0.80;
                };
                if (requiredBet(currentState) == 0) {
                    raiseChance = raiseChance + 0.80;
                }
                return random.nextDouble() < raiseChance;
            }
        };
    }

}
