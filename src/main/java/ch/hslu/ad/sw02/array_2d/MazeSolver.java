package ch.hslu.ad.sw02.array_2d;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MazeSolver {

    private char[][] maze;
    private boolean isSolved = false;
    private int solutionsAmount = 0;

    public MazeSolver(char[][] maze) {
        this.maze = maze;
    }

    public int getSolutionsAmount() {
        return this.solutionsAmount;
    }

    public void findPath(int row, int col) {
        if ((row < 0) || (row > maze.length - 1) || (col < 0) || (col > maze[0].length - 1)) {
            return;
        }
        char current = maze[row][col];

        if (current == 'X') {
            if (!this.isSolved) {
                this.isSolved = true;
                this.printMaze();
            }
            this.solutionsAmount++;
            return;
        }

        if (current == ' ') {
            maze[row][col] = '•';
            this.findPath(row - 1, col); // north
            this.findPath(row, col - 1); // east
            this.findPath(row + 1, col); // south
            this.findPath(row, col + 1); // east
            maze[row][col] = ' ';
        }
    }

    private void printMaze() {
        System.out.println(" ↓ " + "___".repeat(maze[0].length - 1) + "_");
        for (char[] row : this.maze) {
            System.out.print("|");
            for (char col : row) {
                System.out.print(" " + col + " ");
            }
            System.out.print("|\n");
        }

        System.out.println(" " + "\u0305\u0305\u0305".repeat(maze[0].length));
    }

    static void main(String[] args) throws IOException {
        // 4 possible ways
        char[][] mazeSmallExample = {
            {' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#'},
            {' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
            {' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#'},
            {' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#'},
            {'#', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' '},
            {' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', '#', '#', '#', '#', '#', '#', ' ', '#', '#'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}
        };

        String rawMazeLargeExample =
                Files.readString(Path.of("src/main/java/ch/hslu/ad/sw02/array_2d/largeMaze.txt"));
        ;
        String[] lines = rawMazeLargeExample.split("\\R");
        int rows = lines.length;
        int cols = lines[0].length();

        char[][] mazeLargeExample = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            mazeLargeExample[i] = lines[i].toCharArray();
        }

        System.out.println("Small maze");
        System.out.println("------------------------");
        MazeSolver solverSmall = new MazeSolver(mazeSmallExample);
        solverSmall.findPath(0, 0);
        System.out.println("Possible solutions: " + solverSmall.getSolutionsAmount() + "\n");

        System.out.println("Large maze");
        System.out.println("------------------------");
        MazeSolver solverLarge = new MazeSolver(mazeLargeExample);
        solverLarge.findPath(0, 0);
        System.out.println("Possible solutions: " + solverLarge.getSolutionsAmount());
    }
}
