import java.util.Arrays;

public class Main {

    private final int TAMANO = 8;
    private int[] board = new int[TAMANO];
    private int availableCombintations = 0;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {

        initialize();

        mostrarValores();
        System.out.println("---");
        check();
        //funcRec(0);
    }

    private void initialize() {
        for (int i = 0; i < TAMANO; i++) {
            board[i] = 1;
        }
    }

    private int funcRec(int pos) {
        if (!areBoardLastPosition(lastPosition())) {
            if (board[(TAMANO - 1) - pos] != TAMANO) {
                board[(TAMANO - 1) - pos] = ++board[(TAMANO - 1) - pos];
                if (pos != 0) {
                    pos = 0;
                }
                mostrarValores();
            } else {
                board[(TAMANO - 1) - pos] = 1;
                pos++;
            }
            funcRec(pos);
        }

        return -1;
    }

    private void check() {

        int[] lastPosition = lastPosition();

        do {
            //checkSingleIteration(board);
            checkInterationMine();

            mostrarValores();
        } while (areBoardLastPosition(lastPosition));
    }

    private void checkInterationMine() {

        for (int i = TAMANO - 1; i >= 0; i--) {

            int currentValue = board[i];

            if (currentValue < TAMANO) {
                board[i] = (currentValue + 1);
                break;
            } else {
                board[i] = 1;
            }

        }

    }

    private void checkSingleIteration(int[] board) {

        for (int i = TAMANO - 1; i > 0; i--) {

            if (board[i] < TAMANO) {

                board[i]++;

            } else {
                board[i] = 1;

                try {
                    board[i - 1]++;
                } catch (Exception ex) {
                    // board[i] = 0;
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    private void mostrarValores() {
        for (int i = 0; i < TAMANO; i++) {
            System.out.print(board[i] + ", ");
        }
        System.out.println();
    }

    private int[] lastPosition() {
        int[] maxPosition = new int[TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            maxPosition[i] = TAMANO;
        }

        return maxPosition;
    }

    private boolean areBoardLastPosition(int[] lastPosition) {
        return !Arrays.equals(board, lastPosition);
    }
}
