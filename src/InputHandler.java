import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner sc;

    public InputHandler(Scanner scanner) {
        this.sc = scanner;
    }

    public int[] getFieldDimensions() {
        int x = 0;
        int y = 0;

        System.out.print("What size field do you want? (x and y coordinates, no more 15) > ");
        while (true) {
            try {
                x = sc.nextInt();
                y = sc.nextInt();
                if (x < 2 || x > 15 || y < 2 || y > 15) throw new InputMismatchException();
                return new int[]{x, y};
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Incorrect data entered, please, try again > ");
            }
        }
    }
    public int getMinesCount(int x, int y) {
        int count = 0;

        System.out.print("How many mines do you want on the field? > ");
        while (true) {
            try {
                count = sc.nextInt();
                if (count < 1 || count > x * y) throw new InputMismatchException();
                return count;
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Incorrect data entered, please, try again > ");
            }
        }
    }


}
