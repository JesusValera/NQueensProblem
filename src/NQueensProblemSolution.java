
public class NQueensProblemSolution {

    private NQueensProblem problem;

    public NQueensProblemSolution(NQueensProblem problem) {
        this.problem = problem;
    }

    public void printSolutions() {
        System.out.println("\nThere are " + problem.getSolutions() + " differents solutions.");
    }

    public void printTimeInMillis(long time_start) {
        final long time_end = System.currentTimeMillis();
        System.out.println("The task has taken " + (time_end - time_start) + " milliseconds.");
    }

}