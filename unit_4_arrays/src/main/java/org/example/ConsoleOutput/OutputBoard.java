package org.example.ConsoleOutput;

import org.example.Chess.ChessBoard;
import org.example.Chess.PrintFigure;

public class OutputBoard {

    public static void printBoard(ChessBoard board) {

        PrintFigure[][] b = board.getBoardArray();
        System.out.println();
        System.out.println("    \t[A]\t[B]\t[C]\t[D]\t[E]\t[F]\t[G]\t[H]     ");
        for (int i = 0; i < 8; i++) {
            System.out.print("[" + (8 - i) + "]   ");

            for (int j = 0; j < 8; j++) {
                System.out.print(b[i][j].getValue());
            }

            System.out.println("\t [" + (8 - i) + "]" );
        }
    }
}
