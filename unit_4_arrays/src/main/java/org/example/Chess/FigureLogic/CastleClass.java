package org.example.Chess.FigureLogic;

import org.example.Chess.AbstractFigure;
import org.example.Chess.MoveLogic;

public class CastleClass extends AbstractFigure {

    public CastleClass(FigureColor color) {
        super(FigureType.Castle, color, validMoves(), true);
    }

    private static MoveLogic[] validMoves() {
        return new MoveLogic[]{new MoveLogic(1, 0, false, false), new MoveLogic(0, 1, false, false),
                new MoveLogic(-1, 0, false, false), new MoveLogic(0, -1, false, false)};
    }
}
