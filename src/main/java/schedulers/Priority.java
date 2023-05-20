package schedulers;

import java.util.List;

import utilities.CpuInterface;
import utilities.PerformanceMetricGenerator;
import utilities.Process;

/**
 * Priority CPU Scheduling Algorithm
 * @author Bella Mester
 */
public class Priority implements SchedulerInterface {

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

    private CpuInterface cpu;

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

        processes.sort((p1, p2) -> {
            if (p1.getPriority() < p2.getPriority()) {
                return -1;
            } else if (p1.getPriority() > p2.getPriority()) {
                return 1;
            } else {
                try {
                    return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return 0;
        });

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
        return new PerformanceMetricGenerator("Priority", processes,cpu);
    }
}