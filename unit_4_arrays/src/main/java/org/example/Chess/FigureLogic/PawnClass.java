package org.example.Chess.FigureLogic;

import org.example.Chess.AbstractFigure;
import org.example.Chess.MoveLogic;

public class PawnClass extends AbstractFigure {

    public PawnClass(FigureColor color) {
        super(FigureType.Pawn, color, validMoves(color), false);
    }

    private static MoveLogic[] validMoves(FigureColor color) {
        if (color == FigureColor.Black) {
            return new MoveLogic[]{new MoveLogic(0, 1, false, false), new MoveLogic(0, 2, true, false),
                    new MoveLogic(1, 1, false, true), new MoveLogic(-1, 1, false, true)};
        } else {
            return new MoveLogic[]{new MoveLogic(0, -1, false, false), new MoveLogic(0, -2, true, false),
                    new MoveLogic(1, -1, false, true), new MoveLogic(-1, -1, false, true)};
        }
    }
}
