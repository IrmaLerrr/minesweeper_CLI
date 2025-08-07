public class MineFieldCell {
    public static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public static final int MINE = 9;
    public static final int EMPTY = 0;

    private MineFieldCellState state;
    private final int number;

    MineFieldCell(int number) {
        this.number = number;
        this.state = MineFieldCellState.CLOSED;
    }

    public int getNumber() {
        return number;
    }

    public char getDesignation() {
        return switch (state) {
            case CLOSED -> '.';
            case MARKED -> '*';
            case OPENED -> switch (number) {
                case 0 -> '/';
                case 1, 2, 3, 4, 5, 6, 7, 8 -> (char) (number + '0');
                default -> '*';
            };
        };
    }

    public MineFieldCellState getState() {
        return state;
    }

    public void setState(MineFieldCellState state) {
        this.state = state;
    }
}
