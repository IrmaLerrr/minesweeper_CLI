import java.util.InputMismatchException;
import java.util.Scanner;

public class GameEngine {
    MineField field;
    int correctCheckedMines = 0;
    int incorrectCheckedMines = 0;
    int openedCells = 0;
    int cellsWithoutMines = 0;
    boolean isFail = false;

    public void startGame(int columns, int rows, int mines) {
        field = new MineField(rows, columns, mines);
        cellsWithoutMines = rows * columns - mines;
        field.printMineField();
        while (!isFail
                && openedCells != cellsWithoutMines
                && !(correctCheckedMines == mines && incorrectCheckedMines == 0)) {
            System.out.print("Set/unset mines marks or claim a cell as free: > ");
            Scanner sc = new Scanner(System.in);
            try {
                setCommand(sc.nextInt(), sc.nextInt(), sc.next());
                field.printMineField();
                System.out.println("Marked mines:" + (correctCheckedMines + incorrectCheckedMines) + "/" + mines);
                System.out.println("Opened cells:" + openedCells + "/" + cellsWithoutMines);
            } catch (InputMismatchException ex) {
                System.out.println("Incorrect data entered, please, try again");
            } catch (IndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(isFail ? "You stepped on a mine and failed!" : "Congratulations! You found all the mines!");
    }

    public void markMine(int x, int y) {
        MineFieldCell current = field.getCell(x, y);
        switch (current.getState()) {
            case CLOSED -> {
                current.setState(MineFieldCellState.MARKED);
                if (current.getNumber() == MineFieldCell.MINE) {
                    correctCheckedMines++;
                } else {
                    incorrectCheckedMines++;
                }
            }
            case MARKED -> {
                current.setState(MineFieldCellState.CLOSED);
                if (current.getNumber() == MineFieldCell.MINE) {
                    correctCheckedMines--;
                } else {
                    incorrectCheckedMines--;
                }
            }
            case OPENED -> System.out.println("It is already opened!");
        }
    }

    public void openCell(int x, int y) {
        MineFieldCell current = field.getCell(x, y);
        if (current.getNumber() == MineFieldCell.MINE) {
            isFail = true;
            return;
        }
        if (current.getState() == MineFieldCellState.OPENED) {
            return;
        }
        current.setState(MineFieldCellState.OPENED);
        openedCells++;
        if (current.getNumber() == MineFieldCell.EMPTY) {
            for (int[] dir : MineFieldCell.DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (field.isValidCoordinate(newX, newY)) {
                    openCell(newX, newY);
                }
            }
        }
    }

    public void setCommand(int x, int y, String command) {
        switch (command) {
            case "free" -> openCell(x, y);
            case "mine" -> markMine(x, y);
            default -> throw new InputMismatchException();
        }
    }
}
