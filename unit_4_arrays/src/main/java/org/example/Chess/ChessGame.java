package org.example.Chess;

import org.example.Chess.AbstractFigure.*;

import java.util.ArrayList;

public class ChessGame {

    private final ChessBoard board;
    private boolean isFinished;
    private FigureColor currentPlayer;

    public ChessGame() {
        board = new ChessBoard();
        currentPlayer = FigureColor.White;
        isFinished = false;
    }

    public boolean playMove(FigurePosition from, FigurePosition to) {
        if (isValidMove(from, to, false)) {
            PrintFigure fromPrintFigure = board.getBoardArray()[from.Y()][from.X()];
            AbstractFigure figureToMove = fromPrintFigure.getFigure();

            PrintFigure toPrintFigure = board.getBoardArray()[to.Y()][to.X()];
            toPrintFigure.setFigure(figureToMove);

            fromPrintFigure.empty();
            endTurn();
            return true;
        } else {
            return false;
        }
    }

    public ChessBoard getBoard() {
        return board;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean status) {
        isFinished = status;
    }

    private void endTurn() {
        currentPlayer = (currentPlayer == FigureColor.White)
                ? FigureColor.Black
                : FigureColor.White;
    }

    private boolean isShah(FigureColor color) {
        boolean canPreventCheck = false;
        FigurePosition[] locations = board.getAllFiguresLocationForColor(color);

        for (FigurePosition location : locations) {
            PrintFigure fromPrintFigure = board.getPrintFigurefromFigurePosition(location);
            AbstractFigure figure = fromPrintFigure.getFigure();
            FigurePosition[] possibleMoves = validMovesForFigure(figure, location);

            for (FigurePosition newLocation : possibleMoves) {
                PrintFigure toPrintFigure = board.getPrintFigurefromFigurePosition(newLocation);
                AbstractFigure toFigure = toPrintFigure.getFigure();
                toPrintFigure.setFigure(figure);
                fromPrintFigure.empty();
                if (!isKingCheck(color)) {
                    canPreventCheck = true;
                }
                toPrintFigure.setFigure(toFigure);
                fromPrintFigure.setFigure(figure);
                if (canPreventCheck) {
                    System.out.printf("\nSHAH!!!");
                    return canPreventCheck;
                }
            }
        }
        return canPreventCheck;
    }

    private boolean isColorCheckMate(FigureColor color) {
        if (!isKingCheck(color)) return false;
        return !isShah(color);
    }

    private boolean isKingCheck(FigureColor kingColor) {
        FigurePosition kingLocation = board.getKingLocation(kingColor);
        return canOpponentTakeLocation(kingLocation, kingColor);
    }

    private boolean canOpponentTakeLocation(FigurePosition location, FigureColor color) {
        FigureColor opponentColor = AbstractFigure.opponent(color);
        FigurePosition[] figuresLocation = board.getAllFiguresLocationForColor(opponentColor);

        for (FigurePosition fromFigurePosition : figuresLocation) {
            if (isValidMove(fromFigurePosition, location, true))
                return true;
        }
        return false;
    }

    private boolean isValidMove(FigurePosition from, FigurePosition to, boolean hypothetical) {
        PrintFigure fromPrintFigure = board.getPrintFigurefromFigurePosition(from);
        PrintFigure toPrintFigure = board.getPrintFigurefromFigurePosition(to);
        AbstractFigure fromFigure = fromPrintFigure.getFigure();
        AbstractFigure toFigure = toPrintFigure.getFigure();
        if (fromFigure == null) {
            return false;
        } else if (fromFigure.getColor() != currentPlayer) {
            return false;
        } else if (toFigure != null && toFigure.getColor() == currentPlayer) {
            return false;
        } else if (isValidMoveForFigure(from, to)) {
            if (hypothetical) return true;
            toPrintFigure.setFigure(fromFigure);
            fromPrintFigure.empty();
            if (isKingCheck(currentPlayer)) {
                toPrintFigure.setFigure(toFigure);
                fromPrintFigure.setFigure(fromFigure);
                return false;
            }
            if (isColorCheckMate(AbstractFigure.opponent(currentPlayer)))
                isFinished = true;
            toPrintFigure.setFigure(toFigure);
            fromPrintFigure.setFigure(fromFigure);
            return true;
        }
        return false;
    }

    private FigurePosition[] validMovesForFigure(AbstractFigure figure, FigurePosition currentLocation) {
        return figure.hasRepeatableMoves()
                ? validMovesRepeatable(figure, currentLocation)
                : validMovesNonRepeatable(figure, currentLocation);
    }

