package utilities;

import java.util.List;

//TODO: rework class. do we want these values in an array
// or do we want them in their own variables?
// also: a decision regarding data types for each metric
// needs to be made
public class PerformanceMetricGenerator {

    private final float[] metrics;


    public PerformanceMetricGenerator(String schedulingAlgo, List<Process> processes, float totalExecutionTime) {
        metrics = new float[5];

        int turnaroundTimeTotal = 0;
        for (Process process : processes) {
            turnaroundTimeTotal += process.getTurnaroundTime();
        }



        metrics[0] = totalExecutionTime;
        metrics[1] = turnaroundTimeTotal / totalExecutionTime;

    }

    public float[] getPerformanceMetrics() {
        return metrics;
    }

    /**
     * Provides textual performance metrics for file writing
     * or console output
     * @return formatted performance metrics for each process
     */
    @Override
    //TODO: FINISH METHOD FOR CONSOLE OUTPUT
    public String toString() {
        return "";
    }
}
