package org.example.Chess.FigureLogic;

import org.example.Chess.AbstractFigure;
import org.example.Chess.MoveLogic;

public class KingClass extends AbstractFigure {

    public KingClass(AbstractFigure.FigureColor color) {
        super(FigureType.King, color, validMoves(), false);
    }

    private static MoveLogic[] validMoves() {
        return new MoveLogic[]{new MoveLogic(1, 0, false, false), new MoveLogic(0, 1, false, false),
                new MoveLogic(-1, 0, false, false), new MoveLogic(0, -1, false, false),
                new MoveLogic(1, 1, false, false), new MoveLogic(1, -1, false, false),
                new MoveLogic(-1, 1, false, false), new MoveLogic(-1, -1, false, false)};
    }
}
