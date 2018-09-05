
public class NQueens {

    public static void main(String[] args) {
        int QUEENS_BY_DEFAULT = 8;
        int numberOfQueens = (args.length == 0) ? QUEENS_BY_DEFAULT : Integer.parseInt(args[0]);

        NQueensProblem queensProblem = new NQueensProblem(numberOfQueens);

        NQueensProblemSolution solution = queensProblem.getSolution();
        solution.printSolutions();
        solution.printTimeInMillis();
    }
}
