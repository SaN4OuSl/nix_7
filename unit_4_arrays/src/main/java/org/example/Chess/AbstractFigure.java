package org.example.Chess;

public abstract class AbstractFigure {
    private final FigureType type;
    private final FigureColor color;
    private final MoveLogic[] moves;
    private final String name;
    private final char charValue;
    private final boolean repeatableMoves;

    protected AbstractFigure(FigureType type, FigureColor color, MoveLogic[] moves, boolean repeatableMoves) {
        this.type = type;
        this.color = color;
        this.moves = moves;
        this.repeatableMoves = repeatableMoves;
        name = type.name();
        charValue = type.name().trim().charAt(2);
    }

    public enum FigureType {
        Pawn, Castle, Horse, Bishop, Queen, King
    }

    public enum FigureColor {
        White, Black
    }

    public MoveLogic[] getMoves() {
        return moves;
    }

    public String getName() {
        return name;
    }

    public FigureColor getColor() {
        return color;
    }

    public char getCharValue() {
        return charValue;
    }

    public boolean hasRepeatableMoves() {
        return repeatableMoves;
    }

    public FigureType getFigureType() {
        return type;
    }

    public static FigureColor opponent(FigureColor color) {
        return (color == FigureColor.Black) ? FigureColor.White : FigureColor.Black;
    }

}
