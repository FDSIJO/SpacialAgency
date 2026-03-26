package org.sijoexercise.enums;

public enum Direction {
    N, E, S, W;

    public static Direction rotateLeft(Direction direction) {
        return switch (direction) {
            case N -> Direction.W;
            case E -> Direction.N;
            case S -> Direction.E;
            case W -> Direction.S;
        };
    }

    public static Direction rotateRight(Direction direction) {
        return switch (direction) {
            case N -> Direction.E;
            case E -> Direction.S;
            case S -> Direction.W;
            case W -> Direction.N;
        };
    }
}
