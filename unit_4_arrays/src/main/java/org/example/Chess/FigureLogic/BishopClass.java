package org.example.Chess.FigureLogic;

import org.example.Chess.AbstractFigure;
import org.example.Chess.MoveLogic;

public class BishopClass extends AbstractFigure {

    public BishopClass(FigureColor color) {
        super(FigureType.Bishop, color, validMoves(), true);
    }

    private static MoveLogic[] validMoves() {
        return new MoveLogic[]{new MoveLogic(1, 1, false, false), new MoveLogic(1, -1, false, false),
                new MoveLogic(-1, 1, false, false), new MoveLogic(-1, -1, false, false)};
    }
}
