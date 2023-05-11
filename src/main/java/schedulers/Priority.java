package schedulers;

import java.util.List;

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
        float startTime = System.currentTimeMillis();

        // YOUR ALGORITHM HERE :)

        float endTime = System.currentTimeMillis();
        this.totalExecutionTime = endTime - startTime;
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
        return new PerformanceMetricGenerator("Priority", processes, totalExecutionTime);
    }
}