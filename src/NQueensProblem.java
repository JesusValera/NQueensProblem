import java.util.Arrays;

public class NQueensProblem {

    private int QUEENS;
    private int[] board;
    private long time_start;
    private int solutions;

    public static void main(String[] args) {
        int QUEENS_BY_DEFAULT = 8;
        int numberOfQueens = (args.length == 0) ? QUEENS_BY_DEFAULT : Integer.parseInt(args[0]);

        new NQueensProblem(numberOfQueens);
    }

    private NQueensProblem(int nQueens) {

        initialize(nQueens);
        loopThrowTheBoard();
        printSolutionsAndTime();
    }

    private void initialize(int nQueens) {
        time_start = System.currentTimeMillis();
        QUEENS = nQueens;
        board = new int[QUEENS];

        for (int i = 0; i < QUEENS; i++) {
            board[i] = (i + 1);
        }
    }

    private void loopThrowTheBoard() {

        int[] lastPosition = lastPosition();

        do {
            generateNewPosition();
        } while (isBoardLastPosition(lastPosition));
    }

    private int[] lastPosition() {
        int[] maxPosition = new int[QUEENS];
        for (int i = 0; i < QUEENS; i++) {
            maxPosition[i] = (QUEENS - i);
        }

        return maxPosition;
    }

    private boolean isBoardLastPosition(int[] lastPosition) {
        return !Arrays.equals(board, lastPosition);
    }

    private void generateNewPosition() {

        nextPermutation(board);

        if (isFollowingNumberTheNextOne()) {
            checkValues();
        }
    }

    private void nextPermutation(int[] array) {

        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }

        if (i <= 0) {
            return;
        }

        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * This method check that there are not two consecutives numbers placed in the array, if they are,
     * that means that they can catch themselves, so that it is not a valid solution.
     * <p>
     * e.g. [ 3, 2, 4, 1]
     * <p>
     *   _1_2_3_4_
     * 1 | | | |o|
     * 2 | |x| | |
     * 3 |x| | | |
     * 4 | | |o| |
     * <p>
     * 'X' values are in the same diagonal.
     *
     * @return boolean
     */
    private boolean isFollowingNumberTheNextOne() {
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

    /**
     * Check if they are repeated numbers in the array.
     * It is calculated adding the value plus the position it is occupying.
     * <p>
     * e.g. [ 3, 2, 4, 1]
     * <p>
     *   _1_2_3_4_
     * 1 | | | |o|
     * 2 | |x| | |
     * 3 |x| | | |
     * 4 | | |o| |
     * <p>
     * 3 + 1 = 4
     * 2 + 2 = 4
     * 4 + 3 = 7
     * 1 + 4 = 5
     *
     * @return the generated array
     */
    private int[] diagonalValuesA() {
        int[] aux = Arrays.copyOf(board, board.length);
        for (int i = 0; i < QUEENS; i++) {
            aux[i] = board[i] + i;
        }
        Arrays.sort(aux);

        return aux;
    }

    /**
     * Check if they are repeated numbers in the array.
     * It is calculated adding the value minus the position it is occupying.
     * <p>
     * e.g. [ 2, 3, 4, 1]
     * <p>
     *   _1_2_3_4_
     * 1 | | | |o|
     * 2 |x| | | |
     * 3 | |x| | |
     * 4 | | |x| |
     * <p>
     * 2 - 1 = 1
     * 3 - 2 = 1
     * 4 - 3 = 1
     * 1 - 4 = -3
     *
     * @return the generated array
     */
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
        System.out.println(Arrays.toString(board));
    }

    private void printSolutionsAndTime() {
        System.out.println("\nThere are " + solutions + " differents solutions.");

        final long time_end = System.currentTimeMillis();
        System.out.println("The task has taken " + (time_end - time_start) + " milliseconds.");
    }
}
