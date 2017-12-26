import java.util.Arrays;

public class NQueensProblem {

    private final int TAMANO = 8;
    private int[] board = new int[TAMANO];
    private int solutions;

    public static void main(String[] args) {
        new NQueensProblem();
    }

    private NQueensProblem() {
        initialize();
        check();
    }

    private void initialize() {
        for (int i = 0; i < TAMANO; i++) {
            board[i] = (i + 1);
        }
    }

    private void check() {

        int[] lastPosition = lastPosition();

        do {
            generateNewPosition();
        } while (areBoardLastPosition(lastPosition));

        System.out.println("There are " + solutions + " differents solutions.");
    }

    private int[] lastPosition() {
        int[] maxPosition = new int[TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            maxPosition[i] = (TAMANO - i);
        }

        return maxPosition;
    }

    private boolean areBoardLastPosition(int[] lastPosition) {
        return !Arrays.equals(board, lastPosition);
    }

    private void generateNewPosition() {

        for (int i = TAMANO - 1; i >= 0; i--) {

            int currentValue = board[i];

            if (currentValue < TAMANO) {
                board[i] = (currentValue + 1);
                break;
            } else {
                board[i] = 1;
            }

        }

        if (everyNumberIsDif()) {
            checkValues();
        }
    }

    private boolean everyNumberIsDif() {
        int[] aux = Arrays.copyOf(board, board.length);

        Arrays.sort(aux);
        for (int i = 1; i < aux.length; i++) {
            if (aux[i] == aux[i - 1]) {
                return false;
            }
        }

        return true;
    }


    private void checkValues() {

        int[] aux1 = Arrays.copyOf(board, board.length);

        for (int i = 0; i < TAMANO; i++) {
            aux1[i] = board[i] + i;
        }

        Arrays.sort(aux1);
        boolean estaY = false;
        for (int i = 1; i < aux1.length; i++) {
            if (aux1[i] == aux1[i - 1]) {
                estaY = true;
                break;
            }
        }

        int[] aux2 = Arrays.copyOf(board, board.length);

        for (int i = 0; i < TAMANO; i++) {
            aux2[i] = board[i] - i;
        }

        Arrays.sort(aux2);
        boolean estaX = false;
        for (int i = 1; i < aux2.length; i++) {
            if (aux2[i] == aux2[i - 1]) {
                estaX = true;
                break;
            }
        }

        if (!estaX && !estaY) {
            solutions++;
            mostrarValores();
        }

    }

    private void mostrarValores() {
        String res = "[ ";
        for (int i = 0; i < TAMANO; i++) {
            res += (board[i] + ", ");
        }

        res = res.substring(0, res.length()-2) + " ]";
        System.out.println(res);
    }

}
