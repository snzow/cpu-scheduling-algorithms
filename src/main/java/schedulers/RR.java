package schedulers;

import java.util.*;

import utilities.PerformanceMetricGenerator;
import utilities.Process;

/**
 * Round Robin CPU Scheduling Algorithm
 * @author Bella Mester
 */
public class RR implements SchedulerInterface {

    /**
     * Processes for scheduler simulation
     */
    private List<Process> processes;
    /**
     * Total time scheduler is active for all processes
     */
    private float totalExecutionTime;
    /**
     * True if processes have been executed, false if otherwise
     */
    private Boolean processesExecuted;

    /**
     * @inheritDoc
     */
    @Override
    public void loadProcesses(List<Process> processes) {
        this.processes = processes;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void executeProcesses(Boolean contextStream) {
        int size = processes.size();
        int[] remainingTime = new int[size];
        int[] waitingTime   = new int[size];

        for (int i = 0; i < size; i++) {
            remainingTime[i] = processes.get(i).getTotalCPUBurstTime();
        }
        int quantum = 5;
        Queue<Process> processQueue = new LinkedList<>(processes);
        int time = 0;
        while (!processQueue.isEmpty()) {
            Process process = processQueue.poll();
            int index = processes.indexOf(process);

            if (remainingTime[index] > 0) {
                if (remainingTime[index] > quantum) {
                    time += quantum;
                    remainingTime[index] -= quantum;
                    processQueue.offer(process);
                } else {
                    time += remainingTime[index];
                    waitingTime[index] = time - process.getTotalCPUBurstTime();
                    remainingTime[index] = 0;
                }
            }
        }

        this.processesExecuted = true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public PerformanceMetricGenerator generatePerformanceMetrics() throws Exception {
        if (!processesExecuted) {
            throw new Exception("Must complete processes before generating metrics");
        }
        return new PerformanceMetricGenerator("Round Robin", processes);
    }
}