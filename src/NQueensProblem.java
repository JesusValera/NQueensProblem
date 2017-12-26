import java.util.Arrays;

public class NQueensProblem {

    private final int QUEENS = 8;
    private final int[] board;
    private final long time_start = System.currentTimeMillis();
    private int solutions;

    public static void main(String[] args) {
        new NQueensProblem();
    }

    private NQueensProblem() {
        board = new int[QUEENS];
        initialize();
        check();
    }

    private void initialize() {
        for (int i = 0; i < QUEENS; i++) {
            board[i] = (i + 1);
        }
    }

    private void check() {

        int[] lastPosition = lastPosition();

        do {
            generateNewPosition();
        } while (areBoardLastPosition(lastPosition));

        System.out.println("There are " + solutions + " differents solutions.");

        final long time_end = System.currentTimeMillis();
        System.out.println("The task has taken " + (time_end - time_start) + " milliseconds.");
    }

    private int[] lastPosition() {
        int[] maxPosition = new int[QUEENS];
        for (int i = 0; i < QUEENS; i++) {
            maxPosition[i] = (QUEENS - i);
        }

        return maxPosition;
    }

    private boolean areBoardLastPosition(int[] lastPosition) {
        return !Arrays.equals(board, lastPosition);
    }

    private void generateNewPosition() {

        for (int i = QUEENS - 1; i >= 0; i--) {

            int currentValue = board[i];

            if (currentValue < QUEENS) {
                board[i] = (currentValue + 1);
                break;
            } else {
                board[i] = 1;
            }

        }

        if (everyNumberIsDiff() && followingNumberIsNext()) {
            checkValues();
        }
    }

    private boolean everyNumberIsDiff() {
        int[] aux = Arrays.copyOf(board, board.length);

        Arrays.sort(aux);
        for (int i = 1; i < aux.length; i++) {
            if (aux[i] == aux[i - 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method check that there are not two consecutives numbers placed in the array, if they are,
     * that means that they can catch themselves, so that it is not a valid solution.
     * <p>
     * e.g. [ 3, 2, 4, 1]
     * <p>
     * _1_2_3_4_
     * | | | |o|
     * | |x| | |
     * |x| | | |
     * | | |o| |
     * <p>
     * 'X' values are in the same diagonal.
     *
     * @return boolean
     */
    private boolean followingNumberIsNext() {
        for (int i = 0; i < QUEENS - 1; i++) {
            if ((board[i] == board[i + 1] + 1) || board[i] == board[i + 1] - 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if there is any other value in the same diagonal. If not, it is a valid solution.
     */
    private void checkValues() {

        final boolean A_DIAGONAL, B_DIAGONAL;

        int[] aux1 = diagonalValuesA();
        int[] aux2 = diagonalValuesB();

        A_DIAGONAL = sameDiagonal(aux1);
        B_DIAGONAL = sameDiagonal(aux2);

        if (!A_DIAGONAL && !B_DIAGONAL) {
            solutions++;
            showQueens();
        }
    }

    private int[] diagonalValuesA() {
        int[] aux = Arrays.copyOf(board, board.length);
        for (int i = 0; i < QUEENS; i++) {
            aux[i] = board[i] + i;
        }
        Arrays.sort(aux);

        return aux;
    }

    private int[] diagonalValuesB() {
        int[] aux = Arrays.copyOf(board, board.length);
        for (int i = 0; i < QUEENS; i++) {
            aux[i] = board[i] - i;
        }
        Arrays.sort(aux);

        return aux;
    }

    private boolean sameDiagonal(int[] auxBoard) {
        for (int i = 1; i < auxBoard.length; i++) {
            if (auxBoard[i] == auxBoard[i - 1]) {
                return true;
            }
        }

        return false;
    }

    private void showQueens() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        for (Integer value : board) {
            builder.append(value).append(", ");
        }

        String res = builder.substring(0, builder.length() - 2) + " ]";
        System.out.println(res);
    }

}
