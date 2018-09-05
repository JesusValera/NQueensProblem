
public class NQueensProblemSolution {

    private int solutions;
    private long start_time;

    public NQueensProblemSolution(int solutions, long start_time) {
        this.solutions = solutions;
        this.start_time = start_time;
    }

    public void printSolutions() {
        System.out.println("\nThere are " + solutions + " differents solutions.");
    }

    public void printTimeInMillis() {
        final long time_end = System.currentTimeMillis();
        System.out.println("The task has taken " + (time_end - this.start_time) + " milliseconds.");
    }

}