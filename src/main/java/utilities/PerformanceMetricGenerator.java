package utilities;

import java.util.List;

public class PerformanceMetricGenerator {

    private final String algorithmExecuted;
    private final List<Process> processes;
    private final float waitingTimeAverage;
    private final float turnaroundTimeAverage;
    private final float responseTimeAverage;
    private final float cpuUtilization;
    private int totalTimeTotal;

    public PerformanceMetricGenerator(String schedulingAlgoName, List<Process> processes) {
        this.algorithmExecuted = schedulingAlgoName;
        this.processes = processes;

        float waitingTimeTotal = 0;
        float turnaroundTimeTotal = 0;
        float responseTimeTotal = 0;
        this.totalTimeTotal = 0;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int columnWidth1 = 20;
        int columnWidth2 = 6;

        sb.append(String.format("%-" + columnWidth1 + "s", "Total Time:"));
        sb.append(totalTimeTotal);
        sb.append("\n");

        sb.append(String.format("%-" + columnWidth1 + "s", "CPU Utilization:"));
        sb.append(cpuUtilization);
        sb.append("\n");
        sb.append("\n");

        sb.append(String.format("%-" + columnWidth1 + "s", "Waiting Times:"));
        for (Process process : processes) {
            sb.append(String.format("%-" + columnWidth2 + "s", process.getProcessName()));
        }
        sb.append("\n");
        sb.append(String.format("%-" + columnWidth1 + "s", ""));
        for (Process process : processes) {
            sb.append(String.format("%-" + columnWidth2 + "s", process.getWaitingTime()));
        }
        sb.append("\n");
        sb.append(String.format("%-" + columnWidth1 + "s", "Average Wait:"));
        sb.append(waitingTimeAverage);
        sb.append("\n");
        sb.append("\n");

        sb.append(String.format("%-" + columnWidth1 + "s", "Turnaround Times:"));
        for (Process process : processes) {
            sb.append(String.format("%-" + columnWidth2 + "s", process.getProcessName()));
        }
        sb.append("\n");
        sb.append(String.format("%-" + columnWidth1 + "s", ""));
        for (Process process : processes) {
            sb.append(String.format("%-" + columnWidth2 + "s", process.getTurnaroundTime()));
        }
        sb.append("\n");
        sb.append(String.format("%-" + columnWidth1 + "s", "Average Turnaround:"));
        sb.append(turnaroundTimeAverage);
        sb.append("\n");
        sb.append("\n");

        sb.append(String.format("%-" + columnWidth1 + "s", "Response Times:"));
        for (Process process : processes) {
            sb.append(String.format("%-" + columnWidth2 + "s", process.getProcessName()));
        }
        sb.append("\n");
        sb.append(String.format("%-" + columnWidth1 + "s", ""));
        for (Process process : processes) {
            sb.append(String.format("%-" + columnWidth2 + "s", process.getResponseTime()));
        }
        sb.append("\n");
        sb.append(String.format("%-" + columnWidth1 + "s", "Average Response:"));
        sb.append(responseTimeAverage);
        sb.append("\n");
        sb.append("\n");

        return sb.toString();
    }
}