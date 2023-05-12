package utilities;

import java.util.List;

public class PerformanceMetricGenerator {

    private final String algorithmExecuted;
    private final float waitingTimeAverage;
    private final float turnaroundTimeAverage;
    private final float responseTimeAverage;
    private final float cpuUtilization;

    public PerformanceMetricGenerator(String schedulingAlgoName, List<Process> processes) {
        this.algorithmExecuted = schedulingAlgoName;

        float waitingTimeTotal = 0;
        float turnaroundTimeTotal = 0;
        float responseTimeTotal = 0;
        float totalTimeTotal = 0;
        for (Process process : processes) {
            waitingTimeTotal += process.getWaitingTime();
            turnaroundTimeTotal += process.getTurnaroundTime();
            responseTimeTotal += process.getResponseTime();
            totalTimeTotal += process.getTotalTime();
        }
        this.cpuUtilization = turnaroundTimeTotal / totalTimeTotal;
        this.waitingTimeAverage = waitingTimeTotal / processes.size();
        this.turnaroundTimeAverage = turnaroundTimeTotal / processes.size();
        this.responseTimeAverage = responseTimeTotal / processes.size();
    }

    public String getAlgorithmExecuted() {
        return algorithmExecuted;
    }

    public float getWaitingTimeAverage() {
        return waitingTimeAverage;
    }

    public float getTurnaroundTimeAverage() {
        return turnaroundTimeAverage;
    }

    public float getResponseTimeAverage() {
        return responseTimeAverage;
    }

    public float getCpuUtilization() {
        return cpuUtilization;
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
