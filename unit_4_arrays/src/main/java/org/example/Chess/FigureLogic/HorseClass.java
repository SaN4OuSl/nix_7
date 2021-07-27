package org.example.Chess.FigureLogic;

import org.example.Chess.AbstractFigure;
import org.example.Chess.MoveLogic;

public class HorseClass extends AbstractFigure {

    public HorseClass(AbstractFigure.FigureColor color) {
        super(FigureType.Horse, color, validMoves(), false);
    }

    private static MoveLogic[] validMoves() {
        return new MoveLogic[]{new MoveLogic(2, 1, false, false), new MoveLogic(1, 2, false, false),
                new MoveLogic(2, -1, false, false), new MoveLogic(-1, 2, false, false),
                new MoveLogic(2, -1, false, false), new MoveLogic(-1, 2, false, false),
                new MoveLogic(-2, 1, false, false), new MoveLogic(1, -2, false, false),
                new MoveLogic(-2, -1, false, false), new MoveLogic(-1, -2, false, false),
                new MoveLogic(-2, -1, false, false), new MoveLogic(-1, -2, false, false)};
    }
}
