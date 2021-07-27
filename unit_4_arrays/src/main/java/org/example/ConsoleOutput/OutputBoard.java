package org.example.ConsoleOutput;

import org.example.Chess.ChessBoard;
import org.example.Chess.PrintFigure;

public class OutputBoard {

    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void printBoard(ChessBoard board) {

        PrintFigure[][] b = board.getBoardArray();
        System.out.println();
        System.out.println(ANSI_WHITE_BACKGROUND + "    \t[A]\t[B]\t[C]\t[D]\t[E]\t[F]\t[G]\t[H]     " + ANSI_RESET);
        for (int i = 0; i < 8; i++) {
            System.out.print(ANSI_WHITE_BACKGROUND + "[" + (8 - i) + "]   " + ANSI_RESET);

            for (int j = 0; j < 8; j++) {
                System.out.print(ANSI_WHITE_BACKGROUND + b[i][j].getValue() + ANSI_RESET);
            }

            System.out.println(ANSI_WHITE_BACKGROUND + "\t [" + (8 - i) + "]" + ANSI_RESET);
        }
    }
}
