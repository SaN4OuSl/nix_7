package org.example.tasks.level3;

public class GameOfLife {
    private int[][] board;

    {
        board = generateRandomBoard();
    }

    public int[][] generateRandomBoard() {
        int m = 5 + (int) (Math.random() * 10);
        int n = 5 + (int) (Math.random() * 10);
        int[][] b = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = 0;
                int buffer = (int) (Math.random() * 3);
                if (buffer == 1)
                    b[i][j] = buffer;
            }
        }
        return b;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void GameLogicRun() {
        int oldLiveCell = 3, newLiveCell = 2, reductionToOne = 2;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = checkNeighbors(board, m, n, i, j);

                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = oldLiveCell;
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = newLiveCell;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] /= reductionToOne;
            }
        }
        System.out.println("Step:");
        printBoard();
    }

    public int checkNeighbors(int[][] board, int m, int n, int i, int j) {
        int excludeNewLiveCell = 2;
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] % excludeNewLiveCell;
            }
        }
        lives -= board[i][j] % excludeNewLiveCell;
        return lives;
    }

}
