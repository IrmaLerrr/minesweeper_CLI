import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("What size field do you want? (x and y coordinates, no more 15) > ");
        MineField game = new MineField(sc.nextInt(), sc.nextInt());
        System.out.print("How many mines do you want on the field? > ");
        int count = sc.nextInt();
        game.addMines(count);
        game.addNumbersOfMines();
        game.printMineField();
    }


}

class MineField {
    private static final char MINE = 'X';
    private static final char SAFE = '.';
    private static final int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private final int rows;
    private final int columns;
    private final char[][] arr;

    MineField(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.arr = new char[rows][columns];
        for (char[] chars : arr) {
            Arrays.fill(chars, SAFE);
        }
    }

    public void printMineField() {
        System.out.print("  | ");
        for (int i = 0; i < columns; i++) {
            System.out.printf("%X ", i+1);
        }
        System.out.println("|");

        System.out.print("— | ");
        for (int i = 0; i < columns; i++) {
            System.out.print("— ");
        }
        System.out.println("| —");

        for (int i = 0; i < rows; i++) {
            System.out.printf("%X | ", i+1);
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.print("— | ");
        for (int i = 0; i < columns; i++) {
            System.out.print("— ");
        }
        System.out.println("| —");
    }

    public void addMines(int count) {
        Random random = new Random();

        while (count > 0) {
            int randN = random.nextInt(rows);
            int randM = random.nextInt(columns);
            if (arr[randN][randM] == SAFE) {
                arr[randN][randM] = MINE;
                count--;
            }
        }
    }

    public void addNumbersOfMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (arr[i][j] == SAFE) {
                    int minesCount = 0;
                    for (int[] dir : directions) {
                        minesCount += checkForMine(i + dir[0], j + dir[1]) ? 1 : 0;
                    }
                    arr[i][j] = minesCount > 0 ? (char) (minesCount + '0') : SAFE;
                }
            }
        }
    }

    private boolean checkForMine(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < columns && this.arr[i][j] == MINE;
    }

}