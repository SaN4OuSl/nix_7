package org.example.Chess;

import org.example.Chess.FigureLogic.*;

import java.util.ArrayList;

public class ChessBoard {
    private final PrintFigure[][] board;

    public ChessBoard() {
        board = new PrintFigure[8][8];
        initializeBoard();
        fillBoard();
    }

    public PrintFigure[][] getBoardArray() {
        return board;
    }

    private void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j % 2 + i == 0) board[i][j] = new PrintFigure(PrintFigure.FigureColor.Black);
                else board[i][j] = new PrintFigure(PrintFigure.FigureColor.White);
            }
        }
    }

    public FigurePosition getKingLocation(AbstractFigure.FigureColor color) {
        FigurePosition location = new FigurePosition(-1, -1);
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (!board[y][x].isEmpty()) {
                    AbstractFigure figure = board[y][x].getFigure();
                    if (figure.getColor() == color && figure instanceof KingClass) {
                        location = new FigurePosition(x, y);
                    }
                }
            }
        }
        return location;
    }

    public FigurePosition[] getAllFiguresLocationForColor(AbstractFigure.FigureColor color) {
        ArrayList<FigurePosition> locations = new ArrayList<>();
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (!board[y][x].isEmpty() && board[y][x].getFigure().getColor() == color)
                    locations.add(new FigurePosition(x, y));
            }
        }
        return locations.toArray(new FigurePosition[0]);
    }

    public PrintFigure getPrintFigurefromFigurePosition(FigurePosition figurePosition) {
        return board[figurePosition.Y()][figurePosition.X()];
    }

    private void fillBoard() {
        for (int i = 0; i < 8; i++) {
            board[1][i].setFigure(new PawnClass(AbstractFigure.FigureColor.Black));
            board[6][i].setFigure(new PawnClass(AbstractFigure.FigureColor.White));
        }

        board[0][0].setFigure(new CastleClass(AbstractFigure.FigureColor.Black));
        board[0][7].setFigure(new CastleClass(AbstractFigure.FigureColor.Black));
        board[7][0].setFigure(new CastleClass(AbstractFigure.FigureColor.White));
        board[7][7].setFigure(new CastleClass(AbstractFigure.FigureColor.White));

        board[0][1].setFigure(new HorseClass(AbstractFigure.FigureColor.Black));
        board[0][6].setFigure(new HorseClass(AbstractFigure.FigureColor.Black));
        board[7][1].setFigure(new HorseClass(AbstractFigure.FigureColor.White));
        board[7][6].setFigure(new HorseClass(AbstractFigure.FigureColor.White));

        board[0][2].setFigure(new BishopClass(AbstractFigure.FigureColor.Black));
        board[0][5].setFigure(new BishopClass(AbstractFigure.FigureColor.Black));
        board[7][2].setFigure(new BishopClass(AbstractFigure.FigureColor.White));
        board[7][5].setFigure(new BishopClass(AbstractFigure.FigureColor.White));

        board[0][3].setFigure(new QueenClass(AbstractFigure.FigureColor.Black));
        board[7][3].setFigure(new QueenClass(AbstractFigure.FigureColor.White));

        board[0][4].setFigure(new KingClass(AbstractFigure.FigureColor.Black));
        board[7][4].setFigure(new KingClass(AbstractFigure.FigureColor.White));
    }
}
