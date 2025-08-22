import java.util.Random;

public class MineField {
    private final int rows;
    private final int columns;
    private final MineFieldCell[][] arr;

    MineField(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.arr = new MineFieldCell[rows][columns];
        addMines(mines);
        addNumbersOfMines();
    }

    public MineFieldCell getCell(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            throw new IndexOutOfBoundsException("Invalid coordinates: " + x + " " + y);
        }
        return arr[y - 1][x - 1];
    }

    public boolean isValidCoordinate(int x, int y) {
        return x >= 1 && x <= columns && y >= 1 && y <= rows;
    }

    public void printMineField() {
        char vbar = '|';
        char hbar = '-';
        System.out.print("  " + vbar + " ");
        for (int i = 0; i < columns; i++) {
            System.out.printf("%X ", i + 1);
        }
        System.out.println(vbar);

        System.out.print(hbar + " " + vbar);
        for (int i = 0; i < columns; i++) {
            System.out.print(" " + hbar);
        }
        System.out.println(" " + vbar + " " + hbar);

        for (int i = 0; i < rows; i++) {
            System.out.printf("%X %c ", i + 1, vbar);
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j].getDesignation() + " ");
            }
            System.out.println(vbar);
        }

        System.out.print(hbar + " " + vbar + " ");
        for (int i = 0; i < columns; i++) {
            System.out.print(hbar + " ");
        }
        System.out.println(vbar + " " + hbar);
    }

    public void addMines(int count) {
        Random random = new Random();
        while (count > 0) {
            int randN = random.nextInt(rows);
            int randM = random.nextInt(columns);
            if (arr[randN][randM] == null) {
                arr[randN][randM] = new MineFieldCell(MineFieldCell.MINE);
                count--;
            }
        }
    }

    public void addNumbersOfMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (arr[i][j] == null) {
                    int minesCount = 0;
                    for (int[] dir : MineFieldCell.DIRECTIONS) {
                        minesCount += checkForMine(i + dir[0], j + dir[1]) ? 1 : 0;
                    }
                    arr[i][j] = new MineFieldCell(minesCount);
                }
            }
        }
    }

    private boolean checkForMine(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < columns && this.arr[i][j] != null && this.arr[i][j].getNumber() == MineFieldCell.MINE;
    }
}