    private FigurePosition[] validMovesRepeatable(AbstractFigure figure, FigurePosition currentLocation) {
        MoveLogic[] moves = figure.getMoves();
        ArrayList<FigurePosition> possibleMoves = new ArrayList<>();
        for (MoveLogic move : moves) {
            for (int i = 1; i < 7; i++) {
                int newX = currentLocation.X() + move.x * i;
                int newY = currentLocation.Y() + move.y * i;
                if (newX < 0 || newX > 7 || newY < 0 || newY > 7) break;

                FigurePosition toLocation = new FigurePosition(newX, newY);
                PrintFigure printFigure = board.getPrintFigurefromFigurePosition(toLocation);
                if (printFigure.isEmpty()) {
                    possibleMoves.add(toLocation);
                } else {
                    if (printFigure.getFigure().getColor() != figure.getColor())
                        possibleMoves.add(toLocation);
                    break;
                }
            }
        }
        return possibleMoves.toArray(new FigurePosition[0]);
    }

    private FigurePosition[] validMovesNonRepeatable(AbstractFigure figure, FigurePosition currentLocation) {
        MoveLogic[] moves = figure.getMoves();
        ArrayList<FigurePosition> possibleMoves = new ArrayList<>();
        for (MoveLogic move : moves) {
            int currentX = currentLocation.X();
            int currentY = currentLocation.Y();
            int newX = currentX + move.x;
            int newY = currentY + move.y;
            if (newX < 0 || newX > 7 || newY < 0 || newY > 7) continue;
            FigurePosition newLocation = new FigurePosition(newX, newY);
            if (isValidMoveForFigure(currentLocation, newLocation)) possibleMoves.add(newLocation);
        }
        return possibleMoves.toArray(new FigurePosition[0]);
    }

    private boolean isValidMoveForFigure(FigurePosition from, FigurePosition to) {
        AbstractFigure fromFigure = board.getPrintFigurefromFigurePosition(from).getFigure();
        boolean repeatableMoves = fromFigure.hasRepeatableMoves();
        return repeatableMoves
                ? isValidMoveForFigureRepeatable(from, to)
                : isValidMoveForFigureNonRepeatable(from, to);
    }

    private boolean isValidMoveForFigureRepeatable(FigurePosition from, FigurePosition to) {
        AbstractFigure fromFigure = board.getPrintFigurefromFigurePosition(from).getFigure();
        MoveLogic[] validMoves = fromFigure.getMoves();
        int xMove = to.X() - from.X();
        int yMove = to.Y() - from.Y();

        for (int i = 1; i <= 7; i++) {
            for (MoveLogic move : validMoves) {
                if (move.x * i == xMove && move.y * i == yMove) {
                    for (int j = 1; j <= i; j++) {
                        PrintFigure printFigure = board.getPrintFigurefromFigurePosition(new FigurePosition(from.X() + move.x * j, from.Y() + move.y * j));
                        if (j != i && !printFigure.isEmpty())
                            return false;
                        if (j == i && (printFigure.isEmpty() || printFigure.getFigure().getColor() != currentPlayer))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isValidMoveForFigureNonRepeatable(FigurePosition from, FigurePosition to) {
        AbstractFigure fromFigure = board.getPrintFigurefromFigurePosition(from).getFigure();
        MoveLogic[] validMoves = fromFigure.getMoves();
        PrintFigure toPrintFigure = board.getPrintFigurefromFigurePosition(to);

        int xMove = to.X() - from.X();
        int yMove = to.Y() - from.Y();

        for (MoveLogic move : validMoves) {
            if (move.x == xMove && move.y == yMove) {
                if (move.onTakeOnly) {
                    if (toPrintFigure.isEmpty()) return false;
                    AbstractFigure toFigure = toPrintFigure.getFigure();
                    return fromFigure.getColor() != toFigure.getColor();
                } else if (move.firstMoveOnly) {
                    return toPrintFigure.isEmpty() && isFirstMoveForPawn(from, board);
                } else {
                    return toPrintFigure.isEmpty();
                }
            }
        }
        return false;
    }

    public boolean isFirstMoveForPawn(FigurePosition from, ChessBoard board) {
        PrintFigure printFigure = board.getPrintFigurefromFigurePosition(from);
        if (printFigure.isEmpty() || printFigure.getFigure().getFigureType() != FigureType.Pawn) {
            return false;
        } else {
            FigureColor color = printFigure.getFigure().getColor();
            return (color == FigureColor.White)
                    ? from.Y() == 6
                    : from.Y() == 1;
        }
    }
}
