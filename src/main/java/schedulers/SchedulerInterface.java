package schedulers;

import java.util.List;

import utilities.PerformanceMetricGenerator;
import utilities.Process;

/**
 * Interface for simulated CPU schedulers
 * @author Nick Trimmer
 */
public interface SchedulerInterface {

    /**
     * Loads all process objects for simulated scheduling
     * @param processes the collection of processes to schedule
     */
    void loadProcesses(List<Process> processes);

    /**
     * Begins the execution of the scheduler
     * @param contextStream true if context stream is to be
     *                      written to a file, false if else
     */
    void executeProcesses(Boolean contextStream) throws Exception;

    /**
     * Generates scheduler performance metrics for processes given
     * Metrics: 1. total time needed to complete all processes
     *          2. CPU utilization
     *          3. waiting time for each process and average waiting time for all
     *          4. turnaround time for each process and average turnaround time for all
     *          5. response time for each process and average response time for all
     * @throws Exception if process execution has not finished, throw
     */
    PerformanceMetricGenerator generatePerformanceMetrics() throws Exception;
}
