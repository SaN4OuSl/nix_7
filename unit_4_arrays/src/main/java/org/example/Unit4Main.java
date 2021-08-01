package org.example;

import org.example.Chess.ChessGame;
import org.example.Chess.FigurePosition;
import org.example.ConsoleOutput.InputHandler;
import org.example.ConsoleOutput.OutputBoard;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Unit4Main {

    public static void main(String args[]) {
        try {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new InternalError("VM does not support mandatory encoding UTF-8");
        }
        boolean isWhite = true;
        InputHandler handler = new InputHandler();
        Scanner in = new Scanner(System.in);
        ChessGame game = new ChessGame();
        OutputBoard.printBoard(game.getBoard());
        System.out.println("White move");
        while (!game.isFinished()) {
            System.out.println("To end the game, enter exit: ");
            System.out.println("For a draw offer, enter draw: ");
            System.out.println("Enter move (examples: d2-d3, D2-D3): ");
            String input = in.nextLine();
            if (input.equals("exit") || input.equals("draw")) {
                if (input.equals("exit")) {
                    if (isWhite)
                        System.out.println("White lose");
                    else
                        System.out.println("Black lose");
                    game.setFinished(true);
                }
                if (input.equals("draw")) {
                    String CheckDrawEnd;
                    System.out.println("Enter yes if you want a draw too");
                    CheckDrawEnd = in.nextLine();
                    if (CheckDrawEnd.equals("yes")) {
                        game.setFinished(true);
                    } else {
                        System.out.println("Opponent rejected a draw offer");
                    }
                }
            } else {
                if (!handler.isValid(input)) {
                    System.out.println("Invalid input!");
                    System.out.println("Valid input is in form: d2-d3");
                } else {
                    FigurePosition from = handler.getFrom(input);
                    FigurePosition to = handler.getTo(input);

                    boolean movePlayed = game.playMove(from, to);
                    if (!movePlayed)
                        System.out.println("Illegal move!");
                    else {
                        OutputBoard.printBoard(game.getBoard());
                        if (isWhite) {
                            System.out.println("Black move");
                            isWhite = false;
                        } else {
                            System.out.println("White move");
                            isWhite = true;
                        }
                    }
                }
            }
        }
        in.close();
        System.out.println("Game has finished.");
    }
}
