import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputHandler handler = new InputHandler(new Scanner(System.in)) ;

        int[] arr = handler.getFieldDimensions();
        int x = arr[0];
        int y = arr[1];
        int count = handler.getMinesCount(x, y);

        GameEngine game = new GameEngine();
        game.startGame(x, y, count);
    }
}

