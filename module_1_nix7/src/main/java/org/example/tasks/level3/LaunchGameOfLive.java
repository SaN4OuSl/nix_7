package org.example.tasks.level3;

import java.util.Scanner;

public class LaunchGameOfLive {
    private static final Scanner in = new Scanner(System.in);
    private static GameOfLife game = new GameOfLife();

    public static void run() {
        System.out.println("--------------Game of life--------------");
        System.out.println();
        System.out.println("----Board----");
        game.printBoard();
        System.out.println();
        boolean RunGame = true;
        while (RunGame == true) {
            game.GameLogicRun();
            System.out.println();
            System.out.print("If you want to end the game enter '-', to continue enter any other character: ");
            String next = in.nextLine();
            if (next.equals("-")) {
                RunGame = false;
            }
        }
    }
}
