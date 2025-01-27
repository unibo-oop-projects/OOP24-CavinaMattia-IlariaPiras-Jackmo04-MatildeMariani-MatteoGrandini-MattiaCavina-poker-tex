package model.combination.api;

public enum CombinationDimension {
    PAIR(2), TWO_PAIRS(4), TRIS(3), POKER(4), STRAIGHT(5);

    private final int dimension;

    CombinationDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return this.dimension;
    }

}
