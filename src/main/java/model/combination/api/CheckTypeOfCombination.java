package model.combination.api;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Check the type of combination.
 * 
 */
public interface CheckTypeOfCombination <X>{
    Boolean checkCombination(BiFunction<List<X>, List<X>, List<X>> check);

}